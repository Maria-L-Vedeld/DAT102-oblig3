package no.dat102.oppg4; //f

public class HobbyMatchMain {

    public static double match(Person a, Person b) {
        MengdeADT<String> felles = a.getHobbyer().snitt(b.getHobbyer());
        MengdeADT<String> kunHosA = a.getHobbyer().minus(b.getHobbyer());
        MengdeADT<String> kunHosB = b.getHobbyer().minus(a.getHobbyer());

        double antFelles = felles.antallElementer();
        double antKunHosA = kunHosA.antallElementer();
        double antKunHosB = kunHosB.antallElementer();
        double antTotalt = a.getHobbyer().union(b.getHobbyer()).antallElementer();

        return (antFelles - (antKunHosA + antKunHosB)) / antTotalt;
    }

    public static void main(String[] args) {
        MengdeADT<String> hobbyer1 = new TabellMengde<>();
        hobbyer1.leggTil("Jakt");
        hobbyer1.leggTil("Sykling");
        hobbyer1.leggTil("Venner");
        hobbyer1.leggTil("Data");

        MengdeADT<String> hobbyer2 = new TabellMengde<>();
        hobbyer2.leggTil("Fotball");
        hobbyer2.leggTil("Data");
        hobbyer2.leggTil("Matlaging");
        hobbyer2.leggTil("Jakt");

        MengdeADT<String> hobbyer3 = new TabellMengde<>();
        hobbyer3.leggTil("Løping");
        hobbyer3.leggTil("Musikk");
        hobbyer3.leggTil("Lesing");
        hobbyer3.leggTil("Venner");

        Person person1 = new Person("Arne", hobbyer1);
        Person person2 = new Person("Per", hobbyer2);
        Person person3 = new Person("Espen", hobbyer3);

        double match1_2 = match(person1, person2);
        double match1_3 = match(person1, person3);
        double match2_3 = match(person2, person3);

        System.out.println("Match mellom " + person1.getNavn() + " og " + person2.getNavn() + ": " + match1_2);
        System.out.println("Match mellom " + person1.getNavn() + " og " + person3.getNavn() + ": " + match1_3);
        System.out.println("Match mellom " + person2.getNavn() + " og " + person3.getNavn() + ": " + match2_3);


        double bestScore = match1_2;
        String bestMatch = person1.getNavn() + " og " + person2.getNavn();

        if (match1_3 > bestScore) {
            bestScore = match1_3;
            bestMatch = person1.getNavn() + " og " + person3.getNavn();
        }

        else if (match2_3 > bestScore) {
            bestScore = match2_3;
            bestMatch = person2.getNavn() + " og " + person3.getNavn();
        }

        System.out.println("\nBest match er mellom " + bestMatch + " med score: " + bestScore);

        
        System.out.println("\nSjekker at en person matcher perfekt med seg selv:");
        System.out.println(person1.getNavn() + " og " + person1.getNavn() + ": " + match(person1, person1));
    }
}
