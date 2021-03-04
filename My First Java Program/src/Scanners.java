import java.util.*;
public class Scanners {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		
		System.out.println("How old are you?");
		int age = Integer.parseInt(in.nextLine());
		
		System.out.println("What is your name?");
		String name = in.nextLine();
		
		System.out.print(name + ", you are " + age + " years old!");

		in.close();
	}
}
