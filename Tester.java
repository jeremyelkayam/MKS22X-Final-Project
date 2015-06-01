import java.util.*;
public class Tester{
    public static void main(String[]args){
	//System.out.println(Arrays.toString("1,2,3,4".split(",")));
	Sheet s=new Sheet();
	Cell c =new Cell(s);
	c.setData("=SUM(1,2,0,2a)");
	System.out.println(c);
    }
    
}