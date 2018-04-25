/**
 * Author: Ari Woode
 * Task: Glue
 * ID number: 82992019
 */
package Immortal_Conflict;

public class Glue {

    public static void main(String[] args) {
        User_Login             login           = new User_Login();
        User_SignUp            signUp          = new User_SignUp();
        User_CreateCharacter   createCharacter = new User_CreateCharacter();
        Battle_View            view            = new Battle_View();
        Model_BattleGround     model           = new Model_BattleGround();
        Controler_BattleGround controller      = new Controler_BattleGround(login, signUp, createCharacter, view,
                                                                            model);

        controller.handler();
    }
}
