import java.sql.* ;
public class  Connect {

    Connection c ;
    Statement s ;
    public Connect(){
        String url = "jdbc:mysql:///bankManagement" ;
        String userName = "root" ;
        String password = "Gagan@123" ;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //connection string
            c = DriverManager.getConnection( url ,userName , password);
            // create statement
            s = c.createStatement();
        } catch (Exception e){ 
            System.out.println(e);
        }
    }
}
