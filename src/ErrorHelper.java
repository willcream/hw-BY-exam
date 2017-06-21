
public class ErrorHelper {
	public static final int ERROR_EXIT = -1;
	
	public static final int UNKNOW_LINE = -1;
	
	public static final int OTHER = 0;
	public static final int LETTER_NUMBER = 1;
	public static final int TWO_OPERATOR = 2;
	
	public static final int NO_EVALUATE = 11;
	public static final int TWO_EQUAL = 12;
	public static final int WRONG_GRAMMER = 13;
	
	public static final int DIVIDE_0 = 21;
	
	
	public static void wordError(String word, int type, int lineNum){
		String reason = "";
		switch(type){
		case LETTER_NUMBER:
			reason = "字母和数字不能连在一起";
			break;
		case TWO_OPERATOR:
			reason = "这两个运算符不能连续使用";
			break;
		case OTHER:
			reason = "未知";
			break;
		}
		if(lineNum == UNKNOW_LINE)
			System.out.println("词法错误："+word+", 原因："+reason);
		else
			System.out.println("词法错误："+word+", 原因："+reason+", 位于行数："+lineNum);
		System.exit(ERROR_EXIT);
	}
	
	public static void grammerError(String word, int type, int lineNum){
		String reason = "";
		switch(type){
		case NO_EVALUATE:
			reason = "没有赋值语句";
			break;
		case TWO_EQUAL:
			reason = "一个赋值语句不能有两个等于号";
			break;
		case WRONG_GRAMMER:
			reason = "不属于该文法";
			break;
		case OTHER:
			reason = "未知";
			break;
		}
		if(lineNum == UNKNOW_LINE)
			System.out.println("语法错误："+word+", 原因："+reason);
		else
			System.out.println("语法错误："+word+", 原因："+reason+", 位于行数："+lineNum);
		System.exit(ERROR_EXIT);
	}
	
	
	public static void calculateError(String word, int type, int lineNum){
		String reason = "";
		switch(type){
		case DIVIDE_0:
			reason = "除以0";
			break;
		case OTHER:
			reason = "未知";
			break;
		}
		
		if(lineNum == UNKNOW_LINE)
			System.out.println("计算错误："+word+", 原因："+reason);
		else
			System.out.println("计算错误："+word+", 原因："+reason+", 位于行数："+lineNum);
		
		System.exit(ERROR_EXIT);
	}
}
