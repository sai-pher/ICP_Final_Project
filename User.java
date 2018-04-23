/**
 * Author: Ari Woode
 * Task: User class
 * ID number: 82992019
 */
package Immortal_Conflict;


import java.util.Objects;

class User {

    String    name;
    String    userName;
    String    email;
    String    password;
    Character character;

    //Constructor
    public User(String name, String userName, String email, String password) {
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    //Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return Objects.equals(name, user.name) &&
               Objects.equals(userName, user.userName) &&
               Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, userName, email);
    }

    @Override
    public String toString() {
        return "User{" +
               "name='" + name + '\'' +
               ", userName='" + userName + '\'' +
               ", email='" + email + '\'' +
               ", password='" + password + '\'' +
               '}';
    }
}

