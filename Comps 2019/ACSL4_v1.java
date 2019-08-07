import java.util.*;
import java.io.*;

public class ACSL4_v1 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		Scanner in = new Scanner(new File("input.txt"));
		String[] ins = new String[5];
		Deque<Integer> q = new ArrayDeque<>(); 
		Stack<String> stack = new Stack<String>();
		//Stack<Integer> nums = new Stack<Integer>();
		String s;
		int result = 0, a, b, c;

		for(int i = 0; i <= 4; i++) {
			ins[i] = in.nextLine();
		}
		in.close();

		for(int x = 0; x < 5; ++x)
		{
			for(int i = 0; i < ins[x].length(); ++i)
			{//continue with space
				s = ins[x].substring(i, ins[x].indexOf(' '));
				//				if(s == ' ')
				//					continue;
				//				else if(s == '+')
				//					stack.push(s);
				//				else if(s == '-') 
				//					stack.push(s);
				//				else if(s == '*')
				//					stack.push(s);
				//				else if(s == '@') {
				//					stack.push(s);
				//				}else if(s == '>') {
				//					stack.push(s);
				//				}
				//				else
				//					q.add(Character.getNumericValue(s));

				if(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("@") || s.equals(">")){
					stack.push(s);
				}else{
					System.out.println(s);
					q.add(Integer.parseInt(s));
				}
			}

			while(stack.isEmpty() == false) {
				s = stack.pop();
				if(s.equals("+")) {
					q.addFirst(q.poll() + q.poll());
				}else if(s.equals("-")) {
					q.push(q.poll() - q.poll());
				}else if(s.equals("*")) {
					q.push(q.poll() * q.poll());
				}else if(s.equals("@")) {
					a = q.poll();
					b = q.poll();
					c = q.poll();
					if(a > 0)
						result = b;
					else
						result = c;
					q.push(result);
				}else if(s.equals(">")) {
					a = q.poll();
					b = q.poll();
					c = q.poll();
					if(a > b && a > c)
						result = a;
					else if(b > a && b > c)
						result = b;
					else if(c > a && c > b)
						result = c;
					q.push(result);
				}
			}
			System.out.println(q.pop());

		}
	}
}
