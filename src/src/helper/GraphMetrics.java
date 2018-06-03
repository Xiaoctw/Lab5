package src.helper;

import src.graph.Graph;
import src.vertex.Vertex;
import src.graph.*;

import java.sql.Connection;

public class GraphMetrics {

    private static Graph g;

    static double degreeCentrality(Graph g, Vertex vertex){
        g=(ConcreteGraph)g;
        if (g instanceof WordPoet){
            return g.sources(vertex).size();
        }else if (g instanceof MovieGraph){
        }
        return g.sources(vertex).size();
    }
    static double degreeCentrality(Graph g){
        double sum=0;
        for (Object vertex:g.vertices()
             ) {
            if (vertex instanceof Vertex){
                vertex=(Vertex)vertex;
            }
            sum+=degreeCentrality(g, (Vertex) vertex);
        }
        return sum;
    }
    static double betweennessCentrality(Graph g,Vertex v){
        return 0;
    }
    static double closenessCentrality(Graph g,Vertex v){
        return 0;
    }
}
