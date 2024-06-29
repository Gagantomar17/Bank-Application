import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PinChange extends JFrame implements ActionListener {
    private String pinNumber , cardNumber ;
    private JLabel text , pin , rePin;
    private JPasswordField pinText , rePinText ;
    private JButton back , change ;

    PinChange(String pinNumber , String cardNumber){
        this.cardNumber = cardNumber ;
        this.pinNumber = pinNumber ;
        setTitle("Banking Application - Home Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(450 , 50 );
        setSize(700, 700);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("image/atm.png"));
        Image img1 = i1.getImage();
        Image img2 = img1.getScaledInstance(700 , 700 , Image.SCALE_SMOOTH);
        ImageIcon i2 = new ImageIcon(img2);
        JLabel image = new JLabel(i2);
        image.setBounds(0,0,700,700);
        add(image);

        text = new JLabel("Change Your Pin");
        text.setBounds(240 , 130 , 400 , 40);
        text.setFont(new Font("System" ,Font.BOLD , 17));
        text.setForeground(Color.WHITE);
        image.add(text);

        pin = new JLabel("New Pin");
        pin.setBounds(210 , 170 , 150 , 40);
        pin.setFont(new Font("System" ,Font.PLAIN , 17));
        pin.setForeground(Color.WHITE);
        image.add(pin);

        rePin = new JLabel("Re-Enter Pin");
        rePin.setBounds(210 , 200 , 150 , 40);
        rePin.setFont(new Font("System" ,Font.PLAIN , 17));
        rePin.setForeground(Color.WHITE);
        image.add(rePin);

        pinText = new JPasswordField();
        pinText.setBounds(320 , 175 , 150 , 25);
        pinText.setBackground(Color.WHITE);
        pinText.setForeground(Color.BLACK);
        image.add(pinText);

        rePinText = new JPasswordField();
        rePinText.setBounds(320 , 205 , 150 , 25);
        rePinText.setBackground(Color.WHITE);
        rePinText.setForeground(Color.BLACK);
        image.add(rePinText);

        change = new JButton(" Deposit ");
        change.setBounds(360 , 315 , 120 , 25);
        change.addActionListener(this);
        image.add(change);

        back = new JButton(" Back ");
        back.setBounds(360 , 355 , 120 , 25);
        back.addActionListener(this);
        image.add(back);




        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == change){
            try{
                String pin1 = pinText.getText();
                String pin2 = rePinText.getText();

                if( !pin1.equals(pin2) || pin1.equals("") || pin2.equals("")){
                    JOptionPane.showMessageDialog(null , "Entered Pin Doesn't Match");
                    return;
                }
                Connect c = new Connect();

                String query1 = "UPDATE bank set pin = '"+pin1+"' WHERE cardNumber = '"+cardNumber+"' AND pin = '"+pinNumber+"' " ;
                String query2 = "UPDATE login set pin = '"+pin1+"' WHERE cardNumber = '"+cardNumber+"' AND pin = '"+pinNumber+"' " ;
                String query3 = "UPDATE signupthree set pin = '"+pin1+"' WHERE cardNumber = '"+cardNumber+"' AND pin = '"+pinNumber+"' " ;

                c.s.executeUpdate(query1);
                c.s.executeUpdate(query2);
                c.s.executeUpdate(query3);

                JOptionPane.showMessageDialog(null , "PIN Changed Successfully");

                setVisible(false);
                new Transactions(pin1 , cardNumber).setVisible(true);

            } catch (Exception e){
                System.out.println(e);
            }

        }else{
            setVisible(false);
            new Transactions(pinNumber , cardNumber);
        }
    }

    public static void main(String[] args) {
        new PinChange("" , "");
    }
}
