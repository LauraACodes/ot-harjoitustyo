
import com.mycompany.unicafe.Kassapaate;
import com.mycompany.unicafe.Maksukortti;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {
    
    Kassapaate kassapaate;
    Maksukortti maksukortti;
    
    @Before
    public void setUp() {
        this.kassapaate = new Kassapaate();
        this.maksukortti = new Maksukortti(500);
    }
    
    @Test
    public void rahamaaraOikeaAlussa() {
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void edullistenLounaidenMaaraOikeaAlussa() {
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaidenLounaidenMaaraOikeaAlussa() {
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kassaKasvaaOikeinEdullisesta() {
        kassapaate.syoEdullisesti(240);
        assertEquals(100240, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void kassaKasvaaOikeinMaukkaasta() {
        kassapaate.syoMaukkaasti(400);
        assertEquals(100400, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void vaihtorahaOikeinEdullisesta() {
        assertEquals(60, kassapaate.syoEdullisesti(300));
    }
    
    @Test
    public void vaihtorahaOikeinMaukkaasta() {
        assertEquals(100, kassapaate.syoMaukkaasti(500));
    }
    
    @Test
    public void maaraKasvaaEdullisesta() {
        kassapaate.syoEdullisesti(240);
        kassapaate.syoEdullisesti(300);
        kassapaate.syoEdullisesti(100);
        assertEquals(2, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maaraKasvaaMaukkaasta() {
        kassapaate.syoMaukkaasti(400);
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kassaEiKasvaJosEdullisenRahatEiRiita() {
        kassapaate.syoEdullisesti(100);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void kassaEiKasvaJosMaukkaanRahatEiRiita() {
        kassapaate.syoMaukkaasti(300);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void maaraEiKasvaJosEdullisenRahatEiRiita() {
        kassapaate.syoEdullisesti(100);
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maaraEiKasvaJosMaukkaanRahatEiRiita() {
        kassapaate.syoMaukkaasti(300);
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void vaihtorahaOikeinJosEdullisenRahatEiRiita() {
        assertEquals(100, kassapaate.syoEdullisesti(100));
    }
    
    @Test
    public void vaihtorahaOikeinJosMaukkaaseenRahatEiRiita() {
        assertEquals(100, kassapaate.syoMaukkaasti(100));
    }
    
    @Test
    public void edullisenOstoKortillaOnnistuu() {
        assertEquals(true, kassapaate.syoEdullisesti(maksukortti));
    }
    
    @Test
    public void maukkaanOstoKortillaOnnistuu() {
        assertEquals(true, kassapaate.syoMaukkaasti(maksukortti));
    }
    
    @Test
    public void edullisenOstoKortillaKasvattaaMaaraa() {
        kassapaate.syoEdullisesti(maksukortti);
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaanOstoKortillaKasvattaaMaaraa() {
        kassapaate.syoMaukkaasti(maksukortti);
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void rahamaaraEiMuutuJosKortillaEiRahaa() {
        maksukortti.otaRahaa(400);
        kassapaate.syoEdullisesti(maksukortti);
        kassapaate.syoMaukkaasti(maksukortti);
        assertEquals(100, maksukortti.saldo());
    }
    
    @Test
    public void edullistenMaaraEiMuutuJosKortillaEiRahaa() {
        maksukortti.otaRahaa(400);
        kassapaate.syoEdullisesti(maksukortti);
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaidenMaaraEiMuutuJosKortillaEiRahaa() {
        maksukortti.otaRahaa(400);
        kassapaate.syoMaukkaasti(maksukortti);
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullinenPalauttaaFalseJosKortillaEiRahaa() {
        maksukortti.otaRahaa(400);
        assertEquals(false, kassapaate.syoEdullisesti(maksukortti));
    }
    
    @Test
    public void maukkaastiPalauttaaFalseJosKortillaEiRahaa() {
        maksukortti.otaRahaa(400);
        assertEquals(false, kassapaate.syoMaukkaasti(maksukortti));
    }
    
    @Test
    public void kassaEiMuutuJosMaksaaKortillaEdullisesti() {
        kassapaate.syoEdullisesti(maksukortti);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void kassaEiMuutuJosMaksaaKortillaMaukkaasti() {
        kassapaate.syoMaukkaasti(maksukortti);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void kortilleLadatessaKortinSaldoMuuttuu() {
        kassapaate.lataaRahaaKortille(maksukortti, 500);
        assertEquals(1000, maksukortti.saldo());
    }
    
    @Test
    public void kortilleLadatessaKassaMuuttuu() {
        kassapaate.lataaRahaaKortille(maksukortti, 500);
        assertEquals(100500, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void eiLataaKortilleNegatiivistaSummaa() {
        kassapaate.lataaRahaaKortille(maksukortti, -100);
        assertEquals(500, maksukortti.saldo());
    }
}
