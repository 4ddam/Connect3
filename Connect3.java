/*
 * You know the game Connect 4. Create a version of this game called Connect 3: it uses a 3 by 4 table, 
 * and the first player to connect 3 in a row (horizontal, vertical, or diagonal) wins.
 * 
 * Remember, it is a 2-player game, and each player chooses a column to drop a piece into. 
 * “Gravity” pulls each piece to the lowest spot available.
 * 
 * Determine and Display the winner.
 * 
 * Allow the user to play multiple times if they want to.
 *
 * Adam Browning
 * 1/3/2020
 */

import java.util.Scanner;
public class Connect3
{
    public static void main() {
        String[][] board = new String[3][4];                            // Rows, Columns

        start(board);
        printBoard(board);
        doTurns(board);
    }

    private static void start(String[][] input) {                       // Class to fill board with ❏ characters
        for (int c = 0; c < input.length; c++) {
            for (int r = 0; r < input[0].length; r++) {
                input[c][r] = "❏";              
            }
        }   
    }

    private static void printBoard(String[][] input) {                  // Class for printing the 2D array
        System.out.println("        𝘾 𝙊 𝙉 𝙉 𝙀 𝘾 𝙏 3\n");  
        for (int r = 0; r <= input.length; r++) {                       // Prints numbers
            System.out.print("    " + (r+1) + "   ");
        }             

        for (int r = 0; r < input.length; r++) {                        // Print the board characters with proper spacing
            for (int c = 0; c < input[0].length; c++) { 
                if (c%4 == 0) {
                    System.out.print("\n\n\n ");                    
                }
                System.out.print("   " + input[r][c] + "   ");
            }   
        }
        System.out.println("\n\n\nPlayer 1 = ◆\nPlayer 2 = ◇");        // Print player characters
    }

    private static void doTurns(String[][] input) {
        Scanner scanner = new Scanner(System.in);
        boolean p1TurnComplete = false;
        boolean p2TurnComplete = false;
        while (checkForWinner(input) == false) {                        // Tests if there is a winner yet, if not, keep going

            p1TurnComplete = false;
            p2TurnComplete = false;

            System.out.print("\n\n\nPlayer 1 - Enter a column: ");

            while (p1TurnComplete == false) {                                                                               // Tests if player 1's turn is up yet
                String columnNumber = scanner.nextLine();
                if (columnNumber.matches("-?\\d+")) {                                                                       // Tests if the input is a number
                    if ((Integer.parseInt(columnNumber)) > 0 && (Integer.parseInt(columnNumber)) <= 4) {                    // Check if column is on the board
                        if (fullColumnCheck(input, (Integer.parseInt(columnNumber))) == false) {                            // Check if column is full
                            for (int r = input.length; r > 0; r--) {
                                if (!(input[r-1][(Integer.parseInt(columnNumber))-1].equalsIgnoreCase("◇")) &&              // Tests if there is a symbol at lowest spot.
                                !(input[r-1][(Integer.parseInt(columnNumber))-1].equalsIgnoreCase("◆"))) {
                                    input[r-1][(Integer.parseInt(columnNumber))-1] = "◆";                                   // Add symbol to the lowest spot
                                    p1TurnComplete = true;                                                                  // Finish turn
                                    break;
                                } 
                            }
                            System.out.print("\f");
                            printBoard(input);                                                                              // Clear and reprint board after each move
                            if (checkForWinner(input) == true) {                                                            // Check for win condition. If so, winner.
                                System.out.println("\nPlayer 1 Wins!");  
                                playAgain (input);
                            }   
                        } else {
                            System.out.print("\f");
                            printBoard(input);  
                            System.out.print("\nThat column is full\n\nPlayer 1 - Enter a column: ");
                        }
                    } else {
                        System.out.print("\f");
                        printBoard(input);  
                        System.out.print("\nThat column is not on the board\n\nPlayer 1 - Enter a column: ");
                    }
                } else {
                    System.out.print("\f");
                    printBoard(input);  
                    System.out.print("\nThat is not a valid number\n\nPlayer 1 - Enter a column: ");
                }
            }

            if (checkForWinner(input) == false) {                                                                       // If P1 hasn't won, P2 can go

                System.out.print("\n\n\nPlayer 2 - Enter a column: ");

                while (p2TurnComplete == false) {                                                                       // Tests if player 2's turn is up yet
                    String columnNumber = scanner.nextLine();
                    if (columnNumber.matches("-?\\d+")) {                                                                   // Tests if the input is a number
                        if ((Integer.parseInt(columnNumber)) > 0 && (Integer.parseInt(columnNumber)) <= 4) {                // Check if column is on the board
                            if (fullColumnCheck(input, (Integer.parseInt(columnNumber))) == false) {                        // Check if column is full
                                for (int r = input.length; r > 0; r--) {
                                    if (!(input[r-1][(Integer.parseInt(columnNumber))-1].equalsIgnoreCase("◇")) &&          // Tests if there is a symbol at lowest spot.
                                    !(input[r-1][(Integer.parseInt(columnNumber))-1].equalsIgnoreCase("◆"))) {
                                        input[r-1][(Integer.parseInt(columnNumber))-1] = "◇";                               // Add symbol to the lowest spot
                                        p2TurnComplete = true;                                                              // Finish turn
                                        break;
                                    } 
                                }
                                System.out.print("\f");
                                printBoard(input);                                                                          // Clear and reprint board after each move
                                if (checkForWinner(input) == true) {                                                        // Check for win condition. If so, winner.
                                    System.out.println("\nPlayer 2 Wins!");
                                    playAgain (input);
                                }  
                            } else {
                                System.out.print("\f");
                                printBoard(input);  
                                System.out.print("\nThat column is full\n\nPlayer 2 - Enter a column: ");
                            }
                        } else {                       
                            System.out.print("\f");
                            printBoard(input);  
                            System.out.print("\nThat column is not on the board\n\nPlayer 2 - Enter a column: ");
                        }
                    } else {
                        System.out.print("\f");
                        printBoard(input);  
                        System.out.print("\nThat is not a valid number\n\nPlayer 2 - Enter a column: ");
                    }
                }
            }      
        }
    }

    private static boolean checkForWinner(String[][] input) {   
        boolean winState = false;

        for (int c = 0; c < input[0].length; c++) {              // Checks for column win                 
            if (input[0][c].equalsIgnoreCase("◆") && input[1][c].equalsIgnoreCase("◆") && input[2][c].equalsIgnoreCase("◆")) { 
                winState = true;
            }
            if (input[0][c].equalsIgnoreCase("◇") && input[1][c].equalsIgnoreCase("◇") && input[2][c].equalsIgnoreCase("◇")) {
                winState = true;
            }
        }  

        for (int r = 0; r < input.length; r++) {                 // Checks for row win                 
            if ((input[r][0].equalsIgnoreCase("◆") && input[r][1].equalsIgnoreCase("◆") && input[r][2].equalsIgnoreCase("◆")) || 
            (input[r][1].equalsIgnoreCase("◆") && input[r][2].equalsIgnoreCase("◆") && input[r][3].equalsIgnoreCase("◆"))) { 
                winState = true;
            }
            if ((input[r][0].equalsIgnoreCase("◇") && input[r][1].equalsIgnoreCase("◇") && input[r][2].equalsIgnoreCase("◇")) || 
            (input[r][1].equalsIgnoreCase("◇") && input[r][2].equalsIgnoreCase("◇") && input[r][3].equalsIgnoreCase("◇"))) { 
                winState = true;
            }
        }  

        if (input[2][0].equalsIgnoreCase("◆") && input[1][1].equalsIgnoreCase("◆") && input[0][2].equalsIgnoreCase("◆")) {          // Hardcode check for diagonals for P1
            winState = true;
        }
        if (input[2][1].equalsIgnoreCase("◆") && input[1][2].equalsIgnoreCase("◆") && input[0][3].equalsIgnoreCase("◆")) {
            winState = true;
        }
        if (input[0][1].equalsIgnoreCase("◆") && input[1][2].equalsIgnoreCase("◆") && input[2][3].equalsIgnoreCase("◆")) {
            winState = true;
        }
        if (input[0][0].equalsIgnoreCase("◆") && input[1][1].equalsIgnoreCase("◆") && input[2][2].equalsIgnoreCase("◆")) {
            winState = true;
        }

        if (input[2][0].equalsIgnoreCase("◇") && input[1][1].equalsIgnoreCase("◇") && input[0][2].equalsIgnoreCase("◇")) {          // Hardcode check for diagonals for P2
            winState = true;
        }
        if (input[2][1].equalsIgnoreCase("◇") && input[1][2].equalsIgnoreCase("◇") && input[0][3].equalsIgnoreCase("◇")) {
            winState = true;
        }
        if (input[0][1].equalsIgnoreCase("◇") && input[1][2].equalsIgnoreCase("◇") && input[2][3].equalsIgnoreCase("◇")) {
            winState = true;
        }
        if (input[0][0].equalsIgnoreCase("◇") && input[1][1].equalsIgnoreCase("◇") && input[2][2].equalsIgnoreCase("◇")) {
            winState = true;
        }

        return winState;
    }

    private static boolean fullColumnCheck(String[][] input, int column) {          // Check if column is full
        boolean columnFull = false;

        if (input[0][column-1].equalsIgnoreCase("◆")) { 
            columnFull = true;
        }
        if (input[0][column-1].equalsIgnoreCase("◇")) {
            columnFull = true;
        }

        return columnFull;
    }

    private static void playAgain (String[][] input) {
        Scanner scanner = new Scanner (System.in);
        boolean goodResponse = false;
        System.out.print("\nPlay Again? [Y, N]");

        while (goodResponse == false) {
            String response = scanner.nextLine();

            if (response.matches("^([Y|y|N|n])$")) {
                if (response.equalsIgnoreCase("y")) {
                    System.out.print("\f");
                    main();
                } else if (response.equalsIgnoreCase("n")) {
                    System.out.print("\fGame Ended.");
                    System.exit(0);
                }
            } else {
                System.out.print("\f");
                printBoard(input);  
                System.out.print("\nThat is not a choice.\n\nPlay Again? [Y, N]");
            }
        }
    }
}

