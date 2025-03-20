package no.dat102.oppg4; // tester for alle klassene

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MengdeADTTest {
	
    private MengdeADT<String> mengde;

    @BeforeEach
    public void setup() {
        mengde = new TabellMengde<>();
    }

    @Test
    public void testErTom() {
        assertTrue(mengde.erTom());
        mengde.leggTil("a");
        assertFalse(mengde.erTom());
    }

    @Test
    public void testInneholder() {
        mengde.leggTil("a");
        mengde.leggTil("b");
        assertTrue(mengde.inneholder("a"));
        assertFalse(mengde.inneholder("c"));
    }

    @Test
    public void testErDelmengdeAv() {
        MengdeADT<String> annenMengde = new TabellMengde<>();
        annenMengde.leggTil("a");
        annenMengde.leggTil("b");
        mengde.leggTil("a");

        assertTrue(mengde.erDelmengdeAv(annenMengde));
        annenMengde.fjern("a");
        assertFalse(mengde.erDelmengdeAv(annenMengde));
    }

    @Test
    public void testErLik() {
        MengdeADT<String> mengde2 = new TabellMengde<>();
        mengde.leggTil("a");
        mengde.leggTil("b");
        mengde2.leggTil("b");
        mengde2.leggTil("a");
        assertTrue(mengde.erLik(mengde2));

        mengde2.leggTil("c");
        assertFalse(mengde.erLik(mengde2));
    }

    @Test
    public void testErDisjunkt() {
        MengdeADT<String> mengde2 = new TabellMengde<>();
        mengde.leggTil("a");
        mengde2.leggTil("b");
        assertTrue(mengde.erDisjunkt(mengde2));

        mengde2.leggTil("a");
        assertFalse(mengde.erDisjunkt(mengde2));
    }

    @Test
    public void testSnitt() {
        mengde.leggTil("a");
        mengde.leggTil("b");
        MengdeADT<String> mengde2 = new TabellMengde<>();
        mengde2.leggTil("b");
        mengde2.leggTil("c");

        MengdeADT<String> snitt = mengde.snitt(mengde2);
        assertTrue(snitt.inneholder("b"));
        assertFalse(snitt.inneholder("a"));
        assertFalse(snitt.inneholder("c"));
    }

    @Test
    public void testUnion() {
        mengde.leggTil("a");
        mengde.leggTil("b");
        MengdeADT<String> mengde2 = new TabellMengde<>();
        mengde2.leggTil("b");
        mengde2.leggTil("c");

        MengdeADT<String> union = mengde.union(mengde2);
        assertTrue(union.inneholder("a"));
        assertTrue(union.inneholder("b"));
        assertTrue(union.inneholder("c"));
    }

    @Test
    public void testMinus() {
        mengde.leggTil("a");
        mengde.leggTil("b");
        mengde.leggTil("c");

        MengdeADT<String> mengde2 = new TabellMengde<>();
        mengde2.leggTil("b");

        MengdeADT<String> diff = mengde.minus(mengde2);
        assertTrue(diff.inneholder("a"));
        assertTrue(diff.inneholder("c"));
        assertFalse(diff.inneholder("b"));
    }

    @Test
    public void testLeggTilOgFjern() {
        mengde.leggTil("a");
        mengde.leggTil("b");
        assertTrue(mengde.inneholder("a"));
        mengde.fjern("a");
        assertFalse(mengde.inneholder("a"));
    }
}
