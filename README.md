## Laura'sSuperTetris

Ohjelma on käyttäjän omalla koneella toimiva perinteinen tetris-peli.
Palikoiden liikkeitä ohjataan nuolinäppäimillä.
Pelissä nimimerkin tulokset tallennetaan tietokantaan. 
Pelaaja pysyy esim. kärryillä kuinka monta peliä onkaan tullut pelattua.

### LauranSuperTetris

[Käyttöohje](https://github.com/LauraACodes/ot-harjoitustyo/tree/master/dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/LauraACodes/ot-harjoitustyo/tree/master/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuuri](https://github.com/LauraACodes/ot-harjoitustyo/tree/master/dokumentaatio/arkkitehtuuri.md)

[Testausdokumentti](https://github.com/LauraACodes/ot-harjoitustyo/tree/master/dokumentaatio/testausdokumentti.md)

[Tuntikirjanpito](https://github.com/LauraACodes/ot-harjoitustyo/tree/master/dokumentaatio/tuntikirjanpito.md)


## Release

[LaurasSuperTetris - Release](https://github.com/LauraACodes/ot-harjoitustyo/releases/tag/tetrisFinal)

## Komentorivitoiminnot

## Testaus

Pelin testit saa suoritettua komennolla: 

mvn test

Testikattavuusraportin saa luotua:

mvn jacoco:report

## Apua ymmärrykseen

JavaDocin pystyy generoimaan komennolla

mvn javadoc:javadoc

## Tyyliseikat

Alta löytyvällä komennolla saa generoitua checkstyle-raportin, josta löytyy vain 8 huomautusta!

mvn jxt:jxr checkstyle:checkstyle

Huomautukset pääsee generoinnin jälkeen lukemaan checkstyle.html -tiedostosta joka löytyy kansiosta target/site.

