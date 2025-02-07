package management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Calendar;
import java.util.Random;

import com.toedter.calendar.JDateChooser;

public class AddStudent extends JFrame implements ActionListener {
    JTextField tfname, tffname ,tfaddress,tfphone,tfemail,tfx,tfxii,tfaadhar;
    JLabel labelrollno;
    JDateChooser dcdob;
    JComboBox cbcourse,cbbranch;
    JButton submit,cancel;

    Random ran = new Random();
    long first4 = Math.abs((ran.nextLong() % 9000L)+1000L);

    AddStudent(){
        setSize(900,700);
        setLocation(350,50);

        setLayout(null);
        JLabel heading = new JLabel("New Student Details");
        heading.setBounds(310,30,500,50);
        heading.setFont(new Font("serif",Font.BOLD,30));
        add(heading);

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(50,150,100,30);
        lblname.setFont(new Font("serif",Font.BOLD,20));
        add(lblname);

        tfname = new JTextField();
        tfname.setBounds(200,150,150,30);
        add(tfname);

        JLabel lblfname = new JLabel("Father's Name");
        lblfname.setBounds(400,150,200,30);
        lblfname.setFont(new Font("serif",Font.BOLD,20));
        add(lblfname);

        tffname = new JTextField();
        tffname.setBounds(600,150,150,30);
        add(tffname);

        JLabel lblrollno = new JLabel("Roll No.");
        lblrollno.setBounds(50,200,200,30);
        lblrollno.setFont(new Font("serif",Font.BOLD,20));
        add(lblrollno);

        labelrollno = new JLabel("22011"+first4);
        labelrollno.setBounds(200,200,200,30);
        labelrollno.setFont(new Font("serif",Font.BOLD,20));
        add(labelrollno);

        JLabel lbldob = new JLabel("Date of Birth");
        lbldob.setBounds(400,200,200,30);
        lbldob.setFont(new Font("serif",Font.BOLD,20));
        add(lbldob);

        dcdob = new JDateChooser();
        dcdob.setBounds(600,200,150,30);
        add(dcdob);

        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(50,250,100,30);
        lbladdress.setFont(new Font("serif",Font.BOLD,20));
        add(lbladdress);

        tfaddress = new JTextField();
        tfaddress.setBounds(200,250,150,30);
        add(tfaddress);

        JLabel lblphone = new JLabel("Phone no.");
        lblphone.setBounds(400,250,200,30);
        lblphone.setFont(new Font("serif",Font.BOLD,20));
        add(lblphone);

        tfphone = new JTextField();
        tfphone.setBounds(600,250,150,30);
        add(tfphone);

        JLabel lblemail = new JLabel("Email id");
        lblemail.setBounds(50,300,100,30);
        lblemail.setFont(new Font("serif",Font.BOLD,20));
        add(lblemail);

        tfemail = new JTextField("@gmail.com");
        tfemail.setBounds(200,300,150,30);
        add(tfemail);

        JLabel lblx = new JLabel("Class 10th score");
        lblx.setBounds(400,300,200,30);
        lblx.setFont(new Font("serif",Font.BOLD,20));
        add(lblx);

        tfx = new JTextField();
        tfx.setBounds(600,300,150,30);
        add(tfx);

        JLabel lblxii = new JLabel("Class 12th score");
        lblxii.setBounds(50,350,200,30);
        lblxii.setFont(new Font("serif",Font.BOLD,20));
        add(lblxii);

        tfxii = new JTextField();
        tfxii.setBounds(200,350,150,30);
        add(tfxii);

        JLabel lblaadhar = new JLabel("Aadhar no.");
        lblaadhar.setBounds(400,350,200,30);
        lblaadhar.setFont(new Font("serif",Font.BOLD,20));
        add(lblaadhar);

        tfaadhar = new JTextField();
        tfaadhar.setBounds(600,350,150,30);
        add(tfaadhar);

        JLabel lblcourse = new JLabel("Course");
        lblcourse.setBounds(50,400,200,30);
        lblcourse.setFont(new Font("serif",Font.BOLD,20));
        add(lblcourse);

        String course[] = {"B.Tech","M.Tech","BCA","MCA","Bsc","Msc","BBA","MBA"};
        cbcourse = new JComboBox(course);
        cbcourse.setBounds(200,400,150,30);
        add(cbcourse);

        JLabel lblbranch = new JLabel("Branch");
        lblbranch.setBounds(400,400,200,30);
        lblbranch.setFont(new Font("serif",Font.BOLD,20));
        add(lblbranch);

        String branch[] = {"Computer Science","Mechanical","Electrical","Electronics","Civil","IT","Agriculture"};
        cbbranch = new JComboBox(branch);
        cbbranch.setBounds(600,400,150,30);
        add(cbbranch);

        submit = new JButton("Submit");
        submit.setBounds(250,550,120,30);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(450,550,120,30);
        cancel.setBackground(Color.RED);
        cancel.addActionListener(this);
        add(cancel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == submit){
            String name = tfname.getText();
            String fname = tffname.getText();
            String rollno = labelrollno.getText();
            String dob = ((JTextField) dcdob.getDateEditor().getUiComponent()).getText();
            String address = tfaddress.getText();
            String phone = tfphone.getText();
            String email = tfemail.getText();
            String x = tfx.getText();
            String xii = tfxii.getText();
            String aadhar = tfaadhar.getText();
            String course = (String)cbcourse.getSelectedItem();
            String branch = (String)cbbranch.getSelectedItem();

            // Input validation
            if (phone.length() != 10 || !phone.matches("\\d+")) {
                JOptionPane.showMessageDialog(null, "Please enter a valid 10-digit phone number.");
                return;
            }

            if (!email.endsWith("@gmail.com") || email.length() <= 10) {
                JOptionPane.showMessageDialog(null, "Please enter a valid email ending with @gmail.com.");
                return;
            }

            // Date of birth validation - checking if the student is at least 16 years old
            Calendar dobCal = Calendar.getInstance();
            dobCal.setTime(dcdob.getDate());
            int year = dobCal.get(Calendar.YEAR);

            Calendar today = Calendar.getInstance();
            int age = today.get(Calendar.YEAR) - year;
            if (age < 16) {
                JOptionPane.showMessageDialog(null, "Student should be at least 16 years old.");
                return;
            }

            // Checking if Roll No. is unique
            try {
                Conn con = new Conn();
                String queryCheck = "SELECT * FROM student WHERE rollno = '" + rollno + "'";
                ResultSet rs = con.s.executeQuery(queryCheck);
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Roll Number already exists.");
                    return;
                }

                String query = "INSERT INTO student VALUES('"+name+"', '"+fname+"', '"+rollno+"', '"+dob+"', '"+address+"', '"+phone+"', '"+email+"', '"+x+"', '"+xii+"', '"+aadhar+"', '"+course+"', '"+branch+"')";
                con.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null,"Student Details Inserted Successfully");
                setVisible(false);
            } catch(Exception e) {
                e.printStackTrace();
            }

        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new AddStudent();
    }
}
