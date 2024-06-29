import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ManagerDashboard extends JFrame implements ActionListener {
    private JLabel totalUsersLabel, logoImage, heading , topRichestLabel , userDetails , image , totalDepositsLabel ;
    private JComboBox<String> userDropdown;
    private JTextArea userDetailsArea;
    private JTable loanTable ;
    private JButton approve , decline , exit ;
    private String pinNumber ;
    private int selectedRow ;
    private String[] userDropdownItems ;

    ManagerDashboard() {

        setTitle("Lena Dena Bank Pvt Ltd");
        setSize(1000, 700);
        setLocation(250, 50);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("image/backbg.png"));
        Image img1 = i1.getImage();
        Image img2 = img1.getScaledInstance(1000 , 700 ,Image.SCALE_SMOOTH );
        ImageIcon i2 = new ImageIcon(img2);
        image = new JLabel(i2);
        image.setBounds(0 , 0,1000 , 700 );
        add(image);

        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("image/card.png"));
        Image img3 = i3.getImage();
        Image img4 = img3.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon i4 = new ImageIcon(img4);

        logoImage = new JLabel(i2);
        logoImage.setBounds(10, 10, 50, 50);
        image.add(logoImage);

        heading = new JLabel("Lena Dena Bank Pvt Ltd");
        heading.setBounds(300, 40, 500, 60);
        heading.setFont(new Font("Aerial", Font.BOLD, 30));
        heading.setForeground(Color.WHITE);
        image.add(heading);

        // Total Users Label
        totalUsersLabel = new JLabel("Total Active Users: ");
        totalUsersLabel.setBounds(50, 100, 400, 40);
        totalUsersLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        totalUsersLabel.setForeground(Color.WHITE);
        image.add(totalUsersLabel);

        // Total Deposits Label
        totalDepositsLabel = new JLabel("Total Amount Deposited: ");
        totalDepositsLabel.setBounds(50, 140, 400, 40);
        totalDepositsLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        totalDepositsLabel.setForeground(Color.WHITE);
        image.add(totalDepositsLabel);

        topRichestLabel = new JLabel("Top Richest Users: ");
        topRichestLabel.setBounds(50, 180, 500, 100);
        topRichestLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        topRichestLabel.setForeground(Color.WHITE);
        image.add(topRichestLabel);

        try {
            // Establish the connection
            Connect c = new Connect();

            // Calculate total users
            String userQuery = "SELECT COUNT(*) AS totalUsers FROM login";
            ResultSet userRs = c.s.executeQuery(userQuery);
            int totalUsers = 0;
            if (userRs.next()) {
                totalUsers = userRs.getInt("totalUsers");
            }
            totalUsersLabel.setText("Total Active Users: " + totalUsers);

            // Calculate total deposits
            String depositQuery = "SELECT SUM(balance) AS totalDeposits FROM login";
            ResultSet depositRs = c.s.executeQuery(depositQuery);
            double totalDeposits = 0;
            if (depositRs.next()) {
                totalDeposits = depositRs.getDouble("totalDeposits");
            }
            totalDepositsLabel.setText("Total Amount Deposited: Rs " + totalDeposits);

            // Find top richest users (adjust query to your database schema)
            String topUsersQuery = "SELECT l.balance, s.name, s.fathers_name " +
                    "FROM login l " +
                    "JOIN signup s ON l.formno = s.formno " +
                    "ORDER BY l.balance DESC " +
                    "LIMIT 3";;
            ResultSet topUsersRs = c.s.executeQuery(topUsersQuery);
            StringBuilder richestUsers = new StringBuilder("<html>Top Richest Users: <br> <br>");
            String name = "" ;
            String fname = "" ;
            double balance = 0;
            while (topUsersRs.next()) {
                fname = topUsersRs.getString("fathers_name");
                name = topUsersRs.getString("name");
                balance = topUsersRs.getDouble("balance");

                richestUsers.append(name).append(" S/O ").append(fname).append(" balance : ").append(balance).append("<br>");
            }
            richestUsers.append("</html>");
            topRichestLabel.setText(richestUsers.toString());


        } catch (Exception e) {
            //System.out.println(e);

        }

        // fetching data for user drop down
        try {
            Connect c = new Connect();
            String userListQuery = "SELECT name, formno FROM signup";
            ResultSet userListRs = c.s.executeQuery(userListQuery);
            ArrayList<String> list = new ArrayList<>();
            int index = 0;
            while (userListRs.next()) {
                String userName = userListRs.getString("name");
                String userformno = userListRs.getString("formno");
                System.out.println("Adding to JComboBox: " + userformno + " " + userName);
                String text = userformno + " " + userName;
                list.add(text);
            }
            int rowCount = list.size();
            userDropdownItems = new String[rowCount];
            for (int i = 0; i < list.size(); i++) {
                userDropdownItems[i] = list.get(i);
            }

        } catch (Exception e) {
           //System.out.println("Error fetching user list: " + e.getMessage());
        }

        userDetails = new JLabel("Select User Name ");
        userDetails.setBounds(50, 460 , 200, 40);
        userDetails.setFont(new Font("Arial", Font.PLAIN, 20));
        userDetails.setForeground(Color.WHITE);
        image.add(userDetails);

        // User Dropdown
        userDropdown = new JComboBox<>(userDropdownItems);
        userDropdown.setBounds(50, 500, 200, 30);
        userDropdown.addActionListener(this);
        userDropdown.addActionListener(this);
        image.add(userDropdown);

        // User Details Area
        userDetailsArea = new JTextArea();
        userDetailsArea.setBounds(300, 480 , 400, 150);
        userDetailsArea.setFont(new Font("Arial", Font.PLAIN, 16));
        userDetailsArea.setForeground(Color.BLACK);
        userDetailsArea.setEditable(false);
        image.add(userDetailsArea);

        // loan table data fetching
        ArrayList<Object[]> loanDataList = new ArrayList<>();
        try {
            Connect c = new Connect() ;
            String query = "SELECT * FROM loan";
            ResultSet rs = c.s.executeQuery(query);

            // Populate loanDataList with data from ResultSet
            while (rs.next()) {
                Object[] row = new Object[7];
                row[0] = rs.getString("cardNumber");
                row[1] = rs.getDouble("LoanAmount");
                row[2] = rs.getDouble("TotalAmount");
                row[3] = rs.getInt("TimePeriod");
                row[4] = rs.getString("LoanType");
                row[5] = rs.getDouble("Interest");
                row[6] = rs.getString("Statuss");

                loanDataList.add(row);
            }
        }catch (Exception e){
            //System.out.println(e);
        }

        Object[][] data = new Object[loanDataList.size()][7];
        for (int i = 0; i < loanDataList.size(); i++) {
            data[i] = loanDataList.get(i);
        }

        // loan table
        String[] columnNames = {"Card Number", "Loan Amount", "TotalAmount" , "Time Period", "Loan Type", "Interest", "Status"};
        loanTable = new JTable(data,columnNames);
        loanTable.setFont(new Font("Aerial", Font.PLAIN, 15));
        loanTable.setForeground(Color.BLACK);
        loanTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        loanTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        loanTable.getColumnModel().getColumn(3).setPreferredWidth(50);
        loanTable.getColumnModel().getColumn(5).setPreferredWidth(30);

        JScrollPane scrollPane = new JScrollPane(loanTable);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBounds(50 , 300 , 700 , 130);
        image.add(scrollPane);

        approve = new JButton("Approve");
        approve.setBounds(800 , 330 , 100 , 20 );
        approve.addActionListener(this);
        approve.setEnabled(false);
        image.add(approve);

        decline = new JButton("Decline");
        decline.setBounds(800 , 380 , 100 , 20);
        decline.addActionListener(this);
        decline.setEnabled(false);
        image.add(decline);

        exit = new JButton(" Exit  ");
        exit.setBounds(820 , 600 , 100 , 40);
        exit.addActionListener(this);
        exit.setForeground(Color.WHITE);
        exit.setBackground(Color.BLACK);
        image.add(exit);

        loanTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                selectedRow = loanTable.getSelectedRow(); // Get the selected row
                if (selectedRow != -1) { // Check if a row is selected
                    String status = (String) loanTable.getValueAt(selectedRow, 6); // Get the status from the selected row
                    if ("Pending".equals(status)) { // Check if the status is "Pending"
                        approve.setEnabled(true); // Enable the approve button
                        decline.setEnabled(true); // Enable the decline button
                    }
                }
            }
        });
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){

        if(selectedRow != -1){
            String cardNumber = (String) loanTable.getValueAt(selectedRow, 0);

            if(ae.getSource() == approve){
                approve.setEnabled(false);
                decline.setEnabled(false);
                // Update status in database to "Approved"
                updateLoanStatus("Approved", cardNumber);
                // Update status in table model
                loanTable.setValueAt("Approved", selectedRow, 6);
                double amount = (double) loanTable.getValueAt(selectedRow , 1);
                transferMoney(amount , cardNumber);
            }else if(ae.getSource() == decline){
                approve.setEnabled(false);
                decline.setEnabled(false);
                //Update status in database to "Approved"
                updateLoanStatus("Rejected", cardNumber);
                // Update status in table model
                loanTable.setValueAt("Rejected", selectedRow, 6);
            }
        }

        if(ae.getSource() == userDropdown){
            String selectedUser = (String) userDropdown.getSelectedItem();
            String[] str = selectedUser.split(" ");
            String num = str[0];

            try{
                Connect c = new Connect() ;
                String query1 = "SELECT s1.name , s1.fathers_name , s1.email , s1.state , s2.occupation , s2.income , l.balance\n" +
                        "FROM login l " +
                        "JOIN signup s1 ON l.formno = s1.formno " +
                        "JOIN signuptwo s2 ON l.formno = s2.formno " +
                        "JOIN signupthree s3 ON l.formno = s3.formno " +
                        "WHERE l.formno = '"+num+"' " ;

                ResultSet userData = c.s.executeQuery(query1);
                if(userData.next()){
                    StringBuilder userDetails = new StringBuilder();
                    userDetails.append("Name: ").append(userData.getString("name")).append("\n");
                    userDetails.append("Father's Name: ").append(userData.getString("fathers_name")).append("\n");
                    userDetails.append("Email: ").append(userData.getString("email")).append("\n");
                    userDetails.append("Occupation: ").append(userData.getString("occupation")).append("\n");
                    userDetails.append("Income: ").append(userData.getString("income")).append("\n");
                    userDetails.append("Balance: ").append(userData.getString("balance")).append("\n");
                    userDetails.append("State: ").append(userData.getString("state")).append("\n");


                    userDetailsArea.setText(userDetails.toString());
                }
            }catch (Exception e){
                System.out.println(e);
            }

        }

        if(ae.getSource() == exit){
            setVisible(false);
        }


    }

    public void transferMoney(double amount , String cardNumber){
        try{
            Connect c = new Connect();
            Date date = new Date();

            String query3 = "SELECT * FROM login WHERE cardNumber = '"+cardNumber+"' " ;
            ResultSet rs = c.s.executeQuery(query3);
            while(rs.next()){
                pinNumber = rs.getString("pin");
            }

            String loanAmount = String.valueOf(amount);

            String query1 = "INSERT INTO bank VALUES('"+cardNumber+"' , '"+pinNumber+"' , '"+date+"' , 'Loan' , '"+loanAmount+"')";
            c.s.executeUpdate(query1);
            String query2 = "UPDATE login SET balance = balance + '"+ amount + "' WHERE cardNumber = '"+cardNumber+"' AND pin = '"+pinNumber+"' ;";
            c.s.executeUpdate(query2);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void updateLoanStatus(String status , String cardNumber){
        try{
            Connect c = new Connect();
            String query = "UPDATE loan SET Statuss = '"+status+"' WHERE cardNumber = '"+cardNumber+"'";
            c.s.executeUpdate(query);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void main (String[]args){
        new ManagerDashboard();
    }
}
