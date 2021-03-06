package chat_corba;


/**
* chat_corba/ChatServerHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from chat_corba.idl
* vendredi 3 mai 2019 13 h 51 CEST
*/

abstract public class ChatServerHelper
{
  private static String  _id = "IDL:chat_corba/ChatServer:1.0";

  public static void insert (org.omg.CORBA.Any a, chat_corba.ChatServer that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static chat_corba.ChatServer extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (chat_corba.ChatServerHelper.id (), "ChatServer");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static chat_corba.ChatServer read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_ChatServerStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, chat_corba.ChatServer value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static chat_corba.ChatServer narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof chat_corba.ChatServer)
      return (chat_corba.ChatServer)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      chat_corba._ChatServerStub stub = new chat_corba._ChatServerStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static chat_corba.ChatServer unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof chat_corba.ChatServer)
      return (chat_corba.ChatServer)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      chat_corba._ChatServerStub stub = new chat_corba._ChatServerStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
