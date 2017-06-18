import java.util.Scanner;

public class CharReader {
	//使用ASCII码判断数字和字母
	private String word;
	private String input;
	private char nowc;
	private TableStack stack;
	
	
	public static final int ERROR = 9;
	private static final int DIGITAL = 0;
	private static final int LETTER = 1;
	private static final int OPERATOR = 2;
	private static final int LINE = 3;
	public static final int END = 4;
	
	
	//读写单词
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
				//已经成为一个单词
				continue;
			}
			else if(nowType == OPERATOR && nextType < 2){
				//遇到运算符，修改本地域
				System.out.println(word);
				word = "";
			}
			else if(nextType == LINE && nowType < 2){
				//一行读写完毕，本地域改为最高级的
				System.out.println("LINE: "+word);
			}
			else if(nextType == END && nowType < 2){
				//读写完毕
				System.out.println("END: "+word);
			}
			else{
				//未知问题
				System.out.println("未知问题: "+word);
				break;
			}
		}
		return input;
	}
	
	public int whatChar(char c){
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
				c == ')' ||
				c == '='){
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
