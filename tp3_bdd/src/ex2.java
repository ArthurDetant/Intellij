import java.sql.*;
import java.time.LocalTime;


public class ex2 {

    private static void AjouterRetour(Connection connexion) throws SQLException
    {
       Statement recupvol = connexion.createStatement();
       ResultSet rs = recupvol.executeQuery("SELECT * from vol");

       PreparedStatement insererVolRetour = connexion.prepareStatement("INSERT INTO vol VALUES (?,?,?,?,?)");
       while (rs.next()){
           insererVolRetour.setString(1, rs.getString("NumVol")+ "R");
           insererVolRetour.setString(4, rs.getString("Ville_arrivee"));
           insererVolRetour.setString(5, rs.getString("Ville_depart"));

           LocalTime heure_depart = rs.getTime("Heure_depart").toLocalTime();
           LocalTime heure_arrivee = rs.getTime("Heure_arrive").toLocalTime();
           LocalTime heure_depart_retour = heure_arrivee.plusHours(2);
           LocalTime heure_arrivee_retour = heure_depart_retour.plusHours(heure_arrivee.getHour()-heure_depart.getHour());
           heure_arrivee_retour = heure_arrivee_retour.plusMinutes(heure_arrivee.getMinute() - heure_depart.getMinute());
           insererVolRetour.setTime(2, Time.valueOf(heure_depart_retour));
           insererVolRetour.setTime(3, Time.valueOf(heure_arrivee_retour));
           insererVolRetour.executeUpdate();
       }
       rs.close();
       recupvol.close();
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
        AjouterRetour(connexion);
        display(stmt);

        stmt.close();
        connexion.close();
    }
}