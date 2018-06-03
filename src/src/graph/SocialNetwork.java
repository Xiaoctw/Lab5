package src.graph;

import src.edge.*;
import src.vertex.Person;
import src.vertex.Vertex;

import java.sql.Connection;
import java.util.*;

public class SocialNetwork extends ConcreteGraph {
    private Set<CommutTie> commutTieSet=new HashSet<>();
    private Set<ForwardTie> forwardTieSet=new HashSet<>();
    private Set<FriendTie> friendTieSet=new HashSet<>();

    public SocialNetwork() {
        super();
    }
    public void flash(){
        Set<Edge> edges=edges();
        for (Edge edge:edges
                ) {
            if ((edge instanceof CommutTie)&&(!commutTieSet.contains(edge))){
                commutTieSet.add((CommutTie)edge);
            }else if ((edge instanceof ForwardTie)&&(!forwardTieSet.contains(edge))){
                forwardTieSet.add((ForwardTie)edge);
            }else {
                if (!friendTieSet.contains(edge)&&((edge instanceof FriendTie))){
                friendTieSet.add((FriendTie)edge);}
            }
        }
    }
    @Override
    public Map<Vertex, Double> sources(Vertex target) {
        if (!(target instanceof Person)){
            return null;
        }
        Map<Vertex,Double> map=new HashMap<>();
        Set<Edge> edges=edges();
        for (Edge edge:edges
                ) {
            edge=(PersonEdge)edge;
            if (((PersonEdge) edge).getVertices().get(1).equals(target)){
                map.put(((PersonEdge) edge).getVertices().get(0),edge.getWeight());
            }
        }
        return map;
    }

    @Override
    public Map<Vertex, Double> targets(Vertex source) {
        if (!(source instanceof Person)){
            return null;
        }
        Map<Vertex,Double> map=new HashMap<>();
        Set<Edge> edges=edges();
        for (Edge edge:edges
                ) {
            edge=(PersonEdge)edge;
            if (((PersonEdge) edge).getVertices().get(0).equals(source)){
                map.put(((PersonEdge) edge).getVertices().get(1),edge.getWeight());
            }
        }
        return map;
    }

    public Map<Person,Double> commutieSources(Vertex vertex){
        Map<Person, Double> map=new HashMap<>();
        if (!(vertex instanceof Person)){
            return null;
        }
        Person targetPerson=(Person)vertex;
        for (CommutTie edge:commutTieSet
                ) {
            if (edge.getVertices().get(1).equals(targetPerson)){
                map.put((Person) edge.getVertices().get(0),edge.getWeight());
            }
        }
        return map;
    }
    public Map<Person,Double> forwardtieSources(Vertex vertex){
        Map<Person, Double> map=new HashMap<>();
        if (!(vertex instanceof Person)){
            return null;
        }
        Person targetPerson=(Person)vertex;
        for (ForwardTie edge:forwardTieSet
                ) {
            if (edge.getVertices().get(1).equals(targetPerson)){
                map.put((Person) edge.getVertices().get(0),edge.getWeight());
            }
        }
        return map;
    }
    public Map<Person,Double> CommuttieTargets(Vertex vertex){
        Map<Person,Double> map=new HashMap<>();
        if (!(vertex instanceof Person)){
            return null;
        }
        Person sourcePerson=(Person)vertex;
        for (CommutTie edge:commutTieSet
                ) {
            if (edge.getVertices().get(0).equals(sourcePerson)){
                map.put((Person)edge.getVertices().get(1),edge.getWeight());
            }
        }
        return map;
    };
    public Map<Person,Double> forwardtieTargets(Vertex vertex){
        Map<Person,Double> map=new HashMap<>();
        if (!(vertex instanceof Person)){
            return null;
        }
        Person sourcePerson=(Person)vertex;
        for (ForwardTie edge:forwardTieSet
                ) {
            if (edge.getVertices().get(0).equals(sourcePerson)){
                map.put((Person)edge.getVertices().get(1),edge.getWeight());
            }
        }
        return map;
    };
    public Map<Person,Double> frienttieTargets(Vertex vertex){
        Map<Person,Double> map=new HashMap<>();
        if (!(vertex instanceof Person)){
            return null;
        }
        Person sourcePerson=(Person)vertex;
        for (FriendTie edge:friendTieSet
                ) {
            if (edge.getVertices().get(0).equals(sourcePerson)){
                map.put((Person)edge.getVertices().get(1),edge.getWeight());
            }
        }
        return map;
    };

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append("微博社交图中有人：\n");
        for (Vertex person:vertices()
                ) {
            person=(Person)person;
            sb.append(((Person) person).toStringAddToGraph());
        }
        sb.append("评论关系：\n");
        for (CommutTie commiutTie:commutTieSet
                ) {
            sb.append(commiutTie.getVertices().get(0).getLabel()+"评论了"+commiutTie.getVertices().get(1).getLabel());
            sb.append("\n");
        }
        sb.append("转发关系：\n");
        for (ForwardTie forwardTie:forwardTieSet
                ) {
            sb.append(forwardTie.getVertices().get(0).getLabel()+"转发了"+forwardTie.getVertices().get(1).getLabel()+"的微博");
            sb.append("\n");
        }
        sb.append("好友关系：\n");
        for (FriendTie friend:friendTieSet
                ) {
            sb.append(friend.getVertices().get(0).getLabel()+"和"+friend.getVertices().get(1).getLabel()+"是好友关系");
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SocialNetwork)){
            return false;
        }
        obj=(SocialNetwork)obj;
        Boolean vertexResult=this.vertices().containsAll(((SocialNetwork) obj).vertices())&&((SocialNetwork) obj).vertices().containsAll(this.vertices());
        Boolean edgeResult=this.edges().containsAll(((SocialNetwork) obj).edges())&&((SocialNetwork) obj).edges().containsAll(this.edges());
        return vertexResult&&edgeResult;
    }

    @Override
    public int hashCode() {
        return this.vertices().size()+this.edges().size();
    }

    @Override
    public boolean removeEdge(Edge edge) {
        double wb=edge.getWeight();
        boolean result=super.removeEdge(edge);
        if (!result){
            return false;
        }
        for (Edge each:edges()
                ) {
            double wa=each.getWeight();
            each.setWeight(wa/(1-wb));
        }
        if (edge instanceof CommutTie){
            commutTieSet.remove(edge);
        }
        if (edge instanceof ForwardTie){
            forwardTieSet.remove(edge);
        }
        if (edge instanceof FriendTie){
            friendTieSet.remove(edge);
        }
        return true;
    }

    @Override
    public boolean addEdge(Edge edge) {
        double wa=edge.getWeight();
        boolean result= super.addEdge(edge);
        if (!result){
            return false;
        }
        edge=(PersonEdge)edge;
        if (!this.vertices().containsAll(((PersonEdge) edge).getVertices())){
            removeEdge(edge);
            return false;
        }
        for (Edge each:edges()){
            if (each==edge){
                continue;
            }
            double wb=each.getWeight();
            each.setWeight(wb*(1-wa));
        }
        flash();
        return true;
    }

    @Override
    public boolean removeVertex(Vertex v) {
        boolean result=super.removeVertex(v);
        if (!result){ return false; }
            Set<Edge> removeEdge=new HashSet<>();
        for (Edge edge:edges()
                ) {
            edge=(PersonEdge)edge;
            if (((PersonEdge) edge).getVertices().get(0).equals(v)||((PersonEdge) edge).getVertices().get(1).equals(v)){
               removeEdge.add(edge);
            }
        }
        for (Edge edge:removeEdge){
            removeEdge(edge);
        }
        return result;
    }
    //返回不可变的集合
    public Set<CommutTie> getCommutTieSet() {
        return Collections.unmodifiableSet(commutTieSet);
    }

    public Set<ForwardTie> getForwardTieSet() {
        return Collections.unmodifiableSet(forwardTieSet);
    }

    public Set<FriendTie> getFriendTieSet() {
        return Collections.unmodifiableSet(friendTieSet);
    }

    public boolean changeWeight(Edge edge, double wb){
        double wa=edge.getWeight();
        edge.setWeight(wb);
        for (Edge each:edges()
                ) {
            if (each.equals(edge)){
                continue;
            }
            double wc=each.getWeight();
            each.setWeight(wc*(1-wb)/(1-wa));
        }
        return true;
    }
}
