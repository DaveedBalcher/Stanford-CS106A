/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import java.util.ArrayList;

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	public void run() {
		setupPlayers();
		initDisplay();
		playGame();
	}
	
	/**
	 * Prompts the user for information about the number of players, then sets up the
	 * players array and number of players.
	 */
	private void setupPlayers() {
		nPlayers = chooseNumberOfPlayers();	
		
		/* Set up the players array by reading names for each player. */
		playerNames = new String[nPlayers];
		for (int i = 0; i < nPlayers; i++) {
			/* IODialog is a class that allows us to prompt the user for information as a
			 * series of dialog boxes.  We will use this here to read player names.
			 */
			IODialog dialog = getDialog();
			playerNames[i] = dialog.readLine("Enter name for player " + (i + 1));
		}
	}
	
	/**
	 * Prompts the user for a number of players in this game, reprompting until the user
	 * enters a valid number.
	 * 
	 * @return The number of players in this game.
	 */
	private int chooseNumberOfPlayers() {
		/* See setupPlayers() for more details on how IODialog works. */
		IODialog dialog = getDialog();
		
		while (true) {
			/* Prompt the user for a number of players. */
			int result = dialog.readInt("Enter number of players");
			
			/* If the result is valid, return it. */
			if (result > 0 && result <= MAX_PLAYERS)
				return result;
			
			dialog.println("Please enter a valid number of players.");
		}
	}
	
	/**
	 * Sets up the YahtzeeDisplay associated with this game.
	 */
	private void initDisplay() {
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
	}

	/**
	 * Actually plays a game of Yahtzee.  This is where you should begin writing your
	 * implementation.
	 */
	private void playGame() {
		setUpScores();
		
		//This repeats the game for each of the 13 rounds
		for(int round=0; round<N_SCORING_CATEGORIES; round++) {
			
			//This repeats each round for the number of players
			for(int player=0; player<nPlayers; player++) {
				
				playRound(player);
				int category = display.waitForPlayerToSelectCategory();
				int score = findScore(category,player-1);
				display.updateScorecard(category, player, score);
				display.printMessage(playerNames[player] + "'s turn! Click 'Roll Dice' button to roll the dice.");
				addScore(player);
			}
		}
		int winner = 0;
		int winningScore = 0;
		for(int i=0; i<nPlayers; i++) {
			if(totalScore[i] > winningScore) {
				winningScore = totalScore[i];
				winner = i;
			}
		}
		display.printMessage(playerNames[winner] + "'s wins with a score of " + winningScore + "!" );
	}
	
	
	//This method plays through one round for each player
	private void playRound(int player) {
		
		//This repeats the chances, giving each player three chances
		int chance = 0;                    
		while(true) {
			display.waitForPlayerToClickRoll(player);
			diceResults(chance);
			
			display.displayDice(dice);
			if(chance == 2) break;
			
			selectDiceToReroll();
			chance++;
		}
	}
	
	//Determines random numbers for 5 dice
	private void diceResults(int chance) {
		
		//Random generates a number between 1 and 6 for each die
		for(int dieNum=0; dieNum<N_DICE; dieNum++) {
			rgen = RandomGenerator.getInstance();
			int dieResult = rgen.nextInt(1, 6);
			dice[dieNum] = dieResult;
		}
		
		//This method replaces the values of the dice that were chosen to be re-rolled
		for(int i=0; i<choosenDice.length; i++) {
			if(chance > 0 && choosenDice[i] == 0) {
				dice[i] = previousDice[i];
			}
		}
	}
	
	//This method selects the dice to re-roll and sets the previous roll to memory
	private void selectDiceToReroll() {
		display.waitForPlayerToSelectDice();
		for(int i=0; i<choosenDice.length; i++) {
			if (display.isDieSelected(i)) {
				choosenDice[i] = 1;
			} else {
				choosenDice[i] = 0;
			}
		}
		
		for(int i=0; i<dice.length; i++) {
			previousDice[i] = dice[i];
		}
	}
	
	
	//Find score for each turn, comparing the how the die match the selected category
	private int findScore(int category, int player) {
		int score = 0;
		matchedArray();
		player++;
		if(category < 7) {
			int categoryNum = category+1;
			for(int dieNum=0; dieNum<N_DICE; dieNum++) {
				if(dice[dieNum] == categoryNum) {
					score += categoryNum;
				}
			}
			upperScore[player] += score;
		} 
		else if(category < 11 || category == 13 || category == 14){
			score = ofaKind(category);
			lowerScore[player] += score;
		}
		else if(category < 13){
			score = straight(category);
			lowerScore[player] += score;
		}
		return score;
	}

	//This array and method count the results of the dice for each turn
	private int[] matched = new int[6];
	
	private void matchedArray() {
		for(int i=0; i<6; i++) {
			matched[i] = 0;
		}
		for(int dieNum=0; dieNum<N_DICE; dieNum++) {
			for(int dieVal=0; dieVal<6; dieVal++) {
				int match = dice[dieNum] - 1;
				if(match == dieVal) {
					matched[dieVal] += 1;
				}
			}
		}
	}
	
	
	//This method checks if the turn falls into the of a kind category
	private int ofaKind(int category) {
		int score = 0;
		int potScore = 0; //Potential score if turn matches the category
		
		for(int dieNum=0; dieNum<N_DICE; dieNum++) {
			potScore += dice[dieNum];
			}
		for(int dieVal=0; dieVal<6; dieVal++) {
			if(category == 8 && matched[dieVal] > 2) {
				score = potScore;
			}
			if(category == 9 && matched[dieVal] > 3) {
				score = potScore;
			}
			if(category == 10 && matched[dieVal] > 2) {
				for(int dieVal2=0; dieVal2<6; dieVal2++) {
					if(dieVal != dieVal2 && matched[dieVal] > 1) {
						score = 25;
					}
				}
			}
			if(category == 13 && matched[dieVal] > 4) {
				score = 50;
			}
			if(category == 14) {
				score = potScore;
			}
		}
		
		return score;
	}
	
	
	//This method checks if the turn falls into the straight category
	private int straight(int category) {
		int score = 0;
		
		for(int dieVal=3; dieVal<6; dieVal++) {
			if(matched[dieVal] !=0 && matched[dieVal-1] !=0 && matched[dieVal-2] !=0 && matched[dieVal-3] !=0 && category != 12) {
				score = 30;
				}
			if(dieVal != 3 && matched[dieVal] !=0 && matched[dieVal-1] !=0 && matched[dieVal-2] !=0 && matched[dieVal-3] !=0 && matched[dieVal-4] !=0 && category != 11) {
				score = 40;
			}
		}
		return score;
	}
	
	
	//This method adds up all the scores at the end of each round
	private void addScore(int player) {
		display.updateScorecard(UPPER_SCORE, player, upperScore[player]);
		totalScore[player] = upperScore[player] + lowerScore[player];
		if(upperScore[player] > 62) {
			totalScore[player] += bonus;
			display.updateScorecard(UPPER_BONUS, player, bonus);
		}
		display.updateScorecard(LOWER_SCORE, player, lowerScore[player]);
		display.updateScorecard(TOTAL, player, totalScore[player]);
	}
	
	
	//This method sets up array
	private void setUpScores() {
		upperScore = new int[nPlayers];
		lowerScore = new int[nPlayers];
		totalScore = new int[nPlayers];
		for(int i=0; i<nPlayers; i++) {
			upperScore[i] = 0;
			lowerScore[i] = 0;
			totalScore[i] = 0;
		}
	}
	
	/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen; 	//Initiate the random generator
	
	private int[] upperScore;
	private int[] lowerScore;
	private int[] totalScore;
	
	//Initiates an array that stores the dice a player chooses to re-roll
	private int[] choosenDice = new int[N_DICE];
	
	//Initiate array that stores the values of the dice
	private int[] dice = new int[N_DICE];
	private int[] previousDice = new int[N_DICE];
}
