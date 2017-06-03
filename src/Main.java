import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String input = new String();
		if(args == null){
			Scanner scan = new Scanner(System.in);
			int len;
			do{
				input += scan.next();
				len = input.length();
			}while(input.charAt(len-1) != '#');
		}else{
			input = args[0];
		}
		int len = input.length();
		for(int i = 0; i < len; i++){
			char c = input.charAt(i);
			if(
					(c >= 48 && c <= 57) ||
					(c >= 65 && c <= 90)||
					(c >= 97 && c <= 122)){
				continue;
			}
			else if(
					c == '(' ||
					c == ')' ||
					c == ';' ){
				continue;
			}
			else{
				System.out.println("ÊäÈëÓÐÎó");
				break;
			}
		}
		
	}

}
