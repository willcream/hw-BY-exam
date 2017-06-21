import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CharReader {
	//使用ASCII码判断数字和字母
	private String word;
	private String input;
	private char nowc;
	private String localDomain;
	
	private TableStack stack;
	private List<OperQueue> queueList;
	
	
	public static final int ERROR = 9;
	private static final int DIGITAL = 0;
	private static final int LETTER = 1;
	private static final int OPERATOR = 2;
	private static final int LINE = 3;
	public static final int END = 4;
	
	public CharReader(TableStack stack, List<OperQueue> queueList){
		this.stack = stack;
		localDomain = TableStack.EVAL;
		this.queueList = queueList;
	}
	

	//读写单词
	//词法分析，分析整个句子的词法。
	public List<String> readChar(String in){
		List<String> lines = new ArrayList<>();
		int len = in.length();
		word = "";
		for(int i = 0; i < len-1; i++){
			nowc = in.charAt(i);
			word += nowc;
			char nextc = in.charAt(i+1);
			int nowType = whatChar(nowc);
			int nextType = whatChar(nextc);
			
			if(nowType == nextType && nowType < 2){
				//还没成为一个单词
				continue;
			}
			else if(nowType < 2 && nextType >= 2){
				//刚好成为一个单词,开始写入符号表
				System.out.println(word);
				
				if(nowType == DIGITAL){
					stack.pushNode(word,TableStack.DIGITAL, Integer.parseInt(word), localDomain);
				}
				else if(nowType == LETTER){
					stack.pushNode(word, TableStack.SYMBOL, 0, localDomain);
				}
				word = "";
			}
			else if(nowType == OPERATOR && nextType < 2){
				//遇到运算符，修改本地域
				System.out.println(word);
				word = "";
			}
			else if(nextType == LINE && nowType < 2){
				//一行读写完毕，本地域改为最高级的
				System.out.println("LINE: "+word);
				word = "";
			}
			else if(nextType == END && nowType < 2){
				//读写完毕
				System.out.println("END: "+word);
			}
			else{
				//未知问题
				System.out.println("未知问题: "+word+nextc);
				break;
			}
		}
		return lines;
	}
	
	
	/**
	 * 读取字符串，将其简化便于算符优先关系运算。
	 * @param in
	 */
	public void read4Queue(String in){
		OperQueue queue = new OperQueue();
		int len = in.length();
		word = "";
		for(int i = 0; i < len-1; i++){
			nowc = in.charAt(i);
			word += nowc;
			char nextc = in.charAt(i+1);
			int nowType = whatChar(nowc);
			int nextType = whatChar(nextc);
			
			if(nowType == nextType && nowType < 2){
				//还没成为一个单词
				continue;
			}
			else if(nowType < 2 && nextType >= 2){
				//刚好成为一个单词,开始写入算符队列
//				System.out.println(word);
				queue.in('a');
				word = "";
			}
			else if(nowType == OPERATOR){
				//遇到运算符
//				System.out.println("oper--"+nowc);
				queue.in(nowc);
				word = "";
			}
			//TODO 未能正确处理不同行的问题。未能处理等于号的问题
			else if(nowType == LINE){
				word = "";
				queue.in('#');
				queueList.add(queue);
				queue = new OperQueue();
				continue;
			}
			else{
				//TODO 错误检查
				System.out.println("未知问题"+word+nextc);
				break;
			}
			
			if(nextType == END){
				queue.in(nextc);
//				System.out.println(nextc);
				queueList.add(queue);
				break;
			}
		}
		
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
