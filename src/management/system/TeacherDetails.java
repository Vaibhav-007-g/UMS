package management.system;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils; // with dbutils we can directly insert values to table
import java.awt.event.*;


public class TeacherDetails extends JFrame implements ActionListener{

    Choice cempID;
    JTable table;
    JButton search, print, update, add,refresh, delete, cancel;
    TeacherDetails()
    {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Search by Employee ID");
        heading.setBounds(20,20,150,20);
        add(heading);

        cempID = new Choice();
        cempID.setBounds(180,20,150,20);
        add(cempID);

        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from teacher");
            while(rs.next()){
                cempID.add(rs.getString("empID"));
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }

        table = new JTable();

        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from teacher");
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
            String query = "select * from teacher where empID = '"+cempID.getSelectedItem()+"'";
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
                new UpdateTeacher();
        }
        else if(ae.getSource() == add){

            new AddTeacher();
        }
        else if(ae.getSource() == refresh){
            setVisible(false);
            new TeacherDetails();
        }
        else if(ae.getSource() == delete){
            try{
                int a = 0;
                a = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this teacher information");

                if(a == 0) {
                    Conn c = new Conn();
                    c.s.executeUpdate("delete from teacher where empId = '" + cempID.getSelectedItem() + "'");
                    c.s.executeUpdate("delete from teacherleave where empId = '" + cempID.getSelectedItem() + "'");
                    JOptionPane.showMessageDialog(null, "Teacher deleted successfully");
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
        new TeacherDetails();
    }
}
