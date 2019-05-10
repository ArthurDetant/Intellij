package callbacks;


/**
* callbacks/CallbackClientPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from callbacks.idl
* vendredi 3 mai 2019 11 h 20 CEST
*/

public abstract class CallbackClientPOA extends org.omg.PortableServer.Servant
 implements callbacks.CallbackClientOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("getName", new java.lang.Integer (0));
    _methods.put ("showYourName", new java.lang.Integer (1));
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
       case 0:  // callbacks/CallbackClient/getName
       {
         String $result = null;
         $result = this.getName ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 1:  // callbacks/CallbackClient/showYourName
       {
         this.showYourName ();
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:callbacks/CallbackClient:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public CallbackClient _this() 
  {
    return CallbackClientHelper.narrow(
    super._this_object());
  }

  public CallbackClient _this(org.omg.CORBA.ORB orb) 
  {
    return CallbackClientHelper.narrow(
    super._this_object(orb));
  }


} // class CallbackClientPOA
