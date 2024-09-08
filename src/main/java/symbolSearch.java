import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class symbolSearch extends JFrame implements ActionListener {

    JLabel nameLabel , selectedLabel , image ;
    JTextField stockName ;
    JButton search , submit , back ;
    JComboBox<String> symbols ;
    String stockSymbol , pinNumber , cardNumber ;

    symbolSearch( String pinNumber , String cardNumber ){
        this.pinNumber = pinNumber ;
        this.cardNumber = cardNumber ;

        setTitle("Lena Dena Bank Pvt Ltd");
        setSize(1000 , 700);
        setLocation(250 , 50 );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setBackground(new Color(32, 107, 150));
        setLayout(null);

        /* In Java Swing, directly adding an Image object to a JFrame or JPanel
        isn't possible because Swing components like JLabel and JPanel are
         designed to work with ImageIcon for displaying images. */

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("image/stocks.jpg"));
        Image img1 = i1.getImage().getScaledInstance(650 , 400 , Image.SCALE_SMOOTH);
        i1 = new ImageIcon(img1);
        image = new JLabel(i1);
        image.setBounds(20, 150, 650, 400);
        add(image);



        nameLabel = new JLabel("Search any stock");
        nameLabel.setBounds(730, 180 , 200 , 30 );
        nameLabel.setFont(new Font("",Font.BOLD , 20));
        nameLabel.setForeground(Color.WHITE);
        add(nameLabel);

        stockName = new JTextField();
        stockName.setBounds(700, 230 , 230 , 30 );
        stockName.setFont(new Font("",Font.BOLD , 20));
        stockName.setForeground(Color.WHITE);
        add(stockName);

//        selectedLabel = new JLabel("Not selected ");
//        selectedLabel.setBounds(630, 250 , 150 , 30 );
//        add(selectedLabel);

        String[] none = {" None "} ;
        symbols = new JComboBox<>(none);
        symbols.setBounds(700 , 330 , 230 , 30 );
        add(symbols);
        symbols.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stockSymbol = (String) symbols.getSelectedItem();

                if(stockSymbol != null){
                    if(!stockSymbol.equals("Results Found") && !stockSymbol.equals("No match found") & !stockSymbol.equals(" None ") ){
                        submit.setEnabled(true);
                    }else{
                        submit.setEnabled(false);
                    }
                }

            }
        });


        search = new JButton("Search") ;
        search.setBounds(730, 280 , 150 , 30 );
        search.setForeground(Color.WHITE);
        search.setBackground(Color.BLACK);
        search.addActionListener(this);
        add(search);

        submit = new JButton("Submit") ;
        submit.setBounds(730 , 380 , 150 , 30);
        submit.addActionListener(this);
        submit.setForeground(Color.WHITE);
        submit.setBackground(Color.BLACK);
        submit.setEnabled(false);
        add(submit);

        back = new JButton("Back");
        back.setBounds(350 , 600 , 170 , 40);
        back.addActionListener(this);
        back.setForeground(Color.WHITE);
        back.setBackground(Color.BLACK);
        add(back);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == search){
            String sample = stockName.getText();
            stocksAPI api = new stocksAPI() ;
            String[] symbolNames = api.getSymbol(sample);
            symbols.removeAllItems();

            if (symbolNames != null && symbolNames.length > 0) {
                symbols.addItem("Results Found");
                for (String symbol : symbolNames) {
                    symbols.addItem(symbol);
                }
            } else {
                symbols.addItem("No match found");
            }


        }else if (ae.getSource()==submit){
            new stockDashboard(pinNumber , cardNumber , stockSymbol) ;
            setVisible(false);
        } else if (ae.getSource()==back) {
            new deposit_Rules(pinNumber , cardNumber);
            setVisible(false);

        }
    }



    public static void main(String[] args) {
        new symbolSearch("" , "" ) ;
    }
}
