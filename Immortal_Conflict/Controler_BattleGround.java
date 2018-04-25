/**
 * Author: Ari Woode
 * Task: Battle Ground Controller
 * ID number: 82992019
 */
package Immortal_Conflict;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controler_BattleGround implements ActionListener {


    User_Login           login;
    User_SignUp          signUp;
    User_CreateCharacter createCharacter;
    Battle_View          view;
    Model_BattleGround   model;

    public Controler_BattleGround(User_Login login, User_SignUp signUp, User_CreateCharacter createCharacter,
                                  Battle_View view, Model_BattleGround model) {
        this.login = login;
        this.signUp = signUp;
        this.createCharacter = createCharacter;
        this.view = view;
        this.model = model;

        //populations

        //model.rebuild();
    }

    //populations


    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(login.loginButton)) {
            try {
                String email    = login.emailTextField.getText();
                String password = String.valueOf(login.passwordField.getPassword());

                if (model.login(email, password) != null) {
                    User u = model.login(email, password);
                    //TODO: link properly to battle view
                    view.userNameTextField.setText(u.userName);
                    view.charNameTextField.setText(u.character.name);
                    view.charClassTextField.setText(u.character.charClass);
                    view.frame.setVisible(true);

                }
            }
            catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null, "Invalid email or password. " +
                                                    "\nPlease try again or sign up.");
            }


            login.emailTextField.setText(null);
            login.passwordField.setText(null);

        }

        else if (e.getSource().equals(login.SignUpButton)) {
            signUp.frame.setVisible(true);
        }

        else if (e.getSource().equals(signUp.createCharacterButton)) {
            createCharacter.frame.setVisible(true);
        }

        else if (e.getSource().equals(signUp.signUpButton)) {

            try {
                String fName    = signUp.fNameTextField.getText();
                String lName    = signUp.lNameTextField.getText();
                String userName = signUp.userNameTextField.getText();

                String email = null;
                if (!model.find(signUp.emailTextField.getText()))
                    email = signUp.emailTextField.getText();
                else
                    JOptionPane.showMessageDialog(null, "This email already exists." +
                                                        "\nPlease enter a valid email.");

                String password;

                if (!String.valueOf(signUp.rePasswordField.getPassword())
                           .equals(String.valueOf(signUp.passwordField.getPassword()))) {
                    signUp.rePasswordField.setText(null);
                    JOptionPane.showMessageDialog(null, "Password does not match. " +
                                                        "\nPlease re-enter your password.");
                }
                else {
                    password = String.valueOf(signUp.passwordField.getPassword());


                    //TODO: sign up properly: done
                    switch (signUp.charClass) {
                        case "MAGE":
                            model.signUp(fName, lName, userName, email, password, signUp.mage);
                            break;

                        case "WARRIOR":
                            model.signUp(fName, lName, userName, email, password, signUp.warrior);
                            break;

                        case "NINJA":
                            model.signUp(fName, lName, userName, email, password, signUp.ninja);
                            break;
                    }

                    JOptionPane.showMessageDialog(null, "New user " + signUp.userNameTextField.getText() +
                                                        " has successfully been added.");
                    signUp.frame.dispose();
                }

            }
            catch (HeadlessException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null, "Please renter details.");
                signUp.emailTextField.setText(null);
                signUp.passwordField.setText(null);
                signUp.rePasswordField.setText(null);
            }
            catch (NullPointerException e2) {
                e2.printStackTrace();
                JOptionPane.showMessageDialog(null, "Please renter empty fields.");
            }

        }
        //comboboxes
        else if (e.getSource().equals(createCharacter.speciesComboBox)) {
            JComboBox comboBox = (JComboBox) e.getSource();
            signUp.charSpecies = (String) comboBox.getSelectedItem();
        }
        else if (e.getSource().equals(createCharacter.genderComboBox)) {
            JComboBox comboBox = (JComboBox) e.getSource();
            signUp.charGender = (String) comboBox.getSelectedItem();
        }
        else if (e.getSource().equals(createCharacter.classComboBox)) {
            JComboBox comboBox = (JComboBox) e.getSource();
            signUp.charSpecies = (String) comboBox.getSelectedItem();
        }

        else if (e.getSource().equals(createCharacter.addCharacterButton)) {
            try {
                signUp.charName = createCharacter.characterNameTextField.getText();
                signUp.charSpecies = (String) createCharacter.speciesComboBox.getSelectedItem();
                signUp.charGender = (String) createCharacter.genderComboBox.getSelectedItem();
                signUp.charClass = (String) createCharacter.classComboBox.getSelectedItem();

                switch (signUp.charClass) {
                    case "MAGE":
                        signUp.mage = new Mage(signUp.charName,
                                               Character.Gender.valueOf(signUp.charGender),
                                               Character.Species.valueOf(signUp.charSpecies));
                        break;

                    case "WARRIOR":
                        signUp.warrior = new Warrior(signUp.charName,
                                                     Character.Gender.valueOf(signUp.charGender),
                                                     Character.Species.valueOf(signUp.charSpecies));
                        break;

                    case "NINJA":
                        signUp.ninja = new Ninja(signUp.charName,
                                                 Character.Gender.valueOf(signUp.charGender),
                                                 Character.Species.valueOf(signUp.charSpecies));
                        break;
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Something is wrong somewhere");
            }
            createCharacter.frame.setVisible(false);
        }

        else if (e.getSource().equals(view.exitButton)) {
            view.frame.dispose();
        }

    }

    public void handler() {
        login.loginButton.addActionListener(this);
        login.SignUpButton.addActionListener(this);
        createCharacter.speciesComboBox.addActionListener(this);
        createCharacter.genderComboBox.addActionListener(this);
        createCharacter.classComboBox.addActionListener(this);
        createCharacter.addCharacterButton.addActionListener(this);
        signUp.signUpButton.addActionListener(this);
        signUp.createCharacterButton.addActionListener(this);
        view.exitButton.addActionListener(this);
        login.frame.setVisible(true);
    }
}
