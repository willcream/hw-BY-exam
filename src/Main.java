import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
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
//		input += '#';
		int len = input.length();
		
		for(int i = 0; i < len; i++){
			char c = input.charAt(i);
			int res = charReader.whatChar(c);
			if(res == charReader.ERROR){
				System.out.println("输入有误");
			}
		}
		
		
		//清空yyy.txt
		File file = new File("yyy.txt");
		FileOutputStream fos = null;
		try{
			fos = new FileOutputStream(file);
			String empty = "";
			fos.write(empty.getBytes());
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
		queueList = charReader.read4Queue(input);
		int lineNum = 1;boolean istrue = false;
		for(OperQueue queue : queueList){
			LAnalysis any = new LAnalysis(queue);
			istrue = any.analysize(lineNum);
			if(!istrue){
				System.err.println(-1);
			}
			lineNum++;
		}
		
		queueList = charReader.read4Queue(input);
		charReader.printSortCode();
		
		Calculator cal = new Calculator(queueList
				);
		cal.calculate();
		
		System.out.println("\n==========结束===========\n");
//		System.out.println("\n输入的串\n"+input);
		
	}

}

