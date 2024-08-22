import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.regex.Pattern;
import com.toedter.calendar.JDateChooser;


public class SignUpOne extends JFrame implements ActionListener {
    private String formNum ;
    private JDateChooser date ;
    private JLabel name, fname, dob, gender, email, marital, address, image ,
            city, pinCode, state, heading1, heading2, logo, formNo ;
    private JTextField nameText , fnameText, emailText, addressText,
            cityText, pinCodeText ;
    private JRadioButton male , female , others, married , unmarried ;
    private JButton next , cancel ;
    private JComboBox<String> stateText ;

    public SignUpOne(){
        setTitle("Lena Dena Bank Pvt Ltd");
        setSize(1000 , 700);
        setLocation(250 , 50 );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(32, 107, 150));
        setUndecorated(true);
        setLayout(null);

        // bank logo
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("image/logo.jpg"));
        Image img1 = i1.getImage();
        int w = 80;
        int h = 80;
        Image img2 = img1.getScaledInstance(w , h ,Image.SCALE_SMOOTH );
        ImageIcon i2 = new ImageIcon(img2);
        logo = new JLabel(i2);
        logo.setBounds(70 , 10 , w , h );
        add(logo);

        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("image/backbg.png"));
        Image img3 = i3.getImage();
        Image img4 = img3.getScaledInstance(1000 , 700 ,Image.SCALE_SMOOTH );
        ImageIcon i4 = new ImageIcon(img4);

        image = new JLabel(i4);
        image.setBounds(0 , 0,1000 , 700 );
        add(image);

        heading1 = new JLabel("NEW ACCOUNT OPENING FORM ");
        heading1.setBounds(200 , 20 , 650 , 50);
        heading1.setFont(new Font("",Font.BOLD , 40 ));
        heading1.setForeground(Color.WHITE);
        image.add(heading1);

        formNum = generateRandomDigits(4);
        formNo = new JLabel("Form No - " + formNum);
        formNo.setBounds(390 , 75 , 300 , 25);
        formNo.setFont(new Font("",Font.BOLD , 20 ));
        formNo.setForeground(Color.WHITE);
        image.add(formNo);

        heading2 = new JLabel("Page 1: Personal Details ");
        heading2.setBounds(350 , 100 , 300 , 25);
        heading2.setFont(new Font("",Font.BOLD , 20 ));
        heading2.setForeground(Color.WHITE);
        image.add(heading2);

        name = new JLabel("Full Name:");
        name.setBounds(120 , 170 , 150 , 25);
        name.setFont(new Font("",Font.PLAIN, 20 ));
        name.setForeground(Color.WHITE);
        image.add(name);

        nameText = new JTextField();
        nameText.setBounds(300 , 170 , 400 , 25);
        image.add(nameText);

        fname = new JLabel("Father's Name:");
        fname.setBounds(120 , 210 , 150 , 25);
        fname.setFont(new Font("",Font.PLAIN , 20 ));
        fname.setForeground(Color.WHITE);
        image.add(fname);

        fnameText = new JTextField();
        fnameText.setBounds(300 , 210 , 400 , 25);
        image.add(fnameText);

        dob = new JLabel("Date Of Birth:");
        dob.setBounds(120 , 250 , 150 , 25);
        dob.setFont(new Font("",Font.PLAIN , 20 ));
        dob.setForeground(Color.WHITE);
        image.add(dob);

        date = new JDateChooser();
        date.setBounds(300 , 250 , 400 , 25);
        image.add(date);


        gender = new JLabel("Gender:");
        gender.setBounds(120 , 290 , 150 , 25);
        gender.setFont(new Font("",Font.PLAIN , 20 ));
        gender.setForeground(Color.WHITE);
        image.add(gender);

        male = new JRadioButton(" Male ");
        male.setBounds(300 , 290 , 100 , 25);
        male.setFont(new Font("",Font.PLAIN , 17 ));
        male.setBackground(Color.WHITE);
        image.add(male);

        female = new JRadioButton(" Female ");
        female.setBounds(420 , 290 , 100 , 25);
        female.setFont(new Font("",Font.PLAIN , 17 ));
        female.setBackground(Color.WHITE);
        image.add(female);

        others = new JRadioButton(" Others ");
        others.setBounds(540 , 290 , 100 , 25);
        others.setFont(new Font("",Font.PLAIN , 17 ));
        others.setBackground(Color.WHITE);
        image.add(others);

        ButtonGroup genderButtons = new ButtonGroup();
        genderButtons.add(male);
        genderButtons.add(female);
        genderButtons.add(others);

        email = new JLabel("Email Address:");
        email.setBounds(120 , 330 , 150 , 25);
        email.setFont(new Font("",Font.PLAIN , 20 ));
        email.setForeground(Color.WHITE);
        image.add(email);

        emailText = new JTextField();
        emailText.setBounds(300 , 330 , 400 , 25);
        image.add(emailText);

        marital = new JLabel("Marital Status:");
        marital.setBounds(120 , 370 , 150 , 25);
        marital.setFont(new Font("",Font.PLAIN , 20 ));
        marital.setForeground(Color.WHITE);
        image.add(marital);

        married = new JRadioButton(" Married ");
        married.setBounds(300 , 370 , 100 , 25);
        married.setFont(new Font("",Font.PLAIN , 18 ));
        married.setBackground(Color.WHITE);
        image.add(married);

        unmarried = new JRadioButton(" Unmarried ");
        unmarried.setBounds(420 , 370 , 150 , 25);
        unmarried.setFont(new Font("",Font.PLAIN , 18 ));
        unmarried.setBackground(Color.WHITE);
        image.add(unmarried);

        ButtonGroup marital = new ButtonGroup();
        marital.add(married);
        marital.add(unmarried);

        address = new JLabel("Address:");
        address.setBounds(120 , 410 , 150 , 25);
        address.setFont(new Font("",Font.PLAIN , 20 ));
        address.setForeground(Color.WHITE);
        image.add(address);

        addressText = new JTextField();
        addressText.setBounds(300 , 410 , 400 , 25);
        image.add(addressText);

        city = new JLabel("City:");
        city.setBounds(120 , 450 , 150 , 25);
        city.setFont(new Font("",Font.PLAIN , 20 ));
        city.setForeground(Color.WHITE);
        image.add(city);

        cityText = new JTextField();
        cityText.setBounds(300 , 450 , 400 , 25);
        image.add(cityText);

        pinCode = new JLabel("Pin Code:");
        pinCode.setBounds(120 , 490 , 150 , 25);
        pinCode.setFont(new Font("",Font.PLAIN , 20 ));
        pinCode.setForeground(Color.WHITE);
        image.add(pinCode);

        pinCodeText = new JTextField();
        pinCodeText.setBounds(300 , 490 , 400 , 25);
        image.add(pinCodeText);

        state = new JLabel("State:");
        state.setBounds(120 , 530 , 440 , 25);
        state.setFont(new Font("",Font.PLAIN , 20 ));
        state.setForeground(Color.WHITE);
        image.add(state);

        String[] states = {
                "" , "Andaman and Nicobar Islands", "Andhra Pradesh", "Arunachal Pradesh",
                "Assam", "Bihar", "Chandigarh", "Chhattisgarh", "Dadra and Nagar Haveli and Daman and Diu",
                "Delhi", "Goa", "Gujarat", "Haryana", "Himachal Pradesh", "Jammu and Kashmir",
                "Jharkhand", "Karnataka", "Kerala", "Ladakh", "Lakshadweep", "Madhya Pradesh",
                "Maharashtra", "Manipur", "Meghalaya", "Mizoram", "Nagaland", "Odisha",
                "Puducherry", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu", "Telangana",
                "Tripura", "Uttar Pradesh", "Uttarakhand", "West Bengal"
        };


        stateText = new JComboBox<>(states);
        stateText.setBounds(300 , 530 , 400 , 25);
        stateText.addActionListener(this);
        image.add(stateText);

        next = new JButton(" NEXT ");
        next.setBounds(400 , 600 , 100 , 25);
        next.setForeground(Color.BLACK);
        next.setBackground(Color.YELLOW);
        next.addActionListener(this);
        image.add(next);

        cancel = new JButton(" BACK ");
        cancel.setBounds(520 , 600 , 100 , 25);
        cancel.setForeground(Color.BLACK);
        cancel.setBackground(Color.YELLOW);
        cancel.addActionListener(this);
        image.add(cancel);

        setVisible(true);
    }

    private String generateRandomDigits(int length){
        StringBuilder num = new StringBuilder();
        Random random = new Random();
        for(int i=0 ; i< length ; i++){
            num.append(random.nextInt(10));
        }
        return num.toString();
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==next){
            String formno = "" + formNum ;
            String name = nameText.getText();
            String fname = fnameText.getText();
            String dob = ((JTextField) date.getDateEditor().getUiComponent()).getText();
            String gender = "NULL";
            if(male.isSelected()){
                gender = "Male";
            }else if(female.isSelected()){
                gender = "Female";
            }else if(others.isSelected()){
                gender = "Others";
            }
            String email = emailText.getText();
            String marital = "NULL";
            if(married.isSelected()){
                marital = "Married";
            }else if(unmarried.isSelected()){
                marital = "Unmarried";
            }
            String address = addressText.getText();
            String city = cityText.getText();
            String pin = pinCodeText.getText();
            String state = "" ;
            state = (String) stateText.getSelectedItem();

            if (!isValidName(name)) {
                JOptionPane.showMessageDialog(null, "Please enter a valid name.");
            } else if (!isValidName(fname)) {
                JOptionPane.showMessageDialog(null, "Please enter a valid fathers name.");
            } else if (!isValidName(city)) {
                JOptionPane.showMessageDialog(null, "Please enter a valid city name.");
            } else if (!isValidPin(pin)) {
                JOptionPane.showMessageDialog(null, "Please enter a valid pin code.");
            } else if (name.equals("") || fname.equals("") || dob.equals("") || email.equals("") || address.equals("") || city.equals("") || pin.equals("") || state.equals("")) {
                JOptionPane.showMessageDialog(null, "All fields are required.");
            } else if (!isValidEmail(email)) {
                JOptionPane.showMessageDialog(null, "Enter a valid email.");
            } else {
                try {
                    Connect c = new Connect();
                    String query = "INSERT INTO signup VALUES('" + formNum +"' , '" + name + "', '" + fname + "', '" + dob + "', '" + gender + "', '" + email + "', '" + marital + "', '" + address + "', '" + city + "', '" + pin + "', '" + state + "')";
                    c.s.executeUpdate(query);
                    new SignUpTwo(formNum);
                    setVisible(false);
                } catch(Exception e){
                    System.out.println(e.getMessage());
                    System.out.println(e);
                }
            }


        }else if(ae.getSource() == cancel){
            new Login();
            setVisible(false);
        }
    }

    private boolean isValidName(String name) {
        String regex = "^[A-Za-z\\s]+$";
        return Pattern.matches(regex, name);
    }

    private boolean isValidPin(String phoneNumber) {
        String regex = "^\\d{4,6}$";
        return Pattern.matches(regex, phoneNumber);
    }



    private boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return Pattern.matches(regex, email);
    }

    public static void main(String[] args) {
        new SignUpOne();
    }
}
