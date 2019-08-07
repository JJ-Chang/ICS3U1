import java.util.Scanner;
public class PigDiceGame_Bonus {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Jada Chang
		//March 2019
		//Program to play pig dice game against computer.
		//Goes until one player reaches score of 100.
		String name;
		Scanner in = new Scanner(System.in);
		int[] dice = new int[2];
		int roundScore = 0, totalScorePlayer = 0, tempDice;
		int turns, countTurns = 1, totalScoreComputer = 0;
		int playerWins = 0, computerWins = 0, gameType = 0;
		int diceNums = 0;
		boolean check = true, playRound = true;
		String computerTurn, temp, playAgain = "x";

		System.out.print("Enter your name: ");
		name = in.nextLine();
		System.out.println(" ");

		while(playRound == true) {
			System.out.print("Would you like to play with one(1) dice or two(2) dice? ");

			while(check == true) {
				temp = in.nextLine();
				if (temp.contentEquals("1") || temp.contentEquals("2")) {
					diceNums = Integer.parseInt(temp);
					break;
				}
				else
					System.out.print("Invalid! Please choose again. ");
			}
			System.out.println();
			gameType = diceNums - 1;

			while(totalScorePlayer < 100  && totalScoreComputer < 100) {
				System.out.printf("%s's turn:", name);

				//roll player dice
				while(check == true) {
					for(int i = 0; i <= gameType; i += 1){
						tempDice = (int)(Math.random() * 6) + 1;
						dice[i] = tempDice;
						if(gameType == 0)
							System.out.printf("%n Dice roll = %d", dice[i]);
						else if(gameType == 1)
							System.out.printf("%n Dice roll %d = %d", (i + 1), dice[i]);
						roundScore = roundScore + dice[i];
					}
					if((dice[0] == 1) ^ (dice[1] == 1)) {
						roundScore = 0;
						break;
					}
					else if(dice[0] == 1 && dice[1] == 1) {
						roundScore = roundScore + 25;
						System.out.println();
					}
					else if(dice[0] == dice[1]) {
						roundScore = roundScore + ((2 * dice[0]) * 2); // 2 times sum of dice
						System.out.println();
					}
					else {
						//Player play again?
						while(check == true) {
							System.out.printf("%n Play again (Y/N)? ");
							temp  = in.nextLine();
							if (!temp.equals ("")) {
								playAgain = temp;
								if(playAgain.equalsIgnoreCase("Y") || playAgain.equalsIgnoreCase("yes"))
									check = false;
								else if(playAgain.equalsIgnoreCase("N") || playAgain.equalsIgnoreCase("no")){
									totalScorePlayer = totalScorePlayer + roundScore;					
									check = false;
								}
								else
									System.out.printf("%n Invalid! Please choose again.");
							}
							else
								System.out.printf("%n Invalid! Please choose again.");
						}
					}

					check = true;
					if(playAgain.equalsIgnoreCase("N") || playAgain.equalsIgnoreCase("no"))
						break;
				}

				//Reset score, show scores
				if(dice[0] == 1 ^ dice[1] == 1)
					System.out.printf("%nRound over. %s's score is reset to 0.", name);
				else
					System.out.printf("%nRound over. %s's score for the round is %d.", name, roundScore);
				System.out.printf("%n%s's total score: %d%n", name, totalScorePlayer);

				roundScore = 0;
				dice[0] = 0;
				dice[1] = 0;
				playAgain = "x";
				if(totalScorePlayer >= 100)
					break;

				//Computer's turn
				System.out.print("\nPress ENTER for computer's turn.");
				while(check == true) {
					computerTurn = in.nextLine();
					if (computerTurn.equals(""))
						break;
				}

				System.out.print("\nComputer's turn:");
				turns = (int) (Math.random() * 6) + 3;

				while(countTurns <= turns) {
					for(int i = 0; i <= gameType; i++) {
						dice[i] = (int) (Math.random() * 6) + 1;
						if(gameType == 0)
							System.out.printf("%n Dice roll = %d", dice[i]);
						else if(gameType == 1)
							System.out.printf("%n Dice roll %d = %d", (i + 1), dice[i]);

						roundScore = roundScore + dice[i];
					}
					countTurns = countTurns + 1;
					if((dice[0] == 1) ^ (dice[1] == 1)) {
						roundScore = 0;
						break;
					}
					else if(dice[0] == 1 && dice[1] == 1) {
						roundScore = roundScore + 25;
						if(countTurns == turns)
							turns += 1;
					}
					else if(dice[0] == dice[1]) {
						roundScore = roundScore + ((2 * dice[0]) * 2);
						if(countTurns == turns)
							turns++;
					}

					System.out.print("\n Press ENTER to continue.");

					while(check == true) {
						computerTurn = in.nextLine();
						if (computerTurn.equals(""))
							break;
					}
				}

				//Reset computer score, show scores
				totalScoreComputer = totalScoreComputer + roundScore;

				if(dice[0] == 1 ^ dice[1] == 1)
					System.out.printf("%nRound over. Computer score is reset to 0.");
				else
					System.out.printf("%nRound over. Computer score for the round is %d.", roundScore);
				System.out.printf("%nComputer total score: %d%n%n", totalScoreComputer);

				roundScore = 0;
				dice[0] = 0;
				dice[1] = 0;
				turns = 0;
				countTurns = 1;
			}
			if (totalScorePlayer >= 100) {
				playerWins = playerWins + 1;
				System.out.printf("%nCongratulations! You won this round with a total score of %d to %d.", totalScorePlayer, totalScoreComputer);
				System.out.printf("%nYou have won %d rounds, and the computer has won %d rounds.", playerWins, computerWins);
			}
			else if(totalScoreComputer >= 100) {
				computerWins = computerWins + 1;
				System.out.printf("The computer won this round with a total score of %d to %d.", totalScoreComputer, totalScorePlayer);
				System.out.printf("%nYou have won %d rounds, and the computer has won %d rounds.", playerWins, computerWins);
			}

			totalScorePlayer = 0;
			totalScoreComputer = 0;

			//Play another round?
			while(check == true) {
				System.out.printf("%n Play another round (Y/N)? ");
				temp = in.nextLine();
				if (!temp.equals ("")) {
					playAgain = temp;
					if(playAgain.equalsIgnoreCase("Y") || playAgain.equalsIgnoreCase("yes"))
						check = false;
					else if(playAgain.equalsIgnoreCase("N") || playAgain.equalsIgnoreCase("no"))
						break;
				}
				else
					System.out.printf(" Invalid! Please choose again.");
			}

			check = true;
			if(playAgain.equalsIgnoreCase("N") || playAgain.equalsIgnoreCase("no"))
				playRound = false;
			System.out.println(" ");
			playAgain = "x";
		}

		System.out.println("\nThank you for playing!");
		System.out.printf("You won %d rounds, and the computer won %d rounds.", playerWins, computerWins);

		in.close();
	}

}
