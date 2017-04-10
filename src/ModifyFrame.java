import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.regex.*;
import java.sql.*;


class ModifyFrame extends JFrame
{
JPanel jp1,jp2;
JLabel lbl1ID,lbl2Name;
JTextField txtID,txtName;
JButton btnSave,btnBack;

ModifyFrame()
{
super("  Modify Employee  ");
setSize(500,150);
setResizable(false);    //the resize option is greyed out

jp1=new JPanel();
jp1.setLayout(new FlowLayout());

lbl1ID=new JLabel("ID");
txtID=new JTextField(3);//that is 3 coloumns

lbl2Name=new JLabel("Name");
txtName=new JTextField(10);//that is 10 coloumns

//now add all this to Jpane
jp1.add(lbl1ID);
jp1.add(txtID);
jp1.add(lbl2Name);
jp1.add(txtName);

//now add the pane to frame
add(jp1);

jp2=new JPanel();
jp2.setLayout(new FlowLayout());

//save and back buttons

btnSave=new JButton("Save");
btnBack=new JButton("Back");

jp2.add(btnSave);
jp2.add(btnBack);

add(jp2,BorderLayout.SOUTH);
setLocationRelativeTo(null);   //SETS THE WHOLE FRAME AT CENTRE
setVisible(true);

//back button should take us back to homeframe
btnBack.addActionListener(new ActionListener(){

public void actionPerformed(ActionEvent e)
{
HomeFrame h= new HomeFrame();
dispose();
}

});


//close button also should take us back to homeframe
addWindowListener(new WindowAdapter(){

public void windowClosing(WindowEvent e)
{
HomeFrame h= new HomeFrame();
dispose();
}

});

btnSave.addActionListener(new ActionListener(){

public void actionPerformed(ActionEvent ae)
{
int id=0;
String name="";

//id validation
try
{
id=Integer.parseInt(txtID.getText());
}//end try
catch(NumberFormatException e)
{
JOptionPane.showMessageDialog(new JDialog()," Invalid id ");
//gets the focus back to id feild
txtID.setText("");
txtID.requestFocus();
return;
}//end catch

//id validation.id less than 0
if(id<=0){
JOptionPane.showMessageDialog(new JDialog()," Invalid id.ID should be greater than 0 ");
txtID.setText("");
txtID.requestFocus();
return;
}

name=txtName.getText();

//name validation
if(name.length()==0)
{
JOptionPane.showMessageDialog(new JDialog()," Invalid name.Enter name ");
txtName.setText("");
txtName.requestFocus();
return;
}

try
{
if(!name.matches("[a-zA-Z0-9_.-]{3,}"))
{
JOptionPane.showMessageDialog(new JDialog()," Invalid name. ");
txtName.setText("");
txtName.requestFocus();
return;
}

}//end try
catch(PatternSyntaxException pse)
{
}

DatabaseHandler query =new DatabaseHandler();
query.modify(id,name);
txtID.setText("");
txtName.setText("");


}

});//end of btnsave actionhandler

}
}