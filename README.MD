# HititApiProject

Bu proje, Spring Boot kullanarak geliştirilen bir REST API uygulamasıdır. Uygulama, GitHub'dan en popüler 100 Apache repositorilerinden 5 tanesini, bunların bilgilerini ve bu repositorilere en fazla katkıda bulunanları, (bu kişilerin bilgileri) çekmekte ve veritabanına kaydetmektedir. Ayrıca, verilerin doğru bir şekilde çekilip kaydedildiğini test eden bir test sınıfı da içermektedir. Test sınıfı doğru çalışmaktadır fakat githubdan çekilen bazı verilerin boş olması nedeniyle testFetchTopRepositories ve testFetchTopContributors testlerinde nullPointerException hatası vermektedir.


## Proje Yapısı

1. **config:** RestTemplate için konfigürasyon sınıfı.
2. **controller:** API isteği alır, web üzerinden kullanılmak istenirse burası kullanılabilir, bunun yanında web sürümü her açıldığında terminalden printlemek gibi işlemler yapılabilir.
3. **model:** Veritabanı tablolarına karşılık gelen JPA varlık sınıflarını içerir.
4. **repository:** JPA repository arayüzlerini içerir ve veritabanı işlemlerini gerçekleştirir (ekstra querry olarak findByName eklenmiştir).
5. **service:** API'den veri çekme, verileri işleme ve veritabanına kaydetme işlemlerini yapan iş mantığı sınıfını içerir. Bunun yanında çekilen verilerin doğruluğunu kontrol etmek için bir print fonksiyonu içerir bu sayede veriler daha kolay kontrol edilebilir.
6. **test:** Uygulamanın işlevselliğini doğrulayan JUnit ve Mockito ile yazılmış test sınıflarını içerir.


## Teknolojiler

Java 11,
Spring Boot 2.7.0,
Spring Data JPA,
H2 Veritabanı,
JUnit 5,
Mockito,
Maven


## Web Kullanımı
Projeyi çalıştırdıktan sonra localhost/repositories isteği yollayabilirsiniz.

## H2 Veritabanı
* URL: http://localhost:8080/h2-console
* JDBC URL: jdbc:h2:mem:testdb
* Kullanıcı Adı: sa
* Şifre: password
