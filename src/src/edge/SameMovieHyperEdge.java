package src.edge;

import src.vertex.Vertex;
import src.vertex.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SameMovieHyperEdge extends MovieEdge {
    Set<Vertex> vertices;

    public Set<Vertex> getVertices() {
        return vertices;
    }

    public void setVertices(Set<Vertex> vertices) {
        this.vertices = vertices;
    }

    {
        super.vertices=new HashSet<>();
        vertices = (Set<Vertex>) super.vertices;
    }
    public SameMovieHyperEdge(String label) {
        super(label,-1);
    }

    @Override
    public boolean addVertices(List<Vertex> vertices) {
        /*
        确保集合中每个元素均为Actor
         */
        for (Vertex vertex:vertices
             ) {
            if (!(vertex instanceof Actor)){
                return false;
            }
        }
       this.vertices.addAll(vertices);
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SameMovieHyperEdge)){
            return false;
        }
        boolean result1=this.vertices.containsAll(((SameMovieHyperEdge) obj).vertices);
        boolean result2=((SameMovieHyperEdge) obj).vertices.containsAll(vertices);
        return result1&&result2;
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append("参演人员：");
        for (Vertex vertex:vertices
             ) {
            sb.append(vertex.getLabel()+"、");
        }
        return sb.toString();
    }
}
