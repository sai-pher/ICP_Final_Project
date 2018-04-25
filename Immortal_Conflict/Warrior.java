/**
 * Author: Ari Woode
 * Task: Warrior Character Class
 * ID number: 82992019
 */
package Immortal_Conflict;

import java.util.Random;

public class Warrior extends Character {

    /**
     * Determines if an enemy attack will be effective or not.
     */
    enum DodgeMultiplier {
        ZERO(0),
        ONE(1);

        private final int multiplier;

        DodgeMultiplier(int multiplier) {
            this.multiplier = multiplier;
        }

        public int getMultiplier() {
            return multiplier;
        }
    }

    /**
     * Specifies the attack type to be used.
     */
    enum Attacks {
        PUNCH(10, 0),
        SHIELD_BASH(15, 5),
        SLASH(40, 20),
        GODS_FIST(80, 100);

        private final int damage;
        private final int energyCost;

        Attacks(int damage, int energyCost) {
            this.damage = damage;
            this.energyCost = energyCost;
        }

        public int getDamage() {
            return damage;
        }

        public int getEnergyCost() {
            return energyCost;
        }

    }

    final String WARRIOR_CLASS            = "WARRIOR";
    final int    WARRIOR_BASE_LIFE_POINTS = 200;
    final int    WARRIOR_BASE_ENERGY      = 100;
    final int    WARRIOR_CHARGE           = 10;
    final int    WARRIOR_HEAL             = 5;
    final double WARRIOR_DODGE_CHANCE     = 0.2;

    private DodgeMultiplier dodge;

    /**
     * This constructor creates a new character and sets the stats to its default parameters.
     *
     * @param name The name of the new character.
     * @param gen  The gender of the new character.
     * @param spec The species of the new character.
     */
    public Warrior(String name, Gender gen, Species spec) {
        super(name, gen, spec);
        charClass = WARRIOR_CLASS;
        lifePoints = WARRIOR_BASE_LIFE_POINTS;
        energy = WARRIOR_BASE_ENERGY;
        dodge = DodgeMultiplier.ONE;
    }

    /**
     * This Method selects an attack, depletes the characters energy by that attaks energy cost
     * and returns the damage of the given attack.
     *
     * @param attackNum The Attack ID of the characters next attack.
     * @return The damage done by the given Attack.
     */
    @Override
    public int attack(int attackNum) {

        int attackDamage = 0;

        switch (attackNum) {

            case 0:
                attackDamage = Attacks.PUNCH.damage;
                energy -= Attacks.PUNCH.energyCost;
                break;

            case 1:
                attackDamage = Attacks.SHIELD_BASH.damage;
                energy -= Attacks.SHIELD_BASH.energyCost;
                break;

            case 2:
                attackDamage = Attacks.SLASH.damage;
                energy -= Attacks.SLASH.energyCost;
                break;

            case 3:
                attackDamage = Attacks.GODS_FIST.damage;
                energy -= Attacks.GODS_FIST.energyCost;
                break;
        }
        return attackDamage;
    }

    /**
     * This method reduces the life points of the given character by given amount.
     * If the character's life points go bellow 0, the character is defeated.
     *
     * @param damageNum The damage taken by the character.
     */
    @Override
    public void damage(int damageNum) {
        lifePoints -= damageNum * dodge.getMultiplier();
        if (lifePoints <= 0)
            status = BattleStatus.DEFEATED;
        dodge = DodgeMultiplier.ONE;
    }

    /**
     * This Method selects an action and calls the corresponding function or action.
     *
     * @param action The Action ID of the characters next action.
     */
    @Override
    public void action(int action) {
        switch (action) {

            case 0:
                dodge();
                break;

            case 1:
                charge();
                break;

            case 2:
                heal();
                break;
        }
    }

    /**
     * This method takes the dodge chance of a given character and uses it to
     * determine whether or not the character will take the damage dealt to it or not.
     */
    @Override
    public void dodge() {
        final int ran = new Random().nextInt(100);
        if (ran < 100 * WARRIOR_DODGE_CHANCE)
            dodge = DodgeMultiplier.ZERO;
        else
            dodge = DodgeMultiplier.ONE;
    }

    /**
     * This method increases the energy count of the given character.
     */
    @Override
    //todo: make level dependant
    public void charge() {
        if ((energy += WARRIOR_CHARGE) <= WARRIOR_BASE_ENERGY)
            energy += WARRIOR_CHARGE;
    }

    /**
     * This method increases the life points of a given character.
     */
    @Override
    //todo: make level dependant
    public void heal() {
        if ((lifePoints += WARRIOR_HEAL) <= WARRIOR_BASE_LIFE_POINTS)
            lifePoints += WARRIOR_HEAL;
    }
}
