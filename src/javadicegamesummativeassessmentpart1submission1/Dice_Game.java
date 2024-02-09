package javadicegamesummativeassessmentpart1submission1;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.HashSet;

// This class contain all functions/methods, variables, objects for the dice game.
public class Dice_Game {

// This contains all the variables which are integers & booleans.
    public static int rounds = 1;
    public static boolean forfeit = false;
    public static boolean playerTwoTurn = false;
    public static boolean playerOneTurn = true;
    public static int playerThrowsRemaining = 3;
    public static int diceNumber = 5;
    public static int numberSelected;
    public static int asideDices;
    public static int remainingDices;

    public static boolean hasPlayerChosenSequenceP1 = false;
    public static boolean hasPlayerChosenSequenceP2 = false;

    public static boolean selection = true;
    public static boolean playerhasselected = false;

    public static int playerOneScoreOnes = 0;
    public static int playerTwoScoreOnes = 0;

    public static int playerOneScoreTwos = 0;
    public static int playerTwoScoreTwos = 0;

    public static int playerOneScoreThrees = 0;
    public static int playerTwoScoreThrees = 0;

    public static int playerOneScoreFours = 0;
    public static int playerTwoScoreFours = 0;

    public static int playerOneScoreFives = 0;
    public static int playerTwoScoreFives = 0;

    public static int playerOneScoreSixes = 0;
    public static int playerTwoScoreSixes = 0;

    public static int playerOneScoreSequence = 0;
    public static int playerTwoScoreSequence = 0;

    public static int PlayerOneTotalScore = 0;
    public static int PlayerTwoTotalScore = 0;

    // initialisation of objects
    public static ArrayList<Integer> diceList = new ArrayList<>();
    public static ArrayList<Integer> matchedDiceList = new ArrayList<>();
    public static HashSet<Integer> PlayerOneNumbersArechosen = new HashSet<>();
    public static HashSet<Integer> PlayerTwoNumbersArechosen = new HashSet<>();

    // functions for running the game itself.
    public static void main(String[] args) {
        startGame();
        printTable();

        while (gameRounds() == false) { // Also has a while loop which increments by each 1 round.
            printRounds();
            playerTurn();
            rounds += 1;
        }

        playerWin(); // when the game has ended it will display the Winner of the dice game.

    }

    // It is a functions/methods that prints the game title & rules and objectives in the game.
    public static void startGame() {
        System.out.println("The Strategic Dice Game  \n");
        System.out.println("The Instructions for the Rules and Objectives in the Dice Game: ");
        System.out.println("\u2022 Within this game there are two players that will compete with each other to have the highest score.");
        System.out.println("\u2022 Each of the players will play several rounds.");
        System.out.println("\u2022 Both players each having three throws, containing 5 dices maximum.");
        System.out.println("\u2022 Player will have the option to throw or forfeit the game at any stage within the game.");
        System.out.println("\u2022 Also, the players have the options to select a die number or defer their turn if chosen.");
        System.out.println("\u2022 Once the player has selected a die number it can be placed in the following categories.");
        System.out.println("\u2022 If the player has any multiple of the same number, this will be added as a greater score. For example,"
                + "\n  if the player selected 6s and has three 6s, it will be added and displayed as 18 in the score table.");
        System.out.println("\u2022 When the categories have been filled by both of the players.");
        System.out.println("\u2022 The player with the highest total score wins the game. \n");

        System.out.println("Play game (1) or Exit game (0) > "); // Prints a statement to initialise the game.
        Scanner scanner = new Scanner(System.in);
        String i = scanner.nextLine().trim(); // Allows input to be entered in next line and trims the input.
        while (startGameisworking(i) == false) { // The while loop checks the user input with the regex.
            System.out.println("Not A Valid Input"); // Prints a statement if user input is incorrect.
            i = scanner.nextLine().trim();
        }
        if (i.equals("0")) { // A functions when player has been entered 0 which will return a print statement and will end the game for the player. 
            forfeit = true;  // futhermore is player has entered "0" is will make forfeit change/= to true instead of false. 
            System.out.println("You have Exited the Game"); // Prints a statement that display to the player that they have exited the game.
        }
    }

    // functions/methods that enable the user/player to throw or forfeit they throw.
    public static String throwForfeit() {
        System.out.println("Enter 't' or 'f' to throw the dice or forfeit > "); // Prints a statement for displaying throw the dice or forfeit.
        Scanner scanner = new Scanner(System.in);
        String i = scanner.nextLine().trim(); // Allows input to be entered in next line and trims the input.
        while (throwForfeitIsCorrect(i) == false) { // The while loop checks the user input with the regex.
            System.out.println("Not A Valid Input"); // Prints a statement if user input is incorrect.
            i = scanner.nextLine().trim();
        }
        if (i.equals("f")) { // A functions when player has been entered f which will return a print statement and the player will forfeit the game.
            forfeit = true; // if the player has entered 
            System.out.println("You have forfeited the Game");  // futhermore is player has entered "f" is will make forfeit change/= to true instead of false. 
            return i;
        }
        return i;
    }

    // The functions/methods that the player if they have entered "s" / selected,  order to select a category from 1 to 7.
    public static void userinputS() {
        System.out.println("Enter the dice number you wish to select"); // A print statement for prompting the user/player to select their desired category.
        System.out.print("Ones (1) Twos (2) Threes (3) Fours (4) Fives (5) Sixes (6) Sequence (7) > "); // Print statement, which displays the following categories and it's input to initialise it'.
        Scanner scanner = new Scanner(System.in);
        String i = scanner.nextLine().trim(); // Allows input to be entered in next line and trims the input.
        while ((selectDieNumber(i) == false) || (checkIfTheNumberBeenSelected(i) == true)) {  // The while loop checks the user input with the specified conditions.
            System.out.println("Not A Valid Input"); // Prints a statement if user input is incorrect.
            System.out.println("Enter the dice number you wish to select > "); // And again print statement for prompting the user/player to select their desired category.
            i = scanner.nextLine().trim();
        }
        playerhasselected = true; // This variable makes the playerhasselected variable to be equal to true. This allows the player to select only once per turn.
        numberSelected = Integer.parseInt(i); // updating the numberSelected variable to be the user\player input as integer.
        if (playerOneTurn == true) { // Adds the numberSelected to player 1's PlayerOneNumbersArechosen HashSet.
            PlayerOneNumbersArechosen.add(numberSelected);
        } else if (playerTwoTurn == true) { // Adds the numberSelected to player 2's PlayerTwoNumbersArechosen HashSet.
            PlayerTwoNumbersArechosen.add(numberSelected);
        }
        System.out.println(numberSelected + " selected"); // print statment which display the category/number that the player has selected.
        System.out.println("Numbers selected by Player one " + PlayerOneNumbersArechosen);
        System.out.println("Numbers selected by Player Two " + PlayerTwoNumbersArechosen);

    }

    // The functions/methods adds the choosen number to the matched dice list, if its find in the rolled dice list.
    public static void addToMatchedDiceList(int num) {
        for (int i = 0; i < diceList.size(); i++) { // This for loop contains both diceList and matchedDiceList which are arrayslist<integer>,
            // it iterate through each element in diceList.
            if (diceList.get(i) == num) { // and checks if the current element in diceList is equal to the specified 'num'.
                matchedDiceList.add(num); // Also, if the condition is true, add the 'num' to matchedDiceList.
            } else { // Futhermore, if the condition is false, continue to the next iteration.
            }
        }
    }

    // functions/methods that enable the user/player to select a die number or defer your turn.
    public static String selectDefer() {
        System.out.println("Enter 's' or 'd' to select a die number or defer your turn > "); // Prints a statement for displaying the select a die number or defer your turn.
        Scanner scanner = new Scanner(System.in);
        String i = scanner.nextLine().trim(); // Allows input to be entered in next line and trims the input.
        while (selectOrDeferIsCorrect(i) == false) { // The while loop checks the user input with the regex.
            System.out.println("Not A Valid Input"); // Prints a statement if user input is incorrect.
            i = scanner.nextLine().trim();
        }
        return i;
    }

    // functions/methods that enable the user/player to select a die number.
    public static String selectOnly() {
        System.out.println("Enter 's' to select a die number > "); // Prints a statement for displaying the select a die number.
        Scanner scanner = new Scanner(System.in);
        String i = scanner.nextLine().trim(); // Allows input to be entered in next line and trims the input.
        while (selectOnlyIsCorrect(i) == false) { // The while loop checks the user input with the regex.
            System.out.println("Not A Valid Input"); // Prints a statement if user input is incorrect.
            i = scanner.nextLine().trim();
        }
        return i;
    }

    // functions/methods that checks if a given number has been chosen by the current player.
    // I The number to check for being chosen. return True if the number has been chosen, false otherwise.
    public static boolean checkIfTheNumberBeenSelected(String i) {
        // Assume the number has not been chosen until proven otherwise.
        boolean NumberHasBeenSelected = false;
        // Convert the input string to an integer.
        int userChosenNumber = Integer.parseInt(i);
        // Check which player's turn it is and see if the number has been chosen by that player.
        if (playerOneTurn == true) { // check if the number has been selected for player 1.
            NumberHasBeenSelected = PlayerOneNumbersArechosen.contains(userChosenNumber);
        } else if (playerTwoTurn == true) { // check if the number has been selected for player 2.
            NumberHasBeenSelected = PlayerTwoNumbersArechosen.contains(userChosenNumber);
        }
        // Print a message indicating invalid input (this message will be printed regardless of the outcome).
        System.out.println("Invalid input. Please enter a valid number.");
        // Return whether the number has been chosen or not.
        return NumberHasBeenSelected;
    }

    // functions/methods that determines who is the winner.
    public static void playerWin() {
        if (PlayerOneTotalScore > PlayerTwoTotalScore) { // if statement states  player 1 socre is greater than player 2 socre.
            System.out.println("Player 1 is the Winner! with a score of: " + PlayerOneTotalScore); // print statement that display the winner Player 1 and the total score.
        } else if (PlayerTwoTotalScore > PlayerOneTotalScore) { // else if statement states  player 2 socre is greater than player 1 socre.
            System.out.println("Player 2 is the Winner! with a score of: " + PlayerTwoTotalScore); // print statement that display the winner Player 2 and the total score.
        } else {
            System.out.println("Both Players have tied! Player One has scored: "
                    + PlayerOneTotalScore + " Player Two has scored: " + PlayerTwoTotalScore
                    + "\nThere are No Winners in The Strategic Dice Game! ");
            // And when both players have the same equal score is else statment will be used,
            // and then it will print statement that display Both Players have tied! and both of there individual score.
        }
    }

    // functions/methods that changes the turn between two players in a game. If it's currently player one's turn, switch to player two's turn, and vice versa.
    public static void changePlayersTurn() {
        if (playerOneTurn == true) { // This check whose turn it currently is.
            // If it's player one's turn, switch to player two's turn
            playerOneTurn = false;
            playerTwoTurn = true;
        } else {  // If it's not player one's turn, switch to player one's turn
            playerOneTurn = true;
            playerTwoTurn = false;
        }

    }

    // functions/methods creates players, 1 representing player one and 2 representing player two.
    public static void playerTurn() {
        // Iterate over two players
        for (int player = 1; player <= 2; player++) {

            // Throw 1
            System.out.println("First throw for Player " + player + "'s turn, throw " + diceNumber + " dice:");
            // This check if the player wants to forfeit.
            if ("f".equals(throwForfeit())) {
                System.out.println("Player " + player + "'s has Forfeit");
                return; // Exit the method if the player forfeits.
            }
            System.out.print("Throw: ");
            rollDice();  // Roll the dice for the first throw.
            if ("d".equals(selectDefer())) {
                // If detered it follows the defer turn logic.
                System.out.println("You have deferred your turn");
                asideDices = matchedDiceList.size();
                remainingDices = 5 - asideDices;
                diceNumber = remainingDices;
                diceList.clear();
            } else {
                // if selected it follows the Selected turn logic.
                userinputS();
                addToMatchedDiceList(numberSelected);
                System.out.println("matched dice list: " + matchedDiceList);
                asideDices = matchedDiceList.size();
                System.out.println("aside dices count " + asideDices + "\n");
                remainingDices = 5 - asideDices;
                diceNumber = remainingDices;
                diceList.clear();
            }

            // Throw 2
            System.out.println("Next throw for Player " + player + "'s to throw " + remainingDices + " dices");
            // Check if the player wants to forfeit.
            if ("f".equals(throwForfeit())) {
                System.out.println("Player " + player + "'s has Forfeit");
                return;
            }
            System.out.print("Throw: ");
            rollDice(); // Roll the dice for the second throw.
            // Check if the player has already made a selection.
            if (playerhasselected == false) {
                // Player hasn't selected yet.
                if (selection == true) {
                    // Continue turn logic if the player selected in the previous throw.
                    String input = selectDefer();
                    if (input.equals("s")) {
                        userinputS();
                        addToMatchedDiceList(numberSelected);
                        System.out.println("matched dice list: " + matchedDiceList);
                        asideDices = matchedDiceList.size();
                        System.out.println("aside dices count " + asideDices + "\n");
                        remainingDices = 5 - asideDices;
                        diceNumber = remainingDices;
                        diceList.clear();
                        selection = true;
                    } else if (input.equals("d")) {
                        // If detered it follows the defer turn logic.
                        System.out.println("You have deferred your turn");
                        System.out.println("matched dice list: " + matchedDiceList);
                        asideDices = matchedDiceList.size();
                        System.out.println("aside dices count " + asideDices + "\n");
                        remainingDices = 5 - asideDices;
                        diceNumber = remainingDices;
                        diceList.clear();
                    }
                }
            }
            // Throw 3
            System.out.println("Last throw, player1 to throw " + remainingDices + " dices");
            // Check if the player wants to forfeit.
            if ("f".equals(throwForfeit())) {
                System.out.println("Player " + player + "'s has Forfeit");
                return;
            }
            System.out.print("Throw: ");
            rollDice(); // Roll the dice for the third throw.
            // Check if the player has already made a selection.
            if (playerhasselected == false) {
                // Player hasn't selected yet.
                if (selection == true) {
                    // Continue turn logic if the player selected in the previous throw.
                    selectOnly();
                    userinputS();
                    addToMatchedDiceList(numberSelected);
                    System.out.println("matched dice list: " + matchedDiceList);
                    asideDices = matchedDiceList.size();
                    System.out.println("aside dices count " + asideDices + "\n");
                    remainingDices = 5 - asideDices;
                    diceNumber = remainingDices;
                    diceList.clear();
                    updateTurnScore(numberSelected);
                    exitTurn();
                    printTable();
                    changePlayersTurn();
                }
            } else {
                // Continue turn logic if the player deferred or has not selected yet.
                addToMatchedDiceList(numberSelected);
                System.out.println("matched dice list: " + matchedDiceList);
                asideDices = matchedDiceList.size();
                System.out.println("aside dices count " + asideDices + "\n");
                remainingDices = 5 - asideDices;
                diceNumber = remainingDices;
                diceList.clear();
                updateTurnScore(numberSelected);
                exitTurn();
                printTable();
                changePlayersTurn();
            }
        }
    }

    // functions/method prints a formatted message indicating the current round number.
    public static void printRounds() {
        System.out.println("----------\n Round " + rounds + " \n----------\n"); // print statment printing the current round in the dice game.

    }

    // functions/methods resets various game-related variables to their initial values, signaling the end of a player's turn.
    public static void exitTurn() {
        // Reset player-specific variables
        playerThrowsRemaining = 3; // Reset the number of throws for the player.
        diceNumber = 5; // Reset the number of dice available for the player.
        diceList.clear(); // Clear lists used for tracking dice outcomes &  Clear the list of rolled dice.
        matchedDiceList.clear(); // Clear the list of matched dice.
        // Reset selection-related flags.
        selection = true; // Allow the player to make a new selection in the next turn.
        playerhasselected = false; // Indicate that the player has not yet made a selection in the current turn.
    }

    // functions/methods updates the total score for the current player based on the selected number and the number of matched dice.
    // Also updates the individual score for the selected number.
    public static void updateTurnScore(int numberSelected) {
        // Calculate the score multiplier based on the selected number and the number of matched dice.
        int scoreMultiplier = numberSelected * matchedDiceList.size();
        // Update scores based on the current player's turn.
        if (playerOneTurn == true) {
            PlayerOneTotalScore += scoreMultiplier;
            // Update player 1's individual scores for the selected number.
            switch (numberSelected) {
                case 1:
                    playerOneScoreOnes = scoreMultiplier;
                    break;
                case 2:
                    playerOneScoreTwos = scoreMultiplier;
                    break;
                case 3:
                    playerOneScoreThrees = scoreMultiplier;
                    break;
                case 4:
                    playerOneScoreFours = scoreMultiplier;
                    break;
                case 5:
                    playerOneScoreFives = scoreMultiplier;
                    break;
                case 6:
                    playerOneScoreSixes = scoreMultiplier;
                    break;
                default:
                    System.out.println("an error has occured in scoreMultiplier");
                    break;
            }
        } else {
            PlayerTwoTotalScore += scoreMultiplier;
            // Update player 2's individual scores for the selected number.
            switch (numberSelected) {
                case 1:
                    playerTwoScoreOnes = scoreMultiplier;
                    break;
                case 2:
                    playerTwoScoreTwos = scoreMultiplier;
                    break;
                case 3:
                    playerTwoScoreThrees = scoreMultiplier;
                    break;
                case 4:
                    playerTwoScoreFours = scoreMultiplier;
                    break;
                case 5:
                    playerTwoScoreFives = scoreMultiplier;
                    break;
                case 6:
                    playerTwoScoreSixes = scoreMultiplier;
                    break;
                default:
                    System.out.println("an error has occured in scoreMultiplier");
                    break;
            }
        }
    }

    // functions/methods checks if the game has reached the specified number of rounds or if a forfeit has occurred. 
    // return True if the game has reached the specified number of rounds or if a forfeit has occurred, otherwise false.
    public static boolean gameRounds() {
        return rounds == 8 || forfeit == true;
    }

    // functions/methods rolls a specified number of dice and adds the results to the diceList.
    private static void rollDice() {
        // Create a new instance of the Random class to generate random numbers.
        Random random = new Random();
        // Roll a specified number of dice and display the results.
        for (int i = 0; i < diceNumber; i++) {
            // Generate a random number between 1 and 6 (inclusive) to simulate a dice roll.
            int number = random.nextInt(6) + 1;
            // Add the rolled number to a list to keep track of each individual dice result.
            diceList.add(number);
            // Display the rolled number inside square brackets.
            System.out.print("[" + number + "]");
        }
        // Print a newline to separate the results of different dice rolls.
        System.out.println(" ");
    }

    // Score board containing both players scores.
    public static void printTable() {

        System.out.printf("------------------------------------------%n");
        System.out.println("                Dice Game                 ");

        System.out.printf("------------------------------------------%n");
        System.out.printf("| %-15s | %-9s | %-9s |%n", "", "Player 1 ", "Player 2");
        System.out.printf("------------------------------------------%n");

        //System.out.printf("| %-15s | %-9d | %04d |%n",   );
        System.out.printf("| %-15s | %-9d | %-9d |%n", "Ones", playerOneScoreOnes, playerTwoScoreOnes);
        System.out.printf("| %-15s | %-9d | %-9d |%n", "Twos", playerOneScoreTwos, playerTwoScoreTwos);
        System.out.printf("| %-15s | %-9d | %-9d |%n", "Threes", playerOneScoreThrees, playerTwoScoreThrees);
        System.out.printf("| %-15s | %-9d | %-9d |%n", "Fours", playerOneScoreFours, playerTwoScoreFours);
        System.out.printf("| %-15s | %-9d | %-9d |%n", "Fives", playerOneScoreFives, playerTwoScoreFives);
        System.out.printf("| %-15s | %-9d | %-9d |%n", "Sixes", playerOneScoreSixes, playerTwoScoreSixes);
        System.out.printf("| %-15s | %-9d | %-9d |%n", "Sequence 20", playerOneScoreSequence, playerTwoScoreSequence);
        System.out.printf("| %-15s | %-9d | %-9d |%n", "TOTAL", PlayerOneTotalScore, PlayerTwoTotalScore);

        System.out.printf("------------------------------------------%n");

    }

    // This function is boolean which allows the input of String number to be only [01].
    public static boolean startGameisworking(String number) {
        String regex = "^[01]$";     // function use regex to to give its parameters on what inputs are acceptable.
        Pattern pattern = Pattern.compile(regex);    // It creates Pattern object by compiling the given regular expression (regex).
        return pattern.matcher(number).matches(); // It created pattern to create a Matcher object for the input number, The matches() method checks if the entire input sequence matches the pattern which has been given by the user.
        // The function Return the result of the match operation, indicating whether the number has been adhered to the specified regular expression pattern.
    }

    // This function is boolean which allows the input of String number to be only [tf].
    public static boolean throwForfeitIsCorrect(String number) {
        String regex = "^[tf]$";    // function use regex to to give his parameters on what inputs are acceptable.
        Pattern pattern = Pattern.compile(regex); // It creates Pattern object by compiling the given regular expression (regex).
        return pattern.matcher(number).matches(); // It created pattern to create a Matcher object for the input number, The matches() method checks if the entire input sequence matches the pattern which has been given by the user.
        // The function Return the result of the match operation, indicating whether the number has been adhered to the specified regular expression pattern.
    }

    // This function is boolean which allows the input of String number to be only [sd].
    public static boolean selectOrDeferIsCorrect(String num) {
        String regex = "^[sd]$";    // function use regex to to give its parameters on what inputs are acceptable.
        Pattern pattern = Pattern.compile(regex); // It creates Pattern object by compiling the given regular expression (regex).
        return pattern.matcher(num).matches(); // It created pattern to create a Matcher object for the input number, The matches() method checks if the entire input sequence matches the pattern which has been given by the user.
        // The function Return the result of the match operation, indicating whether the number has been adhered to the specified regular expression pattern.
    }

    // This function is boolean which allows the input of String number to be only [s].
    public static boolean selectOnlyIsCorrect(String num) {
        String regex = "^[s]$";    // function use regex to to give its parameters on what inputs are acceptable.
        Pattern pattern = Pattern.compile(regex); // It creates Pattern object by compiling the given regular expression (regex).
        return pattern.matcher(num).matches(); // It created pattern to create a Matcher object for the input number, The matches() method checks if the entire input sequence matches the pattern which has been given by the user.
        // The function Return the result of the match operation, indicating whether the number has been adhered to the specified regular expression pattern.
    }

    // This function is boolean which allows the input of String number to be only [1234567].
    public static boolean selectDieNumber(String num) {
        String regex = "^[1234567]$";    // function use regex to to give its parameters on what inputs are acceptable.
        Pattern pattern = Pattern.compile(regex); // It creates Pattern object by compiling the given regular expression (regex).
        return pattern.matcher(num).matches(); // It created pattern to create a Matcher object for the input number, The matches() method checks if the entire input sequence matches the pattern which has been given by the user.
        // The function Return the result of the match operation, indicating whether the number has been adhered to the specified regular expression pattern.
    }
}
