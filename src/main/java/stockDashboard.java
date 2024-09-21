import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class stockDashboard extends JFrame implements ActionListener {
    double[] minMax , currentData ;
    private JLabel heading , image , logoImage , stockName , volumeLabel , volumeText , priceLabel , priceText , quantityLabel , totalAmount ;
    private JTextField quantityText ;
    private JButton buy , sell , back , calculate;
    String pinNumber , cardNumber , stockSymbol ;
    double price , amount , balance ;
    int quantity ;
    String quantityString ;

    stockDashboard(String pinNumber , String cardNumber , String stockSymbol) {

        this.pinNumber = pinNumber;
        this.cardNumber = cardNumber;
        this.stockSymbol = stockSymbol;

        setTitle("Finance Capital Pvt Ltd");
        setSize(1000, 700);
        setLocation(250, 50);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(32, 107, 150));
        setLayout(null);
        setUndecorated(true);

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
        heading.setBounds(250, 50, 500, 50);
        heading.setFont(new Font("Aerial", Font.BOLD, 40));
        heading.setForeground(Color.WHITE);
        image.add(heading);

        //XYSeries series
        stocksAPI api = new stocksAPI(stockSymbol);
        XYSplineRenderer splineRenderer = new XYSplineRenderer();

        currentData = api.getCurrentData();

        stockName = new JLabel(stockSymbol);
        stockName.setBounds(670, 160, 200, 50);
        stockName.setFont(new Font("", Font.BOLD, 20));
        stockName.setForeground(Color.WHITE);
        image.add(stockName);

        volumeLabel = new JLabel("Volume : ");
        volumeLabel.setBounds(630, 230, 200, 50);
        volumeLabel.setFont(new Font("", Font.PLAIN, 20));
        volumeLabel.setForeground(Color.WHITE);
        image.add(volumeLabel);

        volumeText = new JLabel(String.format("%.2f", currentData[1]));
        volumeText.setBounds(830, 230, 200, 50);
        volumeText.setFont(new Font("", Font.PLAIN, 20));
        volumeText.setForeground(Color.WHITE);
        image.add(volumeText);

        priceLabel = new JLabel("Price : ");
        priceLabel.setBounds(630, 300, 200, 50);
        priceLabel.setFont(new Font("", Font.PLAIN, 20));
        priceLabel.setForeground(Color.WHITE);
        image.add(priceLabel);

        priceText = new JLabel(Double.toString(currentData[0]));
        priceText.setBounds(830, 300, 200, 50);
        priceText.setFont(new Font("", Font.PLAIN, 20));
        priceText.setForeground(Color.WHITE);
        image.add(priceText);

        quantityLabel = new JLabel("Quantity : ");
        quantityLabel.setBounds(630, 370, 200, 50);
        quantityLabel.setFont(new Font("", Font.PLAIN, 20));
        quantityLabel.setForeground(Color.WHITE);
        image.add(quantityLabel);

        quantityText = new JTextField();
        quantityText.setBounds(830, 370, 150, 50);
        quantityText.setFont(new Font("", Font.PLAIN, 20));
        quantityText.setForeground(Color.black);
        image.add(quantityText);

        totalAmount = new JLabel("Total Amount : ");
        totalAmount.setBounds(630, 420, 350, 50);
        totalAmount.setFont(new Font("", Font.PLAIN, 20));
        totalAmount.setForeground(Color.white);
        image.add(totalAmount);

        calculate = new JButton("Calculate");
        calculate.setBounds(630, 500, 100, 25);
        calculate.setForeground(Color.WHITE);
        calculate.setBackground(Color.GREEN);
        calculate.addActionListener(this);
        image.add(calculate);

        buy = new JButton("Buy");
        buy.setBounds(630, 550, 100, 25);
        buy.setForeground(Color.WHITE);
        buy.setBackground(Color.GREEN);
        buy.addActionListener(this);
        buy.setEnabled(false);
        image.add(buy);

        sell = new JButton("Sell");
        sell.setBounds(830, 550, 100, 25);
        sell.setForeground(Color.WHITE);
        sell.setBackground(Color.RED);
        sell.addActionListener(this);
        sell.setEnabled(false);
        image.add(sell);

        back = new JButton("Back");
        back.setBounds(730, 620, 170, 40);
        back.addActionListener(this);
        back.setForeground(Color.WHITE);
        back.setBackground(Color.BLACK);
        image.add(back);

        if (currentData[0] == 0) {
            JLabel closed = new JLabel("Stock Market Is Closed Today");
            closed.setBounds(650, 70, 300, 50);
            closed.setFont(new Font("", Font.BOLD, 20));
            closed.setForeground(Color.RED);
            add(closed);

            buy.setEnabled(false);
            sell.setEnabled(false);
        }

        // Monthly Stock Data Chart Code
        XYSeries seriesMonthly = api.getMonthlyData();
        XYSeriesCollection datasetMonthly = new XYSeriesCollection(seriesMonthly);

        JFreeChart monthlyChart = ChartFactory.createXYLineChart(
                "Past Month",// Chart title
                "Date",                // X-axis label
                "Price",               // Y-axis label
                datasetMonthly,               // Data
                PlotOrientation.VERTICAL,
                true,                  // Include legend
                true,                  // Tooltips
                false                  // URLs
        );

        XYPlot plotMonthly = monthlyChart.getXYPlot(); //Access the plot from the chart
        plotMonthly.setRenderer(splineRenderer);
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, true);

        NumberAxis yAxisMonthly = (NumberAxis) plotMonthly.getRangeAxis(); // Get range axis (Y-axis)
        minMax = api.getMinMaxValues();
        yAxisMonthly.setRange(minMax[0] - 10, minMax[1] + 10);  // Set the range for Y-axis

//        DateAxis xAxisMonthly = new DateAxis("Date");
//        xAxisMonthly.setDateFormatOverride(new SimpleDateFormat("dd-MM"));
//        plotMonthly.setDomainAxis(xAxisMonthly);// Set the range for X-axis

        //Yearly Stock Data Chart Code
        XYSeries seriesYearly = api.getYearlyData();
        XYSeriesCollection datasetYearly = new XYSeriesCollection(seriesYearly);

        JFreeChart yearlyChart = ChartFactory.createXYLineChart(
                "Past Year",// Chart title
                "Date",                // X-axis label
                "Price",               // Y-axis label
                datasetYearly,               // Data
                PlotOrientation.VERTICAL,
                true,                  // Include legend
                true,                  // Tooltips
                false                  // URLs
        );

        XYPlot plotYearly = yearlyChart.getXYPlot(); //Access the plot from the chart
        //plotYearly.setRenderer(splineRenderer);

        NumberAxis yAxisYearly = (NumberAxis) plotYearly.getRangeAxis(); // Get range axis (Y-axis)
        minMax = api.getMinMaxValues();
        yAxisYearly.setRange(minMax[0] - 10, minMax[1] + 10);  // Set the range for Y-axis

        DateAxis xAxisYearly = new DateAxis("Date");
//        xAxisYearly.setDateFormatOverride(new SimpleDateFormat("dd-MM"));
//        plotYearly.setDomainAxis(xAxisYearly);// Set the range for X-axis

        // Five year stock data chart
        XYSeries seriesYearly5 = api.getYearlyData5();
        XYSeriesCollection datasetYearly5 = new XYSeriesCollection(seriesYearly5);

        JFreeChart fiveYearlyChart = ChartFactory.createXYLineChart(
                "Past Five Year",// Chart title
                "Date",                // X-axis label
                "Price",               // Y-axis label
                datasetYearly5,               // Data
                PlotOrientation.VERTICAL,
                true,                  // Include legend
                true,                  // Tooltips
                false                  // URLs
        );

        XYPlot plotYearly5 = fiveYearlyChart.getXYPlot(); //Access the plot from the chart

        NumberAxis yAxisYearly5 = (NumberAxis) plotYearly5.getRangeAxis(); // Get range axis (Y-axis)
        minMax = api.getMinMaxValues();
        yAxisYearly5.setRange(minMax[0] - 10, minMax[1] + 10);  // Set the range for Y-axis

        DateAxis xAxisYearly5 = new DateAxis("Date");
//        xAxisYearly5.setDateFormatOverride(new SimpleDateFormat("dd-MM"));
//        plotYearly.setDomainAxis(xAxisYearly5);// Set the range for X-axis

        // All Time stock data chart
        XYSeries seriesAllTime = api.getAllTimeData();
        XYSeriesCollection datasetAllTime = new XYSeriesCollection(seriesAllTime);

        JFreeChart allTimeChart = ChartFactory.createXYLineChart(
                "All Time",// Chart title
                "Date",                // X-axis label
                "Price",               // Y-axis label
                datasetAllTime,               // Data
                PlotOrientation.VERTICAL,
                true,                  // Include legend
                true,                  // Tooltips
                false                  // URLs
        );

        XYPlot plotAllTime = allTimeChart.getXYPlot(); //Access the plot from the chart
        //plotAllTime.setRenderer(splineRenderer);

        NumberAxis yAxisAllTime = (NumberAxis) plotAllTime.getRangeAxis(); // Get range axis (Y-axis)
        minMax = api.getMinMaxValues();
        yAxisAllTime.setRange(minMax[0] - 10, minMax[1] + 10);  // Set the range for Y-axis

        DateAxis xAxisAllTime = new DateAxis("Date");
//        xAxisAllTime.setDateFormatOverride(new SimpleDateFormat("dd-MM"));
//        plotAllTime.setDomainAxis(xAxisAllTime);// Set the range for X-axis

        ChartPanel monthlyPanel = new ChartPanel(monthlyChart);
        ChartPanel yearlyPanel = new ChartPanel(yearlyChart);
        ChartPanel fiveYearlyPanel = new ChartPanel(fiveYearlyChart);
        ChartPanel allTimePanel = new ChartPanel(allTimeChart);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(20, 150, 550, 500);
        tabbedPane.addTab("1 Month", monthlyPanel);
        tabbedPane.addTab("1 Year", yearlyPanel);
        tabbedPane.addTab("5 Year", fiveYearlyPanel);
        tabbedPane.addTab("All Time", allTimePanel);
        image.add(tabbedPane);

        setVisible(true);
    }


    public void actionPerformed(ActionEvent ae) {

        price = currentData[0];
        LocalDate date = LocalDate.now();

        if(ae.getSource()==calculate){

            if(!quantityText.getText().isEmpty()){
                quantity = Integer.parseInt(quantityText.getText()) ;
                quantityString = String.valueOf(quantity);
                if(!isValidQuantity(quantityString)){
                    JOptionPane.showMessageDialog(null,"Enter valid quantity of stocks");
                }else{
                    amount = quantity * price;
                    totalAmount.setText("Total amount : " + amount);
                    buy.setEnabled(true);
                    sell.setEnabled(true);
                }

            }else{
                JOptionPane.showMessageDialog(null,"Enter valid quantity of stocks ");
            }

        }else if(ae.getSource()==buy){

            try{
                Connect c = new Connect();
                ResultSet getBalance = c.s.executeQuery("SELECT balance FROM login WHERE cardNumber = '"+cardNumber+"' AND pin = '"+ pinNumber+"'  ");
                if(getBalance.next()){
                    balance = getBalance.getDouble("balance");
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            if(balance < amount){
                JOptionPane.showMessageDialog(null,"Insufficient Balance ");
                totalAmount.setText("Total amount : 0");
                quantity = 0 ;
                quantityText.setText("");
                buy.setEnabled(false);
                sell.setEnabled(false);
                return ;
            }else{
                try{
                    Connect c = new Connect();
                    String updateStocks = "INSERT INTO stocks (tradeDate, cardNumber, stockSymbol, tradeType, price, quantity) " +
                            "VALUES ( '" + date + "' , '" + cardNumber + "' , '" + stockSymbol + "' , 'BUY' , '" + price + "' , '" + quantity + "')";
                    c.s.executeUpdate(updateStocks) ;

                    String updateBank = "INSERT INTO bank VALUES('"+cardNumber+"' , '"+pinNumber+"' , '"+date+"' , 'Stocks Invest' , '"+amount+"')";
                    c.s.executeUpdate(updateBank);

                    String updateLogin = "UPDATE login SET balance = balance - '"+amount+"' WHERE cardNumber = '"+cardNumber+"' AND pin = '"+pinNumber+"' " ;
                    c.s.executeUpdate(updateLogin);

                    JOptionPane.showMessageDialog(null,"Successfully bought "+ quantity +" "+stockSymbol+" stocks" );
                    totalAmount.setText("Total amount : 0");
                    quantity = 0 ;
                    quantityText.setText("");
                    buy.setEnabled(false);
                    sell.setEnabled(false);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        }else if (ae.getSource()==sell){

            int boughtQuantity ;
            int soldQuantity ;
            int netQuantity ;
            try{
                Connect c = new Connect();

                String boughtQuery = "SELECT SUM(quantity) FROM stocks WHERE tradeType = 'BUY' AND cardNumber = '"+ cardNumber +"' AND stockSymbol = '"+ stockSymbol +"' " ;
                ResultSet boughtRs = c.s.executeQuery(boughtQuery) ;

                if (boughtRs.next()) {
                    // Get the sum from the result set
                    boughtQuantity = boughtRs.getInt(1);  // Index 1 because it's the first column in the result
                }else{
                    boughtQuantity = 0 ;
                }

                String soldQuery = "SELECT SUM(quantity) FROM stocks WHERE tradeType = 'SELL' AND cardNumber = '"+ cardNumber +"' AND stockSymbol = '"+ stockSymbol +"' " ;
                ResultSet soldRs = c.s.executeQuery(soldQuery) ;

                if (soldRs.next()) {
                    // Get the sum from the result set
                    soldQuantity = soldRs.getInt(1);  // Index 1 because it's the first column in the result
                }else{
                    soldQuantity = 0 ;
                }
                netQuantity = boughtQuantity - soldQuantity ;

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            if(netQuantity < quantity){
                JOptionPane.showMessageDialog(null, "You have only : " + netQuantity +" Stocks ", "Stock Quantity", JOptionPane.INFORMATION_MESSAGE);
                totalAmount.setText("Total amount : 0");
                quantity = 0 ;
                quantityText.setText("");
                buy.setEnabled(false);
                sell.setEnabled(false);
            }else{
                try{
                    Connect c = new Connect();
                    String query = "INSERT INTO stocks (tradeDate, cardNumber, stockSymbol, tradeType, price, quantity) " +
                            "VALUES ( '" + date + "' , '" + cardNumber + "' , '" + stockSymbol + "' , 'SELL' , '" + price + "' , '" + quantity + "')";
                    c.s.executeUpdate(query) ;

                    String updateBank = "INSERT INTO bank VALUES('"+cardNumber+"' , '"+pinNumber+"' , '"+date+"' , 'Stocks Sold' , '"+amount+"')";
                    c.s.executeUpdate(updateBank);

                    String updateLogin = "UPDATE login SET balance = balance + '"+amount+"' WHERE cardNumber = '"+cardNumber+"' AND pin = '"+pinNumber+"' " ;
                    c.s.executeUpdate(updateLogin);

                    JOptionPane.showMessageDialog(null,"Successfully sold "+ quantity +" "+stockSymbol+" stocks" );
                    totalAmount.setText("Total amount : 0");
                    quantity = 0 ;
                    quantityText.setText("");
                    buy.setEnabled(false);
                    sell.setEnabled(false);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }


        } else if (ae.getSource() == back ) {
            new Home(pinNumber , cardNumber);
            setVisible(false);

        }
    }

    public boolean isValidQuantity(String quantity) {
        String regex = "^[1-9]\\d*$"; // ^[1-9] ensures no leading zeroes and no negative numbers.

        return Pattern.matches(regex , quantity);
    }


    public static void main(String[] args) {
        new stockDashboard( "" , "" , "");
    }

}
