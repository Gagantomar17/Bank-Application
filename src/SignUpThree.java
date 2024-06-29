import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Random;

public class SignUpThree extends JFrame implements ActionListener {

    private JButton submit, back, cancel ;
    private JLabel heading1 , formNo , heading2, logo, acc, card, cardNo, pin, pinNo, service , image ;
    private JRadioButton savings , fixedDeposit , current , student;
    private JCheckBox atm, internet, mobile, email, cheque, statement, declaration ;
    private String formNum ;

    SignUpThree(String formNum){
        this.formNum =  formNum ;
        setTitle("Lena Dena Bank Pvt Ltd");
        setSize(1000 , 700);
        setLocation(250 , 50 );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setUndecorated(true);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("image/logo.jpg"));
        Image img1 = i1.getImage();
        int w = 80;
        int h = 80;
        Image img2 = img1.getScaledInstance(w , h ,Image.SCALE_SMOOTH );
        ImageIcon i2 = new ImageIcon(img2);
        logo = new JLabel(i2);
        logo.setBounds(70 , 10 , w , h );
        add(logo);

        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("image/backbg.png"));
        Image img3 = i3.getImage();
        Image img4 = img3.getScaledInstance(1000 , 700 ,Image.SCALE_SMOOTH );
        ImageIcon i4 = new ImageIcon(img4);

        image = new JLabel(i4);
        image.setBounds(0 , 0,1000 , 700 );
        add(image);

        heading1 = new JLabel("NEW ACCOUNT OPENING FORM ");
        heading1.setBounds(200 , 20 , 650 , 50);
        heading1.setFont(new Font("",Font.BOLD , 40 ));
        heading1.setForeground(Color.WHITE);
        image.add(heading1);

        formNo = new JLabel("Form No - " + formNum);
        formNo.setBounds(390 , 75 , 300 , 25);
        formNo.setFont(new Font("",Font.BOLD , 20 ));
        formNo.setForeground(Color.WHITE);
        image.add(formNo);

        heading2 = new JLabel("Page 3: Account Details ");
        heading2.setBounds(350 , 100 , 300 , 25);
        heading2.setFont(new Font("",Font.BOLD , 20 ));
        heading1.setForeground(Color.WHITE);
        image.add(heading2);

        acc = new JLabel("Account Type:");
        acc.setBounds(120 , 170 , 200 , 25);
        acc.setFont(new Font("",Font.BOLD, 20 ));
        acc.setForeground(Color.WHITE);
        image.add(acc);

        savings = new JRadioButton(" Savings Account ");
        savings.setBounds(120 , 210 , 200 , 25);
        savings.setFont(new Font("",Font.PLAIN , 17 ));
        savings.setBackground(Color.WHITE);
        image.add(savings);

        fixedDeposit = new JRadioButton(" Fixed Deposit Account ");
        fixedDeposit.setBounds( 380, 210 , 200 , 25);
        fixedDeposit.setFont(new Font("",Font.PLAIN , 17 ));
        fixedDeposit.setBackground(Color.WHITE);
        image.add(fixedDeposit);

        current = new JRadioButton(" Current Acccount ");
        current.setBounds(120 , 250 , 200 , 25);
        current.setFont(new Font("",Font.PLAIN , 17 ));
        current.setBackground(Color.WHITE);
        image.add(current);

        student = new JRadioButton(" Student Account ");
        student.setBounds(380 , 250 , 200 , 25);
        student.setFont(new Font("",Font.PLAIN , 17 ));
        student.setBackground(Color.WHITE);
        image.add(student);

        ButtonGroup accType = new ButtonGroup();
        accType.add(savings);
        accType.add(fixedDeposit);
        accType.add(current);
        accType.add(student);


        card = new JLabel("Card Number:");
        card.setBounds(120 , 300 , 150 , 25);
        card.setFont(new Font("",Font.BOLD , 20 ));
        card.setForeground(Color.WHITE);
        image.add(card);

        cardNo = new JLabel("XXXX XXXX XXXX XXXX");
        cardNo.setBounds(300 , 300 , 450 , 25);
        cardNo.setFont(new Font("",Font.PLAIN , 20 ));
        cardNo.setForeground(Color.WHITE);
        image.add(cardNo);

        pin = new JLabel("PIN:");
        pin.setBounds(120 , 350 , 150 , 25);
        pin.setFont(new Font("",Font.BOLD , 20 ));
        pin.setForeground(Color.WHITE);
        image.add(pin);

        pinNo = new JLabel("XXXX");
        pinNo.setBounds(300 , 350 , 150 , 25);
        pinNo.setFont(new Font("",Font.PLAIN , 20 ));
        pinNo.setForeground(Color.WHITE);
        image.add(pinNo);

        service = new JLabel("Services Required:");
        service.setBounds(120 , 400 , 300 , 25);
        service.setFont(new Font("",Font.BOLD , 20 ));
        service.setForeground(Color.WHITE);
        image.add(service);

        atm = new JCheckBox(" ATM Card ");
        atm.setBounds(120 , 440 , 200 , 25);
        atm.setFont(new Font("",Font.PLAIN , 17 ));
        atm.setBackground(Color.WHITE);
        image.add(atm);

        internet = new JCheckBox(" Internet Banking ");
        internet.setBounds( 380, 440 , 200 , 25);
        internet.setFont(new Font("",Font.PLAIN , 17 ));
        internet.setBackground(Color.WHITE);
        image.add(internet);

        mobile = new JCheckBox(" Mobile Banking ");
        mobile.setBounds(120 , 480 , 200 , 25);
        mobile.setFont(new Font("",Font.PLAIN , 17 ));
        mobile.setBackground(Color.WHITE);
        image.add(mobile);

        email = new JCheckBox(" Email Alerts ");
        email.setBounds(380 , 480 , 200 , 25);
        email.setFont(new Font("",Font.PLAIN , 17 ));
        email.setBackground(Color.WHITE);
        image.add(email);

        cheque = new JCheckBox(" Cheque Book ");
        cheque.setBounds(120 , 520 , 200 , 25);
        cheque.setFont(new Font("",Font.PLAIN , 17 ));
        cheque.setBackground(Color.WHITE);
        image.add(cheque);

        statement = new JCheckBox(" E-Statement ");
        statement.setBounds(380 , 520 , 200 , 25);
        statement.setFont(new Font("",Font.PLAIN , 17 ));
        statement.setBackground(Color.WHITE);
        image.add(statement);

        declaration = new JCheckBox(" I hereby declare that the above Entered details " +
                "are correct to the best of my knowlwdge  ");
        declaration.setBounds(120 , 580 , 800 , 25);
        declaration.setFont(new Font("",Font.PLAIN , 17 ));
        declaration.setBackground(Color.WHITE);
        image.add(declaration);



        submit = new JButton(" Submit ");
        submit.setBounds(350 , 650 , 100 , 25);
        submit.setForeground(Color.BLACK);
        submit.setBackground(Color.YELLOW);
        submit.addActionListener(this);
        image.add(submit);

        cancel = new JButton(" Cancel ");
        cancel.setBounds(620 , 650 , 100 , 25);
        cancel.setForeground(Color.BLACK);
        cancel.setBackground(Color.YELLOW);
        cancel.addActionListener(this);
        image.add(cancel);

        setVisible(true);
    }

    private String generateRandomDigits(int length){
        StringBuilder num = new StringBuilder();
        Random random = new Random();
        for(int i=0 ; i< length ; i++){
            num.append(random.nextInt(10));
        }
        return num.toString();
    }

    public boolean isUnique(String cardNum){
        boolean unique = true ; ;
        try{
            Connect con = new Connect();
            String query = "SELECT *FROM login WHERE cardNumber = '"+cardNum+"'" ;
            ResultSet rs = con.s.executeQuery(query);
            if(rs.next()){
                unique = false ;
            }
        } catch (Exception e){
            System.out.println(e);
        }
        return unique ;
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==submit){
            String accType = null ;
            if(savings.isSelected()){
                accType = "Savings Account";
            }else if(fixedDeposit.isSelected()){
                accType = "Fixed Deposit Account" ;
            }else if(current.isSelected()){
                accType = "Current Account";
            }else if(student.isSelected()){
                accType = "Student Account";
            }

            String cardNumber = "";
            do{
                cardNumber = "10101010" + generateRandomDigits(8);
            }while (!isUnique(cardNumber));
            String pinNumber = generateRandomDigits(4) ;

            String facility = "";
            if(atm.isSelected()){
                facility += " ATM Card " ;
            }else if(internet.isSelected()){
                facility += " Internet Banking";
            }else if(mobile.isSelected()){
                facility += " Mobile Banking";
            }else if(email.isSelected()){
                facility += " Email Alerts";
            }else if(cheque.isSelected()){
                facility += " Cheque Book";
            }else if(statement.isSelected()){
                facility += " E-Statement";
            }

            try{
                if(accType.equals("")){
                    JOptionPane.showMessageDialog(null , "Account Type is required");
                }else{
                    Connect conn = new Connect();
                    String query = "INSERT INTO signupthree VALUES('" + formNum +"' , '" + accType + "', '" + cardNumber + "', '" + pinNumber + "', '" + facility + "')";
                    String query2 = "INSERT INTO login VALUES('" + formNum +"', '" + cardNumber + "', '" + pinNumber +"' , 0 )";
                    conn.s.executeUpdate(query);
                    conn.s.executeUpdate(query2);
                    JOptionPane.showMessageDialog(null , "Card Number " + cardNumber + "\n Pin Number " + pinNumber);
                    new Login();
                    setVisible(false);
                }

            } catch (Exception e){
                System.out.println(e);
            }
        } else if (ae.getSource()==cancel) {
            new Login() ;
            setVisible(false);
        }
    }

    public static void main(String[] args) {

        new SignUpThree("");
    }
}
