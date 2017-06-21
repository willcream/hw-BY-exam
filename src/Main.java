import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String input = new String();
		TableStack stack = new TableStack();
		List<OperQueue> queueList = new ArrayList<>(); 
		List<DetailNode> details = new ArrayList<>();
		CharReader charReader = new CharReader(stack,queueList,details);
		
		//正常录入控制台的输入
		if(args == null || args.length == 0){
			Scanner scan = new Scanner(System.in);
			int len;
			do{
				input += scan.next();
				len = input.length();
			}while(input.charAt(len-1) != '#');
		}
		//方便于自己使用脚本测试。
		else{
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
		
		
		List<OperQueue> operList = charReader.read4Queue(input);
		int i = 1;boolean istrue = false;
//		for(OperQueue queue : queueList){
//			System.out.println("第"+i+"行：");
//			queue.print();
//			LAnalysis any = new LAnalysis(queue);
//			istrue = any.analysize();
//			if(!istrue){
//				System.err.println(-1);
//			}
//			i++;
//		}
		
		Calculator cal = new Calculator(operList);
		cal.calculate();
		

		
//		input = input.substring(0, --len);
//		System.out.println("\n输入的串\n"+input);
		
	}

}

