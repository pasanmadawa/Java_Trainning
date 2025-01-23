package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminHome extends JFrame{

    public AdminHome(){
        super("Home");

//        JPanel mainPanel = new JPanel();
//        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
//
//        JPanel hPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10,100));
//
//
//        JPanel hPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER,150,100));
//
//
//        JPanel vPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER,0,80));
//        vPanel1.setPreferredSize(new Dimension(400,200));
//        JLabel vLabel1 = new JLabel("ADMIN DETAILS");
//        vLabel1.setFont(new Font("Arial",Font.BOLD, 40));
//        vPanel1.setBackground(Color.GRAY);
//
//        JPanel vPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER,0,80));
//        vPanel2.setPreferredSize(new Dimension(400,200));
//        JLabel vLabel2 = new JLabel("Employee Details");
//        vLabel2.setFont(new Font("Arial",Font.BOLD, 40));
//        vPanel2.setBackground(Color.GRAY);
//
//        JPanel hPanel3 = new JPanel(new FlowLayout(FlowLayout.LEFT,10,100));
//
//
//
//        mainPanel.add(hPanel1);
//
//
//        vPanel1.add(vLabel1);
//        vPanel2.add(vLabel2);
//        hPanel2.add(vPanel1);
//        hPanel2.add(vPanel2);
//        mainPanel.add(hPanel2);
//
//
//        mainPanel.add(hPanel3);
//
//        add(mainPanel);
//
//        vPanel1.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                new AdminDetails();
//                dispose();
//            }
//        });
//
//        vPanel2.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                new AdminMenu();
//                dispose();
//            }
//        });
//
//        setSize(1280, 720);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setVisible(true);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//    }
//
//    public static void main(String[] args) {
//        new AdminHome();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));

        JPanel adminPanel = new JPanel(new GridBagLayout());
        adminPanel.setPreferredSize(new Dimension(500,60));
        adminPanel.setMaximumSize(new Dimension(500,60));
        adminPanel.setBackground(Color.GRAY);
        adminPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel empPanel = new JPanel(new GridBagLayout());
        empPanel.setPreferredSize(new Dimension(500,60));
        empPanel.setMaximumSize(new Dimension(500,60));
        empPanel.setBackground(Color.GRAY);
        empPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel adminDetails = new JLabel("Admin Details");
        adminDetails.setFont(new Font("Arial",Font.BOLD,20));

        JLabel empDetails = new JLabel("Employee Details");
        empDetails.setFont(new Font("Arial",Font.BOLD,20));

        adminPanel.add(adminDetails);
        empPanel.add(empDetails);

        JPanel leftSidePanel = new JPanel();
        leftSidePanel.setPreferredSize(new Dimension(350, 1080));
        leftSidePanel.setBackground(Color.GRAY);
        leftSidePanel.setLayout(new BoxLayout(leftSidePanel,BoxLayout.Y_AXIS));

        JPanel maniMenuPanel = new JPanel(new GridBagLayout());
        maniMenuPanel.setPreferredSize(new Dimension(200,60));
        maniMenuPanel.setMaximumSize(new Dimension(200,60));
        maniMenuPanel.setBackground(Color.LIGHT_GRAY);
        maniMenuPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel mainMenuLabel = new JLabel("Main Menu");
        mainMenuLabel.setFont(new Font("Arial",Font.BOLD,20));

        maniMenuPanel.add(mainMenuLabel);
        leftSidePanel.add(Box.createVerticalStrut(20));
        leftSidePanel.add(maniMenuPanel);

        mainPanel.add(Box.createVerticalStrut(300));
        mainPanel.add(adminPanel);
        mainPanel.add(Box.createVerticalStrut(50));
        mainPanel.add(empPanel);

        add(leftSidePanel,BorderLayout.WEST);
        add(mainPanel,BorderLayout.CENTER);


        setSize(1920,1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        maniMenuPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               new MainGUI();
               dispose();
            }
        });

        adminPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new AdminDetails();
                dispose();
            }
        });

        empPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new AdminMenu();
                dispose();
            }
        });

    }

    public static void main(String[] args) {
        new AdminHome();
    }


}
