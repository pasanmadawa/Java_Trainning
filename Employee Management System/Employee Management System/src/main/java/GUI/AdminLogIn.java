package GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.*;

public class AdminLogIn extends JFrame implements ActionListener {

    JTextField adminId_t;
    JPasswordField password_t;
    JButton submit;
    JButton backButton_b;

    public AdminLogIn(){

        super("Admin LogIn");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        JPanel adminId_p = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel adminId_l = new JLabel("Admin Id");
        JLabel password_l = new JLabel("Password");

        JPanel password_p = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

        adminId_t = new JTextField(16);
        password_t = new JPasswordField(16);

        JPanel button_p = new JPanel(new FlowLayout(FlowLayout.CENTER,150,10));
        submit = new JButton("Submit");

        backButton_b = new JButton("back");

        adminId_p.add(adminId_l);
        adminId_p.add(adminId_t);


        password_p.add(password_l);
        password_p.add(password_t);

        button_p.add(backButton_b);
        button_p.add(submit);


        mainPanel.add(adminId_p);
        mainPanel.add(password_p);
        mainPanel.add(Box.createHorizontalStrut(5));
        mainPanel.add(button_p);

        add(mainPanel);

        submit.addActionListener(this);
        backButton_b.addActionListener(this);

        setSize(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new AdminLogIn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == submit){
            String adminId = adminId_t.getText().trim();
            String password = new String(password_t.getPassword()).trim();

            if (adminId.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fields cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Backend URL
                String url = "http://localhost:8080/api/post/adminLogIn";


                String jsonPayload = String.format("{\"adminId\":\"%s\", \"password\":\"%s\"}", adminId, password);

                try {

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
                            JOptionPane.showMessageDialog(this, response.toString(), "Success", JOptionPane.INFORMATION_MESSAGE);
                            new AdminHome();
                            dispose();
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Error: Check the inputs ", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if (e.getSource() == backButton_b){
            new MainGUI();
        }


    }


}
