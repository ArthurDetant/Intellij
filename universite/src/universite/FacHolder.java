package universite;

/**
* universite/FacHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from universite.idl
* vendredi 3 mai 2019 10 h 18 CEST
*/

public final class FacHolder implements org.omg.CORBA.portable.Streamable
{
  public universite.Fac value = null;

  public FacHolder ()
  {
  }

  public FacHolder (universite.Fac initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = universite.FacHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    universite.FacHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return universite.FacHelper.type ();
  }

}
