package universite.FacPackage;


/**
* universite/FacPackage/EtudiantsHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from universite.idl
* vendredi 11 mars 2016 15 h 49 CET
*/

public final class EtudiantsHolder implements org.omg.CORBA.portable.Streamable
{
  public universite.Etudiant value[] = null;

  public EtudiantsHolder ()
  {
  }

  public EtudiantsHolder (universite.Etudiant[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = universite.FacPackage.EtudiantsHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    universite.FacPackage.EtudiantsHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return universite.FacPackage.EtudiantsHelper.type ();
  }

}
