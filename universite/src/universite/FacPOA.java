package universite;


/**
* universite/FacPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from universite.idl
* vendredi 3 mai 2019 10 h 18 CEST
*/

public abstract class FacPOA extends org.omg.PortableServer.Servant
 implements universite.FacOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("ajouterEtudiant", new java.lang.Integer (0));
    _methods.put ("recupererEtudiant", new java.lang.Integer (1));
    _methods.put ("retirerEtudiant", new java.lang.Integer (2));
    _methods.put ("listerEtudiants", new java.lang.Integer (3));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // universite/Fac/ajouterEtudiant
       {
         String nom = in.read_string ();
         universite.Etudiant $result = null;
         $result = this.ajouterEtudiant (nom);
         out = $rh.createReply();
         universite.EtudiantHelper.write (out, $result);
         break;
       }

       case 1:  // universite/Fac/recupererEtudiant
       {
         int numCarte = in.read_long ();
         universite.Etudiant $result = null;
         $result = this.recupererEtudiant (numCarte);
         out = $rh.createReply();
         universite.EtudiantHelper.write (out, $result);
         break;
       }

       case 2:  // universite/Fac/retirerEtudiant
       {
         int numCarte = in.read_long ();
         this.retirerEtudiant (numCarte);
         out = $rh.createReply();
         break;
       }

       case 3:  // universite/Fac/listerEtudiants
       {
         universite.Etudiant $result[] = null;
         $result = this.listerEtudiants ();
         out = $rh.createReply();
         universite.FacPackage.EtudiantsHelper.write (out, $result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:universite/Fac:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public Fac _this() 
  {
    return FacHelper.narrow(
    super._this_object());
  }

  public Fac _this(org.omg.CORBA.ORB orb) 
  {
    return FacHelper.narrow(
    super._this_object(orb));
  }


} // class FacPOA
