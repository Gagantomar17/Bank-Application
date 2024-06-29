import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Home extends JFrame implements ActionListener {
    private String pinNumber , cardNumber ;
    private JLabel profileImage , image , atmImage , name , fname  , accType , email , state  , heading  ;
    private JButton loan , account , deposits , sip , accTransfer , atm , exit ;

    Home(String pinNumber , String cardNumber){
        this.pinNumber = pinNumber ;
        this.cardNumber = cardNumber ;
        setTitle("Lena Dena Bank Pvt Ltd");
        setSize(1000 , 700);
        setLocation(250 , 50 );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);



        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("image/backbg.png"));
        Image img1 = i1.getImage();
        Image img2 = img1.getScaledInstance(1000 , 700 ,Image.SCALE_SMOOTH );
        ImageIcon i2 = new ImageIcon(img2);
        image = new JLabel(i2);
        image.setBounds(0 , 0,1000 , 700 );
        add(image);

        heading = new JLabel("Lena Dena Bank Pvt Ltd");
        heading.setBounds(350 , 40 , 500 , 60);
        heading.setFont(new Font("Aerial" , Font.BOLD  , 35));
        heading.setForeground(Color.WHITE);
        image.add(heading);

        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("image/profile.png"));
        Image img3 = i3.getImage();
        int w = 100 ;
        int h = 100 ;
        Image img4 = img3.getScaledInstance(w , h , Image.SCALE_SMOOTH);
        ImageIcon i4 = new ImageIcon(img4);
        profileImage = new JLabel(i4);
        profileImage.setBounds(70 , 175 , w , h);
        image.add(profileImage);

        ImageIcon i5 = new ImageIcon(ClassLoader.getSystemResource("image/atm01.jpg"));
        Image img5 = i5.getImage();
        Image img6 = img5.getScaledInstance(200 , 200 , Image.SCALE_SMOOTH);
        ImageIcon i6 = new ImageIcon(img6);
        atmImage = new JLabel(i6);
        atmImage.setBounds(700,100,300,350);
        image.add(atmImage);



        name = new JLabel();
        name.setBounds(40 , 300 , 300 , 30);
        name.setFont(new Font("Aerial" , Font.BOLD , 20));
        name.setForeground(Color.WHITE);
        image.add(name);

        fname = new JLabel();
        fname.setBounds(40 , 330 , 300 , 30);
        fname.setFont(new Font("Aerial" , Font.BOLD , 20));
        fname.setForeground(Color.WHITE);
        image.add(fname);

        email = new JLabel();
        email.setBounds(40 , 360 , 300 , 30);
        email.setFont(new Font("Aerial" , Font.BOLD , 20));
        email.setForeground(Color.WHITE);
        image.add(email);

        accType = new JLabel();
        accType.setBounds(40 , 390 , 350 , 30);
        accType.setFont(new Font("Aerial" , Font.BOLD , 20));
        accType.setForeground(Color.WHITE);
        image.add(accType);

        state = new JLabel();
        state.setBounds(40 , 420 , 300 , 30);
        state.setFont(new Font("Aerial" , Font.BOLD , 20));
        state.setForeground(Color.WHITE);
        image.add(state);

        account = new JButton("Accounts");
        account.setBounds(420 , 175 , 200 , 60);
        account.addActionListener(this);
        account.setForeground(Color.BLACK);
        account.setBackground(Color.YELLOW);
        image.add(account);

        loan = new JButton("Loan");
        loan.setBounds(420 , 300 , 200 , 60);
        loan.addActionListener(this);
        loan.setForeground(Color.BLACK);
        loan.setBackground(Color.YELLOW);
        image.add(loan);

        deposits = new JButton("Deposits");
        deposits.setBounds(420 , 420 , 200 , 60);
        deposits.addActionListener(this);
        deposits.setForeground(Color.BLACK);
        deposits.setBackground(Color.YELLOW);
        image.add(deposits);


        atm = new JButton(" USE ATM  ");
        atm.setBounds(750 , 420 , 200 , 60);
        atm.addActionListener(this);
        atm.setForeground(Color.BLACK);
        atm.setBackground(Color.YELLOW);
        image.add(atm);

        try{
            Connect c = new Connect() ;
            String query1 = "SELECT s1.name , s1.fathers_name , s1.email , s3.accountType , s1.state " +
                    "FROM login l " +
                    "JOIN signup s1 ON l.formno = s1.formno " +
                    "JOIN signuptwo s2 ON l.formno = s2.formno " +
                    "JOIN signupthree s3 ON l.formno = s3.formno " +
                    "WHERE l.cardNumber = '"+cardNumber+"' " ;

            ResultSet userData = c.s.executeQuery(query1);
            if(userData.next()){
                name.setText(" Name: " + userData.getString("name"));
                fname.setText(" Father's Name: " + userData.getString("fathers_name"));
                accType.setText(" Account Type: " + userData.getString("accountType"));
                email.setText(" Email: " + userData.getString("email"));
                state.setText(" State: " + userData.getString("state"));
            }
        }catch (Exception e){
            System.out.println(e);
        }

        exit = new JButton(" Exit  ");
        exit.setBounds(590 , 530 , 200 , 60);
        exit.addActionListener(this);
        exit.setForeground(Color.WHITE);
        exit.setBackground(Color.BLACK);
        image.add(exit);







        setUndecorated(true);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == account ){
            new Account(pinNumber , cardNumber);
            //setVisible(false);
            dispose();
        } else if(ae.getSource() == deposits){
            new Deposits(pinNumber , cardNumber);
            setVisible(false);
        } else if(ae.getSource() == loan){
            new loan_Rules(pinNumber , cardNumber);
            setVisible(false);
        } else if(ae.getSource() == atm){
            new Transactions(pinNumber , cardNumber);
            setVisible(false);
        }else if(ae.getSource() == exit){
            setVisible(false);

        }
    }

    public static void main(String[] args) {
        new Home("0057" , "1010101053744691");
    }
}
