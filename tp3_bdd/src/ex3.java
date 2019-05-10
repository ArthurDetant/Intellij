import java.sql.*;

public class ex3
{
    private static void createTable(Statement stmt) throws SQLException
    {
        stmt.executeUpdate("CREATE TABLE Escales"
                + "(Num_escale INTEGER NOT NULL,"
                + "Ville_escale VARCHAR(20),"
                + "Duree_escale_minimum VARCHAR(3),"
                + "PRIMARY KEY (Num_escale)) "
        );
    }
    private static void deleteTable(Statement stmt) throws SQLException {
        stmt.executeUpdate("DROP TABLE Escales");
    }


    private static void insertTable(Statement stmt) throws SQLException {
        stmt.executeUpdate("INSERT INTO Escales"
                + "(Num_escale,Ville_escale,Duree_escale_minimum)"
                + "VALUES"
                + "(1, 'Moscou', '5h')"
        );
        stmt.executeUpdate("INSERT INTO Escales"
                + "(Num_escale,Ville_escale,Duree_escale_minimum)"
                + "VALUES"
                + "(2, 'Singapour', '5h')"
        );
        stmt.executeUpdate("INSERT INTO Escales"
                + "(Num_escale,Ville_escale,Duree_escale_minimum)"
                + "VALUES"
                + "(3, 'Sydney', '4h')"
        );
        stmt.executeUpdate("INSERT INTO Escales"
                + "(Num_escale,Ville_escale,Duree_escale_minimum)"
                + "VALUES"
                + "(4, 'Tahiti', '4h')"
        );
        stmt.executeUpdate("INSERT INTO Escales"
                + "(Num_escale,Ville_escale,Duree_escale_minimum)"
                + "VALUES"
                + "(5, 'Honululu', '4h')"
        );
        stmt.executeUpdate("INSERT INTO Escales"
                + "(Num_escale,Ville_escale,Duree_escale_minimum)"
                + "VALUES"
                + "(6, 'Los Angeles', '5h')"
        );
        stmt.executeUpdate("INSERT INTO Escales"
                + "(Num_escale,Ville_escale,Duree_escale_minimum)"
                + "VALUES"
                + "(7, 'New York', '4h')"
        );
        stmt.executeUpdate("INSERT INTO Escales"
                + "(Num_escale,Ville_escale,Duree_escale_minimum)"
                + "VALUES"
                + "(8, 'Beijing', '3h')"
        );
        stmt.executeUpdate("INSERT INTO Escales"
                + "(Num_escale,Ville_escale,Duree_escale_minimum)"
                + "VALUES"
                + "(9, 'Lima', '3h')"
        );
        stmt.executeUpdate("INSERT INTO Escales"
                + "(Num_escale,Ville_escale,Duree_escale_minimum)"
                + "VALUES"
                + "(10, 'London', '3h')"
        );
    }

    public static void display(Statement stmt) throws SQLException
    {
        ResultSet resultat = stmt.executeQuery("SELECT * FROM Escales");
        ResultSetMetaData rsmd = resultat.getMetaData();

        int nbColonnes = rsmd.getColumnCount();
        String entete = "";
        String nomCol;

        for(int i = 1; i <= nbColonnes; i++)
        {
            nomCol = rsmd.getColumnName(i);
            entete += nomCol + "\t";

        }
        System.out.println(entete);
        while(resultat.next())
        {
            for(int i = 1; i <= nbColonnes; i++)
            {
                String nom = rsmd.getColumnName(i);
                System.out.print(resultat.getString(nom) + "\t");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Driver non chargé");
        }

        String url = "jdbc:mysql://192.168.22.48:3306/adetant?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
        String utilisateur = "adetant";
        String motDePasse = "13071996";
        Connection connexion = null;

        try
        {
            connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
        }catch (Exception e)
        {
            System.out.println("pas de co");
        }
        if(connexion!=null)
        {
            System.out.println("connecté!");
        }

        Statement stmt = connexion.createStatement();
        deleteTable(stmt);
        createTable(stmt);
        insertTable(stmt);
        display(stmt);
    }
}