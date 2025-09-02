package edu.westga.cs1302.hilogame.tests.model.hilogame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.hilogame.model.HiLoGame;

class TestMakeGuess {

	@Test
	void testGuessIsTooHigh() {
		HiLoGame game = new HiLoGame(3);
		
		String result = game.makeGuess(4);
		
		assertEquals("Too high.", result, "checking result of makeGuess");
		assertEquals(1, game.getGuessCount(), "checking guess count");
	}

	@Test
	void testGuessIsTooLow() {
		HiLoGame game = new HiLoGame(3);
		
		String result = game.makeGuess(2);
		
		assertEquals("Too low.", result, "checking result of makeGuess");
		assertEquals(1, game.getGuessCount(), "checking guess count");
	}
	
	@Test
	void testCorrectGuessFirstTime() {
		HiLoGame game = new HiLoGame(3);

		String result = game.makeGuess(3);
		
		assertEquals("Correct.", result, "checking result of makeGuess");
		assertEquals(1, game.getGuessCount(), "checking guess count");
		
	}
	
	@Test
	void testCorrectGuessSecondTime() {
		HiLoGame game = new HiLoGame(3);
		game.makeGuess(1);

		String result = game.makeGuess(3);
		
		assertEquals("Correct.", result, "checking result of makeGuess");
		assertEquals(2, game.getGuessCount(), "checking guess count");
		
	}
	
	@Test
	void testCorrectGuessThirdTime() {
		HiLoGame game = new HiLoGame(3);
		game.makeGuess(5);
		game.makeGuess(2);

		String result = game.makeGuess(3);
		
		assertEquals("Correct.", result, "checking result of makeGuess");
		assertEquals(3, game.getGuessCount(), "checking guess count");
		
	}

	@Test
	void testTooManyGuesses() {
		HiLoGame game = new HiLoGame(3);
		game.makeGuess(5);
		game.makeGuess(6);
		game.makeGuess(7);
		
		String result = game.makeGuess(1);
		
		assertEquals("Too many guesses.", result, "checking result of makeGuess");
		assertEquals(4, game.getGuessCount(), "checking guess count");
	}

}
