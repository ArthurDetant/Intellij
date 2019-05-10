import java.sql.*;

public class ex4 {
    private static void CalculVol(Statement stmt) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT * from vol WHERE Ville_depart='Paris'");
        String villeD, villeA;
        while (rs.next()){
            villeD="Paris";
            villeA= rs.getString(5);
            System.out.println(villeA);
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
        CalculVol(stmt);

        stmt.close();
        connexion.close();
    }
}
