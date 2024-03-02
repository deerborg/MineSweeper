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

    // 5. Madde : MineSweeper kurucu metodu içerisinde değişkenler tanımlandı
    public MineSweeper(int rowValue, int colValue) {
        this.managerMap = new String[rowValue][colValue];
        this.playerMap = new String[rowValue][colValue];
        this.rowNumber = rowValue;
        this.colNumber = colValue;
        this.boardSize = rowValue * colValue;
    }

    Random mineValue = new Random();
    Scanner userValue = new Scanner(System.in);

    // 6. Madde : Oyun başlatma metodu
    public void goPlay() {

        int goRow, goCol, clearChoice = 0, checkAgainRow = -1, chekAgainCol = -1;

        addMines();
        createBoardMap(managerMap); // Tek seferlik Mayınların gösterildiği harita
        System.out.println("==================");
        createBoardMap(playerMap); // Tek seferlik kullanıcıya gösterilecek harita

        while (true) {

            System.out.print("ENTER ROW: ");
            goRow = userValue.nextInt();  // 9. Madde : Kullanıcıdan alınacak olan işaretleme bilgisi

            if (goRow < 0 || goRow > rowNumber - 1) {  // 10. Madde : Girilen sütun bilgisinin toplam dizi alanı dışında olma durumu
                System.out.println("INVALID VALUE, TRY AGAIN");
                continue;
            }

            System.out.print("ENTER COL: ");
            goCol = userValue.nextInt();  // 9. Madde : Kullanıcıdan alınacak olan işaretleme bilgisi

            if (goCol < 0 || goCol > rowNumber - 1) {  // 10. Madde : Girilen sütun bilgisinin toplam dizi alanı dışında olma durumu
                System.out.println("INVALID VALUE, TRY AGAIN");
                continue;
            }
            if (chekAgainCol == goCol && checkAgainRow == goRow) { // Aynı değerleri tur içinde girerse tekrar değer girmesini ister
                System.out.println("YOU HAVE ENTERED REPEATED VALUES, ENTER DIFFERENT VALUES");
                continue;
            }

            chekAgainCol = goCol;
            checkAgainRow = goRow;

            if (!playerMap[goRow][goCol].equals("-")) { // Daha önceden girdiği konumu tekrar girerse tekrar değer girmesini ister
                System.out.println("YOU ENTERED THE SAME LOCATIONS!");
                continue;
            }


            controlMine(goRow, goCol);
            if (managerMap[goRow][goCol].equals("*")) { // 13. Madde : Seçilen konumda mayın var ise oyunun sonlanacağı blok
                System.out.println("ITS MINE... BOOOM! GAME OVER!");

                break;
            } else {

                checkMineMap(goRow, goCol); // 11. Madde : Kullanıcının her hamlesinde haritayı günceller ve mayın kontrollerini burada gösterir

                mineCounter = 0;

                System.out.println("GOOD CHOICE");
                clearChoice++;

                if (clearChoice == boardSize - ((boardSize * 25) / 100)) { // 14. Madde : %75 alanı doğru ve temiz girerse (tek seferde oyunu bitirirse) koşul bloğu
                    System.out.println("CONGRATULATIONS, YOU WON!!!");
                    userValue.close();
                    break;
                }
            }
        }
    }

    // 6. Madde : Mayınların gösterildiği ve mayınsız gösterilecek tek seferlik haritaları oluşturan metot
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

    // 6. Madde ve 8. Madde : Alanın 4/1 kadar rastgele mayın ekleme metodu
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

    // 6. Madde : Mayınların gösterilmediği, oyuncunun ilerleyeceği ve güncellenecek harita metodu
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

    // 6. Madde ve 12. Madde : Oyun sırasında verilen konumun dikey olarak sağ, sol, alt, üst ve çapraz olarak sağ, sol, alt, üst konumların kontrolü metodu
    public void controlMine(int controlRow, int controlCol) {


        if (managerMap[controlRow][controlCol].equals("-")) {

            // Dikey konumlarda sağ, sol, alt, üst konumların mayın kontrolü
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

            // Çapraz konumlarda sağ, sol, alt, üst konumların mayın kontrolü
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

            playerMap[controlRow][controlCol] = String.valueOf(mineCounter);
        }
    }
}





