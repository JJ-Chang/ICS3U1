import java.util.*;
public class Java11_pdf {
/*
	public static void Arithmetic(String[] args) {
		Scanner in = new Scanner(System.in);
		int a;
		int b;

		System.out.println("What is your first #?");
		a = in.nextInt();
		System.out.println("What is your 2nd #?");
		b = in.nextInt();	

		System.out.println("Your sum is " + (a + b) + ".");
		System.out.println("Your difference is " + (a - b) + ".");
		System.out.println("Your product is " + (a * b) + ".");
		System.out.println("Your mod is " + (a % b) + ".");

		in.close();
	}

	public static void CarefulMixIntWReals(String[] args) {
		// TODO Auto-generated method stub

		Scanner deg = new Scanner (System.in);
		double degF, degC;

		System.out.println("What is the temperature in Fahrenheit?");

		degF = deg.nextDouble();
		degC = 5.0/9*(degF-32);
		degC = Math.round(degC);

		System.out.print("The temperature in Celcius is " + degC + " degrees.");
		deg.close();
	}

	public static void ControlStructures_MorePractice(String[] args) {
		//Question 1
		int n = 1, up, count = 0;
		boolean check = true;
		int enter;
		Scanner in = new Scanner(System.in);

		System.out.print("Enter number: ");
		enter = in.nextInt();

		in.close();

		for (int i = 1; i <= enter; i++) {
			n = i;
			up = n/2;

			if(n == 1)
				check = false;
			else {
				for(int x = 2; x <= up; x++) {
					if(n % x == 0)
						check = false;
				}
			}
			if(check == true) {
				//System.out.println(n);
				count = count++;
			}
			check = true;
		}
		System.out.println("Number of prime numbers from 1 to " + enter + ": " + count);
	}	

	public static void Input(String[] args) {

		Scanner r = new Scanner(System.in);
		double rad, cir, ar;

		System.out.println("What is the radius?");
		rad = r.nextDouble();

		cir = 2 * Math.PI * rad;
		cir = Math.round(cir);
		ar = Math.pow((Math.PI * rad),2);
		ar = Math.round(ar);

		System.out.println("Thank you.");

		System.out.println("Your circumference is " + cir + ".");
		System.out.println("Your area is " + ar + ".");
		//System.out.println("Circumference: " + cir);
		//System.out.println("Area: " + ar);

		r.close();
	}

	public static void IntroducingRepetition(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);

		//2.2.1
		/*for(int i = 1; i <= 10; i++)
			System.out.println(i);
		 */

		//2.2.2
		/*for (int i = 10; i >= 1; --i)
			System.out.println(i);
		 */

		//2.2.3
		/*System.out.print("What increment? ");
		int inc = in.nextInt();

		for(int i = 1; i <= 30; i = i + inc)
			System.out.println(i);
		 */

		//2.2.6
		/*int num;
		int sum = 0;

		for(int i = 1; i <= 5; i++){
			System.out.println("Enter a number.");
			num = in.nextInt();
			sum = sum + num;
		}
		System.out.println("sum = " + sum);
		 */

		//2.2.7
		/*int num, avg;
		int sum = 0;

		for(int i = 1; i <= 5; i++){
			System.out.println("Enter a number.");
			num = in.nextInt();
			sum = sum + num;
		}
		avg = (int) Math.round(sum / 5.0);
		System.out.println("average: " + avg);
		 */

		//2.2.8 / 2.2.9
		/*int num;
		//int largest = -2147483648;
		int smallest = (int) Math.pow(2, 31);

		for(int i = 1; i <= 5; i++) {
			System.out.print("Enter a number: ");
			num = in.nextInt();
			//largest = Math.max(largest, num);
			smallest = Math.min(smallest, num);
		}
		//System.out.println("Largest number: " + largest);
		System.out.println("Smallest number: " + smallest);
		 */

		//2.2.10
		/*int n;
		int fctl = 1;

		System.out.print("Enter a number: ");
		n = in.nextInt();

		for(int i = 1; i <= n; i++)
			fctl = fctl * i;

		System.out.printf("%d! = %,d", n, fctl);
		 */

		//2.2.11
		/*int n;
		int tNum = 0;

		System.out.print("Enter a number: ");
		n = in.nextInt();
		for(int i = n; i >= 0; i--)
			tNum = tNum + i;
		System.out.printf("The %dth triangular number is %,d.", n, tNum);
		 */

		//2.2.12
		/*int n;
		int temp = 0;
		int temp2 = 1;
		int fbNum = 0;

		System.out.print("Enter a number: ");
		n = in.nextInt();

		for(int i = 1; i <= n; i++) {
			fbNum = temp + temp2;
			System.out.println(fbNum);

			temp = temp2;
			temp2 = fbNum;
		}
		 
		in.close();
	}

	public static void IntroducingSelection(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);

		//2.3.1
		/*int mark;

		System.out.print("Enter the mark: ");
		mark = in.nextInt();

		if(mark < 50)
			System.out.println("FAIL");
		else if(mark >= 50) 
			System.out.println("PASS");
		 */

		//2.3.2
		/*double num;
		num = in.nextDouble();

		if (num < 0)
			System.out.print("Negative numbers do not have a square root value.");
		else
			System.out.print(Math.sqrt (num));
		 */

		//2.3.3
		/*int side1, side2, side3, prm, halfPrm;
		double area;

		System.out.print("Length side 1: ");
		side1 = in.nextInt();
		System.out.print("Length side 2: ");
		side2 = in.nextInt();
		System.out.print("Length side 3 ");
		side3 = in.nextInt();

		prm = side1 + side2 + side3;
		halfPrm = prm / 2;
		if (side1 < halfPrm && side2 < halfPrm && side3 < halfPrm) {
			area = Math.sqrt(halfPrm * (halfPrm - side1) 
		 * (halfPrm - side2) * (halfPrm - side3));
			System.out.print("area = " + area);
		}
		else
			System.out.println("No triangle");
		 */

		//2.3.4
		/*double mark;
		int i = 1;

		while(i <= 5) {
			mark = in.nextDouble();
			if (mark < 0)
				System.out.println("invalid");
			else if (mark <= 49) {
				System.out.println("F");
				i += 1;
			}
			else if (mark <= 59) {
				System.out.println("D");
				i += 1;
			}
			else if (mark <= 69) {
				System.out.println("C");
				i += 1;
			}
			else if (mark <= 79) {
				System.out.println("B");
				i += 1;
			}
			else if (mark <= 89) {
				System.out.println("A");
				i += 1;
			}
			else if (mark <= 100) {
				System.out.println("A+");
				i += 1;
			}
		}
		 */

		//2.3.5
		/*double A, B, C;
		double root;

		System.out.println("input values of A, B, C");
		A = in.nextDouble();
		B = in.nextDouble();
		C = in.nextDouble();

		if ((Math.pow(B, 2) - 4 * A * C) == 0) {
			root = -1 * (B / (2 * A));
			System.out.println(root);
		}
		else if ((Math.pow(B, 2) - 4 * A * C) > 0) {
			root = (((-1 * B) + Math.sqrt(Math.pow(B, 2) - 4 * A * C)) / 2 * A);
			System.out.println(root);
			root = (((-1 * B) - Math.sqrt(Math.pow(B, 2) - 4 * A * C)) / 2 * A);
			System.out.println(root);
		}
		else
			System.out.println("no roots");
		 

		in.close();
	}

	public static void kmToMiles(String[] args) {
		// TODO Auto-generated method stub

		Scanner distance = new Scanner (System.in);
		double km, miles;

		System.out.println("What is the distance in km?");

		km = distance.nextDouble();
		miles = km / 1.609;
		miles = Math.round(miles);

		System.out.println("The distance is " + miles + " miles.");
		distance.close();
	}

	public static void Mod(String[] args) {
		// TODO Auto-generated method stub

		Scanner time = new Scanner (System.in);
		double hours1, days, hours2;

		System.out.println("How many hours?");
		hours1 = time.nextDouble();

		//System.out.println("it's working");

		days = hours1 / 24.0;
		days = Math.round(days);
		hours2 = hours1 % 24.0;



		System.out.println("Original hours = " + hours1);
		System.out.println("= ");
		//if (days > 0){
		System.out.print(days + " days, ");
		//}
		//else{
		//	System.out.print(0 + "days, ");
		//}
		System.out.print(hours2 + "hours");

		time.close();
	}

	public static void Part1(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("*****\n  *  \n  *  \n  *  \n**");
		System.out.println("***\n   *\n    * \n   * \n***");
		System.out.println(" \n\n\n");
	}
	*/
	public static void stringMethods(String[] args) {
		// TODO Auto-generated method stub

		//2.2.13
		String word, enter, temp = "", temp2 = "", spaces = "", backward = "";
		boolean run = true;
		int num = 0;
		Scanner in = new Scanner(System.in);

		System.out.print("Enter a word: ");
		word = in.nextLine();
		word = word.trim();
		//space = word.length() + 2;

		for(int i = word.length(); i >= 0; --i)
			System.out.println(word.substring(0, i));

		System.out.print("Press ENTER to continue");
		do{
			enter = in.nextLine();
			if(enter.contentEquals(""))
				break;
		}while(run == true);

		//2.2.14
		for(int i = 0; i < word.length(); ++i)
			System.out.println(word.substring(i));

		System.out.print("\nPress ENTER to continue");
		do{
			enter = in.nextLine();
			if(enter.contentEquals(""))
				break;
		}while(run == true);

		//2.2.15
		temp = word;
		System.out.println(temp);
		for(int i = 0; i < word.length(); ++i)
		{
			temp = temp.substring(0, i) + " " + temp.substring(i + 1);
			System.out.println(temp);
		}
		temp = "";

		System.out.print("Press ENTER to continue");
		do{
			enter = in.nextLine();
			if(enter.contentEquals(""))
				break;
		}while(run == true);

		//2.2.16
		for(int i = 0; i < word.length(); ++i)
			temp = temp + word.charAt(i) + "-";
		temp = temp.substring(0, temp.length() - 1);
		System.out.println(temp);
		temp = temp.substring(0, temp.length() - 1);
		//System.out.println(temp);
		for(int i = temp.length() -1; i >= 0; i -= 2)
		{
			//System.out.println(i);
			temp = temp.substring(0, i);
			spaces = " " + spaces;
			temp2 = spaces + temp;
			System.out.println(temp2);
		}
		temp = "";
		temp2 = "";
		spaces = "";

		System.out.print("\nPress ENTER to continue");
		do{
			enter = in.nextLine();
			if(enter.contentEquals(""))
				break;
		}while(run == true);

		//2.2.17
		temp = word;
		for(int a = temp.length(); a >= 0; --a)
		{
			temp = temp.substring(0, a);
			System.out.print(spaces);
			for(int i = temp.length() - 1; i >= 0; --i) 
				System.out.print(temp.charAt(i));
			for(int i = 0; i < temp.length(); ++i)
				System.out.print(temp.charAt(i));
			System.out.println();
			spaces = spaces + " ";
		}
		temp = "";
		spaces = "";

		System.out.print("Press ENTER to continue");
		do{
			enter = in.nextLine();
			if(enter.contentEquals(""))
				break;
		}while(run == true);

		//2.2.18
		do{
			System.out.print("Enter a number between 1 and 20: ");
			temp = in.nextLine();
			if(!temp.equals(""))
			{
				num = Integer.parseInt(temp);
				if(num >= 1 && num <= 20)
					break;
			}
			else
				System.out.println("Invalid! Please try again.");
		}while(run == true);

		for(int i = 1; i <= num; ++i)
		{
			System.out.println(spaces + "*");
			spaces = spaces + " ";
		}
		//spaces = "";

		System.out.print("\nPress ENTER to continue");
		do{
			enter = in.nextLine();
			if(enter.contentEquals(""))
				break;
		}while(run == true);

		//2.2.19
		for(int a = num; a > 0; --a)
		{
			for(int i = 1; i < a; ++i)
				System.out.print(" ");
			System.out.println("*");
		}

		System.out.print("\nPress ENTER to continue");
		do{
			enter = in.nextLine();
			if(enter.contentEquals(""))
				break;
		}while(run == true);

		//2.4.13
		System.out.println("(Type [stop] to end program.)");
		while(run == true)
		{
			System.out.print("Enter a word: ");
			word = in.nextLine();
			if(word.equals("stop"))
				break;

			for(int i = word.length() - 1; i >= 0; --i)
				backward = backward + word.charAt(i);
			if(word.equals(backward))
				System.out.println(word + " is a palindrome.");
			else
				System.out.println(word + " is not a palindrome.");
			backward = "";
		}

		System.out.print("\nEnd of program.");

		in.close();
	}

	public static void Time(String[] args) {
		// TODO Auto-generated method stub

		Scanner in = new Scanner(System.in);
		double minutes1, minutes2, days, days2, hours1, hours2;

		System.out.println("How many minutes?");
		minutes1 = in.nextDouble();

		if (minutes1 == 60) {
			hours1 = 1;
			days = 0;
			minutes2 = 0;

			System.out.println(minutes1 + " minutes = " + days + " days, " + hours1 + " hours, " + minutes2 + " minutes");
		}
		else if (minutes1 >= 60) {
			hours1 = minutes1 / 60.0;

			if (hours1 >= 24) {
				days = hours1 / 24.0;
			}
			else {
				days = 0;
			}

			if (days % 1 == 0) {
				hours2 = 0;
				minutes2 = 0;

				System.out.println(minutes1 + " minutes = " + days + " days, " + hours2 + " hours, " + minutes2 + " minutes");
			}
			else if(days % 1 != 0) {
				hours2 = hours1 % 24.0;

				if(hours1 % 1 != 0){
					Math.round(days);
					minutes2 = minutes1 - ((days * 24.0) * 60.0);
				}
				else {
					minutes2 = 0;
				}

				System.out.println(minutes2);

				days = Math.round(days);
				hours2 = Math.round(hours2);

				System.out.println(minutes1 + " minutes = " + days + " days, " + hours2 + " hours, " + minutes2 + " minutes");
			}
		}
		else {
			minutes2 = minutes1;
			hours2 = 0;
			days2 = 0;

			System.out.println(minutes1 + " minutes = " + days2 + " days, " + hours2 + " hours, " + minutes2 + " minutes");
		}



		//days = Math.round(days);
		//hours1 = Math.round(hours1);
		//System.out.println(minutes1 + " minutes = " + days + " days");
		//System.out.println(minutes1 + " minutes = " + hours1 + " hours");

		in.close();
	}

	public static void Time2(String[] args) {
		// TODO Auto-generated method stub

		Scanner in = new Scanner(System.in);
		double minutes1, hours1, days1;
		int minutes2, hours2, days2;

		System.out.println("How many minutes?");
		minutes1 = in.nextDouble();

		if (minutes1 == 60) {
			System.out.println(minutes1 + " minutes = 0 days, 1 hour, 0 minutes.");
		}
		else if (minutes1 > 60) {
			hours1 = minutes1 / 60;

			if (hours1 < 24) {
				minutes2 = (int) minutes1 % 60;
				hours2 = (int) Math.round(hours1);

				System.out.print(minutes1 + " minutes = 0 days, ");
				System.out.print (hours2 + " hours, ");
				System.out.print(minutes2 + " minutes.");
			}
			else if (hours1 > 24) {
				days1 = hours1 / 24;

				if (hours1 % 1 == 0) {
					if (days1 % 1 != 0) {
						hours2 = (int) hours1 % 24;
						days2 = (int) Math.round(days1);

						System.out.print(minutes1 + "minutes = ");
						System.out.print(days2 + " days, ");
						System.out.print(hours2 + " hours, ");
						System.out.print("0 minutes");

					}
					else if (days1 % 1 == 0) {
						System.out.print(minutes1 + " minutes = ");
						System.out.print(days1 + " days, 0 hours, 0 minutes");
					}
				}
				else if (hours1 % 1 != 0) {
					minutes2 = (int) hours1 % 60;

					if (days1 % 1 != 0) {
						hours2 = (int) hours1 % 24;
						days2 = (int) Math.round(days1);

						System.out.print(minutes1 + "minutes = ");
						System.out.print(days2 + " days, ");
						System.out.print(hours2 + " hours, ");
						System.out.print(minutes2 + "minutes");
					}
					else if (days1 % 1 == 0) {
						hours2 = (int) hours1 % 24;

						System.out.print(minutes1 + " minutes = ");
						System.out.print(days1 + " days, ");
						System.out.print(hours2 + " hours, ");
						System.out.print(minutes2 + " minutes");
					}
				}
			}
			else if (hours1 == 24) {
				System.out.println(minutes1 + " minutes = 1 day, 0 hours, 0 minutes.");
			}
		}
		else {
			System.out.println(minutes1 + " minutes = 0 days, 0 hours, " + minutes1 + " minutes.");
		}

		in.close();
	}
}

