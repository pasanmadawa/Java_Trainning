package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SignIn extends JFrame implements ActionListener {
    JLabel name_l, gmail_l, empId_l, depId_l, logIn, password_l;
    JTextField name_t, gmail_t, empId_t, depId_t;
    JPasswordField passwordField;
    JButton submitButton, mainMenuButton;

    public SignIn(){
        super("Sign In");

        name_l = new JLabel("Enter the name: ");
        name_t = new JTextField(16);

        gmail_l = new JLabel("Enter the Gmail: ");
        gmail_t = new JTextField(16);

        empId_l = new JLabel("Enter the Employee Id");
        empId_t = new JTextField(16);

        depId_l = new JLabel("Enter the Department Id");
        depId_t = new JTextField(16);

        password_l = new JLabel("Enter the password");
        passwordField = new JPasswordField(16);


        logIn = new JLabel("Have an account");
        logIn.setFont(new Font("Arial",Font.BOLD, 12));
        logIn.setForeground(Color.BLUE);

        submitButton = new JButton("Submit");
        mainMenuButton = new JButton("Main Menu");

        setLayout(new FlowLayout(FlowLayout.LEFT,150,10));

        submitButton.addActionListener(this);

        logIn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new LogIn();
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

        add(name_l);
        add(name_t);
        add(gmail_l);
        add(gmail_t);
        add(empId_l);
        add(empId_t);
        add(depId_l);
        add(depId_t);
        add(password_l);
        add(passwordField);
        add(logIn);
        add(submitButton);
        add(mainMenuButton);

        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {


            if (e.getSource() == submitButton) {

                String empName = name_t.getText().trim();
                String empGmail = gmail_t.getText().trim();
                String empId = empId_t.getText().trim();
                String depId = depId_t.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                if (empName.isEmpty() || empGmail.isEmpty() || empId.isEmpty() || depId.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }


                String jsonPayload = String.format(
                        "{\"empId\":\"%s\",\"depId\":\"%s\",\"empName\":\"%s\",\"empGmail\":\"%s\",\"password\":\"%s\"}",
                        empId, depId, empName, empGmail, password
                );

                try {

                    URL url = new URL("http://localhost:8080/api/post/signIn");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setDoOutput(true);


                    try (OutputStream os = connection.getOutputStream()) {
                        os.write(jsonPayload.getBytes());
                        os.flush();
                    }


                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        try (InputStream is = connection.getInputStream();
                             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
                            StringBuilder response = new StringBuilder();
                            String line;
                            while ((line = reader.readLine()) != null) {
                                response.append(line);
                            }
                            JOptionPane.showMessageDialog(this, "Sign-in successful!\n", "Success", JOptionPane.INFORMATION_MESSAGE);
                            new LogIn();
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Sign-in failed! HTTP Error: " + responseCode, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }


    }

    public static void main(String[] args) {
        new SignIn();
    }
}
