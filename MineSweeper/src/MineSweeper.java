import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    int rowNumber;
    int colNumber;
    int boardSize;
    int[][] managerMap;
    int[][] userMap;

    public MineSweeper(int rowNumber, int colNumber) {
        this.rowNumber = rowNumber;
        this.colNumber = colNumber;
        this.managerMap = new int[rowNumber][colNumber];
        this.userMap = new int[rowNumber][colNumber];
        this.boardSize = rowNumber * colNumber;
    }

    Random random = new Random();
    Scanner scanner = new Scanner(System.in);

    public void goPlay() {
        // Yeni konum satır ve sütün değişkenleri, tek seferde oyunu bitirebilme kontrolü için sayaç
        int goRow,goCol,clearChoice = 0;
        addMine();
        createBoardManager(managerMap);
        System.out.println("---------------");
        while(true){
            createBoardPlayer(userMap);
            // Gidilecek satır bilgisi
            System.out.println("ENTER ROW");
            goRow = scanner.nextInt();
            if(goRow >= rowNumber  || goRow < 0 ){
                System.out.println("INVALID VALUE, TRY AGAIN");
                continue;
            }
            // Gidilecek sütun bilgisi
            System.out.println("ENTER COL");
            goCol = scanner.nextInt();
            if(goCol >= colNumber || goCol < 0){
                System.out.println("INVALID VALUE, TRY AGAIN");
                continue;
            }
            // Mayın kontrolü
            checkMine(goRow,goCol);
            // Eğer girin konumda mayın yoksa
            if(managerMap[goRow][goCol] != -1){
                System.out.println("GOOD CHOICE");
                clearChoice++;
                // %75 alanı doğru ve temiz girerse (tek seferde oyunu bitirirse) koşulu
                if(clearChoice == boardSize - ((boardSize*25) / 100)){
                    System.out.println("CONGRATULATIONS, YOU WON!!!");
                    break;
                }
            }
            else{
                // Girilen konumda mayın varsa
                System.out.println("GAME OVER! BAD CHOICE");
                break;
            }
        }
    }
    // Mayınların gösterildiği harita
    public void createBoardManager(int[][] board) {
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
    // Oyuncuya gösterilecek harita
    public void createBoardPlayer(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(userMap[i][j] == 0){
                    System.out.print(" - ");
                }else{
                    System.out.print(" "+board[i][j]+" ");
                }
            }
            System.out.println();
        }
    }
    // %25 kadar mayın ekleme metodu
    public void addMine() {
        // Satır ve sütün için mayın değişkenleri, %25 kadar ataması için sayaç
        int rowMine, colMine, count = 0;
        while (count != ((boardSize * 25) / 100)) {
            // Verilen toplam satır ve sütun değeri kadar atanacak random mayın bilgisi
            rowMine = random.nextInt(rowNumber);
            colMine = random.nextInt(colNumber);

            if (managerMap[rowMine][colMine] != -1) {
                managerMap[rowMine][colMine] = -1;
                count++;
            }
        }
    }
    // Mayın kontrol metodu
    public void checkMine(int controlRow, int controlCol) {
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