package src.graph;

import src.edge.Edge;
import src.edge.WordNeighborhood;
import src.vertex.Vertex;
import src.vertex.Word;

import java.util.*;

public class WordPoet extends ConcreteGraph{

    @Override
    public Map<Vertex, Double> sources(Vertex target) {
        //return super.sources(target);
        //传入的参数不符合条件
        if (!(target instanceof Word)){
            return null;
        }
        Map<Vertex,Double> map=new HashMap<>();
        Set<Edge> edges=edges();
        for (Edge edge:edges
                ) {
            edge=(WordNeighborhood)edge;
            if (((WordNeighborhood) edge).getVertices().get(1).equals(target)){
                map.put(((WordNeighborhood) edge).getVertices().get(0),edge.getWeight());
            }
        }
        return map;
    }
    @Override
    public Map<Vertex, Double> targets(Vertex source) {
        if (!(source instanceof Word)){
            return null;
        }
        Map<Vertex,Double> map=new HashMap<>();
        Set<Edge> edges=edges();
        for (Edge edge:edges
                ) {
            edge=(WordNeighborhood)edge;
            if (((WordNeighborhood) edge).getVertices().get(0).equals(source)){
                map.put(((WordNeighborhood) edge).getVertices().get(1),edge.getWeight());
            }
        }
        return map;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("包括顶点：");
        for (Vertex vertex:vertices()
                ) {
            vertex=(Word)vertex;
            stringBuilder.append(vertex.getLabel()+" ");
        }
        stringBuilder.append("\n包括边：");
        for (Edge edge :edges()
                ) {
            edge=(WordNeighborhood)edge;
            stringBuilder.append(((WordNeighborhood) edge).source()+"->"+((WordNeighborhood) edge).target()+"\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public int hashCode() {
        return this.vertices().hashCode();
        //return super.hashCode();
    }

    @Override
    public boolean removeVertex(Vertex v) {
        Boolean result=super.removeVertex(v);
        Set<Edge> removeEdge=new HashSet<>();
        for (Edge edge:edges
                ) {
            edge=(WordNeighborhood)edge;
            if (((WordNeighborhood) edge).getVertices().get(0).equals(v)||((WordNeighborhood) edge).getVertices().get(1).equals(v)){
                removeEdge.add(edge);
            }
        }
        edges.removeAll(removeEdge);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public boolean addEdge(Edge edge) {
        boolean result=super.addEdge(edge);
        if (!result) return false;
        edge=(WordNeighborhood)edge;
        if (this.vertices().containsAll(((WordNeighborhood) edge).getVertices())){
            return true;
        }
        removeEdge(edge);
        return false;
    }
}
