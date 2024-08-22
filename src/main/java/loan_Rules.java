import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loan_Rules extends JFrame implements ActionListener {

    private String pinNumber, cardNumber;
    private JLabel image , rules;
    private JButton next , back ;

    loan_Rules(String pinNumber, String cardNumber) {
        this.pinNumber = pinNumber;
        this.cardNumber = cardNumber;
        setTitle("Lena Dena Bank Pvt Ltd");
        setSize(1000, 700);
        setLocation(250, 50);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(32, 107, 150));
        setLayout(null);

        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("image/backbg.png"));
        Image img3 = i3.getImage();
        Image img4 = img3.getScaledInstance(1000, 700, Image.SCALE_SMOOTH);
        ImageIcon i4 = new ImageIcon(img4);

        image = new JLabel(i4);
        image.setBounds(0, 0, 1000, 700);
        add(image);

        rules = new JLabel("<html><ol>" +
                "<li>Understand Your Financial Situation: Before applying for a loan, assess your current financial situation. </li>" +
                "<li>Research Loan Options: Explore different types of loans available, such as personal loans, car loans, home loans, and education loans.</li>" +
                "<li>Determine Loan Amount and Tenure: Decide how much money you need to borrow and the duration of the loan repayment. </li>" +
                "<li>Understand Interest Rates and Fees: Familiarize yourself with the interest rates offered by different lenders and the fees associated with the loan.</li>" +
                "<li>Read and Understand Terms and Conditions: Carefully read the terms and conditions of the loan agreement, including eligibility criteria, repayment terms and mechanisms</li>" +
                "<li>Prepare Documentation: Gather the necessary documents required for the loan application, such as proof of identity, address, income, employment, and assets.</li>" +
                "<li>Seek Professional Advice (if needed): If you're unsure about any aspect of the loan application process or loan terms, consider seeking advice from a financial advisor or loan officer. </li>" +
                "</ol></html>");
        rules.setBounds(100 , 100 , 600 , 500);
        rules.setFont(new Font("System" ,Font.PLAIN , 18));
        image.add(rules);

        next = new JButton("I Agree to the T&C");
        next.setBounds(200 , 600 , 170 , 40);
        next.addActionListener(this);
        next.setForeground(Color.WHITE);
        next.setBackground(Color.BLACK);
        image.add(next);

        back = new JButton("Back");
        back.setBounds(400 , 600 , 170 , 40);
        back.addActionListener(this);
        back.setForeground(Color.WHITE);
        back.setBackground(Color.BLACK);
        image.add(back);



        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == next){
            setVisible(false);
            new Loan(pinNumber , cardNumber);
        }else if(ae.getSource() == back ){
            setVisible(false);
            new Home(pinNumber , cardNumber);
        }
    }

    public static void main(String[] args) {
        new loan_Rules("", "");
    }
}
