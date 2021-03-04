import java.util.*;
public class Test1Practice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int children, adults, seniors, tickets;
		double ticketsCost, ticketsTax, average, child, adult, senior;
		Scanner in = new Scanner(System.in);
		
		System.out.print("How many children? ");
		children = in.nextInt();
		System.out.print("How many adults? ");
		adults = in.nextInt();
		System.out.print("How many seniors? ");
		seniors = in.nextInt();
		
		tickets = children + adults + seniors;
		System.out.println("\nTickets sold: " + tickets);
		
		child = children * 6.5;
		adult = adults * 8.5;
		senior = seniors * 5.0;
		ticketsCost = child + adult + senior;
		System.out.printf("%nSubtotal: $%.2f", ticketsCost);
		ticketsTax = ticketsCost * 1.13;
		System.out.printf("%nTotal: $%.2f", ticketsTax);
		
		average = ticketsTax / tickets;
		System.out.printf("%n%nAverage price per ticket: $%.2f", average);
		
		in.close();
	}

}
