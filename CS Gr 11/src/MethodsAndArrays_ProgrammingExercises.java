//Jada Chang
//April-May 2019
//Questions 1-4

import java.util.*;

public class MethodsAndArrays_ProgrammingExercises {
	static Scanner in = new Scanner(System.in);

	/*Press enter to continue
	 *returns true when enter pressed
	 */
	public static boolean enter() 
	{
		boolean r = true;
		String enter;

		System.out.print("\nPress ENTER to continue");
		do{
			enter = in.nextLine();
			if(enter.contentEquals(""))
				break;
		}while(r == true);
		System.out.println();

		return r;
	}

	/*Choose yes or no
	 *returns true if yes, false if no
	 */
	public static boolean yesNo() {
		boolean cont = false, r = true;
		String ins;

		do {
			System.out.print("continue? (y/n) ");
			ins = in.nextLine();
			if(ins.equalsIgnoreCase("y") || ins.equalsIgnoreCase("yes")) {
				cont = true;
				r = false;
			}else if(ins.equalsIgnoreCase("n") || ins.equalsIgnoreCase("no")) {
				cont = false;
				r = false;
			}else
				System.out.println("Invalid! Please try again.");
		}while(r == true);

		return cont;
	}

	/*Question 1: translates number to spoken form from right to left
	 * n1 is the number to translate
	 * num1 is the string of the spoken form
	 */
	public static String q1(int n1) {
		String num1 = "";
		int digit;
		String [] cases = {"zero ", "one ", "two ", "three ", "four ", "five ", "six ", "seven ", "eight ", "nine "};

		if(Integer.toString(n1).equals("0")) {
			num1 = "zero";
		}else{
			while(n1 > 0) {
				digit = n1 % 10;
				num1 += cases[digit];
				n1 = n1 / 10;
			}
		}
		return num1;
	}

	/*Question 2: modify #1 so numbers are in proper order
	 *n2 is the number to translate
	 *num2 is the string of the spoken form in correct order
	 */
	public static String q2(int n2) {
		String num2 = "", temp = "";
		int digit, i = 0;
		String [] cases = {"zero ", "one ", "two ", "three ", "four ", "five ", "six ", "seven ", "eight ", "nine "};

		while(n2 > 0) {
			digit = n2 % 10;
			temp += digit;
			n2 = n2 / 10;
		}

		if(temp.equals("") && n2 == 0) {
			num2 = "zero";
		}else {
			n2 = Integer.parseInt(temp);
			while(n2 > 0) {
				digit = n2 % 10;
				num2 += cases[digit];
				n2 = n2 / 10;
			}
			while(temp.charAt(i) == '0'){
				num2 += cases[0];
				++i;
			}
		}

		return num2;
	}

	/*Question 3: make a program to randomly construct sentences
	 *returns generated sentence
	 */
	public static String randomS() {
		String sentence = "";
		String nouns[] = {"tea", "car", "hat", "book", "owl", "lamp", "pencil", "band", "shade", "shelf"};
		String verbs[] = {"eats", "reads", "runs", "jumps", "cries", "sings", "dances", "types", "plays", "cooks"};
		String preps[] = {"on", "under", "in", "in front of", "behind", "beside", "over", "around", "with", "by"};
		int w1, w2, w3, w4;

		w1 = (int) (Math.random() * 10);
		w2 = (int) (Math.random() * 10);
		w3 = (int) (Math.random() * 10);
		w4 = (int) (Math.random() * 10);

		System.out.printf("The %s %s %s the %s.%n", nouns[w1], verbs[w2], preps[w3], nouns[w4]);

		return sentence;
	}
	
	/*Question 4: randomizer to find the cards
	 * returns a single random card
	 */
	public static String findC(){
		Random rand = new Random();
		int nums, suits;
		String num[] = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
		String suit[] = {"Diamonds", "Clubs", "Hearts", "Spades"};
		String card;
		
		nums = rand.nextInt(13);
		suits = rand.nextInt(4);
		card = num[nums] + " of " + suit[suits];
		
		return card;
	}
	
	
	/*Question 4: print out a random set of 5 different cards
	 *returns 5 different random cards
	 */
	public static void cards() {
		String c1, c2, c3, c4, c5;
		
		c1 = findC();
		do
			c2 = findC();
		while(c2 == c1);
		do
			c3 = findC();
		while(c3 == c1 || c3 == c2);
		do
			c4 = findC();
		while(c4 == c1 || c4 == c2 || c4 == c3);
		do
			c5 = findC();
		while(c5 == c1 || c5 == c2 || c5 == c3 || c5 == c4);
		
		String card[] = {c1, c2, c3, c4, c5};
		
		for(int i = 0; i < 5; ++i)
			System.out.println(card[i]);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int n1, n2;
		String num1, num2;
		boolean run = true;

		//#1
		System.out.print("Enter a number: ");
		n1 = in.nextInt();
		num1 = q1(n1);
		System.out.println(num1);

		run = enter();

		//#2
		System.out.print("Enter a number: ");
		n2 = in.nextInt();
		num2 = q2(n2);
		System.out.println(num2);

		run = enter();

		//#3
		do {
			randomS();
			run = yesNo();
		}while(run == true);
		System.out.println("END");

		run = enter();

		//#4
		cards();

		in.close();
	}
}
