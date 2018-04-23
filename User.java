/**
 * Author: Ari Woode
 * Task: User class
 * ID number: 82992019
 */
package Immortal_Conflict;


import java.util.Objects;

class User {

    String    fName;
    String    lName;
    String    userName;
    String    email;
    String    password;
    Character character;

    //Constructor
    public User(String fName, String lName, String userName, String email, String password) {
        this.fName = fName;
        this.lName = lName;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    //Getters and Setters


    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    //Helper methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName) &&
               Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userName, email);
    }

    @Override
    public String toString() {
        return "User{" +
               "fName='" + fName + '\'' +
               ", lName='" + lName + '\'' +
               ", userName='" + userName + '\'' +
               ", email='" + email + '\'' +
               ", password='" + password + '\'' +
               ", character=" + character +
               '}';
    }
}

