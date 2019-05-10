package universiteApplication;

import org.omg.CORBA.Object;
import org.omg.PortableServer.POAOperations;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;
import universite.Etudiant;
import universite.EtudiantHelper;
import universite.FacPOA;

import java.util.HashMap;

public class FacImpl extends FacPOA {

    private HashMap<Integer, Etudiant> etudiants = new HashMap<>() ;
    private int nbEtudiants;
    POAOperations rootPOA = null;  /// ????


    @Override
    public Etudiant ajouterEtudiant(String nom) {

        Etudiant etuRef = null;

        EtudiantImpl etu = new EtudiantImpl();
        etu.nom(nom);
        int num = ++ nbEtudiants;
        etu.setNumCarte(num);
        Object ref;

        try{

            ref = rootPOA.servant_to_reference(etu);
            etuRef = EtudiantHelper.narrow(ref);
            etudiants.put(num , etuRef);

        }catch (ServantNotActive | WrongPolicy e) {
            e.printStackTrace();
        }
        return etuRef;

    }

    @Override
    public Etudiant recupererEtudiant(int numCarte) {
        return null;
    }

    @Override
    public void retirerEtudiant(int numCarte) {

    }

    @Override
    public Etudiant[] listerEtudiants() {
        return new Etudiant[0];
    }
}
