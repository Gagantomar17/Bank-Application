import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class symbolSearch extends JFrame implements ActionListener {

    JLabel nameLabel , selectedLabel ;
    JTextField stockName ;
    JButton search , submit ;
    JComboBox<String> symbols ;
    String stockSymbol , pinNumber , cardNumber ;

    symbolSearch( String pinNumber , String cardNumber ){
        this.pinNumber = pinNumber ;
        this.cardNumber = cardNumber ;

        setTitle("Lena Dena Bank Pvt Ltd");
        setSize(1000 , 700);
        setLocation(250 , 50 );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(32, 107, 150));
        setLayout(null);

        nameLabel = new JLabel("Search any stock");
        nameLabel.setBounds(20, 30 , 150 , 30 );
        add(nameLabel);

        stockName = new JTextField();
        stockName.setBounds(200, 30 , 150 , 30 );
        add(stockName);

        selectedLabel = new JLabel("Not selected ");
        selectedLabel.setBounds(200, 60 , 150 , 30 );
        add(selectedLabel);

        String[] none = {" None "} ;
        symbols = new JComboBox<>(none);
        symbols.setBounds(40 , 60 , 150 , 30 );
        add(symbols);
        symbols.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stockSymbol = (String) symbols.getSelectedItem();
                selectedLabel.setText(stockSymbol);

                if(stockSymbol != null){
                    if(!stockSymbol.equals("Results Found") && !stockSymbol.equals("No match found") ){
                        submit.setEnabled(true);
                    }else{
                        submit.setEnabled(false);
                    }
                }

            }
        });


        search = new JButton("Search") ;
        search.setBounds(400, 30 , 150 , 30 );
        search.addActionListener(this);
        add(search);

        submit = new JButton("Submit") ;
        submit.setBounds(400, 60 , 150 , 30 );
        submit.addActionListener(this);
        submit.setEnabled(false);
        add(submit);




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
        }
    }



    public static void main(String[] args) {
        new symbolSearch("" , "" ) ;
    }
}
