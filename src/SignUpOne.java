import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUp extends JFrame implements ActionListener {

    private JPanel perDetails , addDetails , accDetails ;
    public SignUp(){
        setTitle("Lena Dena Bank Pvt Ltd");
        setSize(1000 , 700);
        setLocation(250 , 50 );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        perDetails();
        addDetails();
        accDetails();

        add(perDetails);
        add(addDetails);
        add(accDetails);



        setVisible(true);
    }

    private void perDetails(){
        perDetails = new JPanel();
        perDetails.setLayout(new GridLayout(5, 2));
        perDetails.setBackground(Color.WHITE);

        perDetails.add(new JLabel("Address:"));
        perDetails.add(new JTextField());

        perDetails.add(new JLabel("Email:"));
        perDetails.add(new JTextField());

        perDetails.add(new JLabel("Phone Number:"));
        perDetails.add(new JTextField());

        perDetails.add(new JLabel("Nationality:"));
        perDetails.add(new JTextField());

        perDetails.add(new JButton("Next"));
    }
    private void addDetails(){
        addDetails = new JPanel();
        addDetails.setLayout(new GridLayout(5, 2));
        addDetails.setBackground(Color.WHITE);

        addDetails.add(new JLabel("Address:"));
        addDetails.add(new JTextField());

        addDetails.add(new JLabel("Email:"));
        addDetails.add(new JTextField());

        addDetails.add(new JLabel("Phone Number:"));
        addDetails.add(new JTextField());

        addDetails.add(new JLabel("Nationality:"));
        addDetails.add(new JTextField());

        addDetails.add(new JButton("Next"));
    }
    private void accDetails(){
        accDetails = new JPanel() ;
        accDetails = new JPanel();
        accDetails.setLayout(new GridLayout(4, 2));
        accDetails.setBackground(Color.WHITE);

        accDetails.add(new JLabel("Account Type:"));
        accDetails.add(new JTextField());

        accDetails.add(new JLabel("Initial Deposit:"));
        accDetails.add(new JTextField());

        accDetails.add(new JLabel("Username:"));
        accDetails.add(new JTextField());

        accDetails.add(new JLabel("Password:"));
        accDetails.add(new JPasswordField());

        accDetails.add(new JButton("Submit"));
    }

    public void actionPerformed(ActionEvent ae){

    }

    public static void main(String[] args) {

    }
}
