import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String input = new String();
		TableStack stack = new TableStack();
		CharReader charReader = new CharReader(stack);
		
		if(args == null || args.length == 0){
			Scanner scan = new Scanner(System.in);
			int len;
			do{
				input += scan.next();
				len = input.length();
			}while(input.charAt(len-1) != '#');
		}else{
			input = args[0];
		}
		input += '#';
		int len = input.length();
		
		for(int i = 0; i < len; i++){
			char c = input.charAt(i);
			int res = charReader.whatChar(c);
			if(res == charReader.ERROR){
				System.out.println("输入有误");
			}
		}
//		charReader.read4Queue(input);

		
		input = input.substring(0, --len);
		System.out.println("\n输入的串\n"+input);
		
	}

}
