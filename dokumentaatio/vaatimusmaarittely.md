# Vaatimusmaarittely

## Sovelluksen tarkoitus
Sovellus on perinteinen Tetris-peli.

## Käyttäjät
Pelissä on vain yhdentasoisia käyttäjiä - pelaajia. 

## Käyttöliittymä
Sovelluksen käyttöliittymässä on kolme sivua: Pelaajan valinta, varsinainen pelisivu ja tulossivu.

<img src="https://github.com/LauraACodes/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/kayttoliittyma1.jpg" width="500">

## Perusversion toiminnallisuus
* Pelaaja luo itselle uuden pelinimen.
* Pelaaja valitseen tallennetuista pelaajanimistä omansa.
* **TEHTY** - Peli alkaa tyhjältä pelilaudalta.
* **TEHTY OSIN** (yksi palikkatyyppi tehty) -Erilaiset palikat (ainakin 6 perusmallia) tippuvat ylhäältä tasaiseen tahtiin.
* **TEHTY** - Tippuvia palikoita pystyy siirtämään vaakatasossa nuolinäppäimillä.
* **TEHTY OSIN** - Tippuvia palikoita pysty pyörittämään nuolinäppäimillä.
* **TEHTY** - Tippuva palikka "kiinnittyy" pohjalle ja jos rivi on täysi, rivi katoaa.
* Palikan kiinnittyessä pistelaskuun lisätään palikasta saatu pistemäärä.
* Jos pitkällä palikalla saa pois neljä riviä, ruutuun ilmestyy teksti "Tetris"
* Tietyn palikkamäärän (?) jälkeen palikoiden tippumisvaihe kiihtyy.
* **TEHTY** - Kun palikat täyttävät koko ruudun eikä uusia palikoita enää mahdu, peli loppuu.
* Pelin loppuessa sovellus tallentaa kertyneen pistemäärän tilastotauluun, tarkistaa mille sijalle kertynyt pistemäärä riittää.
* Sovellus näyttää pelaajan pistemäärän sekä sijoituksen ranking-listalla.

## Jatkokehitysideoita
* Palikkatyyppejä voisi olla muutakin kuin peruspalikat.
* Tilastotietoa voisi kerätä eri pelaajien kaikkien pistemäärien summasta, pelattujen pelien määrästä.
* Peliin voisi tuoda äänet, taustamusiikkia, eri ääniä kun rivi katoaa tai tetris tulee.


