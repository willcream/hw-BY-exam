import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class OperQueue {
	public static final String SYMBOL="symbol";
	public static final String OPER="operator";
	public static final String LINE="line";
	public static final String END="end";

	private List<OperNode> queue;
	private List<DetailNode> details;

	public OperQueue(List<DetailNode> details){
		queue = new ArrayList<>();
		this.details = details;
	}

	/**
	 * 终结符输入本身，如果是单词或者数，就直接写作a
	 * @param c 终结符
	 */
	public void in(char c, int typeCode, String name, float val){
		queue.add(new OperNode(name,c));
		//避免重复进入
		if(getDetail(name) == null)
			details.add(new DetailNode(c, typeCode, name, val));
		else{
			
		}
	}

	public void in(char c, int typeCode, String name) {
		in(c,typeCode, name, 0);
	}

	public char out(){
		char c = queue.remove(0).alias;
		return c;
	}
	
	public OperNode outHead(){
		return queue.remove(0);
	}

	/**
	 * 感觉用处不大，用findDetail可能更好
	 * @return
	 */
	@Deprecated
	public DetailNode detailOut(){
		return details.remove(0);
	}

	public DetailNode getDetail(String name){
		for(DetailNode dn : details){
			if(dn.getName().equals(name))
				return dn;
		}
		return null;
	}
	
	public void setDetailValue(String name, float value){
		DetailNode oldDn = getDetail(name);
		DetailNode newDn = new DetailNode(oldDn.getAlias(), oldDn.getTypeCode(), name, value);
		details.remove(oldDn);
		details.add(newDn);
		
	}

	public int detailSize(){
		return details.size();
	}

	public void print(){
		for (OperNode on : queue){
			char c = on.alias;
			System.out.println(c);
		}
	}
	
	public void sortCode(){
		for (OperNode on : queue){
			char c = on.alias;
			int code = DetailNode.getTypeCode(c);
			if(c == 'a')
				System.out.println(on.name+"\t\t"+"("+code+"\t,\t"+getDetail(on.name).getVal()+")");
			else
				System.out.println(on.name+"\t\t"+"("+code+"\t,\t"+on.name+")");
		}
	}

	/**
	 * 获取对首但不移除
	 * @return 对首
	 */
	public char head(){
		return queue.get(0).alias;
	}

	public int size(){
		return queue.size();
	}

}
