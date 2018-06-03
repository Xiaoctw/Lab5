package src.edge;

import src.vertex.Vertex;

import java.util.List;
import java.util.Set;

public class MovieEdge extends Edge {
    public MovieEdge(String label, double weight) {
        super(label, weight);
    }

    @Override
    public boolean addVertices(List<Vertex> vertices) {
        return false;
    }
    //无向图，这两个方法没有用处
    @Override
    Set<Vertex> sourceVertices() {
        return null;
    }

    @Override
    Set<Vertex> targetVertices() {
        return null;
    }
}
