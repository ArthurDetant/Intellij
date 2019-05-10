package callbacks;

/**
* callbacks/CallbackServerHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from callbacks.idl
* vendredi 3 mai 2019 11 h 20 CEST
*/

public final class CallbackServerHolder implements org.omg.CORBA.portable.Streamable
{
  public callbacks.CallbackServer value = null;

  public CallbackServerHolder ()
  {
  }

  public CallbackServerHolder (callbacks.CallbackServer initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = callbacks.CallbackServerHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    callbacks.CallbackServerHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return callbacks.CallbackServerHelper.type ();
  }

}
