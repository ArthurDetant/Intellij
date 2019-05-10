import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Exo1 {
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
        stmt.executeUpdate("ALTER SESSION set NLS_DATE_FORMAT = 'YYYY-MM-DD'");
        stmt.executeUpdate("INSERT INTO personnes"
                + "( idpersonne,nompersonne,prenompersonne,datenaisspersonne,coeffpersonne )"
                + "VALUES"
                + "(6,'nom6','prenom6','1996-03-02',50) "
        );
        stmt.executeUpdate("INSERT INTO personnes"
                + "( idpersonne,nompersonne,prenompersonne,datenaisspersonne,coeffpersonne )"
                + "VALUES"
                + "(7,'nom7','prenom7','1996-07-13',100) "
        );
        stmt.executeUpdate("INSERT INTO grppers"
                + "( idgrppers,idgroupe,idpersonne )"
                + "VALUES"
                + "(6,1,6) "
        );
        stmt.executeUpdate("INSERT INTO grppers"
                + "( idgrppers,idgroupe,idpersonne )"
                + "VALUES"
                + "(7,1,7) "
        );

        stmt.close();
        connexion.close();
    }
}
