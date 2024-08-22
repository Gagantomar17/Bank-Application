import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Transactions extends JFrame implements ActionListener {

    private JLabel text ;
    private JButton deposit , withdrawl , fastCash , miniStatement , pinChange , balance , exit ;
    private String pinNumber , cardNumber ;

    Transactions(String pinNumber , String cardNumber){
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

        text = new JLabel("Please select your transaction");
        text.setBounds(260 , 130 , 200 , 40);
        text.setForeground(Color.WHITE);
        image.add(text);

        deposit = new JButton(" Deposit ");
        deposit.setBounds(220 , 230 , 120 , 25);
        deposit.addActionListener(this);
        image.add(deposit);

        withdrawl = new JButton("Cash Withdrawal");
        withdrawl.setBounds(360 , 230 , 120 , 25);
        withdrawl.addActionListener(this);
        image.add(withdrawl);

        fastCash = new JButton(" Fast Cash ");
        fastCash.setBounds(220 , 275 , 120 , 25);
        fastCash.addActionListener(this);
        image.add(fastCash);

        miniStatement = new JButton("Mini Statement");
        miniStatement.setBounds(360 , 275 , 120 , 25);
        miniStatement.addActionListener(this);
        image.add(miniStatement);

        pinChange = new JButton("Pin Change");
        pinChange.setBounds(220 , 315 , 120 , 25);
        pinChange.addActionListener(this);
        image.add(pinChange);

        balance = new JButton(" Balance ");
        balance.setBounds(360 , 315 , 120 , 25);
        balance.addActionListener(this);
        image.add(balance);

        exit = new JButton(" Exit ");
        exit.setBounds(220 , 355 , 120 , 25);
        exit.addActionListener(this);
        image.add(exit);

        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == exit){
            new Home(pinNumber , cardNumber);
            setVisible(false);
        }else if(ae.getSource() == deposit){
            new deposit(pinNumber , cardNumber);
            setVisible(false);
        }else if(ae.getSource() == withdrawl){
            new Withdrawal(pinNumber , cardNumber);
            setVisible(false);
        }else if(ae.getSource() == fastCash){
            new FastCash(pinNumber , cardNumber);
            setVisible(false);
        }else if(ae.getSource()== pinChange){
            new PinChange(pinNumber , cardNumber);
            setVisible(false);
        }else if(ae.getSource() == balance){
            new Balance(pinNumber , cardNumber);
            setVisible(false);
        }else if(ae.getSource() == miniStatement){
            new MiniStatement(pinNumber , cardNumber);
            new Transactions(pinNumber , cardNumber);
            setVisible(false);
        }
    }

    public static void main(String[] args) {

        new Transactions("" , "");
    }
}
