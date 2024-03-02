/**
 * @author Furkan Aydemir, deerborg
 * @since 2024
 */

import java.util.Random;
import java.util.Scanner;
public class MineSweeper {
    int rowNumber;
    int colNumber;
    int boardSize;
    int[][] managerMap;
    int[][] userMap;
    public MineSweeper(int rowNumber, int colNumber) { // 5. Madde : MineSweeper kurucu metotdu içerisinde değişkenler tanımlandı
        this.rowNumber = rowNumber;
        this.colNumber = colNumber;
        this.managerMap = new int[rowNumber][colNumber];
        this.userMap = new int[rowNumber][colNumber];
        this.boardSize = rowNumber * colNumber;
    }
    Random giveMine = new Random();
    Scanner userValue = new Scanner(System.in);
    public void goPlay() { // 6. Madde : Oyun başlatma metodu

        int goRow , goCol,clearChoice = 0,checkAgainRow = -1,chekAgainCol = -1;


        addMine();
        createBoardManager(managerMap); // Mayınlı haritanın çıktısı
        System.out.println("---------------");
        while(true){
            createBoardPlayer(userMap); // 11. Madde : Kullanıcının her hamlesinde haritayı günceller

            System.out.println("ENTER ROW: ");
            goRow = userValue.nextInt(); // 9. Madde : Kullanıcıdan alınacak olan işaretleme bilgisi

            if(goRow >= rowNumber  || goRow < 0){  // 10. Madde : Girilen satır bilgisinin toplam dizi alanı dışında olma durumu
                System.out.println("INVALID VALUE, TRY AGAIN");
                continue;
            }

            System.out.println("ENTER COL: ");
            goCol = userValue.nextInt(); // 9. Madde : Kullanıcıdan alınacak olan işaretleme bilgisi

            if(goCol >= colNumber || goCol < 0){ // 10. Madde : Girilen sütun bilgisinin toplam dizi alanı dışında olma durumu
                System.out.println("INVALID VALUE, TRY AGAIN");
                continue;
            }

            if (checkAgainRow == goRow && chekAgainCol == goCol) { // Aynı değerleri tur içinde girerse tekrar değer girmesini ister
                System.out.println("SAME VALUE, TRY AGAIN");
                continue;
            }

            chekAgainCol = goCol;
            checkAgainRow = goRow;

            if(userMap[goRow][goCol] > 0){ // Daha önceden çervesinde mayın olan bir konumu açarsa ve tekrar aynı konumu girerse, tekrar değer girmesini ister
                System.out.println("SAME VALUE ! REPEAT SELECTION MAY BREAK GAME MECHANICS!\nGO ENTER NEW VALUE");
                continue;
            }
            // Mayın kontrolü
            checkMine(goRow,goCol);

            if(managerMap[goRow][goCol] != -1){
                System.out.println("GOOD CHOICE");
                clearChoice++;
                if(clearChoice == boardSize - ((boardSize*25) / 100)){  // 14. Madde: %75 alanı doğru ve temiz girerse (tek seferde oyunu bitirirse) koşul bloğu
                    System.out.println("CONGRATULATIONS, YOU WON!!!");
                    userValue.close();
                    break;
                }
            }
            else{ // 13. Madde : Seçilen konumda mayın (-1) varsa kaybedeceği koşul bloğu
                System.out.println("GAME OVER! BAD CHOICE");
                userValue.close();
                break;
            }
        }
    }
    public void createBoardManager(int[][] board) { // 6. Madde : Mayınların ekleneceği ve gösterilecek olan (managerMap) metod
        for (int[] rowValue : board) {
            for (int colValue : rowValue) {
                if (colValue != -1) {
                    System.out.print(" - ");
                } else {
                    System.out.print(" * ");
                }
            }
            System.out.println();
        }
    }
    public void createBoardPlayer(int[][] board) { // 6. Madde : Mayınların gösterilmediği, oyuncunun ilerleyeceği ve gösterilecek olan (userMap) metod
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(userMap[i][j] == 0){
                    System.out.print(" - "); // 12. Madde : Etrafında mayın yoksa " - " olarak gözükür.
                }else{
                    System.out.print(" "+board[i][j]+" "); // 12. Madde : Kontrol edilirken çevresindeki mayınlara göre değeri yazar
                }
            }
            System.out.println();
        }
    }
    public void addMine() { // 6. Madde ve 8. Madde : Alanın 4/1 kadar rastgele mayın ekleme metodu

        int rowMine, colMine, count = 0;
        while (count != ((boardSize * 25) / 100)) {
            rowMine = giveMine.nextInt(rowNumber);
            colMine = giveMine.nextInt(colNumber);
            if (managerMap[rowMine][colMine] != -1) {
                managerMap[rowMine][colMine] = -1;
                count++;
            }
        }
    }
    public void checkMine(int controlRow, int controlCol) { // 6. Madde ve 12. Madde : Oyun sırasında verilen konumun dikey olarak sağ, sol, alt, üst ve çapraz olarak sağ, sol, alt, üst konumların kontrolü metodu
        // Mayın olmama koşulu
        if (managerMap[controlRow][controlCol] == 0) {
            // Dikey konumlarda sağ, sol, alt, üst konumların mayın kontrolü
            if ((controlCol < (colNumber - 1)) && (managerMap[controlRow][controlCol + 1]) == -1) {
                userMap[controlRow][controlCol]++;
            }
            if ((controlRow < (rowNumber - 1)) && (managerMap[controlRow + 1][controlCol]) == -1) {
                userMap[controlRow][controlCol]++;
            }
            if ((controlCol > 0) && (managerMap[controlRow][controlCol - 1]) == -1) {
                userMap[controlRow][controlCol]++;
            }
            if ((controlRow > 0) && (managerMap[controlRow - 1][controlCol]) == -1) {
                userMap[controlRow][controlCol]++;
            }
            // Çapraz konumlarda sağ, sol, alt, üst konumların mayın kontrolü
            if ((controlRow > 0) && (controlCol < (colNumber - 1)) && (managerMap[controlRow - 1][controlCol + 1]) == -1) {
                userMap[controlRow][controlCol]++;
            }
            if ((controlCol > 0) && (controlRow > 0) && (managerMap[controlRow - 1][controlCol - 1]) == -1) {
                userMap[controlRow][controlCol]++;
            }
            if ((controlCol > 0) && (controlRow < (rowNumber - 1)) && (managerMap[controlRow + 1][controlCol - 1]) == -1) {
                userMap[controlRow][controlCol]++;
            }
            if ((controlRow < (rowNumber - 1)) && (controlCol < (colNumber - 1)) && (managerMap[controlRow + 1][controlCol + 1]) == -1) {
                userMap[controlRow][controlCol]++;
            }
        }
    }
}