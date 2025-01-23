package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class UserUpdate extends JFrame{

    public UserUpdate(String jwtToken, JSONObject userDetails,Runnable refreshCallback){
        super("Update Menu");

        setLayout(new GridBagLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.GRAY);
        mainPanel.setPreferredSize(new Dimension(1000,900));
        mainPanel.setMaximumSize(new Dimension(1000,900));

        //label
        JLabel label = new JLabel("Edit Panel");
        label.setFont(new Font("Arial",Font.BOLD,60));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setForeground(Color.BLUE);


        //create labels :)
        JLabel empIdL = customLabel("Employee ID");
        JLabel depIdL = customLabel("Department ID");
        JLabel empNameL = customLabel("Employee Name");
        JLabel empGmailL = customLabel("Employee Gmail");
        JLabel descriptionL = customLabel("Description");

        //save panel
        JPanel savePanel = new JPanel(new GridBagLayout());
        savePanel.setPreferredSize(new Dimension(300,60));
        savePanel.setMaximumSize(new Dimension(300,60));
        savePanel.setBackground(Color.lightGray);
        savePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //save label
        JLabel saveLabel = new JLabel("Save Changes");
        saveLabel.setFont(new Font("Arial",Font.BOLD,24));

        //back panel
        JPanel backPanel = new JPanel(new GridBagLayout());
        backPanel.setPreferredSize(new Dimension(200,60));
        backPanel.setMaximumSize(new Dimension(200,60));
        backPanel.setBackground(Color.lightGray);
        backPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //back label
        JLabel backLabel = new JLabel("Back");
        backLabel.setFont(new Font("Arial",Font.BOLD,24));

        savePanel.add(saveLabel);
        backPanel.add(backLabel);

        JTextField empIdT = customJTextfield(userDetails.getString("empId"));
        JTextField depIdT = customJTextfield(userDetails.getString("depId"));
        JTextField empNameT = customJTextfield(userDetails.getString("empName"));
        JTextField empGmailT = customJTextfield(userDetails.getString("empGmail"));
        JTextField descriptionT = customJTextfield(userDetails.optString("description"));

        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(label);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(empIdL);
        mainPanel.add(empIdT);
        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(depIdL);
        mainPanel.add(depIdT);
        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(empNameL);
        mainPanel.add(empNameT);
        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(empGmailL);
        mainPanel.add(empGmailT);
        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(descriptionL);
        mainPanel.add(descriptionT);
        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(savePanel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(backPanel);

        add(mainPanel);

        savePanel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                sendUpdate(empIdT.getText().trim(),depIdT.getText().trim(),empNameT.getText().trim(),empGmailT.getText().trim(),descriptionT.getText().trim());
            }
        });


        backPanel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                new UserGUI(jwtToken,userDetails);
                refreshCallback.run();
                dispose();

            }
        });

        setSize(1920,1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }



    private JLabel customLabel(String name){
        JLabel label = new JLabel(name);
        label.setFont(new Font("Arial",Font.BOLD,20));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    private JTextField customJTextfield(String name){
        JTextField input = new JTextField(name);
        input.setPreferredSize(new Dimension(500,60));
        input.setMaximumSize(new Dimension(500,60));
        input.setAlignmentX(Component.CENTER_ALIGNMENT);
        return input;
    }

    private void sendUpdate(String empId,String depId,String empName, String empGmail,String description){
        try{
            URL url = new URL("http://localhost:8080/api/put/"+ empId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String jsonPayload = String.format(
                    "{\"empId\": \"%s\", \"depId\": \"%s\", \"empName\": \"%s\", \"empGmail\": \"%s\", \"description\": \"%s\"}",
                    empId, depId, empName, empGmail, description
            );

//            JSONObject jsonPayload = new JSONObject();
//            jsonPayload.put("empId", empId);
//            jsonPayload.put("depId", depId);
//            jsonPayload.put("empName", empName);
//            jsonPayload.put("empGmail", empGmail);
//            jsonPayload.put("description", description);
//
//            String jsonString = jsonPayload.toString();
//            System.out.println(jsonString);

            OutputStream os = connection.getOutputStream();
            os.write(jsonPayload.getBytes());
            os.flush();
            os.close();

            int responseCode = connection.getResponseCode();
            JOptionPane.showMessageDialog(this, "Successfully Updated", "Success", JOptionPane.INFORMATION_MESSAGE);
            if (responseCode == 200) {
                System.out.println("Successfully updated admin review for Employee ID: " + empId);
            } else {
                System.err.println("Failed to update admin review. HTTP Code: " + responseCode);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
