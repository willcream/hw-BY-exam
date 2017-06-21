import java.util.Stack;

/**
 * 语法分析器，算符优先分析器
 * 构造的算符优先表仅仅针对本次文法
 * 需要：移进
 * 还需要函数：移进函数
 * @author Administrator
 *
 */
public class LAnalysis {
	private final char[] VT={'+','-','*','/','a','(',')','=','#'};
	private final String[] P={"##","a=","+","-","*","/","a","()"};
	private final int ERROR = -9;

	/*
	 * -1 --> "<"
	 * 0 --> "="
	 * 1 --> ">"
	 */
	private int[][] order;
	private Stack<Character> stack;
	private OperQueue queue;


	public LAnalysis(OperQueue inputQueue){
		//Initialize
		order = new int[9][9];
		initializeOrder();
		stack = new Stack<>();
		stack.push('#');
		queue = inputQueue;
	}


	//分析函数
	public boolean analysize(){
		int flag = 0;
		char top='%';
		char nowc='%';
		int num = 0;//记录已处理的字符数量
		boolean isEqualExist = false;
		
		if(queue.size() <= 2){
			System.out.println("没有赋值语句");
			return false;
		}
		
		do{
			top = stack.peek();
			nowc = queue.head();
			int len = queue.size();
			
			if(nowc != '=' && num == 1){
				System.out.println("没有赋值语句");
				return false;
			}
			
			if(nowc == '=' ){
				if(isEqualExist){
					flag = -1;
					System.out.println("一个赋值语句不能有两个等于号");
					break;
				}
				else{
					isEqualExist = true;
				}
			}
			
			
			int judge = judge(top, nowc);
			if(judge == -1 || judge == 0){
				if(top == '#' && nowc == '#'){
					System.out.println("语法正确");
					return true;
				}
				
				//移进
				nowc = queue.out();
				stack.push(nowc);
			}
			else if(judge == 1){
				//归约
				reduction();
			}
			else{
				//TODO 有不属于该文法的情况出现
				System.out.println("不属于该文法，出错处： "+top+nowc);
				flag = -1;
				break;
			}
			num++;
		}while(queue.size() != 0);
		if(flag != -1)
			System.out.println("算符优先文法不明错误:"+top+nowc);
		return false;
	}


	public int getVTPos(char vt){
		int i = 0;
		for(Character c : VT){
			if(c.equals(vt))
				return i;
			else
				i++;
		}
		//TODO 错误处理，没有匹配的运算符，可能在词法分析就有错误遗漏
		return -1;
	}

	/**
	 * 当需要归约时调用该函数查看需要用哪个产生式归约
	 * @return 归约(reduce)时需要弹出的数量。
	 */
	private int reduction(){
		/*
		 * 1.弹出栈顶，作为reduce
		 * 2.reduce第一个字符与栈顶top相比，若top<reduce[0],则前往第3步，否则继续弹出，reduce = top+reduce
		 * 3.reduce为待归约串，也是句柄，开始匹配产生式，匹配成功后可以弹出
		 */
		String reduce = new String();
		reduce = stack.pop()+reduce;

		int j = judge(stack.peek(),reduce.charAt(0));
		while(j > -1){
			reduce = stack.pop()+reduce; 
			j = judge(stack.peek(),reduce.charAt(0));
		}

		//开始判断归约串是否属于某个产生式
		for(String s : P){
			if(s.equals(reduce))
				return reduce.length();
		}

		//TODO 错误处理：没有找到适合的产生式
		return -1;
	}

	private int judge(char a,char b){
		int posa = getVTPos(a);
		int posb = getVTPos(b);

		if(posa == -1 || posb == -1){
			System.out.println("获取字符坐标失败");
		}
		return order[posa][posb];
	}
	
	
	private void initializeOrder(){
		//初始化
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++)
				order[i][j] = ERROR;
		}


		//填写算符优先级表
		order[0][2]=-1; 	order[0][3]=-1; 	order[0][4]=-1;
		order[0][5]=-1; 	order[0][6]=1; 		order[0][8]=1;
		order[0][0]=1;		order[0][1]=1;

		order[1][2]=-1; 	order[1][3]=-1;		order[1][4]=-1;
		order[1][5]=-1; 	order[1][6]=1;		order[1][8]=1;
		order[1][0]=1;		order[1][1]=1;

		order[2][0]=1; 		order[2][1]=1;		order[2][4]=-1;
		order[2][5]=-1;		order[2][6]=1;		order[2][8]=1;
		order[2][2]=1;		order[2][3]=1;

		order[3][0]=1;		order[3][1]=1;		order[3][4]=-1;
		order[3][5]=-1;		order[3][6]=1;		order[3][8]=1;
		order[3][2]=1;		order[3][3]=1;

		order[4][0]=1;		order[4][1]=1;		order[4][2]=1;
		order[4][3]=1;		order[4][6]=1;		order[4][7]=0;
		order[4][8]=1;
		
		order[5][0]=-1;		order[5][1]=-1;		order[5][2]=-1;
		order[5][3]=-1;		order[5][4]=-1;		order[5][5]=-1;
		order[5][6]=0;
		
		order[6][0]=1;		order[6][1]=1;		order[6][2]=1;
		order[6][3]=1;		order[6][6]=1;		order[6][8]=1;
		
		order[7][0]=-1;		order[7][1]=-1;		order[7][2]=-1;
		order[7][3]=-1;		order[7][4]=-1;		order[7][5]=-1;
		order[7][8]=1;
		
		order[8][0]=-1;		order[8][1]=-1;		order[8][2]=-1;
		order[8][3]=-1;		order[8][4]=-1;		order[8][5]=-1;
		order[8][8]=0;
	}
}
