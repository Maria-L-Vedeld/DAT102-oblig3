package no.dat102.oppg4; //f

public class Person {
	
    private String navn;
    private MengdeADT<String> hobbyer;

    public Person(String navn, MengdeADT<String> hobbyer) {
        this.navn = navn;
        this.hobbyer = hobbyer;
    }

    public String getNavn() {
        return navn;
    }

    public MengdeADT<String> getHobbyer() {
        return hobbyer;
    }

    @Override
    public String toString() {
        return navn + " med hobbyer: " + String.join(", ", hobbyer.tilTabell());
    }
}
