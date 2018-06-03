package src.graph;

import src.edge.Edge;
import src.vertex.Vertex;
import src.vertex.Word;
import src.edge.*;

public class GraphPoetFactory implements GraphFactory {
    @Override
    public Graph createGraph(String filePath) {
        return new WordPoet();
    }
    public Word createWord(String label){
        return new Word(label);
    }
    public WordNeighborhood createEdge(String label,double weight){
        return new WordNeighborhood(label,weight);
    }
}
