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
     * @param name     The name of the user.
     * @param userName The userName of the user.
     * @param email    The email of the user.
     * @param password The password of the user.
     */
    public void addUser(String name, String userName, String email, String password) {
        try {
            pstmt = conn.prepareStatement("insert into users values (?,?,?,?)");
            pstmt.setString(1, name);
            pstmt.setString(2, userName);
            pstmt.setString(3, email);
            pstmt.setString(4, password);
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
    public void getUser(String email, String password) {
        try {
            pstmt = conn.prepareStatement("select * from users where email = (?) and password = (?)");
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            rset = pstmt.executeQuery();

            while (rset.next()) {
                //Get details
                String name          = rset.getString(1);
                String userName      = rset.getString(2);
                String characterName = rset.getString(5);

                //create & add object to model
                User o = new User(name, userName, email, password);

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

                    Character character = null;

                    switch (charClass) {

                        case "MAGE":
                            Mage m = new Mage(characterName,
                                              Character.Gender.valueOf(charGender),
                                              Character.Species.valueOf(charSpecies));
                            m.setExp(charExp);
                            m.setLevel(charLevel);

                            character = m;
                            break;

                        case "WARRIOR":
                            Warrior w = new Warrior(characterName,
                                                    Character.Gender.valueOf(charGender),
                                                    Character.Species.valueOf(charSpecies));
                            w.setExp(charExp);
                            w.setLevel(charLevel);

                            character = w;
                            break;

                        case "NINJA":
                            Ninja n = new Ninja(characterName,
                                                Character.Gender.valueOf(charGender),
                                                Character.Species.valueOf(charSpecies));
                            n.setExp(charExp);
                            n.setLevel(charLevel);

                            character = n;
                            break;
                    }


                    o.setCharacter(character);
                }

            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
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
