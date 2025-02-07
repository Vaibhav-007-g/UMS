package management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class About extends JFrame{

    About(){

        setSize(700,500);
        setLocation(400,150);
        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("<html>University<br/>Management System</html>"); //br used to break the line and print on next line its an html tag.
        heading.setBounds(70,20,300,130);
        heading.setFont(new Font("Tahoma",Font.BOLD,30));
        add(heading);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpeg"));
        Image i2 = i1.getImage().getScaledInstance(300,200,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350,0,300,200);
        add(image);


        JLabel name = new JLabel("Developed by : Vaibhav Gupta");
        name.setBounds(70,220,550,40);
        name.setFont(new Font("Tahoma",Font.BOLD,30));
        add(name);

        JLabel rollno = new JLabel("Roll Number : 70");
        rollno.setBounds(70,280,550,40);
        rollno.setFont(new Font("Tahoma",Font.BOLD,30));
        add(rollno);

        JLabel section = new JLabel("Section : D2");
        section.setBounds(70,340,550,40);
        section.setFont(new Font("Tahoma",Font.BOLD,30));
        add(section);

        setLayout(null);

        setVisible(true);

    }


    public static void main(String[] args){
           new About();
    }
}
