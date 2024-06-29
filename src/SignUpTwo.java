import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class SignUpTwo extends JFrame implements ActionListener {
    private JButton next, cancel ;
    private JRadioButton yes , no;
    private JLabel heading1 ,formNo , heading2, religion, category, income, edu,
            occupation, pan, adhar,  senior, logo , image;
    private JTextField panText , adharText ;
    private JComboBox religionText ,categoryText , incomeText, eduText, occupationText ;
    private String formNum ;
    public SignUpTwo(String formNum){
        this.formNum = formNum ;
        setTitle("Lena Dena Bank Pvt Ltd");
        setSize(1000 , 700);
        setLocation(250 , 50 );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

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

        formNo = new JLabel("Form No - " + formNum);
        formNo.setBounds(390 , 75 , 300 , 25);
        formNo.setFont(new Font("",Font.BOLD , 20 ));
        formNo.setForeground(Color.WHITE);
        image.add(formNo);

        heading2 = new JLabel("Page 2: Additional Details ");
        heading2.setBounds(350 , 100 , 300 , 25);
        heading2.setFont(new Font("",Font.BOLD , 20 ));
        heading2.setForeground(Color.WHITE);
        image.add(heading2);

        religion = new JLabel("Religion:");
        religion.setBounds(120 , 170 , 150 , 25);
        religion.setFont(new Font("",Font.PLAIN, 20 ));
        religion.setForeground(Color.WHITE);
        image.add(religion);

        String valReligion[] = {"Hindu" , "Muslim" , "Sikh" , "Christian" , "Others"};
        religionText = new JComboBox(valReligion);
        religionText.setBounds(300 , 170 , 400 , 25);
        religionText.setBackground(Color.WHITE);
        image.add(religionText);

        category = new JLabel("Category:");
        category.setBounds(120 , 210 , 150 , 25);
        category.setFont(new Font("",Font.PLAIN , 20 ));
        category.setForeground(Color.WHITE);
        image.add(category);

        String valCategory[] = {"General" , "OBC" , "SC" , "ST" , "Others"};
        categoryText = new JComboBox(valCategory);
        categoryText.setBounds(300 , 210 , 400 , 25);
        categoryText.setBackground(Color.WHITE);
        image.add(categoryText);

        income = new JLabel("Income:");
        income.setBounds(120 , 250 , 150 , 25);
        income.setFont(new Font("",Font.PLAIN , 20 ));
        income.setForeground(Color.WHITE);
        image.add(income);

        String valIncome[] = {"NULL" , "< 1,50,000" , "< 2,50,000" , "< 5,00,000" , "upto 10,00,000" , "above 1,00,000"};
        incomeText = new JComboBox(valIncome);
        incomeText.setBounds(300 , 250 , 400 , 25);
        incomeText.setBackground(Color.WHITE);
        image.add(incomeText);

        edu = new JLabel("Educational:");
        edu.setBounds(120 , 290 , 150 , 25);
        edu.setFont(new Font("",Font.PLAIN , 20 ));
        edu.setForeground(Color.WHITE);
        image.add(edu);

        String valEdu[] = {"Non-Graduate" , "Graduate" , "Post-Graduate" , "Doctrate" , "Others"};
        eduText = new JComboBox(valEdu);
        eduText.setBounds(300 , 290 , 400 , 25);
        eduText.setBackground(Color.WHITE);
        image.add(eduText);

        occupation = new JLabel("Occupation:");
        occupation.setBounds(120 , 330 , 150 , 25);
        occupation.setFont(new Font("",Font.PLAIN , 20 ));
        occupation.setForeground(Color.WHITE);
        image.add(occupation);

        String valOccupation[] = {"Salaried" , "Self-Employed" , "Business" , "Student" , "Retired" , "Others"};
        occupationText = new JComboBox(valOccupation);
        occupationText.setBounds(300 , 330 , 400 , 25);
        occupationText.setBackground(Color.WHITE);
        image.add(occupationText);

        pan = new JLabel("Pan Number:");
        pan.setBounds(120 , 370 , 150 , 25);
        pan.setFont(new Font("",Font.PLAIN , 20 ));
        pan.setForeground(Color.WHITE);
        image.add(pan);

        panText = new JTextField();
        panText.setBounds(300 , 370 , 400 , 25);
        image.add(panText);

        adhar = new JLabel("Aadhar Number:");
        adhar.setBounds(120 , 410 , 150 , 25);
        adhar.setFont(new Font("",Font.PLAIN , 20 ));
        adhar.setForeground(Color.WHITE);
        image.add(adhar);

        adharText = new JTextField();
        adharText.setBounds(300 , 410 , 400 , 25);
        image.add(adharText);

        senior = new JLabel("Senior Citizen:");
        senior.setBounds(120 , 450 , 150 , 25);
        senior.setFont(new Font("",Font.PLAIN , 20 ));
        senior.setForeground(Color.WHITE);
        image.add(senior);

        yes = new JRadioButton(" Yes ");
        yes.setBounds(300 , 450 , 100 , 25);
        yes.setFont(new Font("",Font.PLAIN , 18 ));
        yes.setBackground(Color.WHITE);
        image.add(yes);

        no = new JRadioButton(" No ");
        no.setBounds(420 , 450 , 100 , 25);
        no.setFont(new Font("",Font.PLAIN , 18 ));
        no.setBackground(Color.WHITE);
        image.add(no);

        ButtonGroup seniorCitizen = new ButtonGroup();
        seniorCitizen.add(yes);
        seniorCitizen.add(no);

        next = new JButton(" NEXT ");
        next.setBounds(400 , 600 , 100 , 25);
        next.setForeground(Color.WHITE);
        next.setBackground(Color.YELLOW);
        next.addActionListener(this);
        next.setForeground(Color.BLACK);
        image.add(next);

        cancel = new JButton(" BACK ");
        cancel.setBounds(520 , 600 , 100 , 25);
        cancel.setForeground(Color.WHITE);
        cancel.setBackground(Color.YELLOW);
        cancel.addActionListener(this);
        cancel.setForeground(Color.BLACK);
        image.add(cancel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==next){
            String formno = "" + formNum ;
            String sreligion = (String) religionText.getSelectedItem();
            String scategory = (String) categoryText.getSelectedItem();
            String sincome = (String) incomeText.getSelectedItem();
            String sedu = (String) eduText.getSelectedItem();
            String soccupation = (String) occupationText.getSelectedItem();
            String senior = null ;
            if(yes.isSelected()){
                senior = "Yes";
            }else if(no.isSelected()){
                senior = "No";
            }
            String span = panText.getText();
            String sadhar = adharText.getText();

            try {
                if(sreligion.equals("") || scategory.equals("") || sincome.equals("") || sedu.equals("") || soccupation.equals("") || span.equals("") || sadhar.equals("")){
                    JOptionPane.showMessageDialog(null,"All fields are required");
                } else if (!isValidNumber(sadhar)) {
                    JOptionPane.showMessageDialog(null,"enter a valid Adhar Number");
                } else if (!isValidPan(span)) {
                    JOptionPane.showMessageDialog(null,"enter a valid Pan number");
                } else{
                    Connect c = new Connect();
                    String query = "INSERT INTO signuptwo VALUES('" + formNum +"' , '" + sreligion + "', '" + scategory + "', '" + sincome + "', '" + sedu + "', '" + soccupation + "', '" + span + "', '" + sadhar + "', '" + senior + "')";
                    c.s.executeUpdate(query);
                    new SignUpThree(formNum) ;
                    setVisible(false);
                }
            } catch(Exception e){
                System.out.println(e.getMessage());
                System.out.println(e);
            }
        }else if(ae.getSource()==cancel){
            new Login();
            setVisible(false);
        }
    }

    private boolean isValidNumber(String phoneNumber) {
        String regex = "^\\d{12}$";
        return Pattern.matches(regex, phoneNumber);
    }

    private boolean isValidPan(String phoneNumber) {
        // Regex to match a 10-digit number with at least one alphabet and one digit
        String regex = "^(?=.*[0-9])(?=.*[a-zA-Z])[0-9a-zA-Z]{10}$";
        return Pattern.matches(regex, phoneNumber);
    }


    public static void main(String[] args) {

        new SignUpTwo("");
    }
}
