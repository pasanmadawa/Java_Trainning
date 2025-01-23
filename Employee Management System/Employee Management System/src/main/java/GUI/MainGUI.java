package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainGUI extends JFrame {

    public MainGUI(){
        super("Main Menu");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,50));

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(250,50));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label = new JLabel("Main Menu");
        label.setFont(new Font("Arial",Font.BOLD, 40));

        JPanel adminPanel = new JPanel(new GridBagLayout());
        adminPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        adminPanel.setPreferredSize(new Dimension(250,50));
        JLabel adminLabel = new JLabel("Admin");
        adminLabel.setFont(new Font("Arial",Font.BOLD, 20));
        adminPanel.setBackground(Color.GRAY);
        adminPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel empPanel = new JPanel(new GridBagLayout());
        empPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        empPanel.setPreferredSize(new Dimension(250,50));
        JLabel empLabel = new JLabel("Employee");
        empLabel.setFont(new Font("Arial",Font.BOLD, 20));
        empPanel.setBackground(Color.GRAY);
        empPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(label);
        mainPanel.add(panel);

        adminPanel.add(adminLabel);
        mainPanel.add(adminPanel);

        empPanel.add(empLabel);
        mainPanel.add(empPanel);

        add(mainPanel);

        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        adminPanel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                adminPanel.setBackground(Color.lightGray);
                adminPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                adminPanel.setPreferredSize(new Dimension(250,50));
                adminPanel.setBackground(Color.GRAY);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                new AdminLogIn();
                dispose();
            }
        });

        empPanel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                empPanel.setBackground(Color.lightGray);
                empPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                empPanel.setPreferredSize(new Dimension(250,50));
                empPanel.setBackground(Color.GRAY);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                new LogIn();
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        new MainGUI();
    }

}
