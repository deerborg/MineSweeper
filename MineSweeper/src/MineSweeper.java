import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MineSweeper {

   int rowNumber,colNumber; // Kullanıcıdan alacağımız satır ve sütun bilgisi
   int boardSize; // %25 mayın eklemek için oyun alanı değişkeni
   int[][] adminBoard; // Mayınların nereye eklendiğini gösterecek olan dizi
   int[][] userBoard;  // Kullanıcının göreceği dizi, sadece "-" değerler görecek
   boolean gameIsContinue = true; // Kullanıcının mayına basana kadar ki devam edeceği durumu döngü içinde kullanır, false durumunda  kullanıcı mayına bastığı için oyun bitecektir
   
   Random random = new Random(); // Rastgele mayın atamaları için kullanılacak
   Scanner scanner = new Scanner(System.in); // Kullanıcıdan satır sutun bilgileri almak için kullanılacak

    MineSweeper(int rowNumber, int colNumber) {
        
        this.rowNumber = rowNumber;
        this.colNumber = colNumber;
        this.adminBoard = new int[colNumber][rowNumber];
        this.userBoard = new int[colNumber][rowNumber];
        this.boardSize = (rowNumber * colNumber);
    }
    // Oyunu çalıştıracak olan metot
    public void goPlay(){
        int isSuccess = 0,goRow,goCol; // Kullanıcıdan gideceği konumu eşitleyeceğimiz yeni satır ve sütün değişkenleri
        
        
        createMine();
        crateBoard(adminBoard); // mayınların yerini gösteren, adminin göreceği tablo.
        
        System.out.println("Game Ready");
        while(gameIsContinue){
            
            crateBoard(userBoard); // mayınları göstermeyen, oyuncunun göreceği tablo
            
            
            System.out.println("The row you want to go to : ");
            goRow = scanner.nextInt(); // yeni satır konumu bilgisi
            
            
            // Oluşturulan tablonun satır ve sütünündan fazla değer girerse
            if(goRow >= rowNumber  || goRow < 0 ){
                System.out.println("ERROR");
                continue;
            }
            
            
            System.out.println("The col you want to go to : ");
            goCol = scanner.nextInt(); // yeni sütun konumu bilgisi
            

            
            // Oluşturulan tablonun satır ve sütünündan fazla değer girerse
            if(goCol >= colNumber || goCol < 0){
                System.out.println("ERROR");
                continue;
            }
            // Daha önceden girdiği satır ve sütunu kontol eder
            
            if(adminBoard[goRow][goCol] != -1){ // Verdiği değerler mayına denk gelmiyorsa koşul bloğu

                controlMine(goRow,goCol);
                System.out.println();
                System.out.println("Good choice");
                isSuccess++;
                if(isSuccess == boardSize - ((boardSize * 25) / 100)){
                    System.out.println();
                    System.out.println("TEK ATTİN AQ");
                    gameIsContinue = false;
                }
            }
            else{
                System.out.println();
                System.out.println("BOOOOM!!");
                System.out.println("Game over");
                gameIsContinue = false;
            }
        } 
    }
    // Mayınları eklediğimiz metot
    public void createMine(){
        
        int mineRow,mineCol; // mayınların ekleneceği satır ve sütun değişkenleri
        int count = 0; // %25 kadar mayın ekleyeğimiz için while içinde %25 geçmemesi amaçlı counter
        
        while(count != ((boardSize * 25) / 100 )){ // 0.25 ile çarpmak double değer olduğu için, 5x5 boyutunda tabloda hata alır
            
            mineRow = random.nextInt(rowNumber); // satır içinde rastgele indisleri belirler
            mineCol = random.nextInt(colNumber); // sütun içinde rastgele indisleri belirler
            
            if(adminBoard[mineRow][mineCol] != -1){ // Rastgele mayınlar gene aynı indis içinde değilse koşula girer
                adminBoard[mineRow][mineCol] = -1;  // Koşula girerse mayınları -1 olarak belirler.
                count++;                            // Koşul %25 adet olana kadar counteri arttırır. 
            }            
        }
    }
    // Oyun tablosunun çıktısı - ADMİN
    public void crateBoard(int[][] board){
        
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j] != -1){
                    System.out.print(board[i][j] + " ");
                } 
                else{
                    System.out.print(" x ");
                }
            }
            System.out.println();
        }
    }
  
    
    // Mayınları kontrol eden metot
    public void controlMine(int controlRow, int controlCol){
        
        // Girilen konumun etrafındaki mayınları kontrol eden koşul blokları
        // controlRow ve  controlCol : goPlay() metodu içindeki, kullanıcıdan aldığımız kordinatlardır.
        if(adminBoard[controlRow][controlCol] == 0){
            
        // Girilen konumda koşulların sağladığı konumlar ise, etrafındaki mayınların sayısına göre arttırır, default olarak mayınsız bölgeler 0'dır.
        
        // Girilen konumlar
        if((controlCol < (colNumber - 1)) && (adminBoard[controlRow][controlCol + 1]) == -1){ 
            userBoard[controlRow][controlCol]++;
        }
        if((controlRow < (rowNumber - 1)) && (adminBoard[controlRow + 1][controlCol]) == -1){ 
            userBoard[controlRow][controlCol]++;
        }
        if((controlCol > 0) &&(adminBoard[controlRow][controlCol - 1]) == -1){ 
            userBoard[controlRow][controlCol]++;
        }
        if((controlRow > 0) && (adminBoard[controlRow - 1][controlCol]) == -1){ 
            userBoard[controlRow][controlCol]++;
        }
    } 
  }
}    