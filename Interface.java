import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.border.*;
import java.awt.event.*;

public class Interface extends JFrame{
    private Container pane;
    private JTextField text;
    private String title;
    public Interface(){
	title="Stuycrosoft XL";
	this.setTitle(title);
	this.setSize(800,600);
	this.setLocation(100,100);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	pane=this.getContentPane();
	pane.setLayout(new GridLayout(20,5));
	text = new JTextField();
	//Feel free to delete the following code, it's just me trying out stuff
	pane.add(text);
	pane.add(new JTextField());
	pane.add(new JTextField());
	for(int z=0;z<300;z++){
	    pane.add(new JTextField());
	}
    }
    public static void main(String[]args){
	Interface blah = new Interface();
	blah.setVisible(true);
    }
    
}
