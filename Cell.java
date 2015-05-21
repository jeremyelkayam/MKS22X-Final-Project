/*What does a cell need?
  - Must store any type of data that you can enter into the spreadsheet
  - 
  
 */
public class Cell<T>{
    T data;
    public Cell(T d){
	data=d;
    }
    public T getData(){
	return data;
    }
}
