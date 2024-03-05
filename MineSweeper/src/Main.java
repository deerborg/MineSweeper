import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner userValue = new Scanner(System.in);
        int rowNumber, colNumber;

        // En az 2x2 boyutunda oyun alanı oluşturulabilir
        while (true) { // 7. Madde : Oyun alanının satır boyutu kullanıcı tarafından, 2' den büyük ve sayısal olma şartları sağlanır
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
        while (true) { // 7. Madde : Oyun alanının sütun boyutu kullanıcı tarafından, 2' den büyük ve sayısal olma şartları sağlanır
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

        MineSweeper gameCreate = new MineSweeper(rowNumber, colNumber); // 5. Madde : MineSweeper class'ı çağırılarak oyun başlatılabilir
        gameCreate.goPlay(); // Oyunu başlatan metot
    }
}
