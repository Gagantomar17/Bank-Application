import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

    private JButton login , signUp , clear ;
    private JLabel heading , name , password , image , logoImage ;
    private JTextField cardNum , pinNum ;

    public Login(){
        setTitle("Lena Dena Bank Pvt Ltd");
        setSize(700 , 400);
        setLocation(350 , 100 );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);



        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("image/backbg.png"));
        Image img1 = i1.getImage();
        Image img2 = img1.getScaledInstance(700 , 400 ,Image.SCALE_SMOOTH );
        ImageIcon i2 = new ImageIcon(img2);

        image = new JLabel(i2);
        image.setBounds(0 , 0,700 , 400 );
        add(image);

        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("image/card.png"));
        Image img3 = i3.getImage();
        Image img4 = img3.getScaledInstance(50 , 50 , Image.SCALE_SMOOTH);
        ImageIcon i4 = new ImageIcon(img4);

        logoImage = new JLabel(i4);
        logoImage.setBounds(10 , 10 , 50 , 50);
        image.add(logoImage);

        heading = new JLabel("Lena Dena Bank Pvt Ltd");
        heading.setBounds(200 , 40 , 500 , 40);
        heading.setFont(new Font("Aerial" , Font.BOLD , 25));
        heading.setForeground(Color.WHITE);
        image.add(heading);

        name = new JLabel("Card Number");
        name.setBounds(100 , 115 , 250 , 30);
        name.setFont(new Font("Aerial" , Font.BOLD , 20));
        name.setForeground(Color.WHITE);
        image.add(name);

        cardNum = new JTextField();
        cardNum.setBounds(250 , 115 , 300 , 30);
        cardNum.setFont(new Font("Aerial" , Font.BOLD , 20));
        image.add(cardNum);

        password = new JLabel("Pin Number");
        password.setBounds(100 , 175 , 300 , 30);
        password.setFont(new Font("Aerial" , Font.BOLD , 20));
        password.setForeground(Color.WHITE);
        image.add(password);

        pinNum = new JTextField();
        pinNum.setBounds(250 , 175 , 300 , 30);
        pinNum.setFont(new Font("Aerial" , Font.BOLD , 20));
        image.add(pinNum);

        login = new JButton("Login");
        login.setBounds(200 , 250 , 100 , 30);
        login.addActionListener(this);
        login.setForeground(Color.BLACK);
        login.setBackground(Color.YELLOW);
        image.add(login);

        clear = new JButton("Clear");
        clear.setBounds(340 , 250 , 100 , 30);
        clear.addActionListener(this);
        clear.setForeground(Color.BLACK);
        clear.setBackground(Color.YELLOW);
        image.add(clear);

        signUp = new JButton("Sign Up");
        signUp.setBounds(200 , 300 , 240 , 30);
        signUp.addActionListener(this);
        signUp.setForeground(Color.BLACK);
        signUp.setBackground(Color.YELLOW);
        image.add(signUp);

        setVisible(true);


    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==login){
            Connect c = new Connect();
            String cardNumber = cardNum.getText();
            String pinNumber = pinNum.getText();
            String query = ("SELECT *FROM login where cardNumber = '"+cardNumber+"' and pin = '"+pinNumber+"' ");
            try{
                ResultSet rs =  c.s.executeQuery(query);
                if(rs.next()){
                    new Home(pinNumber , cardNumber);
                    setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(null , "Incorrect card no or pin ");
                }
            } catch (Exception e){
                System.out.println(e);
            }
        }else if(ae.getSource()==signUp){
            new SignUpOne();
            setVisible(false);
        }else if(ae.getSource() == clear){
            cardNum.setText("");
            pinNum.setText("");
            return;
        }
    }

    public static void main(String[] args) {

        Login loginFrame = new Login();
    }


    
}
