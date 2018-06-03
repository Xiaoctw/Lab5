package src.graph;

import src.edge.Edge;
import src.vertex.Vertex;

public interface GraphFactory {
    public  Graph createGraph(String filePath);
}

