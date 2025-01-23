//package GUI;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//
//public class ExampleGUI extends JFrame {
//    JLabel updateLabel;
//    JLabel logOutLabel;
//
//    public ExampleGUI(){
//        super("User Details");
//
//        JPanel mainPanel = new JPanel();
//
//        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
//
//        JPanel panel = customPanel(1000, 200);
//        JPanel empIdPanel = customPanel(1000,60);
//        JPanel depIdPanel = customPanel(1000,60);
//        JPanel empNamePanel = customPanel(1000,60);
//        JPanel empGmailPanel = customPanel(1000,60);
//        JPanel descriptionPanel = customPanel(1000,100);
//        JPanel reviewPanel = customPanel(1000,100);
//
//        JLabel label = customJlabel("User Details",40);
//        JLabel empIdLabel = customJlabel("Employee Id: ",22);
//        JLabel depIdLabel = customJlabel("Department Id:",22);
//        JLabel empNameLabel = customJlabel("Employee Name: ",22);
//        JLabel empGmailLabel = customJlabel( "Employee Gmail: ",22);
//        JLabel descriptionLabel = customJlabel("Description: ",22);
//        JLabel reviewLabel = customJlabel("Admin Review: ",22);
//
////        JTextField field = new JTextField("Name");
////        field.setPreferredSize(new Dimension(30,30));
////        field.setMaximumSize(new Dimension(30,30));
////        empIdPanel.add(field);
//
//        //leftSide panel
//        JPanel leftSidePanel = new JPanel();
//        leftSidePanel.setPreferredSize(new Dimension(250, 1080));
//        leftSidePanel.setBackground(Color.GRAY);
//        leftSidePanel.setLayout(new BoxLayout(leftSidePanel,BoxLayout.Y_AXIS));
//
//        //rightSide panel
//        JPanel rightSidePanel = new JPanel();
//        rightSidePanel.setPreferredSize(new Dimension(350, 1080));
//        rightSidePanel.setBackground(Color.GRAY);
//        rightSidePanel.setLayout(new BoxLayout(rightSidePanel,BoxLayout.Y_AXIS));
//
//        JPanel updatePanel = new JPanel(new GridBagLayout());
//        updatePanel.setPreferredSize(new Dimension(250,60));
//        updatePanel.setMaximumSize(new Dimension(250,60));
//        updatePanel.setBackground(Color.lightGray);
//        updatePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        JPanel logOutPanel = new JPanel(new GridBagLayout());
//        logOutPanel.setPreferredSize(new Dimension(250,60));
//        logOutPanel.setMaximumSize(new Dimension(250,60));
//        logOutPanel.setBackground(Color.lightGray);
//        logOutPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        updateLabel = customJlabel("Edit Details",30);
//        logOutLabel = customJlabel("Log Out",30);
//
//        updatePanel.add(updateLabel);
//        logOutPanel.add(logOutLabel);
//
//        panel.add(label);
//        empIdPanel.add(empIdLabel);
//        depIdPanel.add(depIdLabel);
//        empNamePanel.add(empNameLabel);
//        empGmailPanel.add(empGmailLabel);
//        descriptionPanel.add(descriptionLabel);
//        reviewPanel.add(reviewLabel);
//
//
//        mainPanel.add(Box.createVerticalStrut(20));
//        mainPanel.add(panel);
//        mainPanel.add(Box.createVerticalStrut(30));
//        mainPanel.add(empIdPanel);
//        mainPanel.add(Box.createVerticalStrut(30));
//        mainPanel.add(depIdPanel);
//        mainPanel.add(Box.createVerticalStrut(30));
//        mainPanel.add(empNamePanel);
//        mainPanel.add(Box.createVerticalStrut(30));
//        mainPanel.add(empGmailPanel);
//        mainPanel.add(Box.createVerticalStrut(30));
//        mainPanel.add(descriptionPanel);
//        mainPanel.add(Box.createVerticalStrut(30));
//        mainPanel.add(reviewPanel);
//
//        rightSidePanel.add(Box.createVerticalStrut(40));
//        rightSidePanel.add(updatePanel);
//        rightSidePanel.add(Box.createVerticalStrut(40));
//        rightSidePanel.add(logOutPanel);
//
//        add(rightSidePanel,BorderLayout.EAST);
//        //add(leftSidePanel,BorderLayout.WEST);
//        add(mainPanel,BorderLayout.CENTER);
//
//        updateLabel.addMouseListener(new MouseAdapter() {
//
//            public void mouseEntered(MouseEvent e){
//                updateLabel.setForeground(Color.BLUE);
//                updateLabel.setFont(new Font("Arial",Font.BOLD,34));
//                updateLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
//            }
//
//
//            public void mouseExited(MouseEvent e){
//                updateLabel.setForeground(Color.BLACK);
//                updateLabel.setFont(new Font("Arial",Font.BOLD, 30));
//            }
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                new UserUpdate();
//            }
//        });
//
//        updatePanel.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e){
//                new UserUpdate();
//            }
//        });
//
//
//        logOutLabel.addMouseListener(new MouseAdapter() {
//
//            public void mouseEntered(MouseEvent e){
//                logOutLabel.setForeground(Color.BLUE);
//                logOutLabel.setFont(new Font("Arial",Font.BOLD,34));
//                logOutLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
//            }
//
//
//            public void mouseExited(MouseEvent e){
//                logOutLabel.setForeground(Color.BLACK);
//                logOutLabel.setFont(new Font("Arial",Font.BOLD, 30));
//            }
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                new MainGUI();
//                dispose();
//            }
//        });
//
//        logOutPanel.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e){
//                new MainGUI();
//                dispose();
//            }
//        });
//
//        setSize(1920,1080);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setVisible(true);
//    }
//
//    private JPanel customPanel(int width, int height) {
//        JPanel panel = new JPanel(new GridBagLayout());
//        panel.setPreferredSize(new Dimension(width, height));
//        panel.setMaximumSize(new Dimension(width, height));
//        panel.setBackground(Color.GRAY);
//        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        return panel;
//    }
//
//    private JLabel customJlabel(String name, int size){
//        JLabel label = new JLabel(name);
//        label.setFont(new Font("Arial",Font.BOLD,size));
//        return label;
//    }
//
//    public static void main(String[] args) {
//        new ExampleGUI();
//    }
//
//
//
//}
//
