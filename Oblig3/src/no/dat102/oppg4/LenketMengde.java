package no.dat102.oppg4; //b

public class LenketMengde<T> implements MengdeADT<T> {
    private static class Node<T> {
        T data;
        Node<T> neste;
        Node(T data) { this.data = data; }
    }

    private Node<T> hode;
    private int antall;

    @Override
    public boolean erTom() { return antall == 0; }

    @Override
    public boolean inneholder(T element) {
        Node<T> aktuell = hode;
        while (aktuell != null) {
            if (aktuell.data.equals(element)) return true;
            aktuell = aktuell.neste;
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
        MengdeADT<T> resultat = new LenketMengde<>();
        for (T e : tilTabell()) {
            if (annenMengde.inneholder(e)) resultat.leggTil(e);
        }
        return resultat;
    }

    @Override
    public MengdeADT<T> union(MengdeADT<T> annenMengde) {
        MengdeADT<T> resultat = new LenketMengde<>();
        resultat.leggTilAlleFra(this);
        resultat.leggTilAlleFra(annenMengde);
        return resultat;
    }

    @Override
    public MengdeADT<T> minus(MengdeADT<T> annenMengde) {
        MengdeADT<T> resultat = new LenketMengde<>();
        for (T e : tilTabell()) {
            if (!annenMengde.inneholder(e)) resultat.leggTil(e);
        }
        return resultat;
    }

    @Override
    public void leggTil(T element) {
        if (!inneholder(element)) {
            Node<T> ny = new Node<>(element);
            ny.neste = hode;
            hode = ny;
            antall++;
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
        Node<T> aktuell = hode, forrige = null;
        while (aktuell != null) {
            if (aktuell.data.equals(element)) {
                if (forrige == null) hode = aktuell.neste;
                else forrige.neste = aktuell.neste;
                antall--;
                return aktuell.data;
            }
            forrige = aktuell;
            aktuell = aktuell.neste;
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T[] tilTabell() {
        T[] array = (T[]) new Object[antall];
        Node<T> aktuell = hode;
        for (int i = 0; i < antall; i++) {
            array[i] = aktuell.data;
            aktuell = aktuell.neste;
        }
        return array;
    }

    @Override
    public int antallElementer() {
        return antall;
    }
}
