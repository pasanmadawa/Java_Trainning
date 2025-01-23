package GUI;

import org.json.JSONObject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LogIn extends JFrame implements ActionListener {

        JLabel userId_l,userPassword_l, forget_password, signIn;
        JTextField userId_t;
        JButton submitButton,mainMenuButton;
        JPasswordField  userPassword_t;

    public LogIn(){
            super("User LogIn");

            userId_l = new JLabel("UserId");
            userPassword_l = new JLabel("Password");
            forget_password = new JLabel("Forget Password");
            signIn = new JLabel("Have not an account");
            signIn.setBounds(50, 400, 200, 100);
            signIn.setFont(new Font("Arial",Font.BOLD, 12));
            signIn.setForeground(Color.BLUE);

            userId_t = new JTextField(16);
            userPassword_t = new JPasswordField(16);

            submitButton = new JButton("submit");
            mainMenuButton = new JButton("Main Menu");
            //b2.setBounds(100, 100,10,10);

            submitButton.addActionListener(this);

            add(userId_l);
            add(userId_t);
            add(userPassword_l);
            add(userPassword_t);
            add(submitButton);
            add(mainMenuButton);
            add(forget_password);
            add(signIn);


        signIn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new SignIn();
                dispose();

            }
        });

        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainGUI();
                dispose();
            }
        });


        setLayout(new FlowLayout(FlowLayout.LEFT,150,20));

        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }


    public static void main(String[] args) {
        new LogIn();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String userId = userId_t.getText().trim();
            String password = new String(userPassword_t.getPassword()).trim();

            if (userId.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fields cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String url = "http://localhost:8080/api/post/logIn";
            String jsonPayload = String.format("{\"empId\":\"%s\", \"password\":\"%s\"}", userId, password);

            try {
                JSONObject response = sendPostRequest(url, jsonPayload);
                if (response != null) {

                    String jwtToken = response.getString("token");
                    JSONObject userDetails = response.getJSONObject("userDetails");

//                    String description = userDetails.optString("description", "No description available");
//                    String review = userDetails.optString("review", "No review available");
//
//
//                    System.out.println("Description: " + description);
//                    System.out.println("Review: " + review);


                    UserSession.setJwtToken(jwtToken);


                    new UserGUI(jwtToken, userDetails);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Error: Invalid response", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }


    }

    private JSONObject sendPostRequest(String url, String jsonPayload) throws Exception {
        URL backendUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) backendUrl.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);


        try (OutputStream os = connection.getOutputStream()) {
            os.write(jsonPayload.getBytes());
            os.flush();
        }


        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                return new JSONObject(response.toString());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error: Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }



}
