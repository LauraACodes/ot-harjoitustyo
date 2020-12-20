# Arkkitehtuurikuvaus

## Rakenne
Ohjelman rakenne jakautuu kolmeen tasoon seuraavalla pakkausrakenteella.

![PackagesPic](https://github.com/LauraACodes/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/TetrisPackages.png)

*Tetris.ui* pakkauksesta löytyy ohjelman ja käyttöliittymän käynnistämiseen tarvittava luokat. 
*Tetris.controls* -pakkauksessa on kaikkia toimintoja, aikaa ja tietokantaa ohjaavat luokat.
*Tetris.scenecreators* -pakkauksessa on omat luokat pelin eri scenejen luontiin ja päivittämiseen.
*Tetris.blocksandmoves* -pakkaus vuorostaan sisältää pelin palikoiden luontiin ja liikuttamiseen tarvittavat luokat.

## Käyttöliittymä
Pelaajalle näkyy ohjelman käytön aikana kolme erilaista näkymää:

* StartScene - näkymä, jossa pelaaja kirjoittaa pelaajanimensä ja josta pelaaja voi nappia painamalla aloittaa pelaamisen tai siirtyä statistiikkanäkymään.
* GameScene - varsinainen pelinäkymä, jossa peliä pelataan nuolinäppäimillä.
* StatsScene - näkymä, josta pelaaja näkee mm. tuoreimman ja parhaimman tuloksensa.

Eri näkymät on toteutettu omina Scene-olioina, joista aina yksi on näkyvissä sovelluksen stagessa. Scene olioiden luomista ja rakentamista ohjataan controller-luokassa ja varsinainen rakentaminen tapahtuu tetris.scenecreators -pakkauksesta löytyvissä luokissa.

## Sovelluslogiikka

Sovelluksen toimintaa ohjaa controller-luokka, jonka konstruktorissa alustetaan niin tietokanta kuin useimmat ohjelman tarvitsemat luokat sekä ensimmäinen aloitusnäkymä. 
Tämän jälkeen controllerin avulla kuunnellaan pelaajan käskyjä nappien ja nuolinäppäinten kautta.
Nappien painallusten avulla pelaaja kertoo controllerille, mitä tetris.scenecreators pakkauksen luokkaa pitää kutsua oikean näkymän (peli tai tulos) näyttämiseen.
Nuolinäppäinten kuuntelu vuorostaan kertoo pelin aikana controllerille, mitä tetris.blocksandmoves pakkauksen luokkaa tarvitsee kutsua, jotta tetrisblokki pelilaudalla liikkuu oikein.
TetrisTimer määrää pelin nopeuden ja sen, että vauhti kiihtyy pelin edetessä. 
Pelaajan nimen ja pelitulosten tallentaminen tietokantaan ja tiedon hakeminen on eriytetty muusta logiikasta TetrisDao -luokalle.

tetris.scenecreators -pakkauksen creatorit vastaavat kukin tietyn näkymän luomisesta. Koska näkymissä koostuvat mm. toistuvista Text, Button ja VBox elementeistä, näiden elementtien luominen on eriytetty ElementGenerator-luokkaan. 
Creatoreissa rakennetaan sivun visuaaliset elementit ja tarvittaessa haetaan TetrisDaon avulla tietokannasta sisältöä elementteihin.

Blocksandmoves-pakkauksen block-luokka luo satunnaisesti pelilauden 7 erimuotoista palikkaa, joista kukin koostuu neljästä Rectangle elementistä. Moves vastaa palikoiden liikkumisesta pelilaudalla tarkistaen, että jokaisella palikan osalla on tilaa liikkua. Turns luokka vasta palikoiden (osien) pyörimisestä.

Kokonaisuudessaan sovelluksen luokkakaavio löytyy alta:

![taglespic](https://github.com/LauraACodes/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/TetrisClasses.png)

## Tietojen tallennus
*tetris.controls* -pakkauksesta löytyvä *TetrisDao* -luokka vastaa tietojen tallennukseen tarvittavan tietokannan luomisesta sekä tietojen tallentamisesta ja noutamisesta tietokannasta.

Alta löytyy tietokannan käyttämät taulut:

![taglespic](https://github.com/LauraACodes/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/DBTables.png)

## Päätoiminnallisuudet

Pelin aloitus 

![start game seq](https://github.com/LauraACodes/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/StartGameSeq.png)

Palikan liikuttaminen

![move rights eq](https://github.com/LauraACodes/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/MobeBlockRightSec.png)

## Ohjelman rakenteeseen jääneet heikkoudet

Käyttöliittymän osalta nyt ui-luokassa rakennetaan vain controller ja kutsutaan sitä kerran, jolloin controller luo ensimmäisen näkymän.
Tämän jälkeen kaikki muiden scenejen näyttö stagelle hoidetaan controller-luokassa. 
Mielestäni parempi tapa olisi ollut tehdä tämä (controllerin avulla luotujen) scenejen näyttö uissa ja lisäksi muutenkin vielä pohtia, josko controller -luokkaa olisi voinut jakaa useampiin. Yritin, mutta jokin aina kosahti. Eniten tämä kostautui testaamisessa. 


Nyt kun kirjoitti sekvenssikaavioita uusiksi huomasi pari pienempää hölmöyttä: esim. playerName haetaan nyt StartScenestä getterillä, sen olisi voinut luoda muuttujaksi, johon kaikki sitä tarvitsevat luokat pääsisi käsiksi. 

Turns-luokka jäi varsin pitkäksi (Yli 700 riviä). Silti koin, ettei luokan pilkkominen palvelisi asiaa, sillä kovin montaa toimintoa luokassa ei ole, ne vain tehdään 7 palikkatyypille kaikki erikseen.
