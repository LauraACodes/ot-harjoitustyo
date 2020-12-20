# Vaatimusmaarittely

## Sovelluksen tarkoitus
Sovellus on perinteinen Tetris-peli.

## Käyttäjät
Pelissä on vain yhdentasoisia käyttäjiä - pelaajia. 

## Käyttöliittymä
Sovelluksen käyttöliittymässä on kolme sivua: Pelaajan valinta, varsinainen pelisivu ja tulossivu.

<img src="https://github.com/LauraACodes/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/kayttoliittyma1.jpg" width="500">

## Perusversion toiminnallisuus
* Kolme sceneä - startti, peli, loppu.
* Pelaaja luo itselle pelinimen. 
* Jos pelaaja ei anna pelinimeä, pelaa hän "Anonymous":ksena ja tulos tallentuu tietokantaan "Anonymous" nimimerkin alle.
* Peli alkaa tyhjältä pelilaudalta.
* Erilaiset palikat (7 perusmallia) tippuvat ylhäältä tasaiseen tahtiin.
* Tippuvia palikoita pystyy siirtämään vaakatasossa nuolinäppäimillä.
* Tippuvia palikoita pysty pyörittämään ylös-nuolinäppäimellä.
* Tippuva palikka "kiinnittyy" pohjalle ja jos rivi on täysi, rivi katoaa.
* Palikan pudotessa pistelaskuun lisätään palikasta saatu pistemäärä.
* Jos pitkällä palikalla saa pois neljä riviä, ruutuun ilmestyy teksti "Tetris"
* Vauhti kiihtyy pikkuhiljaa pelin edetessä.
* Kun palikat täyttävät koko ruudun eikä uusia palikoita enää mahdu, peli loppuu.
* Pelin loppuessa sovellus tallentaa kertyneen pistemäärän tietokantaan sekä tarkistaa ja kertoo mille sijalle kertynyt pistemäärä riittää.
* Starttisivulta tai pelin päättysessä nappia painamalla pelaaja pääsee siirtymään statistiikkasivulle.
* Statistiikkasivulla näytetään pelaajan uusin tulos ja sijoitus, parast tulos ja sijoitus sekä pelattujen pelien määrä. 

## Jatkokehitysideoita
* Palikkatyyppejä voisi olla muutakin kuin peruspalikat.
* Tilastotietoa voisi kerätä laajemminkin, esim. kaikkien tuhottuen rivien summa.
* Peliin voisi tuoda äänet, taustamusiikkia, eri ääniä kun rivi katoaa tai tetris tulee.
* Pelaajan tunnus voisi vaatia kirjautumisen salasanoineen. 



