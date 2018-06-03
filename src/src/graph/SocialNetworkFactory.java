package src.graph;

import java.beans.PersistenceDelegate;
import src.edge.*;
import src.vertex.*;
public class SocialNetworkFactory implements GraphFactory {
    @Override
    public Graph createGraph(String filePath) {
        return new SocialNetwork();
    }
    public Graph createGraph(){
        return new SocialNetwork();
    }
    public Person createPerson(String label,String[] args){
        Person person=new Person(label);
        person.fillVertexInfo(args);
        return person;
    }
    public CommutTie createCommuTIe(String label,double weight){
        return new CommutTie(label,weight);
    }
    public ForwardTie createForwardTie(String label,double weight){
        return new ForwardTie(label,weight);
    }
    public FriendTie createFriendTie(String label,double weight){
        return new FriendTie(label,weight);
    }
}
