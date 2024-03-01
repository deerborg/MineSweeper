import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rowNumber,colNumber;
        // En az 2x2 boyutunda oyun alanı oluşturulabilir
        while (true){ // 7. Madde : Oyun alanının satır boyutu kullanıcı tarafından, 2 den küçük olmama şartı ile alınır
            System.out.println("ENTER HEIGHT: ");
            rowNumber = scanner.nextInt();
            if(rowNumber < 2){
                System.out.println("PLEASE ENTER A BIGGER THEN 2");
                continue;
            }
            break;
        }
        while (true){ // 7. Madde : Oyun alanının sütun boyutu kullanıcı tarafından, 2 den küçük olmama şartı ile alınır
            System.out.println("ENTER WIDTH: ");
            colNumber = scanner.nextInt();
            if(colNumber < 2){
                System.out.println("PLEASE ENTER A BIGGER THEN 2");
                continue;
            }
            break;
        }
        MineSweeper gameCreate = new MineSweeper(rowNumber,colNumber); // 5. Madde : MineSweeper class'ı çağırılarak oyun başlatılabilir
        gameCreate.goPlay();
    }
}
