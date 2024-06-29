import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankingApp extends JFrame implements ActionListener {

    JButton login , signUp ;
    JLabel heading , name , password , image ;
    JTextField userName , userPass ;

    public BankingApp(){
        setTitle("Lena Dena Bank Pvt Ltd");
        setSize(1000 , 700);
        setLocation(250 , 50 );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);



        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("image/LoginImage.jpg"));
        Image img1 = i1.getImage();
        int w = 450;
        int h = 500;
        Image img2 = img1.getScaledInstance(w , h ,Image.SCALE_SMOOTH );
        ImageIcon i2 = new ImageIcon(img2);

        image = new JLabel(i2);
        image.setBounds(50 , 50 ,w , h );
        add(image);

        heading = new JLabel("Welcome To Lena Dena Bank");
        heading.setBounds(600 , 60 , 400 , 20);
        heading.setFont(new Font("Aerial" , Font.BOLD , 20));
        heading.setForeground(Color.BLACK);
        add(heading);

        name = new JLabel("Enter Your User ID");
        name.setBounds(650 , 180 , 250 , 20);
        name.setFont(new Font("Aerial" , Font.BOLD , 18));
        name.setForeground(Color.BLACK);
        add(name);

        userName = new JTextField();
        userName.setBounds(600 , 210 , 300 , 30);
        userName.setFont(new Font("Aerial" , Font.BOLD , 20));
        add(userName);

        password = new JLabel("Enter Your Password");
        password.setBounds(650 , 280 , 300 , 20);
        password.setFont(new Font("Aerial" , Font.BOLD , 18));
        password.setForeground(Color.BLACK);
        add(password);

        userPass = new JTextField();
        userPass.setBounds(600 , 310 , 300 , 30);
        userPass.setFont(new Font("Aerial" , Font.BOLD , 20));
        add(userPass);

        login = new JButton("Login");
        login.setBounds(660 , 380 , 100 , 25);
        login.addActionListener(this);
        add(login);

        signUp = new JButton("Sign Up");
        signUp.setBounds(770 , 380 , 100 , 25);
        signUp.addActionListener(this);
        add(signUp);

        setVisible(true);


    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==login){
            setVisible(false);
            new Home();
        }else if(ae.getSource()==signUp){
            setVisible(false);
            new SignUpOne();
        }
    }

    public static void main(String[] args) {
        BankingApp loginFrame = new BankingApp();
    }


    
}
