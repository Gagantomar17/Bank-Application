import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Loan extends JFrame implements ActionListener {

    private String pinNumber , cardNumber , loanTypeText , formno , name ;
    private JLabel logoImage , image , heading2 , heading , type , amount , tenureLabel , interestLabel , interestAmount;
    private JTextField  amountText  ;
    private JButton exit , submit , calculate;
    private JComboBox<String>  tenureText ;
    private JRadioButton personalLoan , homeLoan , carLoan , educationLoan ;
    private ButtonGroup loanTypeGroup ;
    private double interestRate , interest , totalAmount ;
    private int selectedTenure , enteredAmount ;

    Loan(String pinNumber , String cardNumber){
        this.pinNumber = pinNumber ;
        this.cardNumber = cardNumber ;
        setTitle("Lena Dena Bank Pvt Ltd");
        setSize(1000 , 700);
        setLocation(250 , 50 );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(32, 107, 150));
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("image/backbg.png"));
        Image img1 = i1.getImage();
        Image img2 = img1.getScaledInstance(1000 , 700 ,Image.SCALE_SMOOTH );
        ImageIcon i2 = new ImageIcon(img2);
        image = new JLabel(i2);
        image.setBounds(0 , 0,1000 , 700 );
        add(image);

        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("image/logo.jpg"));
        Image img3 = i3.getImage();
        Image img4 = img3.getScaledInstance(65, 65, Image.SCALE_SMOOTH);
        ImageIcon i4 = new ImageIcon(img4);

        logoImage = new JLabel(i4);
        logoImage.setBounds(20, 20, 65, 65);
        image.add(logoImage);

        heading = new JLabel("Lena Dena Bank Pvt Ltd");
        heading.setBounds(280, 20, 500, 50);
        heading.setFont(new Font("Aerial", Font.BOLD, 40));
        heading.setForeground(Color.WHITE);
        image.add(heading);

        heading2 = new JLabel(" Loan Application ");
        heading2.setBounds(400 , 70 , 450 , 50);
        heading2.setFont(new Font("",Font.BOLD , 25));
        heading2.setForeground(Color.BLACK);
        image.add(heading2);

        type = new JLabel(" Select Type  ");
        type.setBounds(100 , 150 , 450 , 40);
        type.setFont(new Font("",Font.BOLD , 20));
        type.setForeground(Color.BLACK);
        image.add(type);

        // Loan type radio buttons
        personalLoan = new JRadioButton("Personal Loan");
        personalLoan.setBounds(100, 200, 200, 30);
        personalLoan.setFont(new Font("", Font.PLAIN, 15));
        personalLoan.setForeground(Color.BLACK);
        image.add(personalLoan);

        homeLoan = new JRadioButton("Home Loan");
        homeLoan.setBounds(100, 250, 200, 30);
        homeLoan.setFont(new Font("", Font.PLAIN, 15));
        homeLoan.setForeground(Color.BLACK);
        image.add(homeLoan);

        carLoan = new JRadioButton("Car Loan");
        carLoan.setBounds(350, 200, 200, 30);
        carLoan.setFont(new Font("", Font.PLAIN, 15));
        carLoan.setForeground(Color.BLACK);
        image.add(carLoan);

        educationLoan = new JRadioButton("Education Loan");
        educationLoan.setBounds(350, 250, 200, 30);
        educationLoan.setFont(new Font("", Font.PLAIN, 15));
        educationLoan.setForeground(Color.BLACK);
        image.add(educationLoan);

        // Grouping radio buttons to ensure only one can be selected
        loanTypeGroup = new ButtonGroup();
        loanTypeGroup.add(personalLoan);
        loanTypeGroup.add(homeLoan);
        loanTypeGroup.add(carLoan);
        loanTypeGroup.add(educationLoan);



        amount = new JLabel("Enter Amount");
        amount.setBounds(100 , 330 , 200 , 50);
        amount.setFont(new Font("",Font.BOLD , 20));
        amount.setForeground(Color.BLACK);
        image.add(amount);

        amountText = new JTextField();
        amountText.setBounds(270, 345, 200, 30);
        amountText.setFont(new Font("", Font.PLAIN, 20));
        image.add(amountText);

        tenureLabel = new JLabel("Select Time Period");
        tenureLabel.setBounds(100 , 400 , 450 , 50);
        tenureLabel.setFont(new Font("",Font.BOLD , 20));
        tenureLabel.setForeground(Color.BLACK);
        image.add(tenureLabel);

        String[] tenureOptions = {"1 year", "2 years", "3 years", "4 years", "5 years" , "10 years"};
        tenureText = new JComboBox<>(tenureOptions);
        tenureText.setBounds(300, 420, 200, 30);
        tenureText.setFont(new Font("", Font.PLAIN, 20));
        image.add(tenureText);

        interestLabel = new JLabel("Interest Rate : 0 %");
        interestLabel.setBounds(100, 480, 200, 30);
        interestLabel.setFont(new Font("", Font.BOLD, 20));
        interestLabel.setForeground(Color.BLACK);
        image.add(interestLabel);

        interestAmount = new JLabel("Interest Amount : 0 ");
        interestAmount.setBounds(100, 520, 400, 30);
        interestAmount.setFont(new Font("", Font.BOLD, 20));
        interestAmount.setForeground(Color.BLACK);
        image.add(interestAmount);

        exit = new JButton("Exit");
        exit.setBounds(300 , 600 , 170 , 40);
        exit.addActionListener(this);
        exit.setForeground(Color.WHITE);
        exit.setBackground(Color.BLACK);
        image.add(exit);

        submit = new JButton("Submit");
        submit.setBounds(500 , 600 , 170 , 40);
        submit.addActionListener(this);
        submit.setForeground(Color.WHITE);
        submit.setBackground(Color.BLACK);
        submit.setEnabled(false);
        image.add(submit);

        calculate = new JButton("Calculate");
        calculate.setBounds(100 , 600 , 170 , 40);
        calculate.addActionListener(this);
        calculate.setForeground(Color.WHITE);
        calculate.setBackground(Color.BLACK);
        image.add(calculate);




        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == exit){
            new Home(pinNumber , cardNumber);
            setVisible(false);
        }else if(ae.getSource() == calculate){
            loanTypeText = "";
            if (personalLoan.isSelected()) {
                loanTypeText =  "Personal Loan";
            } else if (homeLoan.isSelected()) {
                loanTypeText =  "Home Loan";
            } else if (carLoan.isSelected()) {
                loanTypeText =  "Car Loan";
            } else if (educationLoan.isSelected()) {
                loanTypeText =  "Education Loan";
            }

            String num1 = amountText.getText();
            enteredAmount = Integer.parseInt(num1);
            String num2 = ((String) tenureText.getSelectedItem()).split(" ")[0];
            selectedTenure = Integer.parseInt(num2);// Extract numeric value from "X years"re
            if(loanTypeText.equals("") || num1.equals("") || num2.equals("") ){
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
                String query1 = "SELECT formno FROM login WHERE cardNumber = '"+cardNumber+"' " ;
                ResultSet rs1 = c.s.executeQuery(query1);
                if(rs1.next()){
                    formno = rs1.getString("formno");
                }

                String query2 = "SELECT name FROM signup WHERE formno = '"+formno+"' " ;
                ResultSet rs2 = c.s.executeQuery(query2);
                if(rs2.next()){
                    name  = rs2.getString("name");
                }

                String query3 = " INSERT INTO loan (cardNumber , Name, LoanAmount, TotalAmount, TimePeriod, LoanType, Interest, Statuss)" +
                        "VALUES ('"+cardNumber+"' , '"+name+"' , "+enteredAmount+" , "+totalAmount+" , "+selectedTenure+" , '"+loanTypeText+"' , "+interestRate+" , 'Pending'  ) " ;
                c.s.executeUpdate(query3);
                JOptionPane.showMessageDialog(null , "Loan Request Submitted");

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
        new Loan("" , "");
    }
}
