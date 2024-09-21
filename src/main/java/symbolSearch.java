import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class symbolSearch extends JFrame implements ActionListener {

    JLabel nameLabel , logoImage , heading ,stockImage , selectedLabel , image ;
    JTextField stockName ;
    JButton search , submit , back ;
    JComboBox<String> symbols ;
    String stockSymbol , pinNumber , cardNumber ;

    symbolSearch( String pinNumber , String cardNumber ){
        this.pinNumber = pinNumber ;
        this.cardNumber = cardNumber ;

        setTitle("Finance Capital Pvt Ltd");
        setSize(1000 , 700);
        setLocation(250 , 50 );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setLayout(null);

        /* In Java Swing, directly adding an Image object to a JFrame or JPanel
        isn't possible because Swing components like JLabel and JPanel are
         designed to work with ImageIcon for displaying images. */

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

        ImageIcon i5 = new ImageIcon(ClassLoader.getSystemResource("image/stocks.jpg"));
        Image img5 = i5.getImage().getScaledInstance(650 , 400 , Image.SCALE_SMOOTH);
        i5 = new ImageIcon(img5);
        stockImage = new JLabel(i5);
        stockImage.setBounds(20, 150, 650, 400);
        image.add(stockImage);

        heading = new JLabel("Finance Capital Pvt Ltd");
        heading.setBounds(250, 50, 500, 50);
        heading.setFont(new Font("Aerial", Font.BOLD, 40));
        heading.setForeground(Color.WHITE);
        image.add(heading);



        nameLabel = new JLabel("Search any stock");
        nameLabel.setBounds(730, 180 , 200 , 30 );
        nameLabel.setFont(new Font("",Font.BOLD , 20));
        nameLabel.setForeground(Color.WHITE);
        image.add(nameLabel);

        stockName = new JTextField();
        stockName.setBounds(700, 230 , 230 , 30 );
        stockName.setFont(new Font("",Font.BOLD , 20));
        stockName.setForeground(Color.black);
        image.add(stockName);


        String[] none = {" None "} ;
        symbols = new JComboBox<>(none);
        symbols.setBounds(700 , 330 , 230 , 30 );
        image.add(symbols);
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
        image.add(search);

        submit = new JButton("Submit") ;
        submit.setBounds(730 , 380 , 150 , 30);
        submit.addActionListener(this);
        submit.setForeground(Color.WHITE);
        submit.setBackground(Color.BLACK);
        submit.setEnabled(false);
        image.add(submit);

        back = new JButton("Back");
        back.setBounds(350 , 600 , 170 , 40);
        back.addActionListener(this);
        back.setForeground(Color.WHITE);
        back.setBackground(Color.BLACK);
        image.add(back);

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
