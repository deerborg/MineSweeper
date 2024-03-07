/*
  @author Furkan Aydemir , deerborg
  @since  2024
 */

import java.util.*;

public class MineSweeper {

    String[][] managerMap;
    String[][] playerMap;
    int rowNumber;
    int colNumber;
    int boardSize;
    int mineCounter = 0;

    // Section - 5 : Variables are defined within the MineSweeper constructor method
    public MineSweeper(int rowValue, int colValue) {
        this.managerMap = new String[rowValue][colValue];
        this.playerMap = new String[rowValue][colValue];
        this.rowNumber = rowValue;
        this.colNumber = colValue;
        this.boardSize = rowValue * colValue;
    }

    Random mineValue = new Random();
    Scanner userValue = new Scanner(System.in);

    // Section - 6: The method that starts the game.
    public void goPlay() {

        int goRow, goCol, clearChoice = 0, checkAgainRow = -1, checkAgainCol = -1;

        addMines();
        createBoardMap(managerMap); // Map showing single-use mines.
        System.out.println("==================");
        createBoardMap(playerMap); // Map showing single-use player map

        while (true) {

            // Block to query whether the given row information is a numerical value or not
            while (true) {
                System.out.print("ENTER ROW: ");
                if (userValue.hasNextInt()) {
                    goRow = userValue.nextInt();  // Section - 9 : Marking information to be received from the user.
                    if (goRow < 0 || goRow > rowNumber - 1) {  // Section - 10 : The situation where the entered column information is outside the total array area.
                        System.out.println("INVALID VALUE, TRY AGAIN");
                        continue;
                    }
                    break;
                } else {
                    System.out.println("ENTER A INTEGER VALUE");
                    userValue.next();
                }
            }

            // Block to query whether the given column information is a numerical value or not
            while (true) {
                System.out.print("ENTER COL: ");
                if (userValue.hasNextInt()) {
                    goCol = userValue.nextInt();  // Section - 9 : Marking information to be received from the user.
                    if (goCol < 0 || goCol > colNumber - 1) {  // Section - 10 : The situation where the entered column information is outside the total array area.
                        System.out.println("INVALID VALUE, TRY AGAIN");
                        continue;
                    }
                    break;
                } else {
                    System.out.println("ENTER A INTEGER VALUE");
                    userValue.next();
                }
            }
            if (checkAgainCol == goCol && checkAgainRow == goRow) { // If the user enters the same values during the tour, she will be asked to enter a different value.
                System.out.println("YOU HAVE ENTERED REPEATED VALUES, ENTER DIFFERENT VALUES");
                continue;
            }

            checkAgainCol = goCol;
            checkAgainRow = goRow;

            if (!playerMap[goRow][goCol].equals("-")) { // Prevents the user from marking the previously marked location again.
                System.out.println("YOU ENTERED THE SAME LOCATIONS!");
                continue;
            }

            controlMine(goRow, goCol);

            if (managerMap[goRow][goCol].equals("*")) { // Section - 13 , 15 : The block that will work if there is a mine in the marked location
                System.out.println("ITS MINE... BOOOM! GAME OVER!");
                break;
            } else {

                checkMineMap(goRow, goCol); // Section - 11 : Updates the map with every move of the user and shows mine controls here

                mineCounter = 0;

                System.out.println("GOOD CHOICE");
                clearChoice++;

                if (clearChoice == boardSize - ((boardSize * 25) / 100)) { // Section - 15 , 14 : If 75% enters the area correctly and cleanly (finishes the game in one go), the condition block
                    System.out.println("CONGRATULATIONS, YOU WON!!!");
                    userValue.close();
                    break;
                }
            }
        }
    }

    // Section - 6 : Method that shows the mines to the user (managerMap) and creates the map to be played (playerMap)
    public void createBoardMap(String[][] board) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if ("*".equals(board[i][j])) {
                    System.out.print(board[i][j] + " ");
                }
                if (!"*".equals(board[i][j])) {
                    board[i][j] = "-";
                    System.out.print(board[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    // Section - 6 , 8 : A method of adding random mines up to 1/4 of the area.
    public void addMines() {
        int rowMine, colMine, mineCount = 0;

        while (mineCount != (boardSize * 25 / 100)) {

            rowMine = mineValue.nextInt(rowNumber);
            colMine = mineValue.nextInt(colNumber);

            if (!"*".equals(managerMap[rowMine][colMine])) {
                managerMap[rowMine][colMine] = "*";
                mineCount++;
            }
        }
    }

    // Section - 6: This is the method that creates the map to be updated. Changes the value of entered positions according to the number of mines in the environment.
    public void checkMineMap(int goRow, int goCol) {

        for (int i = 0; i < playerMap.length; i++) {
            for (int j = 0; j < playerMap[i].length; j++) {
                if (playerMap[i][j].equals(playerMap[goRow][goCol])) {
                    playerMap[goRow][goCol] = String.valueOf(mineCounter);
                    System.out.print(playerMap[goRow][goCol] + " ");
                    continue;
                }
                System.out.print(playerMap[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Section - 6 , 12 : It checks the locations on the map (managerMap) where the mines are displayed and gives the number of mines around the location given by the user.
    public void controlMine(int controlRow, int controlCol) {


        if (managerMap[controlRow][controlCol].equals("-")) {

            // In vertical positions; right - left - bottom - top mine controls
            if ((controlCol < (colNumber - 1)) && (managerMap[controlRow][controlCol + 1]).equals("*")) {
                mineCounter++;
            }

            if ((controlRow < (rowNumber - 1)) && (managerMap[controlRow + 1][controlCol]).equals("*")) {
                mineCounter++;
            }

            if ((controlCol > 0) && (managerMap[controlRow][controlCol - 1]).equals("*")) {
                mineCounter++;
            }

            if ((controlRow > 0) && (managerMap[controlRow - 1][controlCol]).equals("*")) {
                mineCounter++;
            }

            // In diagonal positions; right - left - bottom - top mine controls
            if ((controlRow > 0) && (controlCol < (colNumber - 1)) && (managerMap[controlRow - 1][controlCol + 1]).equals("*")) {
                mineCounter++;
            }

            if ((controlCol > 0) && (controlRow > 0) && (managerMap[controlRow - 1][controlCol - 1]).equals("*")) {
                mineCounter++;
            }

            if ((controlCol > 0) && (controlRow < (rowNumber - 1)) && (managerMap[controlRow + 1][controlCol - 1]).equals("*")) {
                mineCounter++;
            }

            if ((controlRow < (rowNumber - 1)) && (controlCol < (colNumber - 1)) && (managerMap[controlRow + 1][controlCol + 1]).equals("*")) {
                mineCounter++;
            }

            playerMap[controlRow][controlCol] = String.valueOf(mineCounter); // It converts the numeric value into a String expression, we will need to replace this value with a "-" value.
        }
    }
}





