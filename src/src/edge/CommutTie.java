package src.edge;

/*
评论的关系，为有向图
 */
public class CommutTie extends PersonEdge {
    public CommutTie(String label, double weight) {
        super(label, weight);
    }
    @Override
    public boolean equals(Object obj) {
        if( !(obj instanceof CommutTie)){
            return false;
        }
        obj=(CommutTie)obj;
        if (vertices.get(0).equals(((CommutTie) obj).vertices.get(0))&&vertices.get(1).equals(((CommutTie) obj).vertices.get(1))){
            return true;
        }
        return false;
    }
    @Override
    public int hashCode() {
        return vertices.get(0).getLabel().length()+vertices.get(1).getLabel().length();
    }
}