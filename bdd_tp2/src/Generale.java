import java.sql.*;
import java.util.Scanner;

public class Generale {
    private static void add(Connection connexion) throws SQLException {

        PreparedStatement ps = connexion.prepareStatement("INSERT INTO personnes "
               + "( idpersonne,nompersonne,prenompersonne,datenaisspersonne,coeffpersonne )"
               + "VALUES"
               + "(?,?,?,?,?)");


        Scanner sc = new Scanner(System.in);


        System.out.println("Entrer nom personne :");
        String nomPersonne = sc.nextLine();
        ps.setString(2, nomPersonne);


        System.out.println("Entrer prenom personne :");
        String prenomPersonne = sc.nextLine();
        ps.setString(3, prenomPersonne);


        System.out.println("Entrer date personne :");
        String datePersonne = sc.nextLine();
        ps.setString(4, datePersonne);


        System.out.println("Entrer coef personne :");
        String coeffPersonne = sc.nextLine();
        ps.setString(5, coeffPersonne);

        System.out.println("Entrer ID personne :");
        int idpersonne = sc.nextInt();
        ps.setInt(1, idpersonne);



        ps.executeUpdate();

    }

    private static void displayAll(Connection connexion) throws SQLException{


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
    }
    private static void displayGroup(Connection connexion) throws SQLException{

        Statement stmt = connexion.createStatement();

        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir un groupe: ");
        String groupe = sc.nextLine();

        PreparedStatement prep1 = connexion.prepareStatement("SELECT personnes.*"
                + " FROM personnes"
                + " INNER JOIN grppers ON personnes.idpersonne = grppers.idpersonne"
                + " INNER JOIN groupes ON groupes.idgroupe = grppers.idgroupe"
                + " WHERE  groupes.idgroupe = ?");
        prep1.setString(1,groupe);
        ResultSet resultat = prep1.executeQuery();

        System.out.println("idpersonne nompersonne  prenompersonne  datenaisspersonne coeffpersonne");
        while ( resultat.next() ) {

            int idpersonne = resultat.getInt( "idpersonne" );

            String nompersonne = resultat.getString( "nompersonne" );

            String prenompersonne = resultat.getString( "prenompersonne" );

            String datenaisspersonne = resultat.getString( "datenaisspersonne" );

            int coeffpersonne = resultat.getInt( "coeffpersonne" );

            System.out.println(idpersonne+ " "+ nompersonne+ " "+ prenompersonne+ " "+ datenaisspersonne+ " "+ coeffpersonne);

        }

    }

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

    private static void requete(Connection connexion) throws SQLException{

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
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

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

        Scanner sc = new Scanner(System.in);
        System.out.println("Entrer '1' pour ajouter une personne");
        System.out.println("Entrer '2' pour afficher toutes les personnes");
        System.out.println("Entrer '3' pour afficher les membres d'un groupe");
        System.out.println("Entrer '4' pour faire une requete");
        System.out.println("Entrer '5' pour quitter");


        String q = sc.nextLine();

        if(q.equals("1")){
            add(connexion);
        }
        if(q.equals("2")){
            displayAll(connexion);
        }
        if(q.equals("3")){
            displayGroup(connexion);
        }
        if(q.equals("4")){
            requete(connexion);
        }
    }

}