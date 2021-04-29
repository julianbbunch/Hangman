import java.util.Scanner;
import Hangman.Hangman;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Hangman game = new Hangman();
		Character c;
		
		if (2 <= args.length && null != args[0] && null != args[1]) {
			game.newGame(args[0], Integer.parseInt(args[1]));
		}
		
		while (!game.isLose() && !game.isWin()) {
			System.out.flush();
			System.out.print("***** Hangman *****\n");
			game.print();
			
			System.out.print("\n\nGuess a letter: ");
			c = in.next().charAt(0);
			game.move(c);
		}
		
		if (game.isWin()) {
			System.out.print("You win!\n");
		}
		else if (game.isLose()) {
			System.out.print("You lose!\n");
		}
		
		in.close();
	}
}
