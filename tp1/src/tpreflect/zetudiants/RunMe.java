package tpreflect.zetudiants;

import tpreflect.paquetcadeau.PaquetCadeau;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Vector;

public class RunMe {

  public static void main(String[] args) {

    Vector v = new PaquetCadeau().getPaquetCadeau();

    Iterator i = v.iterator();
    while (i.hasNext()) {
      Object o = i.next();
      introspect(o);
    }

  }
  private static void introspect(Object o) {
   Class cV = o.getClass();
     Method ms[] = cV.getMethods();
    if(ms.length!=0){
      for (int j = 0; j< ms.length; j++) {
        System.out.println(ms[j]);
    }

    } else {
      System.out.println("pas de methode");
    }

    Field f[] = cV.getFields();
    if(f.length!=0){
      for (int j = 0; j< f.length; j++) {
        System.out.println(f[j]);
      }

    } else {
      System.out.println("pas de Field");
    }

   Constructor c[] = cV.getConstructors();
    if(c.length!=0){
      for (int j = 0; j< c.length; j++) {
        System.out.println(c[j]);
      }

    } else {
      System.out.println("pas de Constructeur");
    }

   Field df[] = cV.getDeclaredFields();
    if(df.length!=0){
      for (int j = 0; j< df.length; j++) {
        System.out.println(df[j]);
      }

    } else {
      System.out.println("pas de DeclaredField");
    }



  }

  }




