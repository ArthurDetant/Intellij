import weka.core.Attribute;
import weka.core.Instance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

public class ClientFrame extends JFrame {


    ClientImpl client ;

    public ClientFrame(){
        new ServerImpl();
        client = new ClientImpl(this) ;

    }



    public static void main(String[] args) {
        new ClientFrame() ;
    }



}


