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
	JMenu fileMenu, editMenu;
	JMenuItem menuItem;

	//create the menu bar
	menuBar = new JMenuBar();

	//build the first menu
	fileMenu = new JMenu("File");
	menuBar.add(fileMenu);

	editMenu = new JMenu("Edit");
	menuBar.add(editMenu);
	
	//adding items to FileMenu
	menuItem = new JMenuItem("New");
	menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
	menuItem.getAccessibleContext().setAccessibleDescription("Open a new file");
	fileMenu.add(menuItem);

	menuItem = new JMenuItem("Open..");
	menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.ALT_MASK));
	menuItem.getAccessibleContext().setAccessibleDescription("Open existing file");
	fileMenu.add(menuItem);

	menuItem = new JMenuItem("Rename..");
	menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.ALT_MASK));
	menuItem.getAccessibleContext().setAccessibleDescription("Rename file");
	fileMenu.add(menuItem);

 
	
	
	setJMenuBar(menuBar);
    }
    public static void main(String[]args){
	Interface blah = new Interface();
	blah.setVisible(true);
    }
    
}
