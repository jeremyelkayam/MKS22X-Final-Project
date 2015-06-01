import java.util.*;
public class TempInterface{
    static Sheet sheet=new Sheet(5,5);
    public static void main(String[]args){
	boolean keepGoing=true;
	System.out.println("Welcome to Stuycrosoft XL!\n\n");
	//sheet.setData("A1",3);
	//System.out.println(sheet);
	while(keepGoing){
	    System.out.println("Sheet:\n"+sheet);
	    System.out.println("What would you like to do?\nOptions are:\n\"set\" (sets cell data)\n\"enlarge\" (enlarges sheet)\n\"quit\" (exits program)");
	    String input=getSomething();
	    switch(input.toLowerCase()){
	    case "set":
		String cor;
		while(true){
		System.out.println("Which cell to set?");
		cor=getSomething().toUpperCase();
		if(sheet.hasCell(cor))
		    break;
		System.out.println("That is not a valid coordinate.");
		}
		System.out.println("What data should be put in the cell?");
		String data=getSomething();
		try{
		    sheet.setData(cor,Double.valueOf(data));
		}catch(NumberFormatException nfe){
		    sheet.setData(cor,data);
		}
		break;
	    case "quit":
		keepGoing=false;
		break;
	    case "enlarge":
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
