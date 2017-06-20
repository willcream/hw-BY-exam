import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class OperQueue {
	public static final String SYMBOL="symbol";
	public static final String OPER="operator";
	public static final String LINE="line";
	public static final String END="end";
	
	private List<Character> queue;
	
	
	public OperQueue(){
		queue = new ArrayList<>();
	}
	
	/**
	 * 终结符输入本身，如果是单词或者数，就直接写作a
	 * @param c 终结符
	 */
	public void in(char c){
		queue.add(c);
	}
	
	public char out(){
		char c = queue.remove(0);
		return c;
	}
	
	public void print(){
		for (char c : queue){
			System.out.println(c);
		}
	}
}
