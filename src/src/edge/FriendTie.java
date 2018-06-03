package src.edge;

/*
朋友的关系
 */
public class FriendTie extends PersonEdge {
    public FriendTie(String label, double weight) {
        super(label, weight);
    }
    @Override
    public int hashCode() {
        return vertices.get(0).getLabel().length()+vertices.get(1).getLabel().length();
    }
    @Override
    public boolean equals(Object obj) {
        if( !(obj instanceof FriendTie)){
            return false;
        }
        obj=(FriendTie)obj;
        if (vertices.get(0).equals(((FriendTie) obj).vertices.get(0))&&vertices.get(1).equals(((FriendTie) obj).vertices.get(1))){
            return true;
        }
        return false;
    }

}