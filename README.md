Gazi ATM Yönetim Sistemi

Bu proje, Java Swing ve MySQL (JDBC) kullanarak basit bir ATM yönetim sistemi simülasyonu sunar. Aşağıda adım adım kurulum, çalışma ve dağıtım talimatları yer almaktadır.

İçindekiler

Proje Özeti

Gereksinimler

Veritabanı Kurulumu

Proje Yapısı

Ortam Değişkenleri

Derleme ve Çalıştırma

Görseller ve Kaynak Dosyalar

Hata Ayıklama ve İpuçları

SSS

1. Proje Özeti

Bu uygulama; kullanıcı kayıt, giriş, para yatırma/çekme, hızlı nakit, bakiye sorgulama, mini ekstre görüntüleme ve PIN değiştirme gibi temel ATM işlevlerini masaüstü ortamında simüle eder.

2. Gereksinimler

Java SE Development Kit (JDK) 11 veya üzeri

Eclipse IDE for Java Developers (veya tercihinize göre IntelliJ IDEA)

MySQL Server 8.x

MySQL Connector/J (JDBC sürücüsü)

com.toedter:jcalendar (JDateChooser için)

3. Veritabanı Kurulumu

MySQL sunucusuna bağlanın:

mysql -u root -p

Proje kök dizininde yer alan mysqlQueries.sql dosyasını çalıştırın:

SOURCE mysqlQueries.sql;

Bu dosya veritabanı ATM’yi oluşturur ve tüm tabloları (signup, signuptwo, signupthree, login, bank) tanımlar.

4. Proje Yapısı. Proje Yapısı

GaziATM/
├─ src/
│  ├─ LoginPage.java
│  ├─ SignUp1.java
│  ├─ SignUp2.java
│  ├─ SignUp3.java
│  ├─ PinChange.java
│  ├─ Transaction.java
│  ├─ deposit.java
│  ├─ withdraw.java
│  ├─ FastCash.java
│  ├─ BalanceEnq.java
│  ├─ MiniStatement.java
│  └─ mysql.java         // JDBC bağlantı helper
├─ resources/
│  └─ images/           // UI ikon ve resimleri
├─ lib/
│  ├─ mysql-connector-java.jar
│  └─ jcalendar-1.4.jar
├─ atm.sql              // Veritabanı scripti
└─ README.md            // Bu dosya

5. Ortam Değişkenleri

JDBC URL: jdbc:mysql://localhost:3306/ATM (mysql.java içinde)

MySQL Kullanıcı: root (veya sizin kullandığınız kullanıcı)

MySQL Şifre: (şifreyi mysql.java içinde doğru ayarlayın)

6. Derleme ve Çalıştırma

Eclipse’te proje olarak içe aktarın (Import → Existing Projects into Workspace).

lib/ klasöründeki mysql-connector-java.jar ve jcalendar-1.4.jar dosyalarını projenizin Build Path’e ekleyin.

Projeyi Project → Clean ile temizleyin.

LoginPage.java içinde sağ tıklayıp Run As → Java Application ile başlatın.

Kullanım

Uygulamayı çalıştırmak için aşağıdaki adımları izleyin:

Derleme ve çalıştırma adımlarını tamamlayın (bkz. Bölüm 6).

Eğer Runnable JAR oluşturduysanız, proje dizininde oluşan GaziATM.jar dosyasını terminal veya komut istemcisinden şu komutla çalıştırabilirsiniz:

java -jar GaziATM.jar

Uygulama başlatıldığında, LoginPage ekranı görünecektir. Burada kart numarası ve PIN ile oturum açabilirsiniz.

Kayıtlı değilse “KAYIT OL” seçeneği ile yeni kullanıcı kaydı oluşturabilirsiniz.

7. Hata Ayıklama ve İpuçları. Hata Ayıklama ve İpuçları

Veritabanı Bağlantı Hatası: Communications link failure → MySQL servisini başlatın veya URL/şifreyi kontrol edin.

ClassNotFoundException: JDBC/JCalendar JAR’larının Build Path’te olduğundan emin olun.

GUI Çakışmaları: repaint() veya validate() çağırın, setVisible(true)’yi add(...) sonrası yapın.

8. SSS

Yeni tablo eklemek istiyorum, ne yapmalıyım?mysqlQueries.sql dosyasına CREATE TABLE komutunu ekleyip SOURCE mysqlQueries.sql; ile yeniden yükleyin.

Uygulamadaki değişiklikler kayboluyor mu?Hayır; tüm veriler MySQL veritabanında saklanır, uygulamayı yeniden başlatsanız da kalıcıdır.

JCalendar görünmüyor.jcalendar-1.4.jar’ın projeye doğru eklendiğinden ve IDE’yi yeniden başlattığınızdan emin olun.

IDE dışında çalıştırmak istiyorum.Terminalden java -cp lib/*:bin GirişSınıfı komutuyla ana sınıfı çalıştırabilirsiniz.


Son Güncelleme: Mayıs 2025

