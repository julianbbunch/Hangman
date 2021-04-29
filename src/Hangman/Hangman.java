package Hangman;

import java.util.*;

public class Hangman {
	public Hangman() {
		mText = new String(DEFAULT_TEXT);
		mGuesses = new HashMap<Character, Boolean>();
		mMistakesRemaining = DEFAULT_FAILS;
	}

	public Hangman(String text, int fails) {
		mText = new String(text);
		mGuesses = new HashMap<Character, Boolean>();
		mMistakesRemaining = fails;
	}
	
	// Creates a new game from scratch with a new text
	public void newGame(String text, int fails) {
		mText = new String(text);
		mGuesses = new HashMap<Character, Boolean>();
		mMistakesRemaining = fails;
	}
	
	// Guesses a letter in the text, returns true if contained in text
	public Boolean move(Character c) {
		Boolean bSuccess = false;
		
		if ((-1 != mText.indexOf(Character.toLowerCase(c)) 
				|| -1 != mText.indexOf(Character.toUpperCase(c)))
				&& isLetter(c)) {
			mGuesses.put(Character.toLowerCase(c), true);
			mGuesses.put(Character.toUpperCase(c), true);
			bSuccess = true;
		}
		else if (isLetter(c)) {
			mGuesses.put(c, false);
			mMistakesRemaining--;
		}
		
		return bSuccess;
	}
	
	// Prints the state of the game to standard output
	public void print () {
		System.out.print("Mistakes remaining: " + mMistakesRemaining + "\n\n");
		
		for (Character c: mText.toCharArray()) {
			if (mGuesses.containsKey(c) && mGuesses.get(c)) {
				System.out.print(c + " ");
			} 
			else  if (isLetter(c)) {
				System.out.print(UNKNOWN + " ");
			}
			else {
				System.out.print(c + " ");				
			}
		}
	}
	
	// Returns the number mistakes the user has left
	public int getRemainingMistakes() {
		return mMistakesRemaining;
	}
	
	// Returns true if there are mistakes remaining and all letters have been guessed
	public Boolean isWin() {
		Boolean bWin = true;
		
		for (Character c: mText.toCharArray()) {
			if (isLetter(c)	&& !mGuesses.containsKey(c)) {
				bWin = false;
			}
		}
		
		if (0 >= mMistakesRemaining) {
			bWin = false;
		}
		
		return bWin;
	}
	
	// Returns true if no more mistakes are remaining
	public Boolean isLose() {
		Boolean bLose = true;
		
		if (0 < mMistakesRemaining) {
			bLose = false;
		}
		
		return bLose;
	}
	
	private static Boolean isLetter(Character c) {
		Boolean bIsLetter = false;
		if (Character.getType(c) == Character.UPPERCASE_LETTER 
					|| Character.getType(c) == Character.LOWERCASE_LETTER) {
			bIsLetter = true;
		}
		
		return bIsLetter;
	}
	
	final private static String DEFAULT_TEXT = "Hello World!";
	final private static int DEFAULT_FAILS = 5;
	final private static char UNKNOWN = '_';
	
	private static String mText;
	private static Map<Character, Boolean> mGuesses;
	private static int mMistakesRemaining; 
}
