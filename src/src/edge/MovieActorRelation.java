package src.edge;

import src.vertex.Actor;
import src.vertex.Movie;
import src.vertex.Vertex;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MovieActorRelation extends MovieEdge {
    private Movie movie=null;
    private Actor actor=null;
    public MovieActorRelation(String label, double weight) {
        super(label, weight);
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
            if (vertex instanceof Actor){
                actor=(Actor)vertex;
            }
        }
        if (movie==null||actor==null){
            movie=null;
            actor=null;
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(getLabel()+" ");
        stringBuilder.append(actor+"参演了："+movie);
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MovieActorRelation)){
            return false;
        }
        boolean result1=getLabel().equals(((MovieActorRelation) obj).getLabel());
        boolean result2=actor.equals(((MovieActorRelation) obj).actor);
        boolean result3=movie.equals(((MovieActorRelation) obj).movie);
        return result1&&result2&&result3;
    }

    @Override
    public int hashCode() {
        return getLabel().length();
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }
}
