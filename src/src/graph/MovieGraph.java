package src.graph;
import src.edge.*;
import src.vertex.*;

import java.util.*;

public class MovieGraph extends ConcreteGraph {
    Set<Director> directors=new HashSet<>();
    Set<Actor> actors=new HashSet<>();
    Set<Movie> movies=new HashSet<>();

    public Set<MovieActorRelation> getMovieActorRelations() {
        return movieActorRelations;
    }

    public Set<MovieDirectorRelation> getMovieDirectorRelations() {
        return movieDirectorRelations;
    }

    public Set<SameMovieHyperEdge> getSameMovieHyperEdges() {
        return sameMovieHyperEdges;
    }

    Set<MovieActorRelation> movieActorRelations=new HashSet<>();
    Set<MovieDirectorRelation> movieDirectorRelations=new HashSet<>();
    Set<SameMovieHyperEdge> sameMovieHyperEdges=new HashSet<>();
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
   public boolean add(Vertex v){
        if (v instanceof Director){
            directors.add((Director) v);
        }else if (v instanceof Actor){
            actors.add((Actor)v);
        }else if (v instanceof Movie){
            movies.add((Movie)v);
        }else {
            return false;
        }
        return true;
   }
    @Override
    public boolean addVertex(Vertex v) {
       boolean result1= super.addVertex(v);
       if (result1){
          return add(v);
       }

       return false;
    }

    @Override
    public boolean addEdge(Edge edge) {
        boolean result=super.addEdge(edge);
        if (result) {
            if (edge instanceof MovieDirectorRelation) {
                edge=(MovieDirectorRelation)edge;
                if (!(movies.contains(((MovieDirectorRelation) edge).getMovie())&&directors.contains(((MovieDirectorRelation) edge).getDirector()))){
                   removeEdge(edge);
                    return false;
                }
                movieDirectorRelations.add((MovieDirectorRelation) edge);
            } else if (edge instanceof MovieActorRelation) {
                if (!(movies.contains(((MovieActorRelation) edge).getMovie())&&actors.contains(((MovieActorRelation) edge).getActor()))){
                  removeEdge(edge);
                    return false;
                }
                movieActorRelations.add((MovieActorRelation) edge);
            } else if (edge instanceof SameMovieHyperEdge) {
                if (!(actors.containsAll(((SameMovieHyperEdge) edge).getVertices()))){
                    removeEdge(edge);
                    return false;
                }
                sameMovieHyperEdges.add((SameMovieHyperEdge) edge);
            } else {
                return false;
            }
        }
        return result;
    }

    @Override
    public boolean removeVertex(Vertex v) {
        boolean result= super.removeVertex(v);
        if (result){
            if (v instanceof Actor){
                actors.remove(v);
            }else if (v instanceof Director){

                directors.remove(v);
            }else {
                movies.remove(v);
            }
        }
        Set<Edge> remove=new HashSet<>();
        for (Edge edge:edges
             ) {
            if (edge.containVertex(v)){
                remove.add(edge);
            }
        }
        for (Edge edge:remove
             ) {
            removeEdge(edge);
        }
        return result;
    }

    @Override
    public boolean removeEdge(Edge edge) {
        boolean result=super.removeEdge(edge);
        if (result){
            if (edge instanceof MovieActorRelation){
                movieActorRelations.remove(edge);
            }else if (edge instanceof MovieDirectorRelation){
                movieDirectorRelations.remove(edge);
            }else {
                sameMovieHyperEdges.remove(edge);
            }
        }
        return result;
    }
    public boolean addVertices(Vertex...lists){
        for (Vertex vertex:lists
             ) {
            boolean result=addVertex(vertex);
            if (!result){
                return false;
            }
        }
        return true;
    }
    public boolean addEdges(Edge...edges){
        for (Edge edge:edges
             ) {
            boolean result=addEdge(edge);
            if (!result){
                return false;
            }
        }
        return true;
    }

    @Override
    public Map<Vertex, Double> sources(Vertex target) {
        Map<Vertex,Double> map=new HashMap<>();
       if (target instanceof Director){
           for (MovieDirectorRelation edge:movieDirectorRelations
                ) {
               if (edge.getDirector()==target){
                   map.put(edge.getMovie(),edge.getWeight());
               }
           }
       }else if (target instanceof Movie){
           for (MovieDirectorRelation edge:movieDirectorRelations
                ) {
               if (edge.getMovie()==target){
                   map.put(edge.getDirector(),edge.getWeight());
               }
           }
           for (MovieActorRelation edge:movieActorRelations
                ) {
               if (edge.getMovie()==target){
                   map.put(edge.getActor(),edge.getWeight());
               }
           }
       }else {
           for (MovieActorRelation edge:movieActorRelations
                ) {
               if (edge.getActor()==target){
                   map.put(edge.getMovie(),edge.getWeight());
               }
           }
       }
       return map;
    }

    public Set<Director> getDirectors() {
        return directors;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public Set<Movie> getMovies() {
        return movies;
    }
}
