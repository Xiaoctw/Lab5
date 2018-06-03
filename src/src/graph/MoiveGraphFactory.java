package src.graph;
import src.vertex.*;
import src.edge.*;
public class MoiveGraphFactory implements GraphFactory{
    @Override
    public Graph createGraph(String filePath) {
        return new MovieGraph();
    }
    public Graph createGraph(){
        return new MovieGraph();
    }
    public Director createDirector(String label,String[] args){
        Director director=new Director(label);
        director.fillVertexInfo(args);
        return director;
    }
    public Actor createActor(String label,String[] args){
        Actor actor=new Actor(label);
        actor.fillVertexInfo(args);
        return actor;
    }
    public Movie createMovie(String label,String[] args){
        Movie movie=new Movie(label);
        movie.fillVertexInfo(args);
        return movie;
    }
    public MovieActorRelation createMovieActorRelation(String label,double weight){
        MovieActorRelation movieActorRelation=new MovieActorRelation(label,weight);
        return movieActorRelation;
    }
    public MovieDirectorRelation createMovieDirectorRelation(String label){
        return new MovieDirectorRelation(label);
    }
    public SameMovieHyperEdge createSameMovieHyperEdge(String label){
        return new SameMovieHyperEdge(label);
    }
}
