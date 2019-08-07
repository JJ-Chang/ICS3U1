//Jada Chang
//Methods Exercises
//April 2019
//Questions #8, 9, 13
import java.util.Scanner;

import org.omg.CORBA.DynAnyPackage.Invalid;

public class MethodsExercises {

	/*Press enter to continue
	 *run = true, used to run the loop
	 *returns true when enter pressed
	 */
	public static boolean enter(boolean run) 
	{
		boolean r = true;
		String enter;
		Scanner in = new Scanner(System.in);

		System.out.print("\nPress ENTER to continue");
		do{
			enter = in.nextLine();
			if(enter.contentEquals(""))
				break;
		}while(r == true);
		System.out.println();

		return r;
	}

	/*8: find sum of numbers between two numbers inclusive
	 *n1 is the smaller #, n2 is the larger #
	 *returns sum 
	 */
	public static int sumOfNumbersBetween(int n1, int n2) 
	{
		int sum = 0;
		for(int i = n1; i <= n2; ++i) 
			sum += i;
		return sum;
	}

	/*#9: find length of line between 2 points
	 * (x1, y1) = coordinates of line start point
	 * (x2, y2) = coordinates of line end point
	 * returns distance between the two coordinate points
	 */
	public static double distanceBetween(int x1, int y1, int x2, int y2)
	{
		double distance = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
		return distance;
	}

	/*#13: find factorial of a positive integer
	 * n = the number to find factorial of
	 * returns n!
	 */
	public static int factorial(int n)
	{
		int f = n;
		for(int i = n - 1; i > 0; --i)
			f = f * i;

		return f;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		boolean goNext = true;
		int n1 = 0, n2 = 0;
		int x1, x2, y1, y2;
		int n, f = 0;

		//#8
		while(goNext == true) {
			System.out.print("Enter smaller integer: ");
			n1 = in.nextInt();
			System.out.print("Enter larger integer: ");
			n2 = in.nextInt();
			if(n1 > n2)
				System.out.println("Invalid! Please enter again.");
			else
				break;
		}
		int sum = sumOfNumbersBetween(n1, n2);
		System.out.printf("The sum of all the numbers from %d to %d is %d.%n", n1, n2, sum);

		goNext = enter(goNext);

		//#9
		System.out.print("Enter first x value: ");
		x1 = in.nextInt();
		System.out.print("Enter first y value: ");
		y1 = in.nextInt();
		System.out.print("Enter second x value: ");
		x2 = in.nextInt();
		System.out.print("Enter second y value: ");
		y2 = in.nextInt();
		double length = distanceBetween(x1, y1, x2, y2);
		System.out.printf("The distance between points (%d, %d) and (%d, %d) is %.2f.%n", x1, y1, x2, y2, length);

		goNext = enter(goNext);

		//#13
		do {
			System.out.print("Enter a positive integer: ");
			n = in.nextInt();
			if(n == 0)
				f = 1;
			else if(n < 0)
				System.out.println("Invalid! Please enter a different number.");
			else
				f = factorial(n);
		}while(n < 0);
		System.out.printf("%d! = %,d.%n", n, f);

		System.out.println();
		System.out.println("Program finished.");
		in.close();
	}

}
