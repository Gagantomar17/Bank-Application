import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.jar.JarFile;

public class Balance extends JFrame implements ActionListener {
    private JLabel text , accBalance;
    private String pinNumber , cardNumber ;
    private int balance ;
    private JButton back ;
    Balance(String pinNumber , String cardNumber){
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

        // to show the balance directly on screen
        Connect c = new Connect();
        try{
            ResultSet rs = c.s.executeQuery("SELECT balance FROM login WHERE cardNumber = '"+cardNumber+"' AND pin = '"+ pinNumber+"'  ");
            if(rs.next()){
                balance = rs.getInt("balance");
            }
        } catch (Exception e){
            System.out.println(e);
        }

        text = new JLabel("Your current account balance is ");
        text.setBounds(210 , 130 , 400 , 40);
        text.setFont(new Font("System" ,Font.BOLD , 17));
        text.setForeground(Color.WHITE);
        image.add(text);

        accBalance = new JLabel("Rs : "+balance);
        accBalance.setBounds(250 , 170 , 200 , 40);
        accBalance.setForeground(Color.WHITE);
        accBalance.setFont(new Font("System" ,Font.BOLD , 17));
        image.add(accBalance);

        back = new JButton(" Back ");
        back.setBounds(360 , 355 , 120 , 25);
        back.addActionListener(this);
        image.add(back);





        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        setVisible(false);
        new Transactions(pinNumber , cardNumber).setVisible(true);
    }

    public static void main(String[] args) {

        new Balance("" , "");
    }
}
