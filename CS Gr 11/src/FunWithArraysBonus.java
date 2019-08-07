import java.util.*;
import java.io.*;
public class FunWithArraysBonus {

	//Returns the value of each Roman character from a given Roman numeral(RN)
	//with integer as an element in an array (rChar)
	static int[] change(String RN) {
		int [] rChar = new int[RN.length()];
		char roman;

		for(int i = 0; i < RN.length(); ++i) {
			roman = RN.charAt(i);
			switch(roman) {
			case 'I' : 
				rChar[i] = 1;
				break;
			case 'V' : 
				rChar[i] = 5;
				break;
			case 'X' : 
				rChar[i] = 10;
				break;
			case 'L' : 
				rChar[i] = 50;
				break;
			case 'C' : 
				rChar[i] = 100;
				break;
			case 'D' : 
				rChar[i] = 500;
				break;
			case 'M' : 
				rChar[i] = 1000;
				break;
			}//switch
		}//for
		return rChar;
	}//change

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		Scanner in = new Scanner (new File("input.txt"));
		int num = 0, temp1, temp2, howMany, total = 0;
		String RN;

		howMany = Integer.parseInt(in.nextLine());
		for(int times = 1; times <= howMany; ++times) {
			RN = in.nextLine();

			int[] values = new int[RN.length()];
			values = change(RN);

			for(int i = 0; i < values.length; ++i) {
				temp1 = values[i];//get first #
				if(i + 1 < values.length) {
					temp2 = values[i + 1];//get 2nd #
					if(temp1 >= temp2)//Smaller # after
						num += temp1;
					else {//larger # after
						num += temp2 - temp1;
						++i;
					}
				}else {//end of values
					num += temp1;
					++i;
				}
			}

			System.out.println(RN + " = " + num);
			total += num;
			num = 0;
		}
		System.out.println("Total of all the numbers is: " + total);
		in.close();
	}

}
