import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.text.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.event.*;
import javax.swing.filechooser.*;
import java.io.*;

public class Interface extends JFrame implements ActionListener{
    private Container pane;
    private String title;
    private Sheet sheet;
    private String[][]tableData;
    private JTextField functionThing;

    private JTable table;
    public Interface(){
	title="Stuycrosoft XL";
	this.setTitle(title);
	this.setSize(800,600);
	this.setLocation(100,100);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	pane=this.getContentPane();
	pane.setLayout(new BorderLayout());
	
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
	menuItem.setActionCommand("open");
	menuItem.addActionListener(this);
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
	
	functionThing=new JTextField();
	getContentPane().add(functionThing,BorderLayout.PAGE_START);
    }

    public static String numberToString(int num) {//This method changes a base-10 integer to an alphabetic base-26 integer. A is 1, B is 2, AB is 28, etc.
	String result = "";
	while (num > 0) { //OF NOTE: numberToString(0) RETURNS AN EMPTY STRING.
	    num--; // 1 => a, not 0 => a
	    int remainder = num % 26;
	    char digit = (char) (remainder + 65);
	    result = digit + result;
	    num = (num - remainder) / 26;
	}
	
	return result;
    }

    public void createTable(){
	String[]columnNames=new String[10];
	sheet=new Sheet(10,10);
	for(int z=0;z<columnNames.length;z++){
	    columnNames[z]=numberToString(z+1);
	}
	table = new MyTable(sheet.toStringArray(), columnNames);
	table.setRowHeight(20);
	table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	JScrollPane scrollPane = new JScrollPane(table);
       	table.setFillsViewportHeight(true);


	JTable rowTable = new RowNumberTable(table);
	scrollPane.setRowHeaderView(rowTable);
	scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER,rowTable.getTableHeader());

	table.getTableHeader().setReorderingAllowed(false);
	table.setColumnSelectionAllowed(true);
	table.setRowSelectionAllowed(true);

	getContentPane().add(scrollPane);
	
    }
    
    public void storeData(){
	for(int i=0;i<table.getRowCount();i++){
	    for(int j=0;j<table.getColumnCount();j++){
		String location = "" + table.getColumnName(j) + (i+1);
		sheet.setData(location, table.getValueAt(i, j).toString());
	    }
	}

	//	System.out.println(sheet);
    }
    
    public void actionPerformed(ActionEvent e){
	String act=e.getActionCommand();
	//System.out.println("lol");
	switch(act){
	case "save":
	    
	    break;
	case "open":
	    try{
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
									     "CSV files", "csv");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(this);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
		    //System.out.println("You chose to open this file: " +
		    //		       chooser.getSelectedFile().getName());
		    
		}
		try{
		    sheet=new Sheet(chooser.getSelectedFile());
		}catch(IOException ioe){
		    
		}
		System.out.println(sheet);
	    }catch(NullPointerException teamname){}
	    //THIS DOES NOT PUT THE SHEET INTO THE GUI RIGHT NOW!!!!
	    break;
	default:
	    break;
	}
    }
    
    public static void main(String[]args){
	Interface blah = new Interface();
	blah.setVisible(true);
    }
    private class MyTable extends JTable{
	/*
	public MyTable(){
	    super();
	}
	public MyTable(int numRows,int numCols){
	    super(numRows,numCols);
	}
	*/
	public MyTable(Object[][]rowData, Object[]columnNames){
	    super(rowData,columnNames);
	}
	/*
	public MyTable(TableModel dm){
	    super(dm);
	}
	public MyTable(TableModel dm,TableColumnModel cm){
	    super(dm,cm);
	}
	public MyTable(TableModel dm,TableColumnModel cm, ListSelectionModel sm){
	    super(dm,cm,sm);
	}
	public MyTable(Vector rowDat,Vector colNames){
	    super(rowDat,colNames);
	}
	*/
	public void setValueAt(Object val,int row, int col){
	    String v=val.toString();
	    //System.out.println("hi "+v);
	    try{
		double d=Double.valueOf(v);
		sheet.setData(row,col,d);
	    }catch(NumberFormatException nfe){
		sheet.setData(row,col,v);
	    }
	    v=sheet.getCell(row,col).toString();
	    functionThing.setText(sheet.getCell(row,col).getString());
	    //System.out.println(v);
	    super.setValueAt(v,row,col);
	    System.out.println(sheet);
	}
	public void valueChanged(ListSelectionEvent e){
	    System.out.println(e);
	    super.valueChanged(e);
	    System.out.println(getValueAt(1,2));
	}
    }
}
