import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.border.*;
import java.awt.event.*;

public class Interface extends JFrame{
    private Container pane;
    private String title;
    public Interface(){
	title="Stuycrosoft XL";
	this.setTitle(title);
	this.setSize(800,600);
	this.setLocation(100,100);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	pane=this.getContentPane();
	pane.setLayout(new GridLayout(20,5));
	
	createMenu();
	createTable();

    }

    public void createMenu(){
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

	menuItem = new JMenuItem("Save");
	menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
	menuItem.getAccessibleContext().setAccessibleDescription("Save file");
	fileMenu.add(menuItem);

	//adding items to editMenu
	ImageIcon undo = new ImageIcon("images/undo.png");
	Image undoScaled = undo.getImage().getScaledInstance(16,16,Image.SCALE_DEFAULT);
	ImageIcon undoIcon = new ImageIcon(undoScaled);
	menuItem = new JMenuItem("Undo", undoIcon);
	menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.ALT_MASK));
	menuItem.getAccessibleContext().setAccessibleDescription("Undo");
	editMenu.add(menuItem);

	ImageIcon redo = new ImageIcon("images/redo.png");
	Image redoScaled = redo.getImage().getScaledInstance(16,16,Image.SCALE_DEFAULT);
	ImageIcon redoIcon = new ImageIcon(redoScaled);
	menuItem = new JMenuItem("Redo", redoIcon);
	menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.ALT_MASK));
	menuItem.getAccessibleContext().setAccessibleDescription("Redo");
	editMenu.add(menuItem);
	
	//add line seperator
	editMenu.addSeparator();

	ImageIcon cut = new ImageIcon("images/cut.png");
	Image cutScaled = cut.getImage().getScaledInstance(16,16,Image.SCALE_DEFAULT);
	ImageIcon cutIcon = new ImageIcon(cutScaled);
	menuItem = new JMenuItem("Cut", cutIcon);
	menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.ALT_MASK));
	menuItem.getAccessibleContext().setAccessibleDescription("Cut");
	editMenu.add(menuItem);
	
	ImageIcon copy = new ImageIcon("images/copy.png");
	Image copyScaled = copy.getImage().getScaledInstance(16,16,Image.SCALE_DEFAULT);
	ImageIcon copyIcon = new ImageIcon(copyScaled);
	menuItem = new JMenuItem("Copy", copyIcon);
	menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
	menuItem.getAccessibleContext().setAccessibleDescription("Copy");
	editMenu.add(menuItem);

	ImageIcon paste = new ImageIcon("images/paste.png");
	Image pasteScaled = paste.getImage().getScaledInstance(16,16,Image.SCALE_DEFAULT);
	ImageIcon pasteIcon = new ImageIcon(pasteScaled);
	menuItem = new JMenuItem("Paste", pasteIcon);
	menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.ALT_MASK));
	menuItem.getAccessibleContext().setAccessibleDescription("Paste");
	editMenu.add(menuItem);

	menuItem = new JMenuItem("Find..");
	menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.ALT_MASK));
	menuItem.getAccessibleContext().setAccessibleDescription("Find..");
	editMenu.add(menuItem);

	setJMenuBar(menuBar);
    }

    public void createTable(){
	JTable table = new JTable(100,26);
	
	JScrollPane scrollPane = new JScrollPane(table);
	add(scrollPane);
    }

    public static void main(String[]args){
	Interface blah = new Interface();
	blah.setVisible(true);
    }
    
}
