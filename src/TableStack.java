import java.util.ArrayList;

/**
 * 符号表
 * @author Will
 *
 */
public class TableStack {
	public static final String DIGITAL="digital";
	public static final String SYMBOL="symbol";
	
	
	ArrayList<TableNode> stack; 
	
	public void pushNode(String name,String type,float val,String room){
		
	}
	
	public TableNode topNode(){
		return null;
	}
	
	public TableNode pop(){
		return null;
	}
	
	public void editTop(String name,String type,float val,String room){
		
	}
}

class TableNode{
	private String name;
	private String type;
	private float val;
	private String room;//作用域
	
	
	public TableNode(String name, String type, float val, String room) {
		super();
		this.name = name;
		this.type = type;
		this.val = val;
		this.room = room;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public float getVal() {
		return val;
	}
	public void setVal(float val) {
		this.val = val;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	
	
}
