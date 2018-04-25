/**
 * Author: Ari Woode
 * Task: Character Parent Class
 * ID number: 82992019
 */
package Immortal_Conflict;

import java.util.Objects;

/**
 * Parent Character class.
 */
public abstract class Character implements Battle_Mechanics {

    /**
     * Specifies the character type of the given character.
     */
    enum CharacterClass {
        MAGE,
        WARRIOR,
        NINJA
    }

    /**
     * Specifies the species of the given character.
     */
    enum Species {
        HUMAN,
        ELF,
        SUPER_APE,
        SPIRIT
    }

    /**
     * Specifies the gender of the given character.
     */
    enum Gender {
        MALE,
        FEMALE
    }

    /**
     * Specifies the battle status of the given character.
     */
    enum BattleStatus {
        ALIVE,
        DEFEATED
    }

    /**
     * Specifies the action that can be taken by the given character.
     */
    enum Actions {
        DODGE,
        CHARGE,
        HEAL
    }

    int          lifePoints;
    int          energy;
    int          level;
    int          exp;
    String       charClass;
    String       name;
    BattleStatus status = BattleStatus.ALIVE;
    final Gender  gender;
    final Species species;

    final int     BASE_LIFE_POINTS = 100;
    final int     BASE_ENERGY      = 100;
    final int     EXP_THRESHOLD    = 100;

    //Constructors

    /**
     * Default Character constructor.
     */
    public Character() {
        this(null, null, null);
    }

    /**
     * This constructor creates a new character and sets the stats to its default parameters.
     *
     * @param name The name of the new character.
     * @param gen  The gender of the new character.
     * @param spec The species of the new character.
     */
    public Character(String name, Gender gen, Species spec) {
        this.name = name;
        this.gender = gen;
        this.species = spec;
        this.lifePoints = BASE_LIFE_POINTS;
        this.energy = BASE_ENERGY;
        this.level = 1;
        this.exp = 0;
    }

    //Getters and Setters

    public int getLifePoints() {
        return lifePoints;
    }

    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public Species getSpecies() {
        return species;
    }

    //Unique methods

    /**
     * This function increases the expedience points of a character and their level if the
     * expedience crosses the threshold of the given level.
     *
     * @param amount The Amount to increase a characters expedience by.
     */
    public void updateExp(int amount) {
        exp += amount;
        // todo: change the threshold function to reflect a cross and not an equality.
        if (exp % EXP_THRESHOLD == 0)
            level++;
    }

    //Helper Methods


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Character)) return false;
        Character character = (Character) o;
        return level == character.level &&
               Objects.equals(name, character.name) &&
               gender == character.gender &&
               species == character.species;
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, gender, species);
    }

    @Override
    public String toString() {
        return "Character{" +
               "level=" + level +
               ", name='" + name + '\'' +
               ", gender=" + gender +
               ", species=" + species +
               '}';
    }
}
