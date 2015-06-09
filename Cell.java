/*What does a cell need?
  - Must store any type of data that you can enter into the spreadsheet
  - Should be able to do operations that reference other cells (maybe make a MathCell class that extends Cell?)
  - MathCell could have a reference to its sheet so that it can easily check other cells
  
 */
import java.util.*;
public class Cell{
    double data;//store all numbers internally as doubles since it can only store numbers and strings
    boolean containsNumber;//whether or not we have a number
    String str; //internal string representation of data. 
    boolean doesMath;//maybe MathCell should be a separate class.....
    Sheet sheet;
    public Cell(Sheet t,double d){
	setData(d);
	sheet=t;
    }
    public Cell(Sheet t,String s){
	setData(s);
	sheet=t;
    }
    public Cell(Sheet t){
	setData("");
	sheet=t;
    }
    public double getData() throws UnsupportedOperationException{
	if(containsNumber){
	    if(str.length()>0 && str.charAt(0)=='='){
		//System.out.println("hi");
		return operate(str);
	    }else{
		return data;
	    }
	}else{
	    throw new UnsupportedOperationException("This cell contains a string");
	}
    }
    public String toString(){
	if(containsNumber){
	    //System.out.println("Oh yea");
	    return String.valueOf(getData());
	}
	return str;
    }
    public String getString(){//The difference between getString() and toString() is that when the cell contains functions, this returns the expression.
	return str;
    }
    public boolean containsNumber(){
	return containsNumber;
    }
    public void setData(double d){
	data=d;
	str=String.valueOf(d);
	containsNumber=true;
    }
    public void setData(String s){
	double result=0;
	if(s.length()>0 && s.charAt(0)=='='){
	    data=-100000;//in this case, we calculate the data every time we call getData();
	    str=s;
	    containsNumber=true;
	    //System.out.println("yo");
	    //operate(s);//this is to check if you typed it in right
	}else{
	    str=s;
	    containsNumber=false;
	}
    }
    private double operate(String s){
	//this is where the ma(th)gic happens
	double result;
	int count=0;
	if(s.substring(1,5).equals("SUM(") || s.substring(1,9).equals("AVERAGE(") || s.substring(1,7).equals("COUNT(") || s.substring(1,7).equals("SUMSQ(")){
	    //count doesn't use result but we should initialize it anyway + make sure it doesn't throw an UOE
	    result=0;
	}else if(s.substring(1,9).equals("PRODUCT(")){
	    result=1;
	}else{
	    throw new UnsupportedOperationException("Function not found");
	}
	if(s.length()>0 && s.charAt(0)=='=' && s.indexOf(")")!=-1){
	    //System.out.println(s);
	    String work=s.substring(s.indexOf("(")+1,s.indexOf(")"));
	    String[]nums=work.split(";");
	    for(String z : nums){
		//try{
		if(z.indexOf(":")!=-1){
		    String[]range=z.split(":");
		    //System.out.println(Arrays.toString(range));
		    if(range.length>1){
			String front=range[0];
			String back=range[range.length-1];//this is in case someone puts more than 1 colon in there like an idiot
			if((Sheet.toIndex(back)[0]+Sheet.toIndex(back)[1])<Sheet.toIndex(front)[0]+Sheet.toIndex(front)[1]){
			    //System.out.println("lol");
			    String temp=front;
			    front=back;
			    back=temp;
			    //if someone put them in the wrong order, swap!
			}
			//System.out.println(back);
			//System.out.println(Sheet.toIndex(back)[1]);
			for(int r=Sheet.toIndex(front)[0];r<=Sheet.toIndex(back)[0];r++){
			    for(int c=Sheet.toIndex(front)[1];c<=Sheet.toIndex(back)[1];c++){
				//get everything in the range & add it
				//System.out.println("row:"+r+"\ncol:"+c);
				//try{
				Cell cell=sheet.getCell(r,c);//this tries to find a null cell during initialization
				if(cell.containsNumber() && cell!=this){
				    if(s.substring(1,5).equals("SUM(") || s.substring(1,9).equals("AVERAGE(")){
					result+=cell.getData();
					//System.out.println("hey");
				    }else if(s.substring(1,9).equals("PRODUCT(")){
					result*=cell.getData();
				    }else if(s.substring(1,7).equals("SUMSQ(")){
					result+=(cell.getData()*cell.getData());
				    }
				    count++;
				}
				//}catch(NullPointerException e){
				
				//}
			    }
			}
		    }
			/*}catch(NumberFormatException nfe,StringIndexOutOfBoundsException sioobe){
			  result+=Double.valueOf(z);
			  }
			*/
		}else{
		    double dat;
		    try{
			dat=Double.valueOf(z);
			System.out.println("Data"+dat);
		    }catch(NumberFormatException nfe){
			Cell cell=sheet.getCell(z);
			if(cell.containsNumber() && cell!=this){
			    dat=cell.getData();
			}else{
			    if(s.substring(1,5).equals("SUM(") || s.substring(1,9).equals("AVERAGE(") || s.substring(1,7).equals("COUNT(") || s.substring(1,7).equals("SUMSQ(")){
				//count doesn't use result but we should initialize it anyway + make sure it doesn't throw an UOE
				dat=0;
				System.out.println("uwotm8"+dat);
			    }else{// if(s.substring(1,9).equals("PRODUCT(")){
				dat=1;
			    }
			}
		    }
		    if(s.substring(1,5).equals("SUM(") || s.substring(1,9).equals("AVERAGE(")){
			result+=dat;
		    }else if(s.substring(1,9).equals("PRODUCT(")){
			result*=dat;//lol
			
			//wtf, why did i write lol here >_>
		    }else if(s.substring(1,7).equals("SUMSQ(")){
			result+=(dat*dat);
		    }
		    count++;
		}
	    }
	}else throw new UnsupportedOperationException();
	//there's a buttload of potential exceptions here if the user fucks up while writing the expression. I'm going to have to figure out every single one, and catch them all in setData(String)...
	//THIS SUCKS!!!!!
	//actually it's not that bad, mostly SIOOBEs and maybe some NFEs
	if(s.substring(1,9).equals("AVERAGE("))
	    return result/count;
	else if(s.substring(1,7).equals("COUNT("))//else is unnecessary here but I've included it for clarity
	    return count;
	else return result;
    }
    //IT WORKS!!!!!!
    //!!!!!!!!!!!!!!!!!!!!!!!!
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //THIS MAN IS A GENIUS! GIVE HIM A GOLD MEDAL! 10 GOLD MEDALS! FIFTY GOLD MEDALS! 6.0 GPA! ADMIT HIM TO AWESOME UNIVERSITY!
}
