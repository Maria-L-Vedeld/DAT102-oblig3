package no.dat102.oppg4; //a

import java.util.Arrays;

public class TabellMengde<T> implements MengdeADT<T> {
    private static final int KAPASITET = 10;
    private T[] tabell;
    private int antall;

    @SuppressWarnings("unchecked")
    public TabellMengde() {
        tabell = (T[]) new Object[KAPASITET];
        antall = 0;
    }

    @Override
    public boolean erTom() {
        return antall == 0;
    }

    @Override
    public boolean inneholder(T element) {
        for (int i = 0; i < antall; i++) {
            if (tabell[i].equals(element)) return true;
        }
        return false;
    }

    @Override
    public boolean erDelmengdeAv(MengdeADT<T> annenMengde) {
        for (T e : tilTabell()) {
            if (!annenMengde.inneholder(e)) return false;
        }
        return true;
    }

    @Override
    public boolean erLik(MengdeADT<T> annenMengde) {
        return this.erDelmengdeAv(annenMengde) && annenMengde.erDelmengdeAv(this);
    }

    @Override
    public boolean erDisjunkt(MengdeADT<T> annenMengde) {
        for (T e : tilTabell()) {
            if (annenMengde.inneholder(e)) return false;
        }
        return true;
    }

    @Override
    public MengdeADT<T> snitt(MengdeADT<T> annenMengde) {
        MengdeADT<T> resultat = new TabellMengde<>();
        for (T e : tilTabell()) {
            if (annenMengde.inneholder(e)) resultat.leggTil(e);
        }
        return resultat;
    }

    @Override
    public MengdeADT<T> union(MengdeADT<T> annenMengde) {
        MengdeADT<T> resultat = new TabellMengde<>();
        resultat.leggTilAlleFra(this);
        resultat.leggTilAlleFra(annenMengde);
        return resultat;
    }

    @Override
    public MengdeADT<T> minus(MengdeADT<T> annenMengde) {
        MengdeADT<T> resultat = new TabellMengde<>();
        for (T e : tilTabell()) {
            if (!annenMengde.inneholder(e)) resultat.leggTil(e);
        }
        return resultat;
    }

    @Override
    public void leggTil(T element) {
        if (!inneholder(element)) {
            if (antall == tabell.length) {
                tabell = Arrays.copyOf(tabell, tabell.length * 2);
            }
            tabell[antall++] = element;
        }
    }

    @Override
    public void leggTilAlleFra(MengdeADT<T> annenMengde) {
        for (T e : annenMengde.tilTabell()) {
            leggTil(e);
        }
    }

    @Override
    public T fjern(T element) {
        for (int i = 0; i < antall; i++) {
            if (tabell[i].equals(element)) {
                T fjernet = tabell[i];
                tabell[i] = tabell[antall - 1];
                tabell[antall - 1] = null;
                antall--;
                return fjernet;
            }
        }
        return null;
    }

    @Override
    public T[] tilTabell() {
        return Arrays.copyOf(tabell, antall);
    }

    @Override
    public int antallElementer() {
        return antall;
    }
}
