import java.util.*;
import java.io.*;
public class Sheet{
    Cell[][]sheet;
    public Sheet(){
	this(20,20);
    }
    public Sheet(int rows,int cols){
	sheet=new Cell[rows][cols];
	initialize();
    }
    /*
      Initializes every cell in the sheet to an empty cell
     */
    public void initialize(){
	for(int r=0;r<sheet.length;r++){
	    for(int c=0;c<sheet.length;c++){
		sheet[r][c]=new Cell(this);
	    }
	}
    }
    /*
      Finds the cell at row,col
     */
    public Cell getCell(int row, int col){
	return sheet[row][col];
    }
    /*
      finds the cell at row,col given col in string form
     */
    public Cell getCell(int row, String col){
	return sheet[row][stringToNumber(col)-1];
	//remember that "A" refers to column 0
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
	return sheet[Integer.valueOf(numbers)-1][stringToNumber(alpha)-1];
	//"A1" refers to sheet[0][0]
    }    
    /*
      Sets data to number or string blah blah blah
     */
    public void setData(int row, int col, double value){
	getCell(row,col).setData(value);
    }
    public void setData(int row,String col,double value){
	getCell(row,col).setData(value);
    }
    public void setData(String location,double value){
	getCell(location).setData(value);
    }
    public void setData(int row, int col, String value){
	getCell(row,col).setData(value);
    }
    public void setData(int row,String col,String value){
	getCell(row,col).setData(value);
    }
    public void setData(String location,String value){
	getCell(location).setData(value);
    }    
    /*
      Doubles the size of the sheet either sideways or vertically
     */
    public void enlarge(boolean sideways){
	Cell[][]newSheet;
	if(sideways){//expand sideways
	    newSheet=new Cell[sheet.length][sheet[0].length*2];
	}else{//expand vertically
	    newSheet=new Cell[sheet.length*2][sheet[0].length];
	}
	for(int r=0;r<newSheet.length;r++){
	    for(int c=0;c<newSheet[0].length;c++){
		if(r<sheet.length && c<sheet[0].length){
		    newSheet[r][c]=sheet[r][c];
		}else{
		    newSheet[r][c]=new Cell(this);
		}
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
    /*
      The usual toString......
     */
    public String toString(){
	String result="";
	for(int z=0;z<sheet[0].length;z++){
	    if(z==0)
		result+="\t";
	    else
		result+=Interface.numberToString(z)+"\t";
	}
	result+="\n";
	for(int r=0;r<sheet.length;r++){
	    result+=(r+1)+"\t";
	    for(Cell c : sheet[r]){
		result+=c+"\t";
	    }
	    result+="\n";
	}
	return result;
    }
    /*
      Checks if the array has a cell at the given coordinate
     */
    public boolean hasCell(String cellCor){
	int numIndex;
	int z=0;
	while(!Character.isDigit(cellCor.charAt(z))){
	    z++;
	}
	numIndex=z;
	String alpha=cellCor.substring(0,numIndex);
	String numbers=cellCor.substring(numIndex);
	int row=Integer.valueOf(numbers)-1;
	int col=stringToNumber(alpha)-1;
	return (row<sheet.length) && (col<sheet[0].length);
    }
    /*
      Returns a string representing the sheet in CSV form
      Might need some work but currently it's working ok.
     */
    private String toCSV(){
	String result="";
	for(Cell[]row : sheet){
	    for(Cell z : row){
		if(z.containsNumber()){
		    result+=z.getData();
		}else{
		    result+="\""+z+"\"";
		}
		result+=",";
	    }
	    result+="\n";
	}
	return result;
    }
    public void save(String filename){
	if(filename.length()<4 || !(filename.substring(filename.length()-4,filename.length()).equalsIgnoreCase(".csv"))){
	    filename+=".csv";
	}
	
	try{
	    FileWriter fw=new FileWriter(filename);
	    fw.write(toCSV());
	    fw.close();
	}catch(IOException ioe){
	    System.out.println("Unable to write file.");
	}
    }
}
