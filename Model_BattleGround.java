/**
 * Author: Ari Woode
 * Task: Battle Grounds
 * ID number: 82992019
 */
package Immortal_Conflict;

public class Model_BattleGround {

    User      player_1;
    User      player_2;
    Character character_1;
    Character character_2;
    Database  database;

    public Model_BattleGround() {

        database = new Database();
    }

    public User getPlayer_1() {
        return player_1;
    }

    public void setPlayer_1(User player_1) {
        this.player_1 = player_1;
        this.character_1 = player_1.character;
    }

    public User getPlayer_2() {
        return player_2;
    }

    public void setPlayer_2(User player_2) {
        this.player_2 = player_2;
        this.character_2 = player_2.character;
    }

    /**
     * This method finds an existing user in the data base and returns it as an object to be used.
     *
     * @param email    The email of an existing user.
     * @param password The password of an existing user.
     * @return An existing user as an object to be used by the program.
     */
    User login(String email, String password) {
        User u = null;
        try {
            u = database.getUser(email, password);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("invalid user");
        }
        return u;
    }

    /**
     * This function allows a user to create a new persona in the game.
     *
     * @param fName     The first name of a new user.
     * @param lName     The last name of a new user.
     * @param userName  The user name of a new user.
     * @param email     The email address of a new user.
     * @param password  The password of a new user.
     * @param character The new users unique character.
     * @return The new user as an object to be used by the program.
     */
    User signUp(String fName, String lName, String userName, String email, String password, Character character) {
        User u = new User(fName, lName, userName, email, password);
        u.setCharacter(character);
        database.addUser(fName, lName, userName, email, password, character.getName());
        return u;
    }

    /**
     * This method allows a new user to create a unique character in the game.
     *
     * @param name      The new character's unique name.
     * @param gender    The new character's gender as a string.
     * @param species   The new character's species as a string.
     * @param charClass The new character's class as a string.
     * @return The new character as an object to be used.
     */
    Character createChar(String name, String gender, String species, String charClass) {
        switch (charClass) {

            case "MAGE":

                return new Mage(name,
                                Character.Gender.valueOf(gender),
                                Character.Species.valueOf(species));

            case "WARRIOR":

                return new Warrior(name,
                                   Character.Gender.valueOf(gender),
                                   Character.Species.valueOf(species));

            case "NINJA":

                return new Ninja(name,
                                 Character.Gender.valueOf(gender),
                                 Character.Species.valueOf(species));
        }
        return null;
    }

    /**
     * The battle mechanic of the game. this takes each characters commands and allows them to interact with each other.
     * It executes the players actions first to allow for status effects to take place before executing their attacks.
     * This method is valid until a player is defeated.
     *
     * @param player1Action Player 1's action command.
     * @param player2Action Player 2's action command.
     * @param player1Attack Player 1's attack command.
     * @param player2Attack Player 2's attack command.
     */
    void battle(int player1Action, int player2Action, int player1Attack, int player2Attack) {
        if (!character_1.status.equals(Character.BattleStatus.DEFEATED) |
            !character_2.status.equals(Character.BattleStatus.DEFEATED)) {

            character_1.action(player1Action);
            character_2.action(player2Action);

            character_2.damage(character_1.attack(player1Attack));
            character_1.damage(character_2.attack(player2Attack));

            if ((player1Action != 1))
                character_1.charge();
            if (player2Action != 1)
                character_2.charge();

        }
    }

}
