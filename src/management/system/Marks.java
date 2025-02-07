package management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class Marks extends JFrame implements ActionListener{
    String rollno;
    String sem;
    JButton check,cancel;
    Choice cbsemester;
    JTextField tfsub1,tfsub2,tfsub3,tfsub4,tfsub5, tfmarks1,tfmarks2,tfmarks3,tfmarks4,tfmarks5;
    Marks(String rollno,String sem){
        this.rollno = rollno;
        this.sem = sem;

        setSize(1000,500);
        setLocation(300,150);
        setLayout(null);

        getContentPane().setBackground(Color.WHITE);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpeg"));
        Image i2 = i1.getImage().getScaledInstance(400,300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(500,40,400,300);
        add(image);

        JLabel heading = new JLabel("Graphic Era Hill University");
        heading.setBounds(100,10,500,25);
        heading.setFont(new Font("Tahoma",Font.BOLD,20));
        add(heading);

        JLabel subheading = new JLabel("Result of Examination");
        subheading.setBounds(100,50,500,20);
        subheading.setFont(new Font("Tahoma",Font.BOLD,18));
        add(subheading);

        JLabel lblrollno = new JLabel("Roll Number : "+rollno);
        lblrollno.setBounds(60,100,500,20);
        lblrollno.setFont(new Font("Tahoma",Font.PLAIN,18));
        add(lblrollno);

        JLabel lblsemester = new JLabel("Select Semester : ");
        lblsemester.setBounds(50,150,150,20);
        lblsemester.setFont(new Font("Tahoma",Font.PLAIN,18));
        add(lblsemester);

        cbsemester = new Choice();
        cbsemester.setBounds(200,150,150,20);
        cbsemester.setBackground(Color.WHITE);
        add(cbsemester);

        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from subject where rollno = '"+rollno+"'");
            while(rs.next()){
                cbsemester.add(rs.getString("semester"));
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }

        tfsub1 = new JTextField();
        tfsub1.setBounds(50,200,200,20);
        add(tfsub1);

        tfsub2 = new JTextField();
        tfsub2.setBounds(50,220,200,20);
        add(tfsub2);

        tfsub3 = new JTextField();
        tfsub3.setBounds(50,240,200,20);
        add(tfsub3);

        tfsub4 = new JTextField();
        tfsub4.setBounds(50,260,200,20);
        add(tfsub4);

        tfsub5 = new JTextField();
        tfsub5.setBounds(50,280,200,20);
        add(tfsub5);

        tfmarks1 = new JTextField();
        tfmarks1.setBounds(250,200,200,20);
        add(tfmarks1);

        tfmarks2 = new JTextField();
        tfmarks2.setBounds(250,220,200,20);
        add(tfmarks2);

        tfmarks3 = new JTextField();
        tfmarks3.setBounds(250,240,200,20);
        add(tfmarks3);

        tfmarks4 = new JTextField();
        tfmarks4.setBounds(250,260,200,20);
        add(tfmarks4);

        tfmarks5 = new JTextField();
        tfmarks5.setBounds(250,280,200,20);
        add(tfmarks5);



        check = new JButton("Check");
        check.setBounds(100,350,120,30);
        check.addActionListener(this);
        add(check);

        cancel = new JButton("Back");
        cancel.setBounds(250,350,120,30);
        cancel.addActionListener(this);
        add(cancel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == cancel){
            setVisible(false);
        }
        else if(ae.getSource() == check){
            try{
                Conn c = new Conn();
                ResultSet rs1 = c.s.executeQuery("select * from subject where rollno = '"+rollno+"' and semester = '"+cbsemester.getSelectedItem()+"'");
                while (rs1.next()){
                    tfsub1.setText(rs1.getString("subject1"));
                    tfsub2.setText(rs1.getString("subject2"));
                    tfsub3.setText(rs1.getString("subject3"));
                    tfsub4.setText(rs1.getString("subject4"));
                    tfsub5.setText(rs1.getString("subject5"));
                }

                ResultSet rs2 = c.s.executeQuery("select * from marks where rollno = '"+rollno+"' and semester = '"+cbsemester.getSelectedItem()+"'");
                while (rs2.next()){
                    tfmarks1.setText(rs2.getString("marks1"));
                    tfmarks2.setText(rs2.getString("marks2"));
                    tfmarks3.setText(rs2.getString("marks3"));
                    tfmarks4.setText(rs2.getString("marks4"));
                    tfmarks5.setText(rs2.getString("marks5"));

                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }


    public static void main(String[] args){
        new Marks("","");
    }
}
