import java.util.*;
public class Sheet{
    Cell[][]sheet;
    public Sheet(){
	this(20,20);
    }
    public Sheet(int rows,int cols){
	sheet=new Cell[rows][cols];
	initialize();
    }
    public void initialize(){
	for(int r=0;r<sheet.length;r++){
	    for(int c=0;c<sheet.length;c++){
		sheet[r][c]=new Cell(this);
	    }
	}
    }
    public Cell getCell(int row, int col){
	return sheet[row][col];
    }
    public Cell getCell(int row, String col){
	return sheet[row][stringToNumber(col)];
    }
    /*
      parses location in the alphanumeric form "A1, B6, AGC833, etc."
      String of letters denotes column
      Number denotes row
    */
    public Cell getCell(String location){
	int numIndex;
	int z=0;
	while(!Character.isDigit(location.charAt(z))){
	    z++;
	}
	numIndex=z;//regex would help so much but I don't know how to use it :(
	//System.out.println(numIndex);
	String alpha=location.substring(0,numIndex);
	String numbers=location.substring(numIndex);
	return sheet[Integer.valueOf(numbers)][stringToNumber(alpha)];
    }
    public void setData(int row, int col, double value){
	getCell(row,col).setData(value);
    }
    public void setData(int row,String col,double value){
	getCell(row,col).setData(value);
    }
    public void setData(String location,double value){
	getCell(location).setData(value);
    }
    private void enlarge(boolean sideways){
	Cell[][]newSheet;
	if(sideways){//expand sideways
	    newSheet=new Cell[sheet.length][sheet[0].length*2];
	}else{//expand vertically
	    newSheet=new Cell[sheet.length*2][sheet[0].length];
	}
	for(int r=0;r<sheet.length;r++){
	    for(int c=0;c<sheet[0].length;c++){
		    newSheet[r][c]=sheet[r][c];
	    }
	}
	sheet=newSheet;
    }
    public static int stringToNumber(String str) {//This converts a string created by stringToNumber() back into number form.
	char[] ls = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	Map<Character, Integer> m = new HashMap<Character, Integer>();
	int j = 1;
	for(char c: ls) {
	    m.put(c, j++);
	}
	int i = 0;
	int mul = 1;
	for(char c: new StringBuffer(str).reverse().toString().toCharArray()) {
	    i += m.get(c) * mul;
	    mul *= ls.length;
	}
	return i;
    }
    public String toString(){
	String result="";
	for(int z=0;z<sheet[0].length;z++){
	    if(z==0)
		result+="\t";
	    else
		result+=Interface.numberToString(z)+"\t";
	}
	return result;
    }
}

