import java.util.Scanner;

public class CharReader {
	//���ֺ���ĸʹ��ASCII���ж�
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
	
	
	//����,Ϊ�Ѿ�ȫ��ƴ�ӵ��ַ���
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
				//��ʼд��expression�ķ��ű�
			}
			else if(nextType == LINE && nowType < 2){
				//��wordд����ű�󣬽��������������л�����ֵ���
			}
			else if(nextType == END && nowType < 2){
				//��ȡ���
			}
			else if(nowType == ERROR || nextType == ERROR){
				//����
				break;
			}
			else{
				//����
				break;
			}
		}
		return input;
	}
	
	private int whatChar(char c){
		//����
		if(c >= 48 && c <= 57){
			return DIGITAL;
		}
		//��д��ĸ
		else if(c >= 65 && c <= 90){
			return LETTER;
		}
		//Сд��ĸ
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
