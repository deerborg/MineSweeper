import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner userValue = new Scanner(System.in);
        int rowNumber, colNumber;

        // Can create a playground of at least 2x2 size
        while (true) { // Section - 7 : The row size of the playing field must be greater than 2 and must be a numerical value.
            System.out.print("ENTER HEIGHT: ");
            if (userValue.hasNextInt()) {
                rowNumber = userValue.nextInt();
                if (rowNumber < 2) {
                    System.out.println("PLEASE ENTER A BIGGER THEN 2");
                    continue;
                }
                break;
            } else {
                System.out.println("PLEASE ENTER A INTEGER VALUE");
                userValue.next();
            }

        }
        while (true) { // Section - 7 : The row size of the playing field must be greater than 2 and must be a numerical value.
            System.out.print("ENTER WIDTH: ");
            if (userValue.hasNextInt()) {
                colNumber = userValue.nextInt();
                if (colNumber < 2) {
                    System.out.println("PLEASE ENTER A BIGGER THEN 2");
                    continue;
                }
                break;
            } else {
                System.out.println("PLEASE ENTER A INTEGER VALUE");
                userValue.next();
            }
        }

        MineSweeper gameCreate = new MineSweeper(rowNumber, colNumber); // Section - 5 : The game is started by calling the MineSweeper class.
        gameCreate.goPlay(); // Section - 6: The method that starts the game.
    }
}
