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
    Database  database = new Database();

    public Model_BattleGround() {

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

    User login(String email, String password) {
        return database.getUser(email, password);
    }

    void battle(int p1_ac, int p2_ac, int p1_att, int p2_att) {
        if (!character_1.status.equals(Character.BattleStatus.DEFEATED) |
            !character_2.status.equals(Character.BattleStatus.DEFEATED)) {

            character_1.action(p1_ac);
            character_2.action(p2_ac);

            character_2.damage(character_1.attack(p1_att));
            character_1.damage(character_2.attack(p2_att));

            if ((p1_ac != 1) | (p2_ac != 1)) {
                character_1.charge();
                character_2.charge();
            }
        }
    }

}
