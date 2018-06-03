package src.graph;

import src.edge.Edge;
import src.edge.NetworkConnection;
import src.vertex.NetVertex;
import src.vertex.Vertex;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NetworkTopology extends ConcreteGraph {
    @Override
    public Map<Vertex, Double> sources(Vertex target) {
        Map<Vertex,Double> map=new HashMap<>();
        for (Edge edge :edges()
                ) {
            edge=(NetworkConnection)edge;
            if (((NetworkConnection) edge).getVertices().contains(target)){
                map.put(((NetworkConnection) edge).getAnother((NetVertex) target),edge.getWeight());
            }
        }
        return map;
    }

    @Override
    public Map<Vertex, Double> targets(Vertex source) {
        //由于是无向边，两个方法一致
        return sources(source);
    }

    @Override
    public boolean removeVertex(Vertex v) {
        boolean result=super.removeVertex(v);
        Set<Edge> removeEdge=new HashSet<>();
        if (result){
            for (Edge edge:edges
                 ) {
                edge=(NetworkConnection)edge;
                if (((NetworkConnection) edge).getVertices().contains(v)){
                   removeEdge.add(edge);
                }
            }
            edges.removeAll(removeEdge);
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder=new StringBuilder();
        Set<Vertex> vertices=vertices();
        Set<Edge> edges=edges();
        for (Vertex vertex:vertices
                ) {
            vertex=(NetVertex)vertex;
            stringBuilder.append(((NetVertex) vertex).toStringAddToGraph());
            stringBuilder.append("\n");
        }
        stringBuilder.append("包括边：\n");
        for (Edge edge:edges
                ) {
            edge=(NetworkConnection)edge;
            stringBuilder.append(edge.toString());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean addEdge(Edge edge) {
        boolean result=super.addEdge(edge);
        if (!result) return false;
        edge=(NetworkConnection)edge;
        if (this.vertices().containsAll(((NetworkConnection) edge).getVertices())){
            return true;
        }
        removeEdge(edge);
        return false;
    }
}
