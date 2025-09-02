package edu.westga.cs1302.hilogame.model;

/** Manages game state and generates action responses for a HiLoGame
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class HiLoGame {
	private int guessCount;
	private int targetNumber;
	
	/** Get the number of guesses made so far
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the number of guesses made so far
	 */
	public int getGuessCount() {
		return this.guessCount;
	}

	/** Get the number to guess
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the number to guess
	 */
	public int getTargetNumber() {
		return this.targetNumber;
	}
	
	/** Initialize a new HiLoGame
	 * 
	 * @precondition none
	 * @postcondition getGuessCount() == 0 && getTargetNumber() == 0
	 * 
	 * @param targetNumber the number to guess
	 */
	public HiLoGame(int targetNumber) {
		this.guessCount = 0;
		this.targetNumber = targetNumber;
	}
	
	/** Allow player to make a guess, and generate the appropriate response
	 * 
	 * @precondition none
	 * @postcondition guess count will be updated
	 * 
	 * @param guess the number guessed by the player
	 * 
	 * @return 	"Correct."  		if guess == getTargetNumber()
	 * 			"Too low."  		if guess < getTargetNumber()
	 * 			"Too high." 		if guess == getTargetNumber()
	 * 			"Too many guesses." if getGuessCount() > 3
	 */
	public String makeGuess(int guess) {
		String result = null;
		this.guessCount++;
		if (this.guessCount > 3) {
			result = "Too many guesses.";
		} else if (guess < this.targetNumber) {
			result = "Too low.";
		} else if (guess > this.targetNumber) {
			result = "Too high.";
		} else {
			result = "Correct.";
		}
		return result;
	}
}
