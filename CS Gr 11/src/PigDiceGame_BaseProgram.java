import java.util.Scanner;
public class PigDiceGame_BaseProgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Jada Chang
		//March 2019
		//Program to play pig dice game against computer.
		//Goes until one player reaches score of 100.
		String name;
		Scanner in = new Scanner(System.in);
		int dice = 0; 
		int roundScore = 0, totalScorePlayer = 0;
		int turns, countTurns = 1, totalScoreComputer = 0;
		int playerWins = 0, computerWins = 0;
		char playAgain = '0';
		boolean check = true, playRound = true;
		String computerTurn, temp;

		System.out.print("Enter your name: ");
		name = in.nextLine();
		System.out.println(" ");

		while(playRound == true) {
			while(totalScorePlayer < 100  && totalScoreComputer < 100) {
				System.out.printf("%s's turn:", name);

				//roll player dice
				while(check == true) {
					dice = (int)(Math.random() * 6) + 1;
					System.out.printf("%n Dice roll = %d", dice);
					roundScore = roundScore + dice;

					if(dice == 1) {
						roundScore = 0;
						break;
					}
					//Player play again?
					while(check == true) {
						System.out.printf("%n Play again (Y/N)? ");
						temp  = in.nextLine();
						if (!temp.equals ("")) {
							playAgain = temp.charAt(0);
							if(playAgain == 'Y' || playAgain == 'y')
								check = false;
							else if(playAgain == 'N' || playAgain == 'n'){
								totalScorePlayer = totalScorePlayer + roundScore;					
								check = false;
							}
							else
								System.out.printf(" Invalid! Please choose again.");
						}
						else
							System.out.printf(" Invalid! Please choose again.");

					}

					check = true;
					if(playAgain == 'N' || playAgain == 'n')
						break;
				}

				//Reset score, show scores
				if(dice == 1)
					System.out.printf("%nRound over. %s's score is reset to 0.", name);
				else
					System.out.printf("Round over. %s's score for the round is %d.", name, roundScore);
				System.out.printf("%n%s's total score: %d%n", name, totalScorePlayer);

				roundScore = 0;
				dice = 0;
				if(totalScorePlayer >= 100)
					break;

				//Computer's turn
				System.out.println("\nPress ENTER for computer's turn.");
				while(check == true) {
					computerTurn = in.nextLine();
					if (computerTurn.equals(""))
						break;
				}

				System.out.print("Computer's turn:");
				turns = (int) (Math.random() * 6) + 3;

				while(countTurns <= turns) {
					dice = (int) (Math.random() * 6) + 1;
					System.out.printf("%n Dice roll = %d%n", dice);
					roundScore = roundScore + dice;

					countTurns = countTurns + 1;
					if(dice== 1) {
						roundScore = 0;
						break;
					}

					System.out.print(" Press ENTER to continue.");

					while(check == true) {
						computerTurn = in.nextLine();
						if (computerTurn.equals(""))
							break;
					}
				}

				//Reset computer score, show scores
				totalScoreComputer = totalScoreComputer + roundScore;

				if(dice == 1)
					System.out.print("Round over. Computer score is reset to 0.");
				else
					System.out.printf("Round over. Computer score for the round is %d.", roundScore);
				System.out.printf("%nComputer total score: %d%n%n", totalScoreComputer);

				roundScore = 0;
				dice = 0;
				turns = 0;
				countTurns = 1;
			}
			if (totalScorePlayer >= 100) {
				playerWins = playerWins + 1;
				System.out.printf("%nCongratulations! You won this round with a total score of %d.", totalScorePlayer);
				System.out.printf("%nYou have won %d rounds, and the computer has won %d rounds.", playerWins, computerWins);
			}
			else if(totalScoreComputer >= 100) {
				computerWins = computerWins + 1;
				System.out.printf("The computer won this round with a total score of %d.", totalScoreComputer);
				System.out.printf("%nYou have won %d rounds, and the computer has won %d rounds.", playerWins, computerWins);
			}

			totalScorePlayer = 0;
			totalScoreComputer = 0;

			//Play another round?
			while(check == true) {
				System.out.printf("%n Play another round (Y/N)? ");
				temp = in.nextLine();
				if (!temp.equals ("")) {
					playAgain = temp.charAt(0);
					if(playAgain == 'Y' || playAgain == 'y')
						check = false;
					else if(playAgain == 'N' || playAgain == 'n')
						break;
					else
						System.out.print(" Invalid! Please choose again.");
				}
				else
					System.out.printf(" Invalid! Please choose again.");
			}

			check = true;
			if(playAgain == 'N' || playAgain == 'n')
				playRound = false;
			System.out.println(" ");
		}

		System.out.println("\nThank you for playing!");
		System.out.printf("You won %d rounds, and the computer won %d rounds.", playerWins, computerWins);

		in.close();
	}

}
