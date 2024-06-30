import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class ManagerDashboard extends JFrame implements ActionListener , MouseListener {
    private JLabel loanTableLabel , topRichest , totalUsersLabel, logoImage, heading , topRichestLabel , userDetails , image , totalDepositsLabel ;
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

        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("image/logo.jpg"));
        Image img3 = i3.getImage();
        Image img4 = img3.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon i4 = new ImageIcon(img4);

        logoImage = new JLabel(i4);
        logoImage.setBounds(10, 10, 80, 80);
        image.add(logoImage);

        heading = new JLabel("Lena Dena Bank Pvt Ltd");
        heading.setBounds(280, 20, 500, 50);
        heading.setFont(new Font("Aerial", Font.BOLD, 40));
        heading.setForeground(Color.WHITE);
        image.add(heading);

        // Total Users Label
        totalUsersLabel = new JLabel("Total Active Users: ");
        totalUsersLabel.setBounds(50, 500, 200, 30);
        totalUsersLabel.setFont(new Font("Arial", Font.BOLD, 20));
        totalUsersLabel.setForeground(Color.WHITE);
        image.add(totalUsersLabel);

        // Total Deposits Label
        totalDepositsLabel = new JLabel("Total Amount Deposited: ");
        totalDepositsLabel.setBounds(50, 590, 400, 50);
        totalDepositsLabel.setFont(new Font("Arial", Font.BOLD, 20));
        totalDepositsLabel.setForeground(Color.WHITE);
        image.add(totalDepositsLabel);

        topRichest = new JLabel("Top Richest Users: ");
        topRichest.setBounds(50, 110, 200, 40);
        topRichest.setFont(new Font("Arial", Font.BOLD, 20));
        topRichest.setForeground(Color.WHITE);
        image.add(topRichest);

        topRichestLabel = new JLabel( );
        topRichestLabel.setBounds(250, 100, 500, 100);
        topRichestLabel.setFont(new Font("Arial", Font.PLAIN, 18));
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

            // Find top richest users
            String topUsersQuery = "SELECT l.balance, s.name, s.fathers_name " +
                    "FROM login l " +
                    "JOIN signup s ON l.formno = s.formno " +
                    "ORDER BY l.balance DESC " +
                    "LIMIT 3";;
            ResultSet topUsersRs = c.s.executeQuery(topUsersQuery);
            StringBuilder richestUsers = new StringBuilder("<html>");
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
            list.add("Select User Name");
            while (userListRs.next()) {
                String userName = userListRs.getString("name");
                String userformno = userListRs.getString("formno");
                System.out.println("Adding to JComboBox: " + userformno + " " + userName);
                String text = userformno + " " + userName;
                list.add(text);
            }

            userDropdownItems = list.toArray(new String[0]);

        } catch (Exception e) {
           //System.out.println("Error fetching user list: " + e.getMessage());
        }

        userDetails = new JLabel("User Details Section ");
        userDetails.setBounds(50, 380 , 250, 40);
        userDetails.setFont(new Font("Arial", Font.BOLD, 20));
        userDetails.setForeground(Color.WHITE);
        image.add(userDetails);

        // User Dropdown
        userDropdown = new JComboBox<>(userDropdownItems);
        userDropdown.setBounds(50, 430, 200, 30);
        userDropdown.addActionListener(this);
        image.add(userDropdown);

        // User Details Area
        userDetailsArea = new JTextArea();
        userDetailsArea.setBounds(300, 430 , 400, 150);
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

            while (rs.next()) {
                Object[] row = new Object[8];
                row[0] = rs.getString("loanID");
                row[1] = rs.getString("name");
                row[2] = rs.getDouble("LoanAmount");
                row[3] = rs.getDouble("TotalAmount");
                row[4] = rs.getInt("TimePeriod");
                row[5] = rs.getString("LoanType");
                row[6] = rs.getDouble("Interest");
                row[7] = rs.getString("Statuss");

                loanDataList.add(row);
            }
        }catch (Exception e){
            //System.out.println(e);
        }

        Object[][] data = new Object[loanDataList.size()][8];
        for (int i = 0; i < loanDataList.size(); i++) {
            data[i] = loanDataList.get(i);
        }


        loanTableLabel = new JLabel("Loan Table");
        loanTableLabel.setBounds(50, 180, 200, 50);
        loanTableLabel.setFont(new Font("Arial", Font.BOLD, 20));
        loanTableLabel.setForeground(Color.WHITE);
        image.add(loanTableLabel);


        // loan table
        String[] columnNames = {"loanID" , "Name", "Loan Amount", "TotalAmount" , "Time Period", "Loan Type", "Interest", "Status"};
        loanTable = new JTable(data,columnNames);
        loanTable.setFont(new Font("Aerial", Font.PLAIN, 15));
        loanTable.setForeground(Color.BLACK);
        loanTable.addMouseListener(this);
        loanTable.getColumnModel().getColumn(4).setPreferredWidth(50);
        loanTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        loanTable.getColumnModel().getColumn(6).setPreferredWidth(50);
        loanTable.getColumnModel().getColumn(5).setPreferredWidth(100);

        JScrollPane scrollPane = new JScrollPane(loanTable);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBounds(50 , 230 , 700 , 130);
        image.add(scrollPane);

        approve = new JButton("Approve");
        approve.setBounds(800 , 250 , 100 , 20 );
        approve.addActionListener(this);
        approve.setEnabled(false);
        image.add(approve);

        decline = new JButton("Decline");
        decline.setBounds(800 , 300 , 100 , 20);
        decline.addActionListener(this);
        decline.setEnabled(false);
        image.add(decline);

        exit = new JButton(" Exit  ");
        exit.setBounds(450 , 640 , 100 , 40);
        exit.addActionListener(this);
        exit.setForeground(Color.WHITE);
        exit.setBackground(Color.BLACK);
        image.add(exit);
        setVisible(true);
    }

    public void mouseClicked(MouseEvent me){
        selectedRow = loanTable.getSelectedRow();
        if (selectedRow != -1) { // Check if a row is selected
            String status = (String) loanTable.getValueAt(selectedRow, 7); // Get the status from the selected row
            if ("Pending".equals(status)) {
                approve.setEnabled(true);
                decline.setEnabled(true);
            }
        }
    }

    // other mouse listener methods
    public void mousePressed(MouseEvent me) {}
    public void mouseReleased(MouseEvent me) {}
    public void mouseEntered(MouseEvent me) {}
    public void mouseExited(MouseEvent me){}

    public void actionPerformed(ActionEvent ae){

        if(selectedRow != -1){
            String num = (String) loanTable.getValueAt(selectedRow,0 );
            int loanID = Integer.parseInt(num);
            String cardNumber = "";
            try{
                Connect c = new Connect();
                String query = "SELECT cardNumber FROM loan WHERE loanID = "+loanID+" ";
                ResultSet rs =  c.s.executeQuery(query);
                if(rs.next()){
                    cardNumber = rs.getString("cardNumber");
                }
            }catch (Exception e){
                System.out.println(e);
            }

            if(ae.getSource() == approve){
                approve.setEnabled(false);
                decline.setEnabled(false);
                // Update status in database to "Approved"
                updateLoanStatus("Approved", cardNumber , loanID);
                // Update status in table model
                loanTable.setValueAt("Approved", selectedRow, 7);
                double amount = (double) loanTable.getValueAt(selectedRow , 2);
                transferMoney(amount , cardNumber);
            }else if(ae.getSource() == decline){
                approve.setEnabled(false);
                decline.setEnabled(false);
                //Update status in database to "Approved"
                updateLoanStatus("Rejected", cardNumber , loanID);
                // Update status in table model
                loanTable.setValueAt("Rejected", selectedRow, 7);
            }
        }

        if(ae.getSource() == userDropdown){
            String selectedUser = (String) userDropdown.getSelectedItem();
            if(selectedUser.equals("Select User Name")){
                return;
            }else{
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

    public void updateLoanStatus(String status , String cardNumber , int loanID){
        try{
            Connect c = new Connect();
            String query = "UPDATE loan SET Statuss = '"+status+"' WHERE cardNumber = '"+cardNumber+"' AND loanID ="+loanID+" ";
            c.s.executeUpdate(query);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void main (String[]args){
        new ManagerDashboard();
    }
}
