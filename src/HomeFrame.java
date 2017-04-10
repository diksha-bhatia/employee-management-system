//package javaproj;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;


class HomeFrame extends JFrame
{

JButton btnAdd,btnModify,btnDelete,btnView;

HomeFrame()
{
super("  Home Frame  ");
setSize(500,150);
setResizable(false);    //the resize option is greyed out
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

btnAdd=new JButton("Add");
btnModify=new JButton("Modify");
btnDelete=new JButton("Delete");
btnView=new JButton("View");

setLayout(new FlowLayout());//default centre
add(btnAdd);
add(btnModify);
add(btnDelete);
add(btnView);

setLocationRelativeTo(null);   //y not setLayout(centre)?doubt
setVisible(true);

//navigation to add
btnAdd.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e)
{
AddFrame a = new AddFrame();
dispose();//dispose the current frame
}

});

//navigation to modify
btnModify.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e)
{
ModifyFrame f = new ModifyFrame();
dispose();//dispose the current frame
}

});

btnDelete.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e)
{
DeleteFrame d = new DeleteFrame();
dispose();//dispose the current frame
}

});

btnView.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e)
{
ViewFrame v= new ViewFrame();
dispose();//dispose the current frame
}

});



}


public static void main(String args[])
{
HomeFrame h=new HomeFrame();
}


}

class DatabaseHandler
{
static Connection con;

public static void getConnection(){

try
{
Class.forName("oracle.jdbc.driver.OracleDriver");
con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:xe","system","diksha");
}//end of try
catch(Exception e)
{
JOptionPane.showMessageDialog(new JDialog(),"  "+e);
}

}//end getconnection


//doubt in whole method
public void insert(int id,String name)
{
try
{
getConnection();
String q="insert into Employee values(?,?)";
PreparedStatement pst=con.prepareStatement(q);
pst.setInt(1,id);
pst.setString(2,name);

int i=pst.executeUpdate();
JOptionPane.showMessageDialog(new JDialog()," 1 record added");

}
catch(Exception e)
{
JOptionPane.showMessageDialog(new JDialog()," Record Already Exist ");
}


}//insert end

public void modify(int id,String name)
{
try
{
getConnection();
String q="update Employee set name=? where id=?";
PreparedStatement pst=con.prepareStatement(q);
pst.setInt(2,id); //this becomes 2 as the parameter ie id is accepted nd in the query
pst.setString(1,name);

int i=pst.executeUpdate();
if(i==0){
JOptionPane.showMessageDialog(new JDialog()," 0 records modified");
}
else
{
JOptionPane.showMessageDialog(new JDialog()," 1 records modified");
}

}
catch(Exception e)
{
JOptionPane.showMessageDialog(new JDialog()," Record Already Exist ");
}


}//modify end

public void delete(int id)
{
try
{
getConnection();
String q="delete from Employee where id=?";
PreparedStatement pst=con.prepareStatement(q);
pst.setInt(1,id); 


int i=pst.executeUpdate();
if(i==0){
JOptionPane.showMessageDialog(new JDialog()," 0 records deleted");
}
else
{
JOptionPane.showMessageDialog(new JDialog()," 1 records deleted");
}

}
catch(Exception e)
{
JOptionPane.showMessageDialog(new JDialog()," Record Already Exist ");
}


}//delete end

public String query()
{
StringBuffer sb=new StringBuffer();
try
{
getConnection();
String view="select * from Employee order by id";
Statement st=con.createStatement();
ResultSet rs=st.executeQuery(view);

sb.append("ID"+"\t"+"NAME"+"\n");
while(rs.next())
{
sb.append(rs.getString(1)+"\t"+rs.getString(2)+"\n");
}
rs.close();

}
catch(Exception e)
{
JOptionPane.showMessageDialog(new JDialog()," "+e);
}

return sb.toString();

}


}//end dbhandler

