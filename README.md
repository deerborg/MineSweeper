
![minesweeper](https://github.com/deerborg/MineSweeper/assets/152931069/eb72d0e0-3489-437e-ac20-1e363d89aaa2)
# MINESWEEPER

Patika+ için hazırlanan bitirme projesidir. 
---
    Kullanıcıdan alınan değerler ile oluşturulan oyun tablosu (matris), oyuncunun mayınları (*) seçmeden ilerleyeceği
    kazanma veya kaybetme senaryosu üzerine kurulmuş metin tabanlı oyundur. 

## METOTLAR
| Sıra | Metot  | Açıklama |
| :---:         |     :---:      |          :---  |
| 1   |   goPlay()| Oyunu başlatır    |
| 2   |   createBoardMap()   | managerMap değişkeni tanımlanırsa mayınları, playerMap değişkeni tanımlanırsa sade oyun alanını oluşturur    |
| 3   |   checkMineMap()   | Oyuncunun göreceği ve güncellenecek olan haritayı oluşturur, mayın sayısına göre konumlarında rakam güncellemesi yapar   |
| 4   |   addMine() | Rastgele konumlarda, haritanın alanının %25 kadar mayın (-1) oluşturur    |
| 5   |   controlMine()  | Girilen yeni konumda mayın işaretlemeleri için dikey ve çapraz sorgu yapar.     |



## PROJE ÖZET
| Sıra |  Açıklama |
| :---:         |:---     |     
| 1   |    2 X 2 ve daha büyük tahta oluşturulmalıdır, yoksa oyunu başlatmaz tekrar değer girmenizi ister    |
| 2   |    Oluşturulan oyun tahtası içerisinde konum girmenizi ister, girilen konum alandan büyük veya 0'dan küçük olamaz tekrar değer ister   |
| 3   |    Girilen yeni konum kaydedilir ve bir daha girilmemesi için koşul oluşturur    |
| 4   |    Girilen yeni konumda mayın (*) yok ise oyun sizden konum istemeye devam eder    |
| 5   |    Girilen konum çevresinde (çapraz ve dikey) mayın (*) var ise, mayın adedi kadar değeri oyun tahtasında yazar    |
| 6   |    Girilen konumda mayın (*) var ise döngü biter, kaybedersiniz    |
| 7   |    Girilen tüm konumların %75 kadarı temiz ise oyunu bitirir, kazanırsınız    |

## NOT
      
      Integer diziler üzerine oluşturulan proje; 
      
    * "-" konumların "0" olmama durumu 
    * Girilen değerlerin tekrar sorgulandığında, 3. bir geçmişi kayıt edememesi
    * Projede istenen puan değerlendirme durumunu tam anlamıyla karşılıyamaması
    
      Sebeplerden dolayı tamamen String dizilere çevrilip, baştan yazılmıştır.
      3 Mart 2024 02.35 
   
 ![Güncelleme](https://github.com/deerborg/MineSweeper/commit/21c2bfa68e89c8e00abc09614b9af59bf5d9464c)
    
    

## DEMO

![ezgif com-video-to-gif-converter](https://github.com/deerborg/MineSweeper/assets/152931069/381f21cb-6eb5-4503-9342-a8cbe4b1cdab)


  

  
