package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti != null);
    }

    @Test
    public void saldoAlussaOikein() {
        assertEquals(10, kortti.saldo());
    }

    @Test
    public void lataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(10);
        assertEquals(20, kortti.saldo());
    }

    @Test
    public void ottaminenVaheneeJosOnRahaa() {
        kortti.otaRahaa(5);
        assertEquals(5, kortti.saldo());
    }

    @Test
    public void saldoEiMuutuJosEiOleRahaa() {
        kortti.otaRahaa(15);
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void trueJosRahatRiittaa() {
        assertEquals(true, kortti.otaRahaa(5));
    }
    
    @Test
    public void falseJosRahatEiRiita() {
        assertEquals(false, kortti.otaRahaa(15));
    }
    
    @Test
    public void nayttaakoSaldonOikein() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
        

}
