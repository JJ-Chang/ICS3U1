import java.util.*;

public class FunWithArrays
{
	// Returns an array (array) of doubles of a certain size (howMany), given a certain range (min, max).
	// Each double is rounded to 1 decimal place.
	static double[] generateArray(int howMany, int min, int max) {
		double[] array = new double[howMany];
		double num;
		Random rand = new Random();

		for(int i = 0; i < howMany; ++i) {
			num = (int) (Math.random()*(max - min + 1) + min); //get first part of number
			if(num == max)//1 decimal place
				num = num + .0;
			else
				num = num + (rand.nextInt(10) / 10.0);
			//if(rand.nextBoolean() == true) //positive or negative
			//num *= -1;
			array[i] = num;
		}
		return array;
	}

	//Displays the given array(array) in an organized table, with 8 numbers in each row.
	static void displayArray(double[] array) {
		int count = 0;
		for(int i = 0; i < array.length; ++i) {
			System.out.printf("%8.1f",array[i]);
			count++;
			if(count == 8) {
				System.out.println();
				count = 0;
			}
		}
		System.out.println();
	}

	//Returns the average of all the numbers in the given array(array)
	static double averageOfArray(double[] array) {
		double sum = 0, avg;

		for(int i = 0; i < array.length; ++i) 
			sum += array[i];
		avg = sum / array.length;

		return avg;
	}

	//Returns the index of the smallest value in the given array (array)
	static int indexOfSmallest(double[] array) {
		int index = 0;
		double smallest;

		smallest = array[0];
		for(int i = 1; i < array.length; ++i)
			if(array[i] < smallest) {
				smallest = array[i];
				index = i;
			}
		return index;
	}

	//Sorts given array (array) from smallest to largest
	static void sortArray(double[] array) {
		double temp;

		for(int i = array.length; i > -1; --i)
			for(int a = 1; a < i; ++a)
				if(array[a] < array[a - 1]) {
					temp = array[a - 1];
					array[a - 1] = array[a];
					array[a] = temp;
				}				
	}

	//Combines two given arrays (array1, array2) 
	//and combines & returns them into one big sorted array (newArray)
	static double[] mergeArrays(double[] array1, double[] array2) {
		double[] newArray = new double[array1.length + array2.length];
		int count1 = 0, count2 = 0, countNew = 0;

		while(count1 < array1.length && count2 < array2.length) {
			if(array1[count1] < array2[count2]) {
				newArray[countNew] = array1[count1];
				count1++;
			} else {
				newArray[countNew] = array2[count2];
				count2++;
			}
			countNew++;
		}
		
		if(array1.length > array2.length)
			for(int i = count1; i < array1.length; ++i) {
				newArray[countNew] = array1[i];
				countNew++;
			}
		else if(array2.length > array1.length) {
			for(int i = count2; i < array2.length; ++i) {
				newArray[countNew] = array2[i];
				countNew++;
			}
		}
		
		return newArray;
	}

	public static void main (String[] args)
	{
		System.out.println ("Using Methods with Arrays\n");


		// Generate an array of 30 doubles between 1 and 100 - including 1 and 100
		double[] firstList = generateArray (30, 1, 100);


		// Display array, with 10 numbers in each row
		System.out.println ("Here are the numbers:");
		displayArray (firstList);


		// Find and display the average of the numbers in the array
		System.out.print ("\nThe average of the numbers in the above array is: ");
		System.out.printf ("%.1f%n", averageOfArray (firstList));


		// Find and display the index and value of the smallest number
		int index = indexOfSmallest (firstList);
		System.out.print ("\nThe index of the smallest number is: ");
		System.out.println (index);
		System.out.print ("The smallest number in the above list is: ");
		System.out.printf ("%.2f%n", firstList [index]);


		// Sort and then display the array, with 8 numbers in each row
		sortArray (firstList);
		System.out.println ("\nHere is the sorted array: ");
		displayArray (firstList);


		// Generate a second array of 25 elements between -100 and 100, inclusive,
		// and sort this second list
		double[] secondList = generateArray (25, -100, 100);
		sortArray (secondList);


		// Merge the two sorted arrays into a single sorted array
		double[] mergedList = mergeArrays (firstList, secondList);

		
		// Display the merged array, with 8 numbers in each row
		System.out.println ("\nHere is the merged array: ");
		displayArray (mergedList);


		System.out.println ("\nThe Using Methods with Arrays Program is Complete");
	}
}