package Immortal_Conflict;

public interface Battle_Mechanics {

    /**
     * This Method selects an attack, depletes the characters energy by that attaks energy cost
     * and returns the damage of the given attack.
     *
     * @param attackNum The Attack ID of the characters next attack.
     * @return The damage done by the given Attack.
     */
    int attack(int attackNum);

    /**
     * This method reduces the life points of the given character by given amount.
     * If the character's life points go bellow 0, the character is defeated.
     *
     * @param damageNum The damage taken by the character.
     */
    void damage(int damageNum);

    /**
     * This Method selects an action and calls the corresponding function or action.
     *
     * @param action The Action ID of the characters next action.
     */
    void action(int action);

    /**
     * This method takes the dodge chance of a given character and uses it to
     * determine whether or not the character will take the damage dealt to it or not.
     */
    void dodge();

    /**
     * This method increases the energy count of the given character.
     */
    void charge();

    /**
     * This method increases the life points of a given character.
     */
    void heal();
}
