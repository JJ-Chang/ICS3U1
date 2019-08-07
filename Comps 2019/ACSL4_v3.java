import java.util.*;
import java.io.*;
public class ACSL4_v3 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		Scanner in = new Scanner(new File("input.txt"));
		String[] ins = new String[5];
		char c;
		String temp1, temp2, temp3;
		int result, num1, num2, num3, count = 0, checks, checks2, checks3;
		String s, replaceIt;
		boolean check = true;

		for(int i = 0; i <= 4; i++) {
			ins[i] = in.nextLine();
		}
		in.close();

		for(int x = 0; x < 5; ++x) 
		{
			s = ins[x];
			while(check == true) {
				c = s.charAt(count);
				if(c == ' ')
					continue;
				else if(c == '+') {
					checks = s.indexOf(' ', count + 2);
					temp1 = s.substring(count + 2, checks);
					checks2 = s.indexOf(' ', checks + 1);
					temp2 = s.substring(checks + 1, checks2);
					if(!temp1.contentEquals("+") && !temp1.contentEquals("-") && !temp1.contentEquals("*") && !temp1.contentEquals("@") 
							&& !temp1.contentEquals(">") && !temp2.contentEquals("+") && !temp2.contentEquals("-") && !temp2.contentEquals("*") 
							&& !temp2.contentEquals("@") && !temp2.contentEquals(">")) {
						num1 = Integer.parseInt(temp1);
						num2 = Integer.parseInt(temp2);
						replaceIt = s.substring(count - 1, count + 4);
						result = num1 + num2;
						s = s.replace(replaceIt, Integer.toString(result));
						count = 0;
						System.out.println(s);//*****
					}
					else
						++count;
				}else if(c == '-') {
					checks = s.indexOf(' ', count + 2);
					temp1 = s.substring(count + 2, checks);
					checks2 = s.indexOf(' ', checks + 1);
					temp2 = s.substring(checks + 1, checks2);
					if(!temp1.contentEquals("+") && !temp1.contentEquals("-") && !temp1.contentEquals("*") && !temp1.contentEquals("@") 
							&& !temp1.contentEquals(">") && !temp2.contentEquals("+") && !temp2.contentEquals("-") && !temp2.contentEquals("*") 
							&& !temp2.contentEquals("@") && !temp2.contentEquals(">")) {
						num1 = Integer.parseInt(temp1);
						num2 = Integer.parseInt(temp2);
						replaceIt = s.substring(count - 1, count + 4);
						result = num1 - num2;
						s = s.replace(replaceIt, Integer.toString(result));
						count = 0;
						System.out.println(s);//*****
					}
					else
						++count;
				}else if(c == '*') {
					checks = s.indexOf(' ', count + 2);
					temp1 = s.substring(count + 2, checks);
					checks2 = s.indexOf(' ', checks + 1);
					temp2 = s.substring(checks + 1, checks2);
					if(!temp1.contentEquals("+") && !temp1.contentEquals("-") && !temp1.contentEquals("*") && !temp1.contentEquals("@") 
							&& !temp1.contentEquals(">") && !temp2.contentEquals("+") && !temp2.contentEquals("-") && !temp2.contentEquals("*") 
							&& !temp2.contentEquals("@") && !temp2.contentEquals(">")) {
						num1 = Integer.parseInt(temp1);
						num2 = Integer.parseInt(temp2);
						replaceIt = s.substring(count - 1, count + 4);
						result = num1 * num2;
						s = s.replace(replaceIt, Integer.toString(result));
						count = 0;
						System.out.println(s);//*****
					}
					else
						++count;
				}else if(c == '@'){
					checks = s.indexOf(' ', count + 2);
					temp1 = s.substring(count + 2, checks);
					checks2 = s.indexOf(' ', checks + 1);
					temp2 = s.substring(checks + 1, checks2);
					checks3 = s.indexOf(' ', checks2 + 1);
					temp3 = s.substring(checks2 + 1, checks3);
					if(!temp1.contentEquals("+") && !temp1.contentEquals("-") && !temp1.contentEquals("*") && !temp1.contentEquals("@") 
							&& !temp1.contentEquals(">") && !temp2.contentEquals("+") && !temp2.contentEquals("-") && !temp2.contentEquals("*") 
							&& !temp2.contentEquals("@") && !temp2.contentEquals(">") && !temp3.contentEquals("+") && !temp3.contentEquals("-") 
							&& !temp3.contentEquals("*") && !temp3.contentEquals("@") && !temp3.contentEquals(">") ) {
						num1 = Integer.parseInt(temp1);
						num2 = Integer.parseInt(temp2);
						num3 = Integer.parseInt(temp3);
						replaceIt = s.substring(count - 1, count + 6);
						if(num1 > 0)
							result = num2;
						else
							result = num3;
						s = s.replace(replaceIt, Integer.toString(result));
						count = 0;
						System.out.println(s);//*****
					}
					else
						++count;
				}else if(c == '>') {
					checks = s.indexOf(' ', count + 2);
					temp1 = s.substring(count + 2, checks);
					checks2 = s.indexOf(' ', checks + 1);
					temp2 = s.substring(checks + 1, checks2);
					checks3 = s.indexOf(' ', checks2 + 1);
					temp3 = s.substring(checks2 + 1, checks3);
					if(!temp1.contentEquals("+") && !temp1.contentEquals("-") && !temp1.contentEquals("*") && !temp1.contentEquals("@") 
							&& !temp1.contentEquals(">") && !temp2.contentEquals("+") && !temp2.contentEquals("-") && !temp2.contentEquals("*") 
							&& !temp2.contentEquals("@") && !temp2.contentEquals(">") && !temp3.contentEquals("+") && !temp3.contentEquals("-") 
							&& !temp3.contentEquals("*") && !temp3.contentEquals("@") && !temp3.contentEquals(">") ) {
						num1 = Integer.parseInt(temp1);
						num2 = Integer.parseInt(temp2);
						num3 = Integer.parseInt(temp3);
						replaceIt = s.substring(count - 1, count + 6);
						if(num1 > num2 && num1 > num3)
							result = num1;
						else if(num2 > num1 && num2 > num3)
							result = num2;
						else
							result = num3;
						s = s.replace(replaceIt, Integer.toString(result));
						count = 0;
						System.out.println(s);//*****
					}
					else
						++count;
				}

				if(s.indexOf('+') < 0 && s.indexOf('-') < 0 & s.indexOf('*') < 0 && s.indexOf('@') < 0 && s.indexOf('>') < 0)
					break;
					//System.out.println("test");
			}
			System.out.println(s);
		}
	}

}
