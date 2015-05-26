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
	//added all of the text boxes
	pane.add(text);
	pane.add(new JTextField());
	pane.add(new JTextField());
	for(int z=0;z<301;z++){
	    pane.add(new JTextField());
	}

	//adding menu bar
	JMenuBar menuBar;
	JMenu FileMenu;
	JMenuItem menuItem;

	//create the menu bar
	menuBar = new JMenuBar();

	//build the first menu
	FileMenu = new JMenu("File");
	//open File menu with crtl + f
	FileMenu.setMnemonic('F');
	menuBar.add(FileMenu);
	
	//adding items to FileMenu
	menuItem = new JMenuItem("New");
	menuItem.getAccessibleContext().setAccessibleDescription("Open a new file");
	FileMenu.add(menuItem);
	
	setJMenuBar(menuBar);
    }
    public static void main(String[]args){
	Interface blah = new Interface();
	blah.setVisible(true);
    }
    
}
