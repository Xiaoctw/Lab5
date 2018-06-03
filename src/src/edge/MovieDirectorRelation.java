package src.edge;
import src.vertex.*;
import src.edge.*;
import src.vertex.Vertex;

import java.util.List;

public class MovieDirectorRelation extends MovieEdge {
/*
这个边没有权值
 */
    private Movie movie;
    private Director director;
    public MovieDirectorRelation(String label) {
        super(label, -1);
    }

    @Override
    public boolean addVertices(List<Vertex> vertices) {
        if (vertices.size()!=2){
            return false;
        }
        for (Vertex vertex:vertices
                ) {
            if (vertex instanceof Movie){
                movie=(Movie)vertex;
            }
            if (vertex instanceof Director){
                director=(Director)vertex;
            }
        }
        if (movie==null||director==null){
            movie=null;
            director=null;
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(getLabel()+" ");
        stringBuilder.append(director+"导演了："+movie);
        return stringBuilder.toString();
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MovieDirectorRelation)){
            return false;
        }
        boolean result1=getLabel().equals(((MovieDirectorRelation) obj).getLabel());
        boolean result2=director.equals(((MovieDirectorRelation) obj).director);
        boolean result3=movie.equals(((MovieDirectorRelation) obj).movie);

        return result1&&result2&&result3;
    }

    @Override
    public int hashCode() {
        return getLabel().length();
    }
}
