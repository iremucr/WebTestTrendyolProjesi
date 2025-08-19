Feature: Trendyol Giriş Testi ve Ürün Ekleme


  @Login
  Scenario: Kullanıcı giriş yapar ve ilk ürünü sepete ekler
    Given "common@acceptCookies" tiklanir
    And "common@loginButton" tiklanir
    And "common@emailInput" tiklanir
    And "common@emailInput" alanına "iremflies35@gmail.com" yazılır
    And "common@passwordInput" tiklanir
    And "common@passwordInput" alanına "iremflies262+" yazılır
    And "common@submitButton" tiklanir
    Then "common@hesabimText" elementi gorunur
    And "common@searchInput" tiklanir
    And "common@searchInput" alanına "airpods" yazılır ve enter'a basılır
    And ilk ürün sepete eklenir
    And "common@cartButton" tiklanir
    Then sepette ürün var mı kontrol edilir
    And sepetteki ürün silinir
    Then toplam fiyatin dustugu dogrulanir


