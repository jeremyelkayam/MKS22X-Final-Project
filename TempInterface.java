import java.util.*;
import java.io.*;
public class TempInterface{
    static Sheet sheet=new Sheet(1,1);
    public static void main(String[]args){
	boolean keepGoing=true;
	System.out.println("Welcome to Stuycrosoft XL!\n\n");
	//sheet.setData("A1",3);
	//System.out.println(sheet);
	while(keepGoing){
	    System.out.println("Sheet:\n"+sheet);
	    System.out.println("What would you like to do?\n"
			       +"Options are:\n"
			       +"\"set\" (sets cell data)\n"
			       //+"\"enlarge\" (enlarges sheet)\n"
			       +"\"save\" (saves sheet as .csv file)\n"
			       +"\"load\" (loads sheet from .csv file)\n"
			       +"\"quit\" (exits program)");
	    String input=getSomething();
	    switch(input.toLowerCase()){
	    case "set":
		String cor;		
		System.out.println("Which cell to set?");
		cor=getSomething().toUpperCase();
		System.out.println("What data should be put in the cell?");
		String data=getSomething();
		try{
		    sheet.setData(cor,Double.valueOf(data));
		}catch(NumberFormatException nfe){
		    if(data.equals("\"\""))
			data="";//for testing purposes
		    sheet.setData(cor,data);
		}
		break;
	    case "quit":
		keepGoing=false;
		break;
		/*case "enlarge":
		while(true){
		    System.out.println("Enlarge horizontally or vertically? (H/V)");
		    String hv=getSomething();
		    if(hv.equalsIgnoreCase("h")){
			sheet.enlarge(true);
			break;
		    }else if(hv.equalsIgnoreCase("v")){
			sheet.enlarge(false);
			break;
		    }else{
			System.out.println("Please type in either the letter h or v");
		    }
		}
		break;*/
	    case "save":
		System.out.println("Please enter a filename");
		String name=getSomething();
		sheet.save(name);
		if(name.length()<4 ||
		   !(name.substring(name.length()-4,name.length()).equalsIgnoreCase(".csv"))){
		    name+=".csv";
		}
		System.out.println("File saved as \""+name+"\".");
		break;
	    case "load":
		while(true){
		    System.out.println("Please enter a filename");
		    String fname=getSomething();
		    try{
			try{
			    sheet=new Sheet(fname);
			    break;
			}catch(FileNotFoundException fnfe){
			    System.out.println("File not found.");
			}
		    }catch(IOException ioe){
			System.out.println("Error while reading file");
		    }
		}
		System.out.println("File loaded.");
		break;
	    default:
		System.out.println("I don't understand.");
		break;
	    }
	}
    }
    public static String getSomething(){
	Scanner sc=new Scanner(System.in);
	return sc.nextLine();
    }
}
