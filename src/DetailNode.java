
/**
 * 符号表里包含（代号，种别码，变量名，值）
 * @author Will
 *
 */
public class DetailNode {
	//种别码在最下面有表可查
	
	private char alias;
	private int typeCode;
	private String name;
	private float val;
	
	public DetailNode(char alias, int typeCode, String name, float val) {
		super();
		this.alias = alias;
		this.typeCode = typeCode;
		this.name = name;
		this.val = val;
	}
	
	public DetailNode(char alias, int typeCode, String name) {
		super();
		this.alias = alias;
		this.typeCode = typeCode;
		this.name = name;
		this.val = 0;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		String objName = ((DetailNode) obj).getName();
		return name.equals(objName);
	}

	public static int getTypeCode(char c){
		switch(c){
		case '+':
			return 10;
		case '-':
			return 11;
		case '*':
			return 12;
		case '/':
			return 13;
		case '(':
			return 14;
		case ')':
			return 15;
		case '=':
			return 16;
		case 'a':
			return 20;
		case '#':
			return 30;
		}
		System.err.println("没有这个别名");
		return -1;
	}

	public char getAlias() {
		return alias;
	}

	public void setAlias(char alias) {
		this.alias = alias;
	}

	public int getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(int typeCode) {
		this.typeCode = typeCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getVal() {
		return val;
	}

	public void setVal(float val) {
		this.val = val;
	}
	
	
	
	
	/*
	 *种别码：  
	    +	10
		-	11
	 	*	12
		/	13
		(	14
		)	15
		=	16
		a	20 -->数字和单词都简化为a
		#	30
	 */
}
