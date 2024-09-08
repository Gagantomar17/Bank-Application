import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class deposit_Rules extends JFrame implements ActionListener {
    String pinNumber , cardNumber ;
    JLabel heading , image , rules ;
    JButton back , fd , stocks;

    deposit_Rules(String pinNumber , String cardNumber){
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
        Image img4 = img3.getScaledInstance(1000, 700, Image.SCALE_SMOOTH);
        ImageIcon i4 = new ImageIcon(img4);

        image = new JLabel(i4);
        image.setBounds(0, 0, 1000, 700);
        add(image);

        heading = new JLabel("Terms and Conditions");
        heading.setBounds(250 , 50 , 400 , 50);
        heading.setFont(new Font("System" ,Font.BOLD , 27));
        image.add(heading);

        rules = new JLabel("<html><ol>"
                + "<li><b>Risk Disclaimer for Stocks:</b> Investments in stocks are subject to market risks. The value of your investments can go up or down based on market conditions. "
                + "We do not guarantee returns from stock investments, and you should carefully evaluate your risk tolerance before proceeding. "
                + "Stock prices fluctuate due to company performance, industry trends, and overall economic conditions.</li>"
                + "<li><b>Fixed Deposit Safety:</b> Fixed deposits (FDs) offer a safer, low-risk investment option with a guaranteed return over a specified tenure. "
                + "Early withdrawal from an FD may result in penalties, and interest rates are locked at the time of deposit.</li>"
                + "<li><b>Diversification:</b> It is recommended to diversify your investments across different types of assets to reduce risk. "
                + "Avoid putting all your funds into a single stock or FD to safeguard your portfolio from potential losses.</li>"
                + "</ol></html>");

        rules.setBounds(100 , 80 , 600 , 400);
        rules.setFont(new Font("System" ,Font.PLAIN , 18));
        image.add(rules);

        fd = new JButton("Invest in FD");
        fd.setBounds(750 , 250 , 150 , 70);
        fd.addActionListener(this);
        fd.setForeground(Color.WHITE);
        fd.setBackground(Color.BLACK);
        image.add(fd);

        stocks = new JButton("Invest in Stocks");
        stocks.setBounds(750 , 400 , 150 , 70);
        stocks.addActionListener(this);
        stocks.setForeground(Color.WHITE);
        stocks.setBackground(Color.BLACK);
        image.add(stocks);

        back = new JButton("Back");
        back.setBounds(400 , 600 , 170 , 40);
        back.addActionListener(this);
        back.setForeground(Color.WHITE);
        back.setBackground(Color.BLACK);
        image.add(back);





        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == fd){
            new Deposits(pinNumber , cardNumber) ;
            setVisible(false);
        }else if(ae.getSource() == stocks){
            new symbolSearch(pinNumber , cardNumber);
            setVisible(false);
        }else if(ae.getSource()==back){
            new Home(pinNumber , cardNumber);
        }
    }

    public static void main(String[] args) {
        new deposit_Rules("" , "");
    }
}
