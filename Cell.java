/*What does a cell need?
  - Must store any type of data that you can enter into the spreadsheet
  - Should be able to do operations that reference other cells (maybe make a MathCell class that extends Cell?)
  - MathCell could have a reference to its sheet so that it can easily check other cells
  
 */
public class Cell{
    double data;//store all numbers internally as doubles since it can only store numbers and strings
    boolean containsNumber;//whether or not we have a number
    String str; //data in string form
    boolean emptyCell;
    boolean doesMath;
    Sheet sheet;
    public Cell(Sheet t,double d){
	setData(d);
	str=String.valueOf(d);
	containsNumber=true;
	emptyCell=false;
	sheet=t;
    }
    public Cell(Sheet t,String s){
	setData(s);
	containsNumber=false;
	emptyCell=s.equals("");
	sheet=t;
    }
    public Cell(Sheet t){
	str="";
	containsNumber=false;
	emptyCell=true;
	sheet=t;
    }
    public double getData() throws UnsupportedOperationException{
	if(containsNumber){
	    return data;
	}else{
	    throw new UnsupportedOperationException("This cell contains a string");
	}
    }
    public String toString(){
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
	if(s.charAt(0)=='='){//this is where the ma(th)gic happens
	    if(s.substring(1,5).equals("SUM(")){
		int z=5;
		if(s.indexOf(")")!=-1){
		    int dex=0;
		    while(dex<s.length()){
			dex=s.indexOf(",");
			
		    }
		}
	    }
	    data=result;
	    
	}else{
	    str=s;
	    containsNumber=false;
	    emptyCell=s.equals("");
	}
    }
    public boolean emptyCell(){
	return emptyCell;
    }
    
}
