import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.trees.J48;
import weka.core.Instances;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl implements ServerInterface{

    Classifier c;

    public ServerImpl() {
        try {

            ServerInterface stub = (ServerInterface) UnicastRemoteObject.exportObject(this, 0) ;
            c = new J48();
            String name = "server" ;
            Registry registry = LocateRegistry.createRegistry(1099) ;
            registry.rebind(name, stub);

            System.out.println("Server bound");

        } catch (Exception e) {
            e.printStackTrace() ;
        }
    }

    public static void main(String[] args) {
        new ServerImpl();
    }

    @Override
    public Classifier getClassifierClass() throws RemoteException {
        return c;
    }

    @Override
    public void setOptions(String[] options) throws Exception {

    }

    @Override
    public void buildClassifier(Instances train) throws Exception {
        c.buildClassifier(train);
    }

    @Override
    public Evaluation eval(Instances cross) throws Exception {
        Evaluation eval ;
        eval = new Evaluation(cross);
        eval.evaluateModel(c, cross);
        System.out.println(eval.toSummaryString());
        return eval;
    }

    @Override
    public double classifyInstance(Instances instance) throws Exception {
        return 0;
    }
}
