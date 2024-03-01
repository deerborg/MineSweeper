import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int rowNumber,colNumber;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Create a Board");

        System.out.println("Enter widht: ");
        rowNumber = scanner.nextInt();

        System.out.println("Enter height: ");
        colNumber = scanner.nextInt();

        MineSweeper gameCreate = new MineSweeper(rowNumber,colNumber);
        gameCreate.goPlay();
    }
}
