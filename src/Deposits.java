import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class Deposits extends JFrame implements ActionListener {

    private String pinNumber , cardNumber , amount ;
    private JLabel image , pAmount , tenureLabel , interestLabel , interestAmount  ;
    private JTextField amountText  ;
    private JPanel fd ;
    private JButton submit , calculate , exit ;
    private JComboBox<String> tenureText;
    private double interestRate , interest , totalAmount ;
    private int selectedTenure , enteredAmount , balance;

    Deposits(String pinNumber , String cardNumber){
        this.pinNumber = pinNumber ;
        this.cardNumber = cardNumber ;

        setTitle("Lena Dena Bank Pvt Ltd");
        setSize(1000 , 700);
        setLocation(250 , 50 );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(32, 107, 150));
        setLayout(null);

        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("image/backbg.png"));
        Image img3 = i3.getImage();
        Image img4 = img3.getScaledInstance(1000 , 700 ,Image.SCALE_SMOOTH );
        ImageIcon i4 = new ImageIcon(img4);

        image = new JLabel(i4);
        image.setBounds(0 , 0,1000 , 700 );
        add(image);

        fd = new JPanel();
        fd.setLayout(null);
        fd.setBackground(Color.WHITE);
        image.add(fd);

        pAmount = new JLabel("Enter the principal amount :");
        pAmount.setBounds(20 , 40 , 250 , 40);
        pAmount.setFont(new Font("System" ,Font.PLAIN , 18));
        pAmount.setForeground(Color.BLACK);
        fd.add(pAmount);


        amountText = new JTextField();
        amountText.setBounds(280 , 40 , 200 , 30);
        fd.add(amountText);

        tenureLabel = new JLabel("Select Time Period");
        tenureLabel.setBounds(20 , 100 , 200 , 50);
        tenureLabel.setFont(new Font("",Font.PLAIN , 20));
        tenureLabel.setForeground(Color.BLACK);
        fd.add(tenureLabel);

        String[] tenureOptions = {"1 year", "2 years", "3 years", "4 years", "5 years" , "10 years"};
        tenureText = new JComboBox<>(tenureOptions);
        tenureText.setBounds(280, 100, 100, 30);
        tenureText.setFont(new Font("", Font.PLAIN, 18));
        fd.add(tenureText);

        interestLabel = new JLabel("Interest Rate : 0 %");
        interestLabel.setBounds(20 , 150, 250, 40);
        interestLabel.setFont(new Font("", Font.PLAIN, 20));
        interestLabel.setForeground(Color.BLACK);
        fd.add(interestLabel);

        interestAmount = new JLabel("Interest Amount : 0 ");
        interestAmount.setBounds(20, 200, 250, 40);
        interestAmount.setFont(new Font("", Font.PLAIN, 20));
        interestAmount.setForeground(Color.BLACK);
        fd.add(interestAmount);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(270, 100, 550, 500);
        tabbedPane.addTab("fixed Deposits", fd);
        image.add(tabbedPane);

        calculate = new JButton("Calculate");
        calculate.setBounds(260 , 330 , 140 , 40);
        calculate.addActionListener(this);
        calculate.setForeground(Color.WHITE);
        calculate.setBackground(Color.BLACK);
        fd.add(calculate);


        submit = new JButton("Submit");
        submit.setBounds(110 , 330 , 140 , 40);
        submit.addActionListener(this);
        submit.setForeground(Color.WHITE);
        submit.setEnabled(false);
        submit.setBackground(Color.BLACK);
        fd.add(submit);


        exit = new JButton("Back");
        exit.setBounds(120 , 390 , 250 , 40);
        exit.addActionListener(this);
        exit.setForeground(Color.WHITE);
        exit.setBackground(Color.BLACK);
        fd.add(exit);


        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == exit){
            new Home(pinNumber , cardNumber);
            setVisible(false);
        }else if(ae.getSource() == calculate){

            amount = amountText.getText();
            enteredAmount = Integer.parseInt(amount);
            String num2 = ((String) tenureText.getSelectedItem()).split(" ")[0];
            selectedTenure = Integer.parseInt(num2);
            if(amount.equals("") || num2.equals("") ){
                JOptionPane.showMessageDialog(null , "All fields are requires");

            }else{
                if(selectedTenure == 1){
                    interestRate = 5 ;
                }else if(selectedTenure == 2){
                    interestRate = 5.5 ;
                }else if (selectedTenure == 3){
                    interestRate = 6 ;
                }else if(selectedTenure == 4){
                    interestRate = 6.5 ;
                }else if(selectedTenure == 5){
                    interestRate = 7 ;
                }else if(selectedTenure == 10){
                    interestRate = 10 ;
                }

                interestLabel.setText("Interest Rate: " + interestRate + " %");
                interest = calculateInterest(enteredAmount, selectedTenure , interestRate);
                interestAmount.setText("Interest amount : " + interest );
                totalAmount = enteredAmount + interest ;

                submit.setEnabled(true);
            }

        } else if (ae.getSource() == submit) {
            try{
                Connect c = new Connect() ;

                ResultSet rs = c.s.executeQuery("SELECT balance FROM login WHERE cardNumber = '"+cardNumber+"' AND pin = '"+ pinNumber+"'  ");
                if(rs.next()){
                    balance = rs.getInt("balance");
                }

                if(balance < enteredAmount){
                    JOptionPane.showMessageDialog(null , "Insufficient balance ");
                }else{
                    Date date = new Date();
                    String query1 = "INSERT INTO bank VALUES('"+cardNumber+"' , '"+pinNumber+"' , '"+date+"' , 'invested' , '"+amount+"')";
                    c.s.executeUpdate(query1);

                    String query2 = "INSERT INTO deposits VALUES ('"+cardNumber+"' , "+enteredAmount+" , "+interestRate+" , "+selectedTenure+" , "+totalAmount+" )" ;
                    c.s.executeUpdate(query2);
                    JOptionPane.showMessageDialog(null , "Amount Invested Successfully");

                    double amountNum = Integer.parseInt(amount) ;
                    String query3 = "UPDATE login SET balance = balance - '"+ amountNum + "' WHERE cardNumber = '"+cardNumber+"' AND pin = '"+pinNumber+"' ;";
                    c.s.executeUpdate(query3);


                }

                String query = "INSERT INTO deposits VALUES ('"+cardNumber+"' , "+enteredAmount+" , "+interestRate+" , "+selectedTenure+" , "+totalAmount+" )" ;
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null , "Amount Invested Successfully");

            }catch (Exception e){
                System.out.println(e);
            }
            new Home(pinNumber , cardNumber);
            setVisible(false);
        }
    }

    public double calculateInterest(int amount , int tenure , double interestRAte){
        double rate = interestRAte / 100.0 ;
        double interestAmount = amount * rate * tenure ;
        return interestAmount ;

    }

    public static void main(String[] args) {
        new Deposits("1020","1010101083471065");
    }


}
