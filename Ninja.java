/**
 * Author: Ari Woode
 * Task: Ninja Character Class
 * ID number: 82992019
 */
package Immortal_Conflict;

import java.util.Random;

public class Ninja extends Character {


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
        STAB(10, 5),
        SHURIKEN(15, 10),
        CHIDORI(45, 30),
        NIGHT_GUY(100, 180);

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

    final String NINJA_CLASS            = "NINJA";
    final int    NINJA_BASE_LIFE_POINTS = 80;
    final int    NINJA_BASE_ENERGY      = 145;
    final int    NINJA_CHARGE           = 15;
    final int    NINJA_HEAL             = 40;
    final double NINJA_DODGE_CHANCE     = 0.8;

    private DodgeMultiplier dodge;

    /**
     * This constructor creates a new character and sets the stats to its default parameters.
     *
     * @param name The name of the new character.
     * @param gen  The gender of the new character.
     * @param spec The species of the new character.
     */
    public Ninja(String name, Gender gen, Species spec) {
        super(name, gen, spec);
        charClass = NINJA_CLASS;
        lifePoints = NINJA_BASE_LIFE_POINTS;
        energy = NINJA_BASE_ENERGY;
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
                attackDamage = Attacks.STAB.damage;
                energy -= Attacks.STAB.energyCost;
                break;

            case 1:
                attackDamage = Attacks.SHURIKEN.damage;
                energy -= Attacks.SHURIKEN.energyCost;
                break;

            case 2:
                attackDamage = Attacks.CHIDORI.damage;
                energy -= Attacks.CHIDORI.energyCost;
                break;

            case 3:
                attackDamage = Attacks.NIGHT_GUY.damage;
                energy -= Attacks.NIGHT_GUY.energyCost;
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
        if (ran < 100 * NINJA_DODGE_CHANCE)
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
        energy += NINJA_CHARGE;
    }

    /**
     * This method increases the life points of a given character.
     */
    @Override
    //todo: make level dependant
    public void heal() {
        if ((lifePoints += NINJA_HEAL) <= NINJA_BASE_LIFE_POINTS & (energy -= 25) > 0)
            lifePoints += NINJA_HEAL;
        energy -= 25;
    }
}
