package src.edge;

public class ForwardTie extends PersonEdge {
    public ForwardTie(String label, double weight) {
        super(label, weight);
    }
    @Override
    public int hashCode() {
        return vertices.get(0).getLabel().length()+vertices.get(1).getLabel().length();
    }
    @Override
    public boolean equals(Object obj) {
        if( !(obj instanceof ForwardTie)){
            return false;
        }
        obj=(ForwardTie)obj;
        if (vertices.get(0).equals(((ForwardTie) obj).vertices.get(0))&&vertices.get(1).equals(((ForwardTie) obj).vertices.get(1))){
            return true;
        }
        return false;
    }

}
