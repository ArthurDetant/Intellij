import java.sql.*;

public class Exo2 {

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

        ResultSet resultat = stmt.executeQuery( "SELECT * FROM personnes" );

        System.out.println("idpersonne nompersonne  prenompersonne  datenaisspersonne coeffpersonne");
        while ( resultat.next() ) {

            int idpersonne = resultat.getInt( "idpersonne" );

            String nompersonne = resultat.getString( "nompersonne" );

            String prenompersonne = resultat.getString( "prenompersonne" );

            String datenaisspersonne = resultat.getString( "datenaisspersonne" );

            int coeffpersonne = resultat.getInt( "coeffpersonne" );

            System.out.println(idpersonne+ " "+ nompersonne+ " "+ prenompersonne+ " "+ datenaisspersonne+ " "+ coeffpersonne);

        }

        stmt.close();
        connexion.close();
    }
}
