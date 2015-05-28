import java.util.*;
public class Sheet{
    Cell[][]sheet;
    public Sheet(){
	sheet=new Cell[20][20];
	for(int r=0;r<sheet.length;r++){
	    for(int c=0;c<sheet.length;c++){
		sheet[r][c]=new Cell(this);
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
    public int parseColumn(String column){//turns a column in letter form to number form
	int result=0;
	int coef=1;
	String reversed = new StringBuilder(column).reverse().toString();
	for(int z=0;z<reversed.length();z++){
	    char c=reversed.charAt(z);
	    int value=0;
	    switch(c){//this is gonna suck
	    case 'A':
		value=0;
		break;
	    case 'B':
		value=1;
		break;
	    case 'C':
		value=2;
		break;
	    case 'D':
		value=3;
		break;
	    case 'E':
		value=4;
		break;
	    case 'F':
		value=5;
		break;
	    case 'G':
		value=6;
		break;
	    case 'H':
		value=7;
		break;
	    case 'I':
		value=8;
		break;
	    case 'J':
		value=9;
		break;
	    case 'K':
		value=10;
		break;
	    case 'L':
		value=11;
		break;
	    case 'M':
		value=12;
		break;
	    case 'N':
		value=13;
		break;
	    case 'O':
		value=14;
		break;
	    case 'P':
		value=15;
		break;
	    case 'Q':
		value=16;
		break;
	    case 'R':
		value=17;
		break;
	    case 'S':
		value=18;
		break;
	    case 'T':
		value=19;
		break;
	    case 'U':
		value=20;
		break;
	    case 'V':
		value=21;
		break;
	    case 'W':
		value=22;
		break;
	    case 'X':
		value=23;
		break;
	    case 'Y':
		value=24;
		break;
	    case 'Z':
		value=25;
		break;
	    }
	    result+=value*coef;//TODO account for 'AA' which should evaluate to 26 but will evaluate to 0 using this method
	    coef*=26;
	}
	return result;
    }
}
