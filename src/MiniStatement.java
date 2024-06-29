import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.util.ArrayList ;

public class MiniStatement extends JFrame {

    private String pinNumber , nameVal ,formVal , cardVal  , cardNumber;
    private JLabel text , name  ,  card  , mini , acccBalance;
    private int balance ;

    MiniStatement(String pinNumber , String cardNumber){
        this.pinNumber = pinNumber ;
        this.cardNumber = cardNumber ;
        setTitle("Mini Statement");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(20 , 20 );
        setSize(400, 600);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);


        text = new JLabel("Lena Dena Bank Pvt Ltd");
        text.setBounds(80 , 10 , 400 , 40);
        text.setFont(new Font("System" ,Font.PLAIN , 15));
        text.setForeground(Color.BLACK);
        add(text);

        name = new JLabel();
        name.setBounds(30 , 70 , 400 , 40);
        name.setFont(new Font("System" ,Font.PLAIN , 15));
        name.setForeground(Color.BLACK);
        add(name);

        try{
            Connect c = new Connect();
            ResultSet rs1 = c.s.executeQuery("SELECT *FROM login WHERE pin = '"+pinNumber+"' ");
            while(rs1.next()){
                formVal = rs1.getString("formno");
            }
            ResultSet rs2 = c.s.executeQuery("SELECT *FROM signup WHERE formno = '"+formVal+"'");
            while(rs2.next()){
                nameVal = rs2.getString("name");
                name.setText("A/C Holder Name : "+nameVal);
            }

        }catch (Exception e){
            System.out.println(e);
        }


        card = new JLabel("Card Number : ");
        card.setBounds(30 , 100 , 400 , 40);
        card.setFont(new Font("System" ,Font.PLAIN , 15));
        card.setForeground(Color.BLACK);
        add(card);

        cardVal = cardNumber;
        card.setText("Card Number : "+cardVal.substring(0,4) +"XXXXXXXX" + cardVal.substring(12));

//        try{
//            Connect c = new Connect();
//            ResultSet rs = c.s.executeQuery("SELECT *FROM login WHERE pin = '"+pinNumber+"'");
//            while(rs.next()){
//                cardVal = rs.getString("cardNumber");
//                card.setText("Card Number : "+cardVal.substring(0,4) +"XXXXXXXX" + cardVal.substring(12));
//            }
//        } catch (Exception e){
//            System.out.println(e);
//        }

        mini = new JLabel("tHIS IS MINI STATEMENT");
        mini.setBounds(20 , 140 , 400 , 400);
        mini.setFont(new Font("System" ,Font.PLAIN , 15));
        mini.setForeground(Color.BLACK);
        add(mini);

        try{
            Connect c = new Connect();
            ResultSet rs = c.s.executeQuery("SELECT *FROM bank WHERE cardNumber = '"+cardNumber+"' AND pin = '"+pinNumber+"'");
            ArrayList<String> List = new ArrayList<>();
            while(rs.next()) {
                String data = rs.getString("date")+ "&nbsp; &nbsp; &nbsp; " + rs.getString("type")+ "&nbsp; &nbsp; &nbsp; &nbsp;" +rs.getString("amount") +"<br>";
                List.add(data);
            }

// Reverse order printing
            StringBuilder miniSt = new StringBuilder();
            miniSt.append("<html>");
            int n = List.size();
            for (int i = n - 1; i >= 0 && i >= n - 8; i--) {
                miniSt.append(List.get(i));
            }
            miniSt.append("</html>");
            mini.setText(miniSt.toString());

//            int n = List.size();
//            for (int i = n - 1; i >= n - 8 ; i--) {
//                mini.setText(mini.getText() + List.get(i));
//            }

        } catch (Exception e){
            System.out.println(e);
        }

        acccBalance = new JLabel();
        acccBalance.setBounds(70 , 500 , 250 , 40);
        acccBalance.setFont(new Font("System" ,Font.PLAIN , 15));
        acccBalance.setForeground(Color.BLACK);
        add(acccBalance);

        try{
            Connect c = new Connect();
            ResultSet rs = c.s.executeQuery("SELECT balance FROM login WHERE cardNumber = '"+cardNumber+"' AND pin = '"+pinNumber+"'");
            if(rs.next()){
                balance = rs.getInt("balance");
            }
            String balanceNum = Integer.toString(balance);
            acccBalance.setText("Your account balance is "+ balanceNum);
        } catch (Exception e){
            System.out.println(e);
        }

        setVisible(true);
    }

    public static void main(String[] args) {

        new MiniStatement("" , "");
    }
}
