import java.sql.*;

public class Build {

    private static void createTable(Statement stmt) throws SQLException
    {
            stmt.executeUpdate("CREATE TABLE groupes"+
                    "(idgroupe NUMBER(4) NOT NULL,"+
                    "nomgroupe varchar(50) default NULL,"+
                    "commentairegroupe varchar(150) default NULL,"+
                    "CONSTRAINT PK_grp PRIMARY KEY (idgroupe))");

            stmt.executeUpdate("CREATE TABLE personnes"
                    + "( idpersonne NUMBER(11) NOT NULL,"
                    + "nompersonne VARCHAR(50) default NULL,"
                    + "prenompersonne VARCHAR(50) default NULL,"
                    + "datenaisspersonne DATE default NULL, "
                    + "coeffpersonne NUMBER(11) default NULL, "
                    + "CONSTRAINT PK_pers PRIMARY KEY (idpersonne))");

            stmt.executeUpdate("CREATE TABLE grppers"
                    + "( idgrppers NUMBER(11) NOT NULL,"
                    + "idgroupe NUMBER(4) default NULL,"
                    + "idpersonne NUMBER(11) default NULL,"
                    +"CONSTRAINT PK_grppers PRIMARY KEY (idgrppers),"
                    +"CONSTRAINT fk_pers FOREIGN KEY (idpersonne) REFERENCES personnes(idpersonne),"
                    +"CONSTRAINT fk_grp FOREIGN KEY (idgroupe) REFERENCES groupes(idgroupe))");
    }

    private static void deleteTables(Statement stmt) throws SQLException {
            stmt.executeUpdate("DROP TABLE grppers");
            stmt.executeUpdate("DROP TABLE groupes");
            stmt.executeUpdate("DROP TABLE personnes");
    }

    private static void insertTable(Statement stmt) throws SQLException
    {

		stmt.executeUpdate("INSERT INTO groupes"
				+ "( idgroupe , nomgroupe , commentairegroupe )"
				+ "VALUES"
				+ "(1,'groupe 1',NULL)"
		);

		stmt.executeUpdate("INSERT INTO groupes"
				+ "( idgroupe , nomgroupe , commentairegroupe )"
				+ "VALUES"
				+ "(2,'groupe 2', NULL) "
		);

        stmt.executeUpdate("ALTER SESSION set NLS_DATE_FORMAT = 'YYYY-MM-DD'");

		stmt.executeUpdate("INSERT INTO personnes"
				+ "( idpersonne,nompersonne,prenompersonne,datenaisspersonne,coeffpersonne )"
				+ "VALUES"
				+ "(1,'nom1','prenom1','1967-01-06',123) "
		);
		stmt.executeUpdate("INSERT INTO personnes"
				+ "( idpersonne,nompersonne,prenompersonne,datenaisspersonne,coeffpersonne )"
				+ "VALUES"
				+ "(2,'nom2','prenom2','1973-08-11',34) "
		);
		stmt.executeUpdate("INSERT INTO personnes"
				+ "( idpersonne,nompersonne,prenompersonne,datenaisspersonne,coeffpersonne )"
				+ "VALUES"
				+ "(3,'nom3','prenom3','1956-04-28',145) "
		);
		stmt.executeUpdate("INSERT INTO personnes"
				+ "( idpersonne,nompersonne,prenompersonne,datenaisspersonne,coeffpersonne )"
				+ "VALUES"
				+ "(4,'nom4','prenom4','1980-12-02',23) "
		);
		stmt.executeUpdate("INSERT INTO personnes"
				+ "( idpersonne,nompersonne,prenompersonne,datenaisspersonne,coeffpersonne )"
				+ "VALUES"
				+ "(5,'nom5','prenom5','1966-10-13',119) "
		);

		stmt.executeUpdate("INSERT INTO grppers"
				+ "( idgrppers,idgroupe,idpersonne )"
				+ "VALUES"
				+ "(1,1,1) "
		);

		stmt.executeUpdate("INSERT INTO grppers"
				+ "( idgrppers,idgroupe,idpersonne )"
				+ "VALUES"
				+ "(2,2,2) "
		);
		stmt.executeUpdate("INSERT INTO grppers"
				+ "( idgrppers,idgroupe,idpersonne )"
				+ "VALUES"
				+ "(3,2,3) "
		);
		stmt.executeUpdate("INSERT INTO grppers"
				+ "( idgrppers,idgroupe,idpersonne )"
				+ "VALUES"
				+ "(4,1,4) "
		);
		stmt.executeUpdate("INSERT INTO grppers"
				+ "( idgrppers,idgroupe,idpersonne )"
				+ "VALUES"
				+ "(5,1,5) "
		);
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

        deleteTables(stmt);
        createTable(stmt);
        insertTable(stmt);

		stmt.close();
		connexion.close();
    }


}
