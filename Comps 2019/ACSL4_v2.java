import java.util.*;
import java.io.*;

public class ACSL4_v2 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		Scanner in = new Scanner(new File("input.txt"));
		String[] ins = new String[5];
		Deque<Integer> q = new LinkedList<>(); 
		char s;
		int result = 0, a, b, c;

		for(int i = 0; i <= 4; i++) {
			ins[i] = in.nextLine();
		}
		in.close();

		for(int x = 0; x < 5; ++x)
		{
			for(int i = 0; i < ins[x].length(); ++i)
			{
				s = ins[x].charAt(i);
				if(s == ' ')
					continue;
				else if(s != '+' || s != '-' || s != '*' || s != '@' || s != '>')
					q.add(Character.getNumericValue(s));
				else if(s == '+')
					q.push(q.poll() + q.poll());
				else if(s == '-') 
					q.push(q.poll() - q.poll());
				else if(s == '*')
					q.push(q.poll() * q.poll());
				else if(s == '@') {
					a = q.poll();
					b = q.poll();
					c = q.poll();
					if(a > 0)
						result = b;
					else
						result = c;
					q.push(result);
				}else if(s == '>') {
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
				System.out.println(q.pop());
			}
		}
	}
}
