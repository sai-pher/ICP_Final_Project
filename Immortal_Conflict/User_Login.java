/**
 * Author: Ari Woode
 * Task:
 * ID number: 82992019
 */
package Immortal_Conflict;

import javax.swing.*;

public class User_Login extends JFrame {
    JFrame         frame;
    JTextField     emailTextField;
    JPasswordField passwordField;
    public  JButton loginButton;
    public  JButton SignUpButton;
    private JPanel  rootPanel;
    private JPanel  tittlePanel;
    private JPanel  controlPanel;
    private JPanel  labelPanel;
    private JLabel  emailLabel;
    private JLabel  passwordLabel;
    private JLabel  tittleLabel;

    public User_Login() {
        frame = new JFrame("User_Login");
        frame.setContentPane(rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
//        frame.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("User_Login");
        frame.setContentPane(new User_Login().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
