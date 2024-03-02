
![minesweeper](https://github.com/deerborg/MineSweeper/assets/152931069/eb72d0e0-3489-437e-ac20-1e363d89aaa2)
# MINESWEEPER

Patika+ için hazırlanan bitirme projesidir. 
---
    Kullanıcıdan alınan değerler ile oluşturulan oyun tablosu (matris),
    oyuncunun mayınları (-1) seçmeden ilerleyeceği kazanma veya kaybetme senaryosu üzerine kurulmuş metin tabanlı oyundur. 

## METOTLAR
| Sıra | Metot  | Açıklama |
| :---:         |     :---:      |          :---  |
| 1   |   goPlay()| Oyunu başlatır    |
| 2   |   createBoardManager()   | Mayınların konumunu gösteren haritayı oluşturur (managerMap[ ][ ] kullanılmalıdır)    |
| 3   |   createBoardPlayer()   | Oyuncunun göreceği haritayı oluşturur (userMap [ ] [ ] kullanılmalıdır)   |
| 4   |   addMine() | Rastgele konumlarda, haritanın alanının %25 kadar mayın (-1) oluşturur    |
| 5   |   checkMine()  | Girilen yeni konumda mayın işaretlemeleri için dikey ve çapraz sorgu yapar.     |



## GENEL
| Sıra |  Açıklama |
| :---:         |:---     |     
| 1   |    2 X 2 ve daha büyük tahta oluşturulmalıdır, yoksa oyunu başlatmaz tekrar değer girmenizi ister    |
| 2   |    Oluşturulan oyun tahtası içerisinde konum girmenizi ister, girilen konum alandan büyük veya 0'dan küçük olamaz tekrar değer ister   |
| 3   |    Girilen yeni konum kaydedilir ve bir daha girilmemesi için koşul oluşturur    |
| 4   |    Girilen yeni konumda mayın (-1) yok ise oyun sizden konum istemeye devam eder    |
| 5   |    Girilen konum çevresinde (çapraz ve dikey) mayın (-1) var ise, mayın adedi kadar değeri oyun tahtasında yazar    |
| 6   |    Girilen konumda mayın (-1) var ise döngü biter, kaybedersiniz    |
| 7   |    Girilen konumun çervesinde mayın (-1) yoksa " - " olarak devam eder   |
| 8   |    Girilen tüm konumların %75 kadarı temiz ise oyunu bitirir, kazanırsınız    |



## DEMO

![ezgif com-video-to-gif-converter](https://github.com/deerborg/MineSweeper/assets/152931069/381f21cb-6eb5-4503-9342-a8cbe4b1cdab)


  

  
