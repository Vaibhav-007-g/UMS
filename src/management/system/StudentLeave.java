package management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import com.toedter.calendar.JDateChooser;

public class StudentLeave extends JFrame implements ActionListener {

    Choice crollno, ctime;
    JDateChooser dcdate;
    JButton submit,cancel;

    StudentLeave(){

        setSize(500,550);
        setLocation(550,100);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("Apply leave (Student)");
        heading.setBounds(40,50,300,30);
        heading.setFont(new Font("Tahoma",Font.BOLD,20));
        add(heading);

        JLabel lblrollno = new JLabel("Search by Roll Number");
        lblrollno.setBounds(60,100,200,20);
        lblrollno.setFont(new Font("Tahoma",Font.PLAIN,18));
        add(lblrollno);

        crollno = new Choice();
        crollno.setBounds(60,130,150,20);
        add(crollno);

        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from student");
            while(rs.next()){
                crollno.add(rs.getString("rollno"));
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }

        JLabel lbldate = new JLabel("Date");
        lbldate.setBounds(60,180,200,20);
        lbldate.setFont(new Font("Tahoma",Font.PLAIN,18));
        add(lbldate);

        dcdate = new JDateChooser();
        dcdate.setBounds(60,210,200,30);
        add(dcdate);

        JLabel lbltime = new JLabel("Leave Type");
        lbltime.setBounds(60,260,200,20);
        lbltime.setFont(new Font("Tahoma",Font.PLAIN,18));
        add(lbltime);

        ctime = new Choice();
        ctime.setBounds(60,290,150,25);
        ctime.add("Full Day");
        ctime.add("Half Day");
        add(ctime);

        submit = new JButton("Submit");
        submit.setBounds(60,350,120,30);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(200,350,120,30);
        cancel.setBackground(Color.RED);
        cancel.addActionListener(this);
        add(cancel);




        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == submit){
        String rollno = crollno.getSelectedItem();
        String date = ((JTextField)dcdate.getDateEditor().getUiComponent()).getText();
        String duration = ctime.getSelectedItem();

        String query = "insert into studentleave values('"+rollno+"', '"+date+"', '"+duration+"')";

        try{
            Conn c = new Conn();
            c.s.executeUpdate(query);
            JOptionPane.showMessageDialog(null,"Leave Updated");
            setVisible(false);

        }catch(Exception e){
            e.printStackTrace();
        }

        }else if(ae.getSource() == cancel){
            setVisible(false);
        }
    }

    public static void main(String[] args){
        new StudentLeave();
    }
}
