/**
 * Author: Ari Woode
 * Task:
 * ID number: 82992019
 */
package Immortal_Conflict;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class User_CreateCharacter {
    public JComboBox speciesComboBox;
    public JComboBox genderComboBox;
    public JComboBox classComboBox;
    public JTextPane descriptionTextPane;
    public JButton   addCharacterButton;
    JFrame     frame;
    JPanel     characterRootPanel;
    JPanel     createPanel;
    JPanel     controlPanel;
    JPanel     headerPanel;
    JTextField characterNameTextField;
    String     charName;
    String     charSpecies;
    String     charGender;
    String     charClass;

    Character character;

    public User_CreateCharacter() {

        frame = new JFrame("User_Login");
        frame.setContentPane(characterRootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
    }
}
