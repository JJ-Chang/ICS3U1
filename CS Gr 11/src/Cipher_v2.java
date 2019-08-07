//Jada Chang
//April 2019
//Encrypt and decrypt hidden messages with a cipher.
//Encrypted file will have a number at the beginning and end of each line to keep track of the extra spaces
//If original string has capital letters, encrypted file will have a second line under the encryption line
//tracking the index positions of the capital letters
import java.util.*;
import java.io.*;

public class Cipher_v2 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		String mode = "", readFrom, printTo, temp = "";
		boolean check = true;
		Scanner get = new Scanner(System.in);
		String str, caps = "-", newStr = "";
		char c;
		int searchFrom = 0, beforeSpaces = 0, afterSpaces = 0, length, middle, capIt, search2 = 0;
		String subs1 = "AEIJOPRSTVX ", subs2 = "@=!?*#&$+^%_";
		String caps1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		//ENCRYPT OR DECRYPT MODE//
		do{
			System.out.println("Would you like to encrypt(1) or decrypt(2)?");
			mode = get.nextLine();
			if(!mode.equals("")) {
				if(mode.equals("1") || mode.equals("2"))
					break;
				else
					System.out.println("INVALID! Please enter again.");
			}
			else
				System.out.println("INVALID! Please enter again.");
		}while(check == true);

		if(mode.equals("1"))
			System.out.println("ENCRYPTING");
		else
			System.out.println("DECRYPTING");

		//GET FILE NAMES//
		do {
			System.out.print("Enter the file name to read from: ");
			readFrom = get.nextLine();
			if(!readFrom.contentEquals("")) {
				do {
					System.out.print("Is <" + readFrom + "> the correct name? (Y/N) ");
					temp = get.nextLine();
					if(temp.equalsIgnoreCase("y") || temp.equalsIgnoreCase("yes"))
						break;
					else if(temp.equalsIgnoreCase("n") || temp.equalsIgnoreCase("no")) {
						System.out.println("Please enter again.");
						break;
					}
					else
						System.out.println("INVALID! Please enter again.");
				}while(check == true);
			}

			if(temp.equalsIgnoreCase("y") || temp.equalsIgnoreCase("yes"))
				break;
		}while(check == true);
		System.out.println();

		do {
			System.out.print("Enter the file name to print to: ");
			printTo = get.nextLine();
			if(!printTo.equals("")) {
				System.out.print("Is <" + printTo + "> the correct name? (Y/N) ");
				temp = get.nextLine();
				if(temp.equalsIgnoreCase("y") || temp.equalsIgnoreCase("yes"))
					break;
				else if(!temp.equalsIgnoreCase("n") && !temp.equalsIgnoreCase("no"))
					System.out.println("INVLAID! Please enter again.");
			}
			else
				System.out.println("INVALID! Please enter again.");
		}while(check == true);
		PrintWriter out = new PrintWriter(new FileWriter(printTo, true));

		//READ FROM FILES//
		Scanner inFile = new Scanner(new File (readFrom));
		while(inFile.hasNextLine()) {
			if(caps.charAt(0) != '-')
				str = caps;
			else
				str = inFile.nextLine();
			//System.out.println(str);//*****

			System.out.println();
			if(str.equals(""))
				continue;

			//ENCRYPTION//
			if(mode.equals("1")){
				//STEP 1: SPACES//
				searchFrom = 0;
				c = str.charAt(searchFrom);
				//System.out.println(c);//*****
				while(c == ' ') {
					beforeSpaces++;
					searchFrom++;
					c = str.charAt(searchFrom);
				}

				searchFrom = str.length() - 1;
				c = str.charAt(searchFrom);
				while(c == ' ') {
					afterSpaces++;
					searchFrom--;
					c = str.charAt(searchFrom);
				}
				searchFrom = 0;

				newStr = str.trim();

				//STEP 2: UPPERCASE//
				check = false;
				for(int i = 0; i < newStr.length(); ++i) {
					if(caps1.indexOf(newStr.charAt(i)) > -1)
						check = true;
				}
				if(check = true) {
					for(int i = 0; i < newStr.length(); ++i) {
						searchFrom = caps1.indexOf(newStr.charAt(i));
						if(searchFrom > -1)
							caps = caps + " " + searchFrom;
					}
				}
				
				searchFrom = 0;

				newStr = newStr.toUpperCase();
				check = true;

				//STEP 3: SUBSTITUTIONS//
				for(int i = 0; i < newStr.length(); ++i) {
					searchFrom = subs1.indexOf(newStr.charAt(i));
					if(searchFrom > -1)
						newStr = newStr.substring(0, i) + subs2.charAt(searchFrom) + newStr.substring(i + 1);
				}
				searchFrom = 0;

				//STEP 4: MOVE FIRST HALF//
				length = newStr.length();
				if(length % 2 == 0)
					newStr = newStr.substring(length / 2) + newStr.substring(0, length / 2);
				else
					newStr = newStr.substring((length + 1) / 2) + newStr.substring(0, (length + 1) / 2);

				//STEP 5: SWAP 1ST AND LAST CHARS//
				newStr = newStr.substring(length - 2, length) + newStr.substring(2, length - 2) + newStr.substring(0, 2); 

				//STEP 6: SWAP MIDDLE CHARS//
				if(length % 2 == 0)
					middle = (newStr.length() / 2) - 1;
				else
					middle = (newStr.length() -1) / 2;

				newStr = newStr.substring(0, middle - 2) + newStr.substring(middle, middle + 2) + newStr.substring(middle - 2, middle) + newStr.substring(middle + 2);

				//STEP 7: SWAP EVERY 2 LETTERS//
				if(length % 2 == 0) {
					for(int i = 0; i < length - 2; i += 2) 
						newStr = newStr.substring(0, i) + newStr.charAt(i + 1) + newStr.charAt(i) + newStr.substring(i + 2);
					newStr = newStr.substring(0, length - 2) + newStr.charAt(length - 1) + newStr.charAt(length - 2);
				}else {
					for(int i = 0; i < length - 3; i += 2) 
						newStr = newStr.substring(0, i) + newStr.charAt(i + 1) + newStr.charAt(i) + newStr.substring(i + 2);
					newStr = newStr.substring(0, length - 3) + newStr.charAt(length - 2) + newStr.charAt(length - 3) + newStr.charAt(length - 1);
				}

				//PRINT RESULTS//
				newStr = beforeSpaces + " " + newStr + " " + afterSpaces;
				//System.out.println(beforeSpaces);//*****
				//System.out.println(afterSpaces);//*****
				//System.out.println(newStr);//*****
				out.println(newStr);
				//System.out.println(newStr);//*****
				if(!caps.contentEquals("-"))
					out.println(caps);

				beforeSpaces = 0;
				afterSpaces = 0;
			}
			//DECRYPTION//
			if(mode.equals("2")) {
				newStr = str;
				//STEP 1: FIND SPACES//
				searchFrom = str.indexOf(" ");
				if(searchFrom > -1) {
					beforeSpaces = Integer.parseInt(str.substring(0, searchFrom));
					newStr = newStr.substring(searchFrom + 1);
				}
				searchFrom = newStr.lastIndexOf(" ");
				if(searchFrom >= 0) {
					afterSpaces = Integer.parseInt(newStr.substring(searchFrom + 1));
					newStr = newStr.substring(0, searchFrom);
				}
				//STEP 2: SWAP EVERY 2 CHARS//
				length = newStr.length();
				if(length % 2 == 0) {
					newStr = newStr.substring(0, length - 2) + newStr.charAt(length - 1) + newStr.charAt(length - 2);
					for(int i = 0; i < length - 2; i += 2) 
						newStr = newStr.substring(0, i) + newStr.charAt(i + 1) + newStr.charAt(i) + newStr.substring(i + 2);
				}else {
					newStr = newStr.substring(0, length - 3) + newStr.charAt(length - 2) + newStr.charAt(length - 3) + newStr.charAt(length - 1);
					for(int i = 0; i < length - 3; i += 2) 
						newStr = newStr.substring(0, i) + newStr.charAt(i + 1) + newStr.charAt(i) + newStr.substring(i + 2);
				}

				//STEP 3: SWAP MIDDLE CHARS//
				if(length % 2 == 0)
					middle = (newStr.length() / 2) - 1;
				else
					middle = (newStr.length() -1) / 2;

				newStr = newStr.substring(0, middle - 2) + newStr.substring(middle, middle + 2) + newStr.substring(middle - 2, middle) + newStr.substring(middle + 2);


				//STEP 4: SWAP 1ST AND LAST CHARS//
				newStr = newStr.substring(newStr.length() - 2, newStr.length()) + newStr.substring(2, newStr.length() - 2) + newStr.substring(0, 2); 

				//STEP 5: MOVE FIRST HALF//
				length = newStr.length();
				if (length % 2 == 0)
					newStr = newStr.substring(length / 2) + newStr.substring(0, length / 2);
				else
					newStr = newStr.substring((length - 1) / 2) + newStr.substring(0, (length - 1) / 2);

				//STEP 6: SUBSTITUTIONS//
				for(int i = 0; i < newStr.length(); ++i) {
					searchFrom = subs2.indexOf(newStr.charAt(i));
					if(searchFrom > -1)
						newStr = newStr.substring(0, i) + subs1.charAt(searchFrom) + newStr.substring(i + 1);
				}
				searchFrom = 0;

				//STEP 7: LOWERCASE//
				newStr = newStr.toLowerCase();
				if(inFile.hasNextLine()) {
					caps = inFile.nextLine();
					if(!caps.contentEquals("") ) {
						if(caps.charAt(0) == '-') {
							searchFrom = caps.indexOf(' ', 2);
							capIt = Integer.parseInt(caps.substring(2, searchFrom));
							newStr = Character.toUpperCase(newStr.charAt(capIt)) + newStr.substring(capIt + 1);
							while(searchFrom > 0 || search2 > 0) {
								search2 = caps.indexOf(' ', searchFrom + 1);
								if(searchFrom < 0 || search2 < 0)
									break;
								capIt = Integer.parseInt(caps.substring(searchFrom + 1, search2));
								//System.out.println(capIt);//*****
								newStr = newStr.substring(0, capIt) + Character.toUpperCase(newStr.charAt(capIt)) + newStr.substring(capIt + 1);
								searchFrom = search2;
							}
							//System.out.println(caps);//*****
							searchFrom = caps.lastIndexOf(' ');
							capIt = Integer.parseInt(caps.substring(searchFrom + 1));
							newStr = newStr.substring(0, capIt) + Character.toUpperCase(newStr.charAt(capIt)) + newStr.substring(capIt + 1);
						}
					}
				}

				//STEP 8: ADD SPACES//
				for(int i = 1; i <= beforeSpaces; ++i)
					newStr = " " + newStr;
				for(int i = 1; i <= afterSpaces; ++i)
					newStr = newStr + " ";

				//PRINT RESULTS//
				out.println(newStr);
				//System.out.println(newStr);
				//if(!caps.contentEquals("- "))
				//System.out.println(caps);
			}
			searchFrom = 0;
		}

		System.out.println("END. Check files for output.");

		inFile.close();
		get.close();
		out.close();
	}
}