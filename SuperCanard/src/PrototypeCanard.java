public class PrototypeCanard extends Canard {
    public PrototypeCanard() {
        comportementVol = new NePasVoler();
        comportementCancan = new Cancan();
    }
    public void afficher() {
        System.out.println("Je suis un prototype de canard");
    }
}