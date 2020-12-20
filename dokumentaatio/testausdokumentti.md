# Testausdokumentti

Testauksessa keskityttiin pelaamisella huomioitaviin virheisiin sekä yksikkötestauksiin etenkin blocksandmoves.-paketin luokissa sekä TetrisDao -tietokantaluokassa.

Testaamatta jäi SceneCreatorit sekä suureksi harmiksi Controller-luokka.  Puhtaasti siitä syystä etten osannut: Luokka linkittyy niin vahvasti stageen ja eri sceneihin, enkä saanut teisteissä edes luotoa koko controlleria. 

![Jacoco](https://github.com/LauraACodes/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Jacoco1.png)

Pieni yksityiskohta: testit sisältävä pakkaus ei antanut nimetä itseään uudelleen, siksi "default package". 
