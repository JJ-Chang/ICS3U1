import java.util.*;
import java.io.*;
public class StringExercises {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub

		Scanner in = new Scanner(System.in);
		Scanner inFile = new Scanner(new File ("input.txt"));
		String name, formatName;
		int split;

		//#6 Reverse names
		System.out.print("Enter a full name: ");
		name = in.nextLine();
		name = name.trim();

		split = name.lastIndexOf(" ");
		if (split >= 0)
			formatName = name.substring(split + 1) + ", " + name.substring(0, split);
		else
			formatName = name;
		System.out.println(formatName);
		System.out.println();

		//#9 subStringCount
		String str = "", subStr = "";
		int check, count = 0, checkPlace = 0;

		while(count < 1)
		{
			System.out.print("Enter the string: ");
			str = in.nextLine();
			if (!str.equals(""))
			{
				while(count < 1) {
					System.out.print("Enter the substring: ");
					subStr = in.nextLine();
					if(!subStr.equals(""))
						break;
					else
						System.out.println("INVALID! Enter again.");
				}
				break;
			}
			else
				System.out.println("INVALID! Enter again.");
		}

		if(str.equals(subStr))
			System.out.printf("%n'%s' is found 1 time in '%s'.", subStr, str);
		else
		{		
			for(int i = 0; i < str.length() - 1; i++) 
			{
				check = str.indexOf(subStr, checkPlace);
				if(check >= 0) {
					count++;
					checkPlace = check + subStr.length();
				}
			}
			System.out.printf("%n'%s' is found %d times in '%s'.", subStr, count, str);
		}
		check = 0;
		checkPlace = 0;
		System.out.println();

		//#10. Search and Replace
		String origStr, searchStr, replaceStr, newStr;
		origStr = inFile.nextLine();
		newStr = origStr;
		searchStr = inFile.nextLine();
		replaceStr = inFile.nextLine();

		for(int i = 0; i < origStr.length() - 1; i++) 
		{
			check = origStr.indexOf(searchStr);
			if(check == 0)
				newStr = replaceStr + origStr.substring(searchStr.length() - 1);
			else if(check > 0)
				newStr = newStr.substring(0, check) + replaceStr + newStr.substring(check + searchStr.length());
		}
		
		//fix the thing from file

		System.out.println("\n" + origStr);

		in.close();
		inFile.close();
	}

}
