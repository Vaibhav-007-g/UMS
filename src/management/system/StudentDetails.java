package management.system;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils; // with dbutils we can directly insert values to table
import java.awt.event.*;


public class StudentDetails extends JFrame implements ActionListener{

    Choice crollno;
    JTable table;
    JButton search, print, update, add, refresh,cancel,delete;
    StudentDetails()
    {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Search by Roll Number");
        heading.setBounds(20,20,150,20);
        add(heading);

        crollno = new Choice();
        crollno.setBounds(180,20,150,20);
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

        table = new JTable();

        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from student");
           table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e)
        {
            e.printStackTrace();
        }

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0,100,900,600); //leftse 0 upar se 100 width frame wali same 900
        add(jsp);

        search = new JButton("Search");
        search.setBounds(20,70,80,20);
        search.addActionListener(this);
        add(search);

        print = new JButton("Print");
        print.setBounds(120,70,80,20);
        print.addActionListener(this);
        add(print);

        update = new JButton("Update");
        update.setBounds(220,70,80,20);
        update.addActionListener(this);
        add(update);

        add = new JButton("Add");
        add.setBounds(320,70,80,20);
        add.addActionListener(this);
        add(add);

        refresh = new JButton("Refresh");
        refresh.setBounds(420,70,80,20);
        refresh.addActionListener(this);
        add(refresh);

        delete = new JButton("Delete");
        delete.setBounds(520,70,80,20);
        delete.addActionListener(this);
        add(delete);

        cancel = new JButton("Cancel");
        cancel.setBounds(620,70,80,20);
        cancel.setBackground(Color.RED);
        cancel.addActionListener(this);
        add(cancel);


        setSize(900,700); //Length 900 width 700
        setLocation(300,100); //Left se 300 upar se 100
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource() == search){
            String query = "select * from student where rollno = '"+crollno.getSelectedItem()+"'";
            try{
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            }catch(Exception e){
                e.printStackTrace();
            }

        }
        else if(ae.getSource() == print){
            try {
                table.print();
            }catch(Exception e){
                e.printStackTrace();
            }

        }
        else if(ae.getSource() == update){
               new UpdateStudent();
        }
        else if(ae.getSource() == add){

             new AddStudent();
        }
        else if(ae.getSource() == refresh){
            setVisible(false);
            new StudentDetails();
        }
        else if(ae.getSource() == delete){
            try{


                int a = 0;
                a = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this student information");
               if(a == 0) {
                   Conn c = new Conn();
                   c.s.executeUpdate("delete from student where rollno = '"+crollno.getSelectedItem()+"'");
                   c.s.executeUpdate("delete from studentleave where rollno = '"+crollno.getSelectedItem()+"'");
                   c.s.executeUpdate("delete from subject where rollno = '"+crollno.getSelectedItem()+"'");
                   c.s.executeUpdate("delete from marks where rollno = '"+crollno.getSelectedItem()+"'");
                   c.s.executeUpdate("delete from collegefee where rollno = '"+crollno.getSelectedItem()+"'");
                   JOptionPane.showMessageDialog(null, "Student deleted successfully");
               }
               }catch(Exception e){
                e.printStackTrace();
            }
        }
        else{
            setVisible(false);
        }
    }

    public static void main(String[] args)
    {
        new StudentDetails();
    }
}
