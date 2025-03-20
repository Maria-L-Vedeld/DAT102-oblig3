package no.dat102.oppg4.uke11;

import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

public class HashSetVsBinarySoek {
	public static void main(String[] args) {
        int antElementer = 100_000;
        int maksVerdi = 1_000_000;
        int steg = 45713;  // Må ikke ha felles faktor med 1 000 000
        int startVerdi = 376;

        // Lag datastrukturene
        HashSet<Integer> hashSet = new HashSet<>(antElementer);
        Integer[] tabell = new Integer[antElementer];

        int tall = startVerdi;
        for (int i = 0; i < antElementer; i++) {
            hashSet.add(tall);
            tabell[i] = tall;
            tall = (tall + steg) % maksVerdi;
        }

        // Sorter tabellen for å gjøre binærsøk
        Arrays.sort(tabell);

        // Lag 10 000 tilfeldige søketall
        int antSok = 10_000;
        int[] soketall = new int[antSok];
        for (int i = 0; i < antSok; i++) {
            soketall[i] = ThreadLocalRandom.current().nextInt(0, maksVerdi);
        }

        // Mål tid og antall funn i HashSet
        long startHashSet = System.nanoTime();
        int funnHashSet = 0;
        for (int søk : soketall) {
            if (hashSet.contains(søk)) {
                funnHashSet++;
            }
        }
        long sluttHashSet = System.nanoTime();

        // Mål tid og antall funn med binærsøk
        long startBinary = System.nanoTime();
        int funnBinary = 0;
        for (int søk : soketall) {
            if (Arrays.binarySearch(tabell, søk) >= 0) {
                funnBinary++;
            }
        }
        long sluttBinary = System.nanoTime();

        // Skriv ut resultater
        System.out.println("Antall funn i HashSet: " + funnHashSet);
        System.out.println("Tid brukt i HashSet: " + (sluttHashSet - startHashSet) / 1_000_000.0 + " ms");
        System.out.println("Antall funn med binærsøk: " + funnBinary);
        System.out.println("Tid brukt med binærsøk: " + (sluttBinary - startBinary) / 1_000_000.0 + " ms");
    }

}
