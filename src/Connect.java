import java.sql.* ;
public class connection {

    Connection c ;
    public connection(){
        try {
            Class.forName(com.mysql.cj.jdbc.Driver);
            //connection string
            c = DriverManager.getConnection("jdbc:mysql:///bankManagement","root" , "Gagan@123");
            // create statement
            s = c.createStatement();
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
