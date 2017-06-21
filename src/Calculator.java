import java.util.List;

/**
 * 使用前，需要再次使用CharReader的read4Queue，给operList重新赋值
 * ch改为符号a，从符号表中获取，符号表里包含（代号，种别码，变量名，值）
 * 运算时要小心值为0的变量，最好添加throws
 * 
 * 每个OperQueue为一行的代码，其实queue并没什么用，主要是用details
 * 使用别名来引导程序进行，name用来输出四元组的。
 * @author Will
 *
 */
public class Calculator {
	private final int UNKNOW_ERROR = -999;
	
	private List<OperQueue> operList;
	private int flag=1; //标识是否有错误，flag为1正确，为0错误。

	//不断替换的变量
	private char ch;
	private OperQueue queue;
	private OperNode on;
	private OperNode genOn;//用于更新符号表数据，记录的是“=”前面的单词，在运算“=”时更新数值


	public Calculator(List<OperQueue> operList) {
		super();
		this.operList = operList;
	}

	public void calculate(){
		try{
			if(operList.size() == 0){
				throw new Exception();
			}
		}catch(NullPointerException e){
			e.printStackTrace();
		}catch (Exception e) {
			System.err.println("语义计算---operList无内容");
		}

		for(OperQueue q : operList){
			queue = q;
			//开始计算该行
			//得到第一个字符
			on = queue.outHead();
			ch = on.alias;
			genOn = on;
			v();
			if(ch == '#'){
				//success
				break;
			}
			
		}
	}

	private float v(){
		float v1_place=f();
		if(ch == '='){
			char oper = ch;
			on = queue.outHead();
			ch = on.alias;
			float v2_place=e();
			if(flag == 0){
			}
			float temp = 0;
			v1_place = gen(oper,v1_place,v2_place,temp);
		}
		return v1_place;
	}

	private float e(){
		float e1_place=t();
		while(ch=='+' || ch=='-'){
			char oper = ch;
			on = queue.outHead();
			ch = on.alias;
			float e2_place=t();
			if(flag == 0){
				break;
			}
			float temp = 0;
			e1_place = gen(oper,e1_place,e2_place,temp);
		}
		return e1_place;
	}

	private float t(){
		float t1_place=f();
		while(ch=='*' || ch=='/'){
			char oper = ch;
			on = queue.outHead();
			ch = on.alias;
			float t2_place = f();
			if(flag== 0){
				break;
			}
			float temp = 0;
			t1_place = gen(oper,t1_place,t2_place,temp);
		}
		return t1_place;
	}

	private float f(){
		if(ch=='a'){
			OperNode tempOn = on;
			on = queue.outHead();
			ch = on.alias;
			return getValue(tempOn.name);
		}
		else if(ch=='('){
			on = queue.outHead();
			ch = on.alias;
			float place = e();
			if(ch==')'){
				on = queue.outHead();
				ch = on.alias;
				return place;
			}
			else flag= 0;
			return (Float) null;
		}
		else{
			flag= 0;
			return (Float) null;
		}
	}

	private float gen(char oper,float place1,float place2,float temp){
		//		temp = e1_place+oper+e2_place;
		switch(oper){
		case '+':
			temp = place1 + place2;
			break;
		case '-':
			temp = place1 - place2;
			break;
		case '*':
			temp = place1 * place2;
			break;
		case '/':
			try{
				temp = place1 / place2;
			}catch(ArithmeticException e){
				System.out.println("有为0的除数");
				e.printStackTrace();
			}
			break;
		case '=':
			temp = place2;
			queue.setDetailValue(genOn.name, temp);
			break;
		default:
			System.err.println("未知运算规则： "+oper);
		}

		System.out.println("("+oper+" , "+place1+" , "+place2+" , "+temp+')');

		return temp;
	}


	private float getValue(String name){
		return queue.getDetail(name).getVal();
	}

}
