import java.util.*;
public class MyAwesomeRestaurant_v2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Jada Chang
		//My Awesome Restaurant assignment
		//Feb. 15, 2019
		//Calculates the bill and how to make change for customer order at the awesome restaurant.
		//Includes bonuses - if there is no change, grammatically correct change. 
		//Will continue prompting if amount ordered is invalid or if amount paid is not enough.

		Scanner in = new Scanner(System.in);
		double bingsu = 1.1; 
		double burgers = 1.1;
		double sRoll = 1.1;
		double gelato = 1.1;
		double bbt = 1.1;
		double priceBS, priceBurg, priceSR, priceG, priceBbt, total, totalTax;
		double paid, change, remaining;
		int twenty, ten, five, toonie, loonie, quarter, dime, nickel, penny;
		String twenties1 = "twenty-dollar bill";
		String twenties = "twenty-dollar bills";
		String fives1 = "five-dollar bill";
		String fives = "five-dollar bills";
		String loonies1 = "loonie";
		String loonies = "loonies";
		String dimes1 = "dime";
		String dimes = "dimes";
		boolean done = false;

		System.out.println("WELCOME TO MY AWESOME RESTAURANT\n");

		//Take order
		while(bingsu % 1 != 0 || bingsu < 0 ){
			System.out.print("How many bingsu would you like? ");
			bingsu = in.nextDouble();

			if(bingsu % 1 != 0)
				System.out.println("You must order a full serving. Please order again.");
			if(bingsu < 0)
				System.out.println("You may not order less than 0 servings. Please order again.");
		}

		while(burgers % 1 != 0 || burgers < 0) {
			System.out.print("How many burgers would you like? ");
			burgers = in.nextDouble();

			if(burgers % 1 != 0)
				System.out.println("You must order a full serving. Please order again.");
			if(burgers < 0)
				System.out.println("You may not order less than 0 servings. Please order again.");
		}

		while(sRoll % 1 != 0 || sRoll < 0) {
			System.out.print("How many spring rolls would you like? ");
			sRoll = in.nextDouble();

			if(sRoll % 1 != 0)
				System.out.println("You must order a full serving. Please order again.");
			if(sRoll < 0)
				System.out.println("You may not order less than 0 servings. Please order again.");
		}

		while(gelato % 1 != 0 || gelato < 0) {
			System.out.print("How many bowls of gelato would you like? ");
			gelato = in.nextDouble();

			if(gelato % 1 != 0)
				System.out.println("You must order a full serving. Please order again.");
			if(gelato < 0)
				System.out.println("You may not order less than 0 servings. Please order again.");
		}

		while(bbt % 1 != 0 || bbt < 0) {
			System.out.print("How many bubble teas would you like? ");
			bbt = in.nextDouble();

			if(bbt % 1 != 0)
				System.out.println("You must order a full serving. Please order again.");
			if(bbt < 0)
				System.out.println("You may not order less than 0 servings. Please order again.");
		}

		System.out.println("\nYOUR BILL\n");

		//calculate sub-totals
		priceBS = bingsu * 9.5;
		priceBurg = burgers * 7.5;
		priceSR = sRoll * 2;
		priceG = gelato * 4;
		priceBbt = bbt * 6;

		//display sub-totals
		//System.out.printf("%d bingsu @ $9.50 each = $%.2f%n", bingsu, priceBS);
		System.out.printf("%.0f bingsu @ $9.50 each = $%.2f%n", bingsu, priceBS);
		System.out.printf("%.0f burgers @ $7.50 each = $%.2f%n", burgers, priceBurg);
		System.out.printf("%.0f spring rolls @ $2.00 each = $%.2f%n", sRoll, priceSR);
		System.out.printf("%.0f gelato @ $4.00 each = $%.2f%n", gelato, priceG);
		System.out.printf("%.0f bubble teas @ $6.00 each = $%.2f%n%n", bbt, priceBbt);

		//display totals
		total = priceBS + priceBurg + priceSR + priceG + priceBbt;
		totalTax = total * 1.13;

		System.out.printf("Total = $%.2f%n", total);
		//totalTax = 4.42;
		totalTax = (Math.round(totalTax * 100.0)) / 100.0;
		System.out.printf("Total with tax = $%.2f%n%n", totalTax);

		//Payment & change
		while (done == false) {
			System.out.print("Please enter the amount paid: $");
			paid = in.nextDouble();
			//System.out.println("total tax = " + totalTax);
			change = paid - totalTax;
			change = (Math.round(change * 100.0)) / 100.0;
			//System.out.println("change = " + change);

			if (change == 0){
				System.out.println("Thank you for your purchase! There is no change.");
				done = true;
			}
			else if (change < 0) {
				System.out.println("That is less than the required amount to pay for this purchase. ");
				remaining = change * -1;
				remaining = Math.round(remaining * 100.0) / 100.0;
				System.out.printf("You still owe: $%.2f%n", remaining);
				
				totalTax = remaining;
			}
			else {
				System.out.print("The change will be: $");
				System.out.printf("%.2f%n%n",change);

				System.out.println("To make up this amount, you will need:");

				twenty = (int) change / 20;
				change = Math.round((change - (20 * twenty)) * 100.0) / 100.0;
				ten = (int) change / 10;
				change = Math.round ((change - (10 * ten)) * 100.0) / 100.0;
				five = (int) change / 5;
				change = Math.round ((change - (5 * five)) * 100.0) / 100.0;
				toonie = (int) change / 2;
				change = Math.round((change - (2 * toonie)) * 100.0) / 100.0;
				loonie = (int) change / 1;
				change = Math.round((change - loonie) * 100.0) / 100.0;
				quarter = (int) (change / 0.25);
				change = Math.round((change - (0.25 * quarter)) * 100.0) / 100.0;
				dime = (int) (change / 0.1);
				change = Math.round((change - (0.1 * dime)) * 100.0) / 100.0;
				nickel = (int) (change / 0.05);
				change = Math.round((change - (0.05 * nickel)) * 100.0) / 100.0;
				penny = (int) (change / 0.01);
				change = Math.round((change - (0.01 * penny)) * 100.0) / 100.0;
				//System.out.println("check change 0, change = " + change);

				//Make change with correct pluralization
				if (twenty == 1)
					System.out.printf("%4d %-21s", twenty, twenties1);
				else
					System.out.printf("%4d %-21s", twenty, twenties);
				if (ten == 1)
					System.out.printf("%4d ten-dollar bill%n", ten);
				else
					System.out.printf("%4d ten-dollar bills%n", ten);
				if (five == 1)
					System.out.printf("%4d %-21s", five, fives1);
				else
					System.out.printf("%4d %-21s", five, fives);
				if (toonie == 1)
					System.out.printf("%4d tooonie%n", toonie);
				else
					System.out.printf("%4d tooonies%n", toonie);
				if (loonie == 1)
					System.out.printf("%4d %-21s", loonie, loonies1);
				else
					System.out.printf("%4d %-21s", loonie, loonies);
				if (quarter == 1)
					System.out.printf("%4d quarter%n", quarter);
				else
					System.out.printf("%4d quarters%n", quarter);
				if (dime == 1)
					System.out.printf("%4d %-21s", dime, dimes1);
				else
					System.out.printf("%4d %-21s", dime, dimes);
				if (nickel == 1)
					System.out.printf("%4d nickel%n", nickel);
				else
					System.out.printf("%4d nickels%n", nickel);
				if (penny == 1)
					System.out.printf("%4d penny", penny);
				else
					System.out.printf("%4d pennies", penny);
				done = true;
			}
		}
		in.close();
	}
}
