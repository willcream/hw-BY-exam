import java.util.Scanner;

public class CharReader {
	//数字和字母使用ASCII码判断
	private String word;
	private String input;
	private char nowc;
	private TableStack stack;
	
	
	private final int ERROR = 9;
	private final int DIGITAL = 0;
	private final int LETTER = 1;
	private final int OPERATOR = 2;
	private final int LINE = 3;
	private final int END = 4;
	
	
	//读词,为已经全部拼接的字符串
	public String readChar(String in){
		int len = in.length();
		word = "";
		for(int i = 0; i < len-1; i++){
			nowc = in.charAt(i);
			word += nowc;
			char nextc = in.charAt(i+1);
			int nowType = whatChar(nowc);
			int nextType = whatChar(nextc);
			
			if(nowType == nextType && nowType < 2){
				continue;
			}
			else if(nowType == OPERATOR){
				//开始写入expression的符号表
			}
			else if(nextType == LINE && nowType < 2){
				//将word写入符号表后，接下来的作用域切换到赋值语句
			}
			else if(nextType == END && nowType < 2){
				//读取完毕
			}
			else if(nowType == ERROR || nextType == ERROR){
				//报错
				break;
			}
			else{
				//报错
				break;
			}
		}
		return input;
	}
	
	private int whatChar(char c){
		//数字
		if(c >= 48 && c <= 57){
			return DIGITAL;
		}
		//大写字母
		else if(c >= 65 && c <= 90){
			return LETTER;
		}
		//小写字母
		else if(c >= 97 && c <= 122){
			return LETTER;
		}
		else if(c == '+' ||
				c == '-' ||
				c == '*' ||
				c == '/' ||
				c == '(' ||
				c == ')'){
			return OPERATOR;
		}
		else if(c == ';'){
			return LINE;
		}
		else if(c == '#'){
			return END;
		}
		else{
			return ERROR;
		}
	}
	
	
}
