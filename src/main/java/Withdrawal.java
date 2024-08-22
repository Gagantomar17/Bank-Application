import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;
import java.util.regex.Pattern;

public class  Withdrawal extends JFrame implements ActionListener {

    private JLabel text ;
    private JTextField amountText ;
    private JButton withdraw , back ;
    int balance ;
    private String pinNumber , cardNumber ;

    Withdrawal(String pinNumber , String cardNumber){
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

        text = new JLabel("Enter amount to withdraw");
        text.setBounds(240 , 130 , 400 , 40);
        text.setFont(new Font("Arial" , Font.PLAIN , 17));
        text.setForeground(Color.WHITE);
        image.add(text);

        amountText = new JTextField();
        amountText.setBounds(220 , 180 , 250 , 25);
        amountText.setForeground(Color.BLACK);
        amountText.setBackground(Color.WHITE);
        image.add(amountText);

        withdraw = new JButton(" Withdraw ");
        withdraw.setBounds(360 , 315 , 120 , 25);
        withdraw.addActionListener(this);
        image.add(withdraw);

        back = new JButton(" Back ");
        back.setBounds(360 , 355 , 120 , 25);
        back.addActionListener(this);
        image.add(back);


        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == withdraw){
            String amount = amountText.getText();
            if(amount.equals("") || !isValidAmount(amount)){
                JOptionPane.showMessageDialog(null , "Please enter a valid amount ");
            }else{
                Connect c = new Connect();
                try{
                    ResultSet rs = c.s.executeQuery("SELECT balance FROM login WHERE cardNumber = '"+cardNumber+"' AND pin = '"+ pinNumber+"'  ");
                    if(rs.next()){
                        balance = rs.getInt("balance");
                    }
                    if(balance < Integer.parseInt(amount)){
                        JOptionPane.showMessageDialog(null , "Insufficient balance ");

                    }else{
                        Date date = new Date();
                        String query1 = "INSERT INTO bank VALUES('"+cardNumber+"' , '"+pinNumber+"' , '"+date+"' , 'Withdrawl' , '"+amount+"')";
                        c.s.executeUpdate(query1);
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
        } else if(ae.getSource() == back){
            setVisible(false);
            new Transactions(pinNumber , cardNumber).setVisible(true);
        }
    }

    private boolean isValidAmount(String input) {
        // non-negative and non-zero number
        String regex = "^[1-9]\\d*$";
        return Pattern.matches(regex, input);
    }

    public static void main(String[] args) {

        new Withdrawal("" , "");
    }
}
