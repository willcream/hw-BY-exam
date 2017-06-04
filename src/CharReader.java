import java.util.Scanner;

public class CharReader {
	//鏁板瓧鍜屽瓧姣崭娇鐢ˋSCII镰佸垽鏂?
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
	
	
	//璇昏瘝,涓哄凡缁忓叏閮ㄦ嫾鎺ョ殑瀛楃涓?
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
			else if(nowType < 2 && nextType == OPERATOR){
				//阆囧埌杩愮畻绗︼紝鎴愬崟璇崭简
				System.out.println(word);
				word = "";
			}
			else if(nowType == OPERATOR && nextType < 2){
				//寮€濮嫔啓鍏xpression镄勭鍙疯〃
				System.out.println(word);
				word = "";
			}
			else if(nextType == LINE && nowType < 2){
				//灏唚ord鍐椤叆绗﹀佛琛ㄥ悗锛屾帴涓嬫潵镄勪綔鐢ㄥ烟鍒囨崲鍒拌祴链艰鍙?
				System.out.println("LINE: "+word);
			}
			else if(nextType == END && nowType < 2){
				//璇诲彇瀹屾瘯
				System.out.println("END: "+word);
			}
			else if(nowType == ERROR || nextType == ERROR){
				//鎶ラ敊
				System.out.println("鍑虹幇涓嶅悎娉旷殑瀛楃");
				break;
			}
			else{
				//鎶ラ敊
				System.out.println("涓嶅悎娉旷殑鍗曡瘝: "+word);
				break;
			}
		}
		return input;
	}
	
	public int whatChar(char c){
		//鏁板瓧
		if(c >= 48 && c <= 57){
			return DIGITAL;
		}
		//澶у啓瀛楁瘝
		else if(c >= 65 && c <= 90){
			return LETTER;
		}
		//灏忓啓瀛楁瘝
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
