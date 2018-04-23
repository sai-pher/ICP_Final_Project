/**
 * Author: Ari Woode
 * Task:
 * ID number: 82992019
 */
package Immortal_Conflict;

import javax.swing.*;

public class Battle_View {
    JFrame frame;
    private JPanel viewPanel;
    JTextField nameTextField;
    JTextField userNameTextField;
    JTextField emailTextField;
    JButton    exitButton;

    public Battle_View() {
        frame = new JFrame("Battle_View");
        frame.setContentPane(viewPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Battle_View");
        frame.setContentPane(new Battle_View().viewPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}


