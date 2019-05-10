import java.sql.*;

public class ex1
{
    private static void createTable(Statement stmt) throws SQLException
    {
        stmt.executeUpdate("CREATE TABLE vol"
                + "(NumVol VARCHAR(8),"
                + "Heure_depart DATE,"
                + "Heure_arrive DATE,"
                + "Ville_depart VARCHAR(20),"
                + "Ville_arrivee VARCHAR(20),"
                + "PRIMARY KEY (NumVol)) "
        );
    }

    private static void deleteTable(Statement stmt) throws SQLException {
        stmt.executeUpdate("DROP TABLE vol");
    }

    private static void insertTable(Statement stmt) throws SQLException
    {

        stmt.executeUpdate("INSERT INTO vol"
                + "( NumVol,Heure_depart,Heure_arrive,Ville_depart,Ville_arrivee )"
                + "VALUES"
                + "('AF118', '08:30', '10:57','Paris','Athens')"
        );
        stmt.executeUpdate("INSERT INTO vol"
                + "( NumVol,Heure_depart,Heure_arrive,Ville_depart,Ville_arrivee )"
                + "VALUES"
                + "( 'AF212','09:21','14:10','Paris','Moscow') "
        );

        stmt.executeUpdate("INSERT INTO vol"
                + "( NumVol,Heure_depart,Heure_arrive,Ville_depart,Ville_arrivee )"
                + "VALUES"
                + "('AF178','12:56','14:15','Paris','London') "
        );
        stmt.executeUpdate("INSERT INTO vol"
                + "( NumVol,Heure_depart,Heure_arrive,Ville_depart,Ville_arrivee )"
                + "VALUES"
                + "('AF010','07:53','14:19','Paris','New York') "
        );
        stmt.executeUpdate("INSERT INTO vol"
                + "( NumVol,Heure_depart,Heure_arrive,Ville_depart,Ville_arrivee )"
                + "VALUES"
                + "('AF012','07:58','20:10','Paris','Los Angeles') "
        );
        stmt.executeUpdate("INSERT INTO vol"
                + "( NumVol,Heure_depart,Heure_arrive,Ville_depart,Ville_arrivee )"
                + "VALUES"
                + "('AF001','22:10','12:00','Paris','Tahiti') "
        );
        stmt.executeUpdate("INSERT INTO vol"
                + "( NumVol,Heure_depart,Heure_arrive,Ville_depart,Ville_arrivee )"
                + "VALUES"
                + "('SA854','22:00','10:14','Singapore','Athens') "
        );
        stmt.executeUpdate("INSERT INTO vol"
                + "( NumVol,Heure_depart,Heure_arrive,Ville_depart,Ville_arrivee )"
                + "VALUES"
                + "('AA111','15:45','21:10','Beijing','Singapore') "
        );
        stmt.executeUpdate("INSERT INTO vol"
                + "( NumVol,Heure_depart,Heure_arrive,Ville_depart,Ville_arrivee )"
                + "VALUES"
                + "('SA012','07:57','11:26','Sydney','Singapore') "
        );
        stmt.executeUpdate("INSERT INTO vol"
                + "( NumVol,Heure_depart,Heure_arrive,Ville_depart,Ville_arrivee )"
                + "VALUES"
                + "('AF109','07:39','14:10','Tahiti','Sydney') "
        );
        stmt.executeUpdate("INSERT INTO vol"
                + "( NumVol,Heure_depart,Heure_arrive,Ville_depart,Ville_arrivee )"
                + "VALUES"
                + "('AA517','23:57','07:12','Honolulu','Tokyo') "
        );
        stmt.executeUpdate("INSERT INTO vol"
                + "( NumVol,Heure_depart,Heure_arrive,Ville_depart,Ville_arrivee )"
                + "VALUES"
                + "('JA014','15:35','19:00','Tokyo','Beijing') "
        );
        stmt.executeUpdate("INSERT INTO vol"
                + "( NumVol,Heure_depart,Heure_arrive,Ville_depart,Ville_arrivee )"
                + "VALUES"
                + "('JA115','21:26','10:10','Los Angeles', 'Tokyo') "
        );
        stmt.executeUpdate("INSERT INTO vol"
                + "( NumVol,Heure_depart,Heure_arrive,Ville_depart,Ville_arrivee )"
                + "VALUES"
                + "('AA015','20:50','07:00','New York', 'Lima') "
        );
        stmt.executeUpdate("INSERT INTO vol"
                + "( NumVol,Heure_depart,Heure_arrive,Ville_depart,Ville_arrivee )"
                + "VALUES"
                + "('AA515','07:20','12:38', 'Lima', 'Los Angeles') "
        );
        stmt.executeUpdate("INSERT INTO vol"
                + "( NumVol,Heure_depart,Heure_arrive,Ville_depart,Ville_arrivee )"
                + "VALUES"
                + "('AA516','15:20','21:38', 'Beijing','Athens') "
        );
        stmt.executeUpdate("INSERT INTO vol"
                + "( NumVol,Heure_depart,Heure_arrive,Ville_depart,Ville_arrivee )"
                + "VALUES"
                + "('AF218','21:12','09:16','Beijing','Paris') "
        );
        stmt.executeUpdate("INSERT INTO vol"
                + "( NumVol,Heure_depart,Heure_arrive,Ville_depart,Ville_arrivee )"
                + "VALUES"
                + "('AA118','07:15','13:10','New York', 'Paris') "
        );
        stmt.executeUpdate("INSERT INTO vol"
                + "( NumVol,Heure_depart,Heure_arrive,Ville_depart,Ville_arrivee )"
                + "VALUES"
                + "('TA215','08:00','10:10','Tunis','Paris') "
        );
        stmt.executeUpdate("INSERT INTO vol"
                + "( NumVol,Heure_depart,Heure_arrive,Ville_depart,Ville_arrivee )"
                + "VALUES"
                + "('OA005','14:20','17:00','Athens','Paris') "
        );
        stmt.executeUpdate("INSERT INTO vol"
                + "( NumVol,Heure_depart,Heure_arrive,Ville_depart,Ville_arrivee )"
                + "VALUES"
                + "('PA022','10:12','23:55','Lima','Paris') "
        );
        stmt.executeUpdate("INSERT INTO vol"
                + "( NumVol,Heure_depart,Heure_arrive,Ville_depart,Ville_arrivee )"
                + "VALUES"
                + "('AF002','15:52','00:12','Tokyo','Paris') "
        );
    }

    public static void display(Statement stmt) throws SQLException
    {
        ResultSet resultat = stmt.executeQuery("SELECT * FROM vol");
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


    public static void main(String[] args) throws ClassNotFoundException, SQLException
    {
        Class.forName("oracle.jdbc.driver.OracleDriver");

        String url="jdbc:oracle:thin:\"adetant\"/13071996@192.168.22.60:1521/pdborcl2";

        Connection connexion = null;
        try
        {
            connexion = DriverManager.getConnection(url);
        }catch (Exception e)
        {
            System.out.println("pas de co");
        }
        if(connexion!=null)
        {
            System.out.println("connecté!");
        }

        Statement stmt = connexion.createStatement();

        stmt.executeUpdate("ALTER SESSION set NLS_DATE_FORMAT = 'HH24:MI:SS'");
        deleteTable(stmt);
        createTable(stmt);
        insertTable(stmt);
        display(stmt);

        stmt.close();
        connexion.close();
    }
}