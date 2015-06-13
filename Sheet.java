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
      constructs a Sheet based on the .csv file given.
      PRECONDITIONS
      Cells are split by commas (no leading commas, no trailing commas)
      Rows are split by newlines
      Strings may be enclosed by quotes but don't have to be (it will run more slowly if they aren't)
      OF NOTE: if a " begins a cell but does not end it, then this breaks (it does not convert the text to a cell properly)
      
     */
    public Sheet(String filename)throws FileNotFoundException,IOException{
	BufferedReader br=new BufferedReader(new FileReader(filename));
	ArrayList<Cell[]>list=new ArrayList<Cell[]>();
	while(br.ready()){
	    String line=br.readLine();
	    String[]lol=line.split(",");
	    Cell[]arr=new Cell[lol.length];
	    for(int i=0;i<lol.length;i++){
		String z=lol[i];
		if(z.length()>0 && z.charAt(0)=='"'){
		    z=z.substring(1,z.length()-1);
		    arr[i]=new Cell(this,z);
		}else{
		    try{
			double d=Double.valueOf(z);
			arr[i]=new Cell(this,d);
		    }catch(NumberFormatException nfe){
			arr[i]=new Cell(this,z);
		    }
		}
	    }
	    list.add(arr);
	}
	sheet=new Cell[list.size()][];
	for(int z=0;z<list.size();z++){
	    sheet[z]=list.get(z);
	}
    }
    /*
      Initializes every cell in the sheet to an empty cell
     */
    public void initialize(){
	for(int r=0;r<sheet.length;r++){
	    for(int c=0;c<sheet[0].length;c++){
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
    /*
      public void setData(int row, int col, double value){
      getCell(row,col).setData(value);
      }
      public void setData(int row,String col,double value){
      getCell(row,col).setData(value);
      }
      public void setData(int row, int col, String value){
      getCell(row,col).setData(value);
      }
      
      public void setData(int row,String col,String value){
      getCell(row,col).setData(value);
      }
    */
    public void setData(String location, String value){
	int row=toIndex(location)[0];
	int col=toIndex(location)[1];
	setData(row,col,value);
    }
    public void setData(int row,int col,String value){
	//TODO czech if the user is setting the cell to an empty string. If user is,then automatically ensmallen the sheet.
	if(value.equals("")){
	    getCell(row,col).setData(value);
	    int lastRow=0;
	    int lastCol=0;
	    for(int r=0;r<sheet.length;r++){
		for(int c=0;c<sheet[0].length;c++){
		    //System.out.println((r+1)+Interface.numberToString(c+1));
		    if(!sheet[r][c].toString().equals("")){
			//System.out.println("ayy lmao");
			if(r>lastRow)
			    lastRow=r;
			if(c>lastCol)
			    lastCol=c;
			//System.out.println(lastRow+" "+lastCol);
		    }
		}
	    }
	    
	    if(lastRow<row)
		resize(lastRow+1,sheet[0].length);
	    if(lastCol<col)
		resize(sheet.length,lastCol+1);	    
	}else{ 
	    getCellResize(row,col).setData(value);
	}
    }
    public void setData(String location,double value){
	//don't need the empty cell clause here because any number (even 0) will fill a cell.
	getCellResize(location).setData(value);
    }
    public void setData(int row,int col,double value){
	getCellResize(row,col).setData(value);
    }
    /*
      Gets cell at location. If this sheet does not contain a cell at location,
      the sheet is automatically resized to include that cell.
     */
    public Cell getCellResize(String location){
	return getCellResize(toIndex(location)[0],toIndex(location)[1]);
    }
    public Cell getCellResize(int row,int col){
	if(!hasCell(row,col)){
	    row++;
	    col++;
	    if(row>sheet.length && col>sheet[0].length){//expand length and height to accomodate cell
		resize(row,col);
		System.out.println("hi");
	    }else if(row>sheet.length){//expand length to acco
		resize(row,sheet[0].length);
	    }else if(col>sheet[0].length){
		resize(sheet.length,col);
	    }//otherwise we don't need to worry because it has that cell
	    row--;
	    col--;
	}
	return getCell(row,col);
    }
    public static int[]toIndex(String cellCor)throws IllegalArgumentException{
	try{
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
	    int[]result={row,col};
	    return result;
	}catch(StringIndexOutOfBoundsException|NumberFormatException e){
	    throw new IllegalArgumentException();
	}
    }
    /*
      Resizes sheet to given size.
      If the new size is smaller than the existing sheet, values outside the new sheet are deleted.
      If the new size is larger than the existing sheet, new empty cells are added
     */
    public void resize(int rows,int cols){
	Cell[][]newSheet=new Cell[rows][cols];
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
	for(int z=0;z<=sheet[0].length;z++){
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
	int[]lol=toIndex(cellCor);
	return hasCell(lol[0],lol[1]);
    }
    public boolean hasCell(int row,int col){
	return (row<sheet.length && col<sheet[0].length);
    }
    /*
      Returns a string representing the sheet in CSV form
      Might need some work but currently it's working ok.
     */
    private String toCSV(){
	String result="";
	for(Cell[]row : sheet){
	    for(int z=0;z<row.length;z++){
		if(row[z].containsNumber()){
		    result+=row[z].getString();
		}else{
		    result+="\""+row[z].getString()+"\"";
		}
		if(z!=row.length-1){
		    result+=",";
		}
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
    public String[][]toStringArray(){
	String[][]result=new String[sheet.length][sheet[0].length];
	for(int r=0;r<sheet.length;r++){
	    for(int c=0;c<sheet[0].length;c++){
		result[r][c]=sheet[r][c].toString();
	    }
	}
	return result;
    }
}
