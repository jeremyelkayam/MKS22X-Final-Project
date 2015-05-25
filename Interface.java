import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.border.*;
import java.awt.event.*;

public class Interface extends JFrame{
    private Container pane;
    private JTextField text;
    public Interface(){
	this.setTitle("Spreadsheet");
	this.setSize(600,500);
	this.setLocation(100,100);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	pane=this.getContentPane();
	pane.setLayout(new GridLayout(5,5));
	text = new JTextField();
	//Feel free to delete the following code, it's just me testing out stuff
	pane.add(text);
	pane.add(new JTextField());
	pane.add(new JTextField());
	
    }
    public static void main(String[]args){
	Interface blah = new Interface();
	blah.setVisible(true);
    }
    
}
