import java.sql.*;
import java.util.Scanner;

public class Exo4 {

    public static void select(ResultSet resultat) throws SQLException
    {
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

        Class.forName("oracle.jdbc.driver.OracleDriver");

        String url = "jdbc:oracle:thin:\"adetant\"/13071996@192.168.22.60:1521/pdborcl2";

        Connection connexion = null;
        try {
            connexion = DriverManager.getConnection(url);
        } catch (Exception e) {
            System.out.println("pas de co");
        }
        if (connexion != null) {
            System.out.println("connecté!");
        }
        Statement stmt = connexion.createStatement();

        Scanner sc = new Scanner(System.in);
        System.out.println("Entrer requete :");
        String req =sc.nextLine();

        if(req.substring(0, 3).equals("SEL") ){
            ResultSet resultat = stmt.executeQuery(req);
            select(resultat);
        }
        if(req.substring(0, 3).equals("DEL")){
            int resultat = stmt.executeUpdate(req);
        }
        if(req.substring(0, 3).equals("UPD")){
            int resultat = stmt.executeUpdate(req);
        }
        stmt.close();
        connexion.close();
    }
}
