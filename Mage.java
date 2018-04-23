/**
 * Author: Ari Woode
 * Task: Mage Character Class
 * ID number: 82992019
 */
package Immortal_Conflict;

import java.util.Random;

/**
 * This class of character is a magic user. It emphasises high energy and powerful but expensive attacks.
 */
public class Mage extends Character {

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
        SNOWBALL(15, 10),
        FIREBALL(20, 30),
        HELL_FIRE(40, 70),
        FINAL_CALL(90, 110);

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

    final String MAGE_CLASS            = "MAGE";
    final int    MAGE_BASE_LIFE_POINTS = 100;
    final int    MAGE_BASE_ENERGY      = 140;
    final int    MAGE_CHARGE           = 30;
    final int    MAGE_HEAL             = 20;
    final double MAGE_DODGE_CHANCE     = 0.4;

    private DodgeMultiplier dodge;

    /**
     * This constructor creates a new character and sets the stats to its default parameters.
     *
     * @param name The name of the new character.
     * @param gen  The gender of the new character.
     * @param spec The species of the new character.
     */
    public Mage(String name, Gender gen, Species spec) {
        super(name, gen, spec);
        charClass = MAGE_CLASS;
        lifePoints = MAGE_BASE_LIFE_POINTS;
        energy = MAGE_BASE_ENERGY;
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
                attackDamage = Attacks.SNOWBALL.damage;
                energy -= Attacks.SNOWBALL.energyCost;
                break;

            case 1:
                attackDamage = Attacks.FIREBALL.damage;
                energy -= Attacks.FIREBALL.energyCost;
                break;

            case 2:
                attackDamage = Attacks.HELL_FIRE.damage;
                energy -= Attacks.HELL_FIRE.energyCost;
                break;

            case 3:
                attackDamage = Attacks.FINAL_CALL.damage;
                energy -= Attacks.FINAL_CALL.energyCost;
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
        if (ran < 100 * MAGE_DODGE_CHANCE)
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
        if ((energy += MAGE_CHARGE) <= MAGE_BASE_ENERGY)
            energy += MAGE_CHARGE;
    }

    /**
     * This method increases the life points of a given character.
     */
    @Override
    //todo: make level dependant
    public void heal() {
        if ((lifePoints += MAGE_HEAL) <= MAGE_BASE_LIFE_POINTS & (energy -= 10) > 0)
            energy -= 10;
            lifePoints += MAGE_HEAL;
    }
}
