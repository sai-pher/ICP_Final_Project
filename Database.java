/**
 * Author: Ari Woode
 * Task: Immortal Combat Database
 * ID number: 82992019
 */
package Immortal_Conflict;

import java.sql.*;

public class Database {


    private              Connection        conn;
    private              ResultSet         rset;
    private              PreparedStatement pstmt;
    private static final String            JDBC = "com.mysql.jdbc.Driver";
    private static final String            URL  = "jdbc:mysql://localhost:3306/Immortal_Conflict";

    /**
     * This constructor creates a {@code MonitoringDatabase} object by loading the JDBC driver and connecting
     * to the database url.
     */
    public Database() {

        //Load driver.
        try {
            System.out.println("Loading driver....");
            Class.forName(JDBC);
            System.out.println("Successfully loaded JDBC driver!");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Failed to load JDBC driver");
            e.printStackTrace();
        }

        //Connect to database.
        try {
            System.out.println("Connecting to database....");
            conn = DriverManager.getConnection(URL, "root", "");
            System.out.println("Connection successful!");
        }
        catch (SQLException e) {
            System.out.println("Connection failed.");
            e.printStackTrace();
        }
    }

    /**
     * This method adds a new {@code User} object to the database.
     *
     * @param fName    The first name of the user.
     * @param lName    The last name of the user.
     * @param userName The userName of the user.
     * @param email    The email of the user.
     * @param password The password of the user.
     */
    public void addUser(String fName, String lName, String userName, String email, String password, String charName) {
        try {
            pstmt = conn.prepareStatement("insert into users values (?,?,?,?,?,?)");
            pstmt.setString(1, fName);
            pstmt.setString(2, lName);
            pstmt.setString(3, userName);
            pstmt.setString(4, email);
            pstmt.setString(5, password);
            pstmt.setString(6, charName);
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCharacter(String name, Character.Gender gender, Character.Species species, String charClass) {
        try {
            pstmt = conn.prepareStatement("insert into `character` values (?,?,?,?)");
            pstmt.setString(1, name);
            pstmt.setString(2, String.valueOf(gender));
            pstmt.setString(3, String.valueOf(species));
            pstmt.setString(4, charClass);
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * This method retrieves a {@code User} object from the database.
     *
     * @param email    The email of the user.
     * @param password The password of the user.
     */
    public User getUser(String email, String password) {
        User o = null;
        try {
            pstmt = conn.prepareStatement("select * from users where email = (?) and password = (?)");
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            rset = pstmt.executeQuery();

            while (rset.next()) {
                //Get details
                String fName         = rset.getString(1);
                String lName         = rset.getString(2);
                String userName      = rset.getString(3);
                String characterName = rset.getString(5);

                //create & add object to model
                o = new User(fName, lName, userName, email, password);

                pstmt = conn.prepareStatement("select * from `character` where Name = (?)");
                pstmt.setString(1, characterName);
                pstmt.executeUpdate();
                rset = pstmt.executeQuery();

                while (rset.next()) {
                    String charSpecies = rset.getString(2);
                    String charGender  = rset.getString(3);
                    String charClass   = rset.getString(4);
                    int    charLevel   = rset.getInt(5);
                    int    charExp     = rset.getInt(6);

                    switch (charClass) {

                        case "MAGE":
                            Mage m = new Mage(characterName,
                                              Character.Gender.valueOf(charGender),
                                              Character.Species.valueOf(charSpecies));
                            m.setExp(charExp);
                            m.setLevel(charLevel);

                            o.setCharacter(m);
                            break;

                        case "WARRIOR":
                            Warrior w = new Warrior(characterName,
                                                    Character.Gender.valueOf(charGender),
                                                    Character.Species.valueOf(charSpecies));
                            w.setExp(charExp);
                            w.setLevel(charLevel);

                            o.setCharacter(w);
                            break;

                        case "NINJA":
                            Ninja n = new Ninja(characterName,
                                                Character.Gender.valueOf(charGender),
                                                Character.Species.valueOf(charSpecies));
                            n.setExp(charExp);
                            n.setLevel(charLevel);

                            o.setCharacter(n);
                            break;
                    }

                }

            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return o;
    }

    /**
     * This method executes specialised mySQL queries.
     *
     * @param query The mySQL query to be executed.
     * @return The result of the mySQL query as a {@code ResultSet} object.
     */
    public ResultSet getQuery(String query) {
        try {
            pstmt = conn.prepareStatement(query);
            rset = pstmt.executeQuery();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return rset;
    }

    /**
     * This method retrieves data from the database and uses it to create the labeled objects.
     * It then puts them into the {@code Monitoring} object to be used by the program.
     * @return A {@code Monitoring} object containing all saved database objects.
     */
    /** public HashMap<String, User> buildObjects(){
     Model model = new Model();

     //Create Model
     try {
     pstmt = conn.prepareStatement("select * from users");
     rset = pstmt.executeQuery();

     while (rset.next()) {
     //Get details
     String name    = rset.getString(1);
     String userName = rset.getString(2);
     String    email    = rset.getString(3);
     String password    = rset.getString(4);

     //create & add object to model
     User o = new User(name, userName, email, password);
     model.addFromDB(o);
     }
     } catch (SQLException e) {
     e.printStackTrace();
     }

     return model.modelMap;
     }*/


}
