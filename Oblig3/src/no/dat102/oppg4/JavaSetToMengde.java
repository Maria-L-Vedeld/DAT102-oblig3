package no.dat102.oppg4; //c


import java.util.HashSet;
import java.util.Set;

public class JavaSetToMengde<T> implements MengdeADT<T> {
    private Set<T> sett = new HashSet<>();

    @Override
    public boolean erTom() { return sett.isEmpty(); }

    @Override
    public boolean inneholder(T element) { return sett.contains(element); }

    @Override
    public boolean erDelmengdeAv(MengdeADT<T> annenMengde) {
        return sett.stream().allMatch(annenMengde::inneholder);
    }

    @Override
    public boolean erLik(MengdeADT<T> annenMengde) {
        return erDelmengdeAv(annenMengde) && annenMengde.erDelmengdeAv(this);
    }

    @Override
    public boolean erDisjunkt(MengdeADT<T> annenMengde) {
        return sett.stream().noneMatch(annenMengde::inneholder);
    }

    @Override
    public MengdeADT<T> snitt(MengdeADT<T> annenMengde) {
        JavaSetToMengde<T> resultat = new JavaSetToMengde<>();
        sett.stream().filter(annenMengde::inneholder).forEach(resultat::leggTil);
        return resultat;
    }

    @Override
    public MengdeADT<T> union(MengdeADT<T> annenMengde) {
        JavaSetToMengde<T> resultat = new JavaSetToMengde<>();
        resultat.leggTilAlleFra(this);
        resultat.leggTilAlleFra(annenMengde);
        return resultat;
    }

    @Override
    public MengdeADT<T> minus(MengdeADT<T> annenMengde) {
        JavaSetToMengde<T> resultat = new JavaSetToMengde<>();
        sett.stream().filter(e -> !annenMengde.inneholder(e)).forEach(resultat::leggTil);
        return resultat;
    }

    @Override
    public void leggTil(T element) { sett.add(element); }

    @Override
    public void leggTilAlleFra(MengdeADT<T> annenMengde) {
        for (T e : annenMengde.tilTabell()) {
            sett.add(e);
        }
    }

    @Override
    public T fjern(T element) {
        if (sett.remove(element)) return element;
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T[] tilTabell() {
        return (T[]) sett.toArray(new Object[0]);
    }

    @Override
    public int antallElementer() {
        return sett.size();
    }
}
