import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.sql.ResultSet;

public class FastCash extends JFrame implements ActionListener {

    private JLabel text ;
    private JButton a100 , a500 , a1000 , a2000 , a5000 , a10000 , back ;
    private String pinNumber , cardNumber ;
    private  int balance ;

    FastCash(String pinNumber , String cardNumber){
        this.pinNumber = pinNumber ;
        this.cardNumber = cardNumber ;
        setTitle("Banking Application - Home Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(450 , 50 );
        setSize(700, 700);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("image/atm.png"));
        Image img1 = i1.getImage();
        Image img2 = img1.getScaledInstance(700 , 700 , Image.SCALE_SMOOTH);
        ImageIcon i2 = new ImageIcon(img2);
        JLabel image = new JLabel(i2);
        image.setBounds(0,0,700,700);
        add(image);

        text = new JLabel("Select Withdrawl Amount");
        text.setBounds(260 , 130 , 200 , 40);
        text.setForeground(Color.WHITE);
        image.add(text);

        a100 = new JButton("Rs 100");
        a100.setBounds(220 , 230 , 120 , 25);
        a100.addActionListener(this);
        image.add(a100);

        a500 = new JButton("Rs 500");
        a500.setBounds(360 , 230 , 120 , 25);
        a500.addActionListener(this);
        image.add(a500);

        a1000 = new JButton("Rs 1000");
        a1000.setBounds(220 , 275 , 120 , 25);
        a1000.addActionListener(this);
        image.add(a1000);

        a2000 = new JButton("Rs 2000");
        a2000.setBounds(360 , 275 , 120 , 25);
        a2000.addActionListener(this);
        image.add(a2000);

        a5000 = new JButton("Rs 5000");
        a5000.setBounds(220 , 315 , 120 , 25);
        a5000.addActionListener(this);
        image.add(a5000);

        a10000 = new JButton("Rs 10000");
        a10000.setBounds(360 , 315 , 120 , 25);
        a10000.addActionListener(this);
        image.add(a10000);

        back = new JButton(" Back ");
        back.setBounds(220 , 355 , 120 , 25);
        back.addActionListener(this);
        image.add(back);

        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == back){
            setVisible(false);
            new Transactions(pinNumber , cardNumber);
        }else{
            String amount = ( (JButton)ae.getSource() ).getText().substring(3);
            Connect c = new Connect();
            try{
                // ResultSet is used to store the rows retrieved from the database using sql querry
                ResultSet rs = c.s.executeQuery("SELECT balance FROM login WHERE cardNumber = '"+cardNumber+"' AND pin = '"+ pinNumber+"'  ");
                if(rs.next()){
                    balance = rs.getInt("balance");
                }

                if(ae.getSource() != back && balance < Integer.parseInt(amount)){
                    JOptionPane.showMessageDialog(null , "Insufficient balance ");
                }else{
                    Date date = new Date();
                    String query = "INSERT INTO bank VALUES( '"+cardNumber+"' , '"+pinNumber+"' , '"+date+"' , 'Withdrawl' , '"+amount+"')";
                    c.s.executeUpdate(query);
                    int amountNum = Integer.parseInt(amount);
                    String query2 = "UPDATE login SET balance = balance - '"+amountNum+"' WHERE cardNumber = '"+cardNumber+"' AND pin = '"+pinNumber+"' ;";
                    c.s.executeUpdate(query2);
                    JOptionPane.showMessageDialog(null , "Rs "+amount+" withdrawl successfully");
                    setVisible(false);
                    new Transactions(pinNumber , cardNumber).setVisible(true);
                }

            } catch (Exception e){
                System.out.println(e);
            }

        }
    }

    public static void main(String[] args) {

        new FastCash("" , "");
    }
}

