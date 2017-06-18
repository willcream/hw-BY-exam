import java.util.ArrayList;

/**
 * 符号表
 * @author Will
 *
 */
public class TableStack {
	private ArrayList<TableNode> stack; 
	
	
	public static final String DIGITAL="digital";
	public static final String SYMBOL="symbol";
	public static final String OPER="operator";
	public static final String L="line";
	public static final String E="end";
	public static final String EVAL="evaluate";
	public static final String EXPR="expression";
	public static final String TERM="term";
	public static final String FACTOR="factor";
	
	public TableStack(){
		stack = new ArrayList<>();
	}
	
	public void pushNode(String name,String type,float val,String room){
		TableNode tn = new TableNode(name,type,val,room);
		stack.add(tn);
		System.out.println("--> ["+name+", "+type+", "+val+", "+room+"]");
	}
	
	public TableNode topNode(){
		int len = stack.size();
		return stack.get(len-1);
	}
	
	public TableNode pop(){
		int len = stack.size();
		return stack.remove(len-1);
	}
	
	public void editTop(String name,String type,float val,String room){
		pop();
		TableNode tn = new TableNode(name,type,val,room);
		stack.add(tn);
	}
}

class TableNode{
	private String name;
	private String type;
	private float val;
	private String room;//作用域
	
	
	public TableNode(String name, String type, float val, String room) {
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
