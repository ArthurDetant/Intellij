import java.sql.*;
import java.util.Scanner;

public class ex1et2 {

    public static void main(String[] args) {
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


            System.out.println("Creating table in given database...");
            Statement stmt = connexion.createStatement();

            String drop = "DROP TABLE IF EXISTS Produit";
            stmt.executeUpdate(drop);

            String sql = "CREATE TABLE Produit " +
                    "(reference VARCHAR(255) not NULL, " +
                    " designation VARCHAR(255), " +
                    " quantite INTEGER, " +
                    " tva FLOAT, " +
                    " prix_ht FLOAT, " +
                    " PRIMARY KEY ( reference ))";

            stmt.executeUpdate(sql);

            Statement statement = connexion.createStatement();

            int statut = statement.executeUpdate("INSERT INTO Produit (reference, designation, quantite, tva, prix_ht) VALUES ('0001', 'PC MM MMX', 5, 19.6, 6500.00), ('0002', 'Imprimante HP jet', 20, 19.6, 3500.00),('0003', 'Ecran 17 ', 15, 19.6, 1200.00),('0004', 'CD*10', 100, 19.6, 99.00)");

            ResultSet resultat = statement.executeQuery( "SELECT * FROM Produit;" );

            System.out.println("reference designation quantite tva prix_ht");
            while ( resultat.next() ) {

                String reference = resultat.getString( "reference" );

                String designation = resultat.getString( "designation" );

                int quantite = resultat.getInt( "quantite" );

                float tva = resultat.getFloat( "tva" );

                float prix_ht = resultat.getFloat( "prix_ht" );

                System.out.println(reference+ " "+ designation+ " "+ quantite+ " "+ tva+ " "+ prix_ht);

            }

        }
        catch (SQLException e)
        {
            System.out.println("Connexion ratée");
        }
        finally
        {
            if (connexion != null) {
                try
                {
                    connexion.close();
                }
                catch (SQLException ignore)
                {
                    System.out.println("erreur fermeture");
                }
            }
        }



    }
}
