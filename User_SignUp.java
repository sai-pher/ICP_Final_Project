/**
 * Author: Ari Woode
 * Task:
 * ID number: 82992019
 */
package Immortal_Conflict;

import javax.swing.*;

public class User_SignUp {
    JFrame frame;
    private JLabel signUpLabel;
    private JPanel signUpRootPanel;
    private JPanel controlPanel;
    private JPanel labelPanel;
    private JPanel textPanel;
    JTextField     nameTextField;
    JTextField     userNameTextField;
    JTextField     emailTextField;
    JPasswordField passwordField;
    JPasswordField rePasswordField;
    public JButton signUpButton;

    public User_SignUp() {

        frame = new JFrame("User_SignUp");
        frame.setContentPane(signUpRootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
//        frame.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("User_SignUp");
        frame.setContentPane(new User_SignUp().signUpRootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
