package GUI;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

public class AdminMenu extends JFrame{

    public AdminMenu(){
        super("Employee Log");

        List<EmpDetails> employees = fetchData();


        if (employees != null) {

            String[] columnNames = {"Employee ID", "Department ID", "Employee Name", "Employee Email","Description","Admin Review"};
            Object[][] tableData = new Object[employees.size()][6];
            for(int i = 0; i < employees.size(); i++){
                EmpDetails employee = employees.get(i);
                tableData[i][0] = employee.getEmpId();
                tableData[i][1] = employee.getDepId();
                tableData[i][2] = employee.getEmpName();
                tableData[i][3] = employee.getEmpGmail();
                tableData[i][4] = employee.getDescription();
                tableData[i][5] = employee.getReview();
            }

            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BorderLayout());

//            JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER,0,80));
//            panel1.setPreferredSize(new Dimension(800,600));
//
//            JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER,0,80));
//            panel1.setPreferredSize(new Dimension(800,60));
//
//            JPanel panel3 = new JPanel(new BorderLayout());
//            panel3.add(panel3, BorderLayout.WEST);
//            panel3.setBackground(Color.GRAY);

            DefaultTableModel tableModel = new DefaultTableModel(tableData,columnNames);
            JTable employeeTable = new JTable(tableModel);

            JScrollPane table = new JScrollPane(employeeTable);
            table.setPreferredSize(new Dimension(800,200));
//            mainPanel.add(panel3);
//            panel1.add(table);
//            mainPanel.add(panel1);
//            mainPanel.add(panel2);
//
//            add(mainPanel);



            mainPanel.add(table,BorderLayout.CENTER);

//            //horizontal panel
//            JPanel panel1 = new JPanel();
//            panel1.setPreferredSize(new Dimension(200,400));
//            panel1.setBackground(Color.YELLOW);
//            mainPanel.add(panel1,BorderLayout.SOUTH);

            //side panel
            JPanel sidePanel = new JPanel();
            sidePanel.setPreferredSize(new Dimension(200, 720));
            sidePanel.setBackground(Color.GRAY);
            sidePanel.setLayout(new BoxLayout(sidePanel,BoxLayout.Y_AXIS));

            JLabel homeLabel = new JLabel("Home");
            homeLabel.setFont(new Font("Arial",Font.BOLD, 30));
            homeLabel.setForeground(Color.BLACK);
            homeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            sidePanel.add(homeLabel);
            mainPanel.add(sidePanel, BorderLayout.WEST);

            employeeTable.getDefaultEditor(Object.class).addCellEditorListener(new CellEditorListener() {
                @Override
                public void editingStopped(ChangeEvent e) {
                    int selectedRow = employeeTable.getSelectedRow();
                    int selectedColumn = employeeTable.getSelectedColumn();


                    if (selectedColumn == employeeTable.getColumnCount() - 1) {
                        String empId = employeeTable.getValueAt(selectedRow, 0).toString(); // Employee ID
                        String adminReview = employeeTable.getValueAt(selectedRow, selectedColumn).toString(); // Updated Review
                        System.out.println(empId);

                        sendUpdateToBackend(empId, adminReview);
                    }
                }

                @Override
                public void editingCanceled(ChangeEvent e) {

                }


            });

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

            add(mainPanel);

        } else {
            JLabel label = new JLabel("Failed to fetch employee data.");
            label.setHorizontalAlignment(SwingConstants.CENTER);
            add(label);
        }

        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


    }

    public static void main(String[] args) {

        new AdminMenu();
    }

    private  void sendUpdateToBackend(String empId, String adminReview) {
        try {
            URL url = new URL("http://localhost:8080/api/put/adminReview/"+ empId); // Update with the correct backend endpoint
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String jsonPayload = String.format("{\"adminReview\": \"%s\"}", adminReview);

            OutputStream os = connection.getOutputStream();
            os.write(jsonPayload.getBytes());
            os.flush();
            os.close();

            int responseCode = connection.getResponseCode();
            JOptionPane.showMessageDialog(this, "Need to add admin review?", "Success", JOptionPane.INFORMATION_MESSAGE);
            if (responseCode == 200) {
                System.out.println("Successfully updated admin review for Employee ID: " + empId);

            } else {
                System.err.println("Failed to update admin review. HTTP Code: " + responseCode);
            }

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static List<EmpDetails> fetchData(){
        try {
            URL url = new URL("http://localhost:8080/api/get/getDetails");
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
                JsonNode employeesNode = rootNode.get("employees");

                return mapper.readValue(employeesNode.toString(), new TypeReference<List<EmpDetails>>() {});

//                for(EmpDetails employee : employees){
//                    System.out.println("Employee Id: "+employee.getEmpId());
//                    System.out.println("Department Id: " + employee.getDepId());
//                    System.out.println("Employee Name: "+ employee.getEmpName());
//                    System.out.println("Employee Gmail; "+ employee.getEmpGmail());
//                    System.out.println("Description: "+ employee.getDescription());
//                }

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


