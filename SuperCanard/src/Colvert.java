public class Colvert extends Canard {
    public Colvert() {
        comportementCancan = new Cancan();
        comportementVol = new VolerAvecDesAiles();
    }

    public void afficher() {
        System.out.println("Je suis un vrai colvert");
    }
}