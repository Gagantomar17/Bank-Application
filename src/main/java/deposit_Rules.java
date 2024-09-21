import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class deposit_Rules extends JFrame implements ActionListener {
    String pinNumber , cardNumber ;
    JLabel heading1 , heading2 , image , rules , logoImage ;
    JButton back , fd , stocks;

    deposit_Rules(String pinNumber , String cardNumber){
        this.pinNumber = pinNumber ;
        this.cardNumber = cardNumber ;

        setTitle("Finance Capital Pvt Ltd");
        setSize(1000 , 700);
        setLocation(250 , 50 );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
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

        heading1 = new JLabel("Finance Capital Pvt Ltd");
        heading1.setBounds(250, 50, 500, 50);
        heading1.setFont(new Font("Aerial", Font.BOLD, 40));
        heading1.setForeground(Color.WHITE);
        image.add(heading1);

        heading2 = new JLabel("Terms and Conditions");
        heading2.setBounds(130 , 115 , 400 , 40);
        heading2.setFont(new Font("System" ,Font.BOLD , 27));
        heading2.setForeground(Color.WHITE);
        image.add(heading2);

        rules = new JLabel("<html><ol>"
                + "<li><b>Risk Disclaimer for Stocks:</b> Investments in stocks are subject to market risks. The value of your investments can go up or down based on market conditions. "
                + "We do not guarantee returns from stock investments, and you should carefully evaluate your risk tolerance before proceeding. "
                + "Stock prices fluctuate due to company performance, industry trends, and overall economic conditions.</li>"
                + "<li><b>Fixed Deposit Safety:</b> Fixed deposits (FDs) offer a safer, low-risk investment option with a guaranteed return over a specified tenure. "
                + "Early withdrawal from an FD may result in penalties, and interest rates are locked at the time of deposit.</li>"
                + "<li><b>Diversification:</b> It is recommended to diversify your investments across different types of assets to reduce risk. "
                + "Avoid putting all your funds into a single stock or FD to safeguard your portfolio from potential losses.</li>"
                + "</ol></html>");

        rules.setBounds(100 , 130 , 600 , 400);
        rules.setFont(new Font("System" ,Font.PLAIN , 18));
        rules.setForeground(Color.WHITE);
        image.add(rules);

        fd = new JButton("Invest in FD");
        fd.setBounds(750 , 250 , 150 , 70);
        fd.addActionListener(this);
        fd.setForeground(Color.BLACK);
        fd.setBackground(Color.YELLOW);
        image.add(fd);

        stocks = new JButton("Invest in Stocks");
        stocks.setBounds(750 , 400 , 150 , 70);
        stocks.addActionListener(this);
        stocks.setForeground(Color.BLACK);
        stocks.setBackground(Color.YELLOW);
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
