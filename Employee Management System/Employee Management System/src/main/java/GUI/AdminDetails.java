package GUI;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class AdminDetails extends JFrame {

    public AdminDetails(){
        super("Admin Log");

        List<AdminObject> admins = fetchData();


        if (admins != null) {

            String[] columnNames = {"Admin ID", "Department ID", "Admin Name", "Admin Email"};

            Object[][] tableData = new Object[admins.size()][4];

            for (int i = 0; i < admins.size(); i++){
                AdminObject admin = admins.get(i);
                tableData[i][0] = admin.getAdminId();
                tableData[i][1] = admin.getDepId();
                tableData[i][2] = admin.getAdminName();
                tableData[i][3] = admin.getAdminGmail();
            }

            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BorderLayout());

            DefaultTableModel tableModel = new DefaultTableModel(tableData,columnNames);
            JTable adminTable = new JTable(tableModel);

            JScrollPane table = new JScrollPane(adminTable);
            table.setPreferredSize(new Dimension(800,200));

            JButton backButton = new JButton("Main Menu");

            mainPanel.add(table,BorderLayout.CENTER);

            //side panel
            JPanel sidePanel = new JPanel(new GridBagLayout());
            sidePanel.setPreferredSize(new Dimension(200, 720));
            sidePanel.setBackground(Color.GRAY);
            sidePanel.setLayout(new BoxLayout(sidePanel,BoxLayout.Y_AXIS));

//            JButton backButton = new JButton("Back");
//            backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//            sidePanel.add(backButton);
//            sidePanel.add(Box.createRigidArea(new Dimension(0,30)));

            JLabel homeLabel = new JLabel("Home");
            homeLabel.setFont(new Font("Arial",Font.BOLD, 30));
            homeLabel.setForeground(Color.BLACK);
            homeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            sidePanel.add(homeLabel);
            sidePanel.add(Box.createVerticalStrut(30));
            mainPanel.add(sidePanel, BorderLayout.WEST);

            add(mainPanel);

            homeLabel.addMouseListener(new MouseAdapter() {

                public void mouseEntered(MouseEvent e){
                    homeLabel.setForeground(Color.BLUE);
                    homeLabel.setFont(new Font("Arial",Font.BOLD,34));
                    homeLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }


                public void mouseExited(MouseEvent e){
                    homeLabel.setForeground(Color.BLACK);
                    homeLabel.setFont(new Font("Arial",Font.BOLD, 30));
                }
                @Override
                public void mouseClicked(MouseEvent e) {
                    new AdminHome();
                    dispose();
                }
            });


        } else {
            JLabel label = new JLabel("Failed to fetch admin data.");
            label.setHorizontalAlignment(SwingConstants.CENTER);
            add(label);
        }

        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new AdminDetails();
    }

    private static List<AdminObject> fetchData(){
        try {
            URL url = new URL("http://localhost:8080/api/adminDetails");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json ");

            if(connection.getResponseCode() == 200){
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null){
                    builder.append(line);
                }
                reader.close();

                ObjectMapper mapper = new ObjectMapper();
                String jsonResponse = builder.toString();

                JsonNode rootNode = mapper.readTree(jsonResponse);
                JsonNode adminNode = rootNode.get("admins");

                return mapper.readValue(adminNode.toString(), new TypeReference<List<AdminObject>>() {});


            }
            else {
                System.out.println("Failed to fetch data "+connection.getResponseCode());
            }
            connection.disconnect();


        }
        catch (Exception e){
            e.printStackTrace();

        }
        return null;
    }
}

