import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date ;
import java.util.jar.JarFile;
import java.util.regex.Pattern;

public class Account extends JFrame implements ActionListener {
    private String pinNumber , cardNumber , nameVal ,formVal , cardVal , toAccount ,toPin , amount , pin ;
    private JLabel   image , text  , mini , acccBalance , accTransfer , loan , deposit , accTran1 , accTran2 , accTran3  ;
    private JPanel accTransferPanel , loanPanel , depositPanel ;
    private JButton accTranSubmit , accTranClear , back ;
    private JTextField text1 , text2 , text3 ;
    private int balance ;

    Account(String pinNumber , String cardNumber){
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

        text = new JLabel("<html>" + "Date & Time" + "&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;" + "Type" + "&nbsp; &nbsp; &nbsp; " + "Amount" +  "<html>");
        text.setBounds(0 , 100 , 400 , 40);
        text.setFont(new Font("System" ,Font.BOLD , 20));
        text.setForeground(Color.WHITE);
        image.add(text);

        // Account transfer method code
        accTransferPanel = new JPanel();
        accTransferPanel.setLayout(null);
        accTransferPanel.setBackground(Color.WHITE);

        accTransfer = new JLabel("Transfer Money To Bank Account");
        accTransfer.setBounds(20 , 50 , 400 , 40);
        accTransfer.setFont(new Font("System" ,Font.BOLD , 20));
        accTransferPanel.add(accTransfer);

        accTran1 = new JLabel("Enter Account Number ");
        accTran1.setBounds(20 , 100 , 250 , 40);
        accTran1.setFont(new Font("System" ,Font.PLAIN , 18));
        accTransferPanel.add(accTran1);

        text1 = new JTextField();
        text1.setBounds(250 , 110 , 200 , 25);
        accTransferPanel.add(text1);

        accTran2 = new JLabel("Enter Amount ");
        accTran2.setBounds(20 , 150 , 150 , 40);
        accTran2.setFont(new Font("System" ,Font.PLAIN , 18));
        accTransferPanel.add(accTran2);

        text2 = new JTextField();
        text2.setBounds(250 , 160 , 200 , 25);
        accTransferPanel.add(text2);

        accTran3 = new JLabel("Enter Your Pin ");
        accTran3.setBounds(20 , 200 , 150 , 40);
        accTran3.setFont(new Font("System" ,Font.PLAIN , 18));
        accTransferPanel.add(accTran3);

        text3 = new JTextField();
        text3.setBounds(250 , 210 , 200 , 25);
        accTransferPanel.add(text3);

        accTranSubmit = new JButton("Submit");
        accTranSubmit.setBounds(260 , 330 , 140 , 40);
        accTranSubmit.addActionListener(this);
        accTranSubmit.setForeground(Color.WHITE);
        accTranSubmit.setBackground(Color.BLACK);
        accTransferPanel.add(accTranSubmit);

        accTranClear = new JButton("Clear");
        accTranClear.setBounds(110 , 330 , 140 , 40);
        accTranClear.addActionListener(this);
        accTranClear.setForeground(Color.WHITE);
        accTranClear.setBackground(Color.BLACK);
        accTransferPanel.add(accTranClear);

        back = new JButton("Back");
        back.setBounds(120 , 390 , 250 , 40);
        back.addActionListener(this);
        back.setForeground(Color.WHITE);
        back.setBackground(Color.BLACK);
        accTransferPanel.add(back);



        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(420, 150, 550, 500);
        tabbedPane.addTab("Account Transfer", accTransferPanel);
        image.add(tabbedPane);

        mini = new JLabel();
        mini.setBounds(10 , 160 , 400 , 500);
        mini.setFont(new Font("System" ,Font.PLAIN , 15));
        mini.setForeground(Color.BLACK);
        image.add(mini);

        try{
            Connect c = new Connect();
            ResultSet rs = c.s.executeQuery("SELECT *FROM bank WHERE cardNumber = '"+cardNumber+"' AND pin = '"+pinNumber+"'");
            ArrayList<String> List = new ArrayList<>();
            while(rs.next()) {
                String data = "<html>" + rs.getString("date")+ "&nbsp; &nbsp; &nbsp; " + rs.getString("type")+ "&nbsp; &nbsp; &nbsp; &nbsp;" +rs.getString("amount") +"<br><br> <html>";
                List.add(data);
            }

// Reverse order printing
            int n = List.size();
            for (int i = n - 1; i >= 0 ; i--) {
                mini.setText(mini.getText() + List.get(i));
                mini.repaint();;
            }

        } catch (Exception e){
            System.out.println(e);
        }

        acccBalance = new JLabel();
        acccBalance.setBounds(70 , 500 , 250 , 40);
        acccBalance.setFont(new Font("System" ,Font.PLAIN , 15));
        acccBalance.setForeground(Color.BLACK);
        image.add(acccBalance);

        try{
            Connect c = new Connect();
            ResultSet rs = c.s.executeQuery("SELECT balance FROM login WHERE cardNumber = '"+cardNumber+"' AND pin = '"+ pinNumber+"'  ");
            if(rs.next()){
                balance = rs.getInt("balance");
            }

            acccBalance.setText("Your account balance is "+ balance);
        } catch (Exception e){
            System.out.println(e);
        }
        JScrollPane scrollPane = new JScrollPane(mini);
        scrollPane.setBounds(10 , 160 , 400 , 500); // Set bounds manually
        image.add(scrollPane);




        setUndecorated(true);
        setVisible(true);
    }

    public boolean checkAcc(String num){
        String n = num ;
        boolean isFound = false ;
        try{
            Connect c = new Connect();
            String query = "SELECT COUNT(*) AS count FROM login WHERE cardNumber = '"+n+"' " ;
            ResultSet rs = c.s.executeQuery(query);
            if(rs.next() && rs.getInt("count")>0){
                isFound = true ;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return isFound ;
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == back){
            new Home(pinNumber , cardNumber);
            setVisible(false);
        }else if(ae.getSource() == accTranSubmit){
            toAccount = text1.getText();
            amount = text2.getText();
            pin = text3.getText();

            try{
                if(toAccount.equals("") || amount.equals("")){
                    JOptionPane.showMessageDialog(null , "All fields are required");
                } else if (!pin.equals(pinNumber)) {
                    JOptionPane.showMessageDialog(null , "Incoreect Pin ");
                } else if (!checkAcc(toAccount)) {
                    JOptionPane.showMessageDialog(null , "Incoreect / Invalid CardNumber ");
                }else if (toAccount.equals(cardNumber)){
                    JOptionPane.showMessageDialog(null , "Cant transfer to the same cardNumber ");
                } else if (!isValidAmount(amount)) {
                    JOptionPane.showMessageDialog(null , "Enter a valid amount");
                } else{
                    Connect c = new Connect();

                    ResultSet rs = c.s.executeQuery("SELECT balance FROM login WHERE cardNumber = '"+cardNumber+"' AND pin = '"+ pinNumber+"'  ");
                    if(rs.next()){
                        balance = rs.getInt("balance");
                    }

                    if(balance < Integer.parseInt(amount)){
                        JOptionPane.showMessageDialog(null , "Insufficient balance ");
                    }else{
                        Date date = new Date();
                        String query1 = "INSERT INTO bank VALUES('"+cardNumber+"' , '"+pin+"' , '"+date+"' , 'Withdrawl' , '"+amount+"')";
                        c.s.executeUpdate(query1);
                        JOptionPane.showMessageDialog(null , "Rs "+amount+" Transferred successfully");

                        double amountNum = Integer.parseInt(amount) ;
                        String query2 = "UPDATE login SET balance = balance - '"+ amountNum + "' WHERE cardNumber = '"+cardNumber+"' AND pin = '"+pinNumber+"' ;";
                        c.s.executeUpdate(query2);

                        String query3 = "SELECT pin FROM login WHERE cardNumber = '"+toAccount+"'" ;
                        ResultSet rs2 = c.s.executeQuery(query3);
                        if (rs2.next()) { // Check if a result is found
                            toPin = rs2.getString("pin"); // Get the PIN value from the "pin" column
                            String query4 = "INSERT INTO bank VALUES('"+toAccount+"' , '"+toPin+"' , '"+date+"' , 'Deposit' , '"+amount+"')";
                            c.s.executeUpdate(query4);

                            String query5 = "UPDATE login SET balance = balance + '"+ amountNum + "' WHERE cardNumber = '"+toAccount+"' AND pin = '"+toPin+"' ;";
                            c.s.executeUpdate(query5);

                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid account number");
                        }
                    }
                    new Home(pinNumber , cardNumber);
                    setVisible(false);


                };

            } catch (Exception e){
                System.out.println(e);

            }

        }else if(ae.getSource() == accTranClear){
            accTran1.setText("");
            accTran2.setText("");
            accTran3.setText("");
            return;
        }
    }

    private boolean isValidAmount(String input) {
        // non-negative and non-zero number
        String regex = "^[1-9]\\d*$";
        return Pattern.matches(regex, input);
    }

    public static void main(String[] args) {

        new Account("1020" , "1010101083471065");
    }
}
