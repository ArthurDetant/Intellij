package universite;


/**
* universite/PersonneHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from universite.idl
* vendredi 11 mars 2016 15 h 49 CET
*/

abstract public class PersonneHelper
{
  private static String  _id = "IDL:universite/Personne:1.0";

  public static void insert (org.omg.CORBA.Any a, universite.Personne that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static universite.Personne extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (universite.PersonneHelper.id (), "Personne");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static universite.Personne read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_PersonneStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, universite.Personne value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static universite.Personne narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof universite.Personne)
      return (universite.Personne)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      universite._PersonneStub stub = new universite._PersonneStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static universite.Personne unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof universite.Personne)
      return (universite.Personne)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      universite._PersonneStub stub = new universite._PersonneStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
