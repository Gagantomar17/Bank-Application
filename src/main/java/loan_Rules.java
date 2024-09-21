import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loan_Rules extends JFrame implements ActionListener {

    private String pinNumber, cardNumber;
    private JLabel image , rules , heading , logoImage ;
    private JButton next , back ;

    loan_Rules(String pinNumber, String cardNumber) {
        this.pinNumber = pinNumber;
        this.cardNumber = cardNumber;
        setTitle("Finance Capital Pvt Ltd");
        setSize(1000, 700);
        setLocation(250, 50);
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

        heading = new JLabel("Finance Capital Pvt Ltd");
        heading.setBounds(280, 50, 500, 50);
        heading.setFont(new Font("Aerial", Font.BOLD, 40));
        heading.setForeground(Color.WHITE);
        image.add(heading);

        rules = new JLabel("<html><ol>"
                + "<li><b>Understand Your Financial Situation:</b> Before applying for a loan, assess your current financial situation.</li>"
                + "<li><b>Research Loan Options:</b> Explore different types of loans available, such as personal loans, car loans, home loans, and education loans.</li>"
                + "<li><b>Determine Loan Amount and Tenure:</b> Decide how much money you need to borrow and the duration of the loan repayment.</li>"
                + "<li><b>Understand Interest Rates and Fees:</b> Familiarize yourself with the interest rates offered by different lenders and the fees associated with the loan.</li>"
                + "<li><b>Read and Understand Terms and Conditions:</b> Carefully read the terms and conditions of the loan agreement, including eligibility criteria, repayment terms, and mechanisms.</li>"
                + "<li><b>Prepare Documentation:</b> Gather the necessary documents required for the loan application, such as proof of identity, address, income, employment, and assets.</li>"
                + "<li><b>Seek Professional Advice (if needed):</b> If you're unsure about any aspect of the loan application process or loan terms, consider seeking advice from a financial advisor or loan officer.</li>"
                + "</ol></html>");

        rules.setBounds(100 , 100 , 600 , 500);
        rules.setFont(new Font("System" ,Font.PLAIN , 18));
        rules.setForeground(Color.white);
        image.add(rules);

        next = new JButton("I Agree to the T&C");
        next.setBounds(450 , 600 , 170 , 40);
        next.addActionListener(this);
        next.setForeground(Color.BLACK);
        next.setBackground(Color.YELLOW);
        image.add(next);

        back = new JButton("Back");
        back.setBounds(250 , 600 , 170 , 40);
        back.addActionListener(this);
        back.setForeground(Color.WHITE);
        back.setBackground(Color.BLACK);
        image.add(back);



        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == next){
            new Loan(pinNumber , cardNumber);
            setVisible(false);
        }else if(ae.getSource() == back ){
            new Home(pinNumber , cardNumber);
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new loan_Rules("", "");
    }
}
