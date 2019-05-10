import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {

    //public Evaluation eval(Classifier classifier, Instances train, Instances cross) throws RemoteException, Exception;

    public Classifier getClassifierClass() throws RemoteException ;
    public void setOptions(String[] options) throws Exception;
    public void buildClassifier(Instances train) throws Exception;
    public Evaluation eval(Instances cross) throws Exception;
    public double classifyInstance(Instances instance) throws Exception ;

}
