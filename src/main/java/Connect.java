import java.sql.* ;

public class Connect {
    Connection c ;
    Statement s ;
    public Connect(){
        String url = "jdbc:mysql:///your databse" ;
        String userName = "your username" ;
        String password = "your password" ;

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