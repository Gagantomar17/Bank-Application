import java.sql.* ;

public class Connect {
    Connection c ;
    Statement s ;
    public Connect(){
        String url = "jdbc:mysql:///databaseName" ;
        String userName = "your_username" ;
        String password = "your_password" ;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //connection string
            c = DriverManager.getConnection( url ,userName , password);
            // create statement
            s = c.createStatement();
        } catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
        }
    }
}