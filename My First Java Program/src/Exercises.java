import java.util.*;
public class Exercises {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner in = new Scanner(System.in);
		
		//Question #14
		/*int length, width;
		
		System.out.println("What is the length of the rectangle?");
		length = in.nextInt();
		
		System.out.println("What is the width of the rectangle?");
		width = in.nextInt();
		
		System.out.println("The area of the rectangle is " + (length * width) + ".");
		*/
		
		//Question #15
		/*double height, radius, sArea, volume;
		final double PI = 3.14159265;
		
		System.out.println("What is the height of the cylinder?");
		height = in.nextDouble();
		
		System.out.println("What is the radius of the cylinder?");
		radius = in.nextDouble();
		
		sArea = (2 * PI * radius * height) + (2 * PI * Math.pow(radius, 2));
		volume = PI * Math.pow(radius, 2) * height;
		
		sArea = Math.round(sArea * 10) / 10.0;
		volume = Math.round(volume * 10) / 10.0;
		
		System.out.printf("The surface area of the cylinder is %,.1f.%n", sArea);
		System.out.printf("The volume of the cylinder is %,.1f.", volume);
		*/
	
		//Question #17
		/*int pizza;
		double calories, hours, minutes;
		System.out.println("How many slices of pizza did you eat?");
		
		pizza = in.nextInt();
		calories = pizza * 355;
		hours = calories / 550;
		
		minutes = hours * 60;
		minutes = minutes % 60;
		//minutes = Math.round(minutes);
		//hours = Math.round(hours * 100) / 100.0;
		//hours = Math.round(hours);
		
		System.out.printf("You need to cycle for %.0f hours and %.0f minutes to burn off that pizza.", hours, minutes);
		*/
		
		in.close();
	}
}
