import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ClientImpl implements ClientInterface {

    private final int IMAGE_SIZE = 20 ;
    private final int IMAGE_SCALED = 80 ;

    JFrame frm;
    private JLabel digit, realValue, predicted;
    private JButton nextButton;

    private Random random = new Random(33) ;

    private Classifier classifier;
    private Instances test;
    private Evaluation e;

    ClientInterface stub;
    ServerInterface serv;


    public ClientImpl(ClientFrame f){
        frm = f;
        try {
            stub = (ClientInterface) UnicastRemoteObject.exportObject(this, 0) ;

            String name = "server" ;
            Registry registry = LocateRegistry.getRegistry() ;

            Instances dataset = TP_00_DigitsLoader.loadDigitsData() ;

            Instances[] split = TP_02_DigitsClassifierFinder.split(dataset, 80) ;
            Instances train = split[0] ;
            test = split[1] ;


            serv = (ServerInterface) registry.lookup(name) ;
            serv.buildClassifier(train);
            e = serv.eval(test);

            buildUI() ;

            showDigit(test.firstInstance());
            frm.pack();
            frm.setVisible(true);





        } catch (Exception e) {
            e.printStackTrace() ;
        }
    }



    protected void buildUI() throws RemoteException {
        frm = new JFrame() ;
        frm.getContentPane().setLayout(new BorderLayout());

        digit = new JLabel() ;
        realValue = new JLabel("?") ;
        predicted = new JLabel("?") ;

        JPanel titlePanel = new JPanel() ;
        classifier = serv.getClassifierClass();
        JLabel label = new JLabel("Classifier : " + classifier.getClass().getSimpleName()) ;
        label.setFont(new Font("Serif", Font.BOLD, 22));
        titlePanel.add(label) ;
        frm.add(titlePanel, BorderLayout.NORTH) ;

        frm.add(digit, BorderLayout.WEST) ;

        JPanel infosPanel = new JPanel() ;
        infosPanel.setLayout(new GridLayout(0,2));
        infosPanel.add(new JLabel("Real : ")) ;
        infosPanel.add(realValue) ;
        infosPanel.add(new JLabel("Predicted : ")) ;
        infosPanel.add(predicted) ;
        frm.add(infosPanel, BorderLayout.CENTER) ;

        nextButton = new JButton("Next") ;
        frm.add(nextButton, BorderLayout.SOUTH) ;

        addListeners();

        frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    protected void addListeners(){
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showDigit(test.get(random.nextInt(test.size())));
            }
        });
    }

    private void showDigit(Instance instance) {
        BufferedImage img = new BufferedImage(IMAGE_SIZE, IMAGE_SIZE, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g = (Graphics2D)img.getGraphics() ;
        ArrayList<Attribute> attributes = Collections.list(instance.enumerateAttributes());
        int y = 0, x = 0 ; float intensity ;
        for (Attribute attr: attributes){
            intensity = (float)instance.value(attr) ;
            intensity = intensity < 0 ? 0 : intensity ;
            intensity = intensity > 1 ? 1 : intensity ;
            g.setColor(new Color(intensity, intensity, intensity));
            g.drawLine(x, y, x, y);
            x = attr.index() / 20 ;
            y = (y+1) % 20 ;
        }
        Image image = img.getScaledInstance(IMAGE_SCALED, IMAGE_SCALED, Image.SCALE_SMOOTH);;
        digit.setIcon(new ImageIcon(image));
        digit.revalidate();
        digit.repaint();
        String label ;
        label = instance.classAttribute().value((int) instance.value(instance.classAttribute()));
        realValue.setText(""+Integer.valueOf(label)%10);
        try {
            classifier = serv.getClassifierClass();
            label = instance.classAttribute().value((int) classifier.classifyInstance(instance));
            predicted.setText("" + Integer.valueOf(label) % 10);
        }catch (Exception ex){ ex.printStackTrace(); }
    }



    @Override
    public void train() throws RemoteException {

    }

}
