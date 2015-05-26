import java.util.*;
public class Sheet{
    Cell[][]sheet;
    public Sheet(){
	sheet=new Cell[20][20];
	for(int r=0;r<sheet.length;r++){
	    for(int c=0;c<sheet.length;c++){
		sheet[r][c]=new Cell();
	    }
	}
    }
    public Cell getCell(int row, int col){
	return sheet[row][col];
    }
    public void setData(int row, int col, double value){
	sheet[row][col].setData(value);
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
    public String toString(){
	String result="";
	for(Cell[]z : sheet){
	    result+=Arrays.toString(z)+"\n";
	}
	return result;
    }
}
