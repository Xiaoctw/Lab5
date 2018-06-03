package src.edge;

import src.vertex.NetVertex;
import src.vertex.Vertex;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class NetworkConnection extends Edge {
    /*
    由于是无向图，所以用集合来存储边
     */
    protected Set<Vertex> vertices;
    {
        super.vertices=new HashSet<>();
        vertices = (Set<Vertex>) super.vertices;
    }
    public NetworkConnection(String label, double weight) {
        super(label, weight);
    }

    @Override
    public boolean addVertices(List<Vertex> vertices) {
        if (this.vertices.size()!=0){
            this.vertices.clear();
        }
        this.vertices.addAll(vertices);
        return true;
    }
//    public boolean containVertex(Vertex v){
//        if (vertices.contains(v)){
//            return true;
//        }
//        return false;
//    }
    /*
    并不清楚这两个方法写一些什么，因为是无向边
     */
    @Override
    Set<Vertex> sourceVertices() {
        return null;
    }

    @Override
    Set<Vertex> targetVertices() {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        int flag=1;
        for (Vertex vertex:vertices
                ) {
            sb.append(vertex.getLabel());
            if (flag==1) {
                sb.append("--");
            }
            flag--;
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof NetworkConnection)){
            return false;
        }
        Boolean result1=this.vertices.containsAll(((NetworkConnection) obj).vertices);
        Boolean result2=((NetworkConnection) obj).vertices.containsAll(this.vertices);
        return result1&&result2;
    }

    @Override
    public int hashCode() {
        int length=0;
        for (Vertex ver:vertices
                ) {
            length+=ver.getLabel().length();
        }
        return length;
    }
    public NetVertex getAnother(NetVertex vertex){
        if (this.vertices.contains(vertex)){
            for (Vertex vertex1:vertices
                    ) {
                if (vertex1!=vertex){
                    return (NetVertex) vertex1;
                }
            }
        }
        return null;//不存在这个顶点
    }

    public Set<Vertex> getVertices() {
        return vertices;
    }
}
