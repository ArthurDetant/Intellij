package universite.application;

import java.util.HashMap;

import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import universite.Etudiant;
import universite.EtudiantHelper;
import universite.FacPOA;

public class FacImpl extends FacPOA {
	
	POA rootPOA = null ;
	
	private int nbEtudiants = 0 ;
	HashMap<Integer, Etudiant> etudiants = new HashMap<>() ;
	
	public FacImpl(POA rootPOA) {
		this.rootPOA = rootPOA ;
	}

	@Override
	public Etudiant ajouterEtudiant(String nom) {
		Etudiant etuRef = null ;
		
		EtudiantImpl etu = new EtudiantImpl() ;
		etu.nom(nom);
		int num = ++nbEtudiants ;
		etu.setNumCarte(num);
		org.omg.CORBA.Object ref;
		try {
			ref = rootPOA.servant_to_reference(etu);
			etuRef = EtudiantHelper.narrow(ref) ;
		    etudiants.put(num, etuRef) ;
		} catch (ServantNotActive | WrongPolicy e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return etuRef;
	}

	@Override
	public Etudiant recupererEtudiant(int numCarte) {
		return etudiants.get(numCarte) ;
	}

	@Override
	public Etudiant[] listerEtudiants() {
		return etudiants.values().toArray(new Etudiant[etudiants.size()]);
	}

	@Override
	public void retirerEtudiant(int numCarte) {
		etudiants.remove(numCarte) ;
	}

}
