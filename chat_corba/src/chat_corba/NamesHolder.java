package chat_corba;


/**
* chat_corba/NamesHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from chat_corba.idl
* vendredi 3 mai 2019 13 h 51 CEST
*/

public final class NamesHolder implements org.omg.CORBA.portable.Streamable
{
  public String value[] = null;

  public NamesHolder ()
  {
  }

  public NamesHolder (String[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = chat_corba.NamesHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    chat_corba.NamesHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return chat_corba.NamesHelper.type ();
  }

}
