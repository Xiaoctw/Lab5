package src.application;


import src.exception.*;
import src.graph.*;
import src.edge.*;
import src.vertex.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.management.ManagementFactory;
import java.util.*;

public class Lab5App {
    /**
     * 输入psv，就可以很容易的创建出main方法。
     * @param args
     */
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        System.out.println(name);
// get pid
        String pid = name.split("@")[0];
        System.out.println("pid:" + pid);
        MovieGraph graph=new MovieGraph();
        List<Actor> actors=new ArrayList<>();
        List<Director> directors=new ArrayList<>();
        List<Movie> movies=new ArrayList<>();
        Random random=new Random();
        for (int i = 1; i <=1000 ; i++) {
            Actor actor=new Actor("actor"+i);
            if (i%2==0) {
                actor.fillVertexInfo(new String[]{"" + (random.nextInt(40) + 20),"F"});
            }else {
                actor.fillVertexInfo(new String[]{"" + (random.nextInt(40) + 20),"M"});
            }
            actors.add(actor);
            graph.add(actor);
        }
        for (int i = 1; i <=500 ; i++) {
            Movie movie=new Movie("movie"+i);
            if (i%2==0){
                movie.fillVertexInfo(new String[]{""+(random.nextInt(40)+1980),"China",""+(random.nextDouble()+7.0)});
            }else {
                movie.fillVertexInfo(new String[]{""+(random.nextInt(40)+1980),"Japan",""+(random.nextDouble()+7.0)});
            }
            movies.add(movie);
            graph.add(movie);
        }
        for (int i = 1; i <=200; i++) {
            Director director=new Director("director"+i);
            if (i%2==0) {
                director.fillVertexInfo(new String[]{"" + (random.nextInt(40) + 20),"F"});
            }else {
                director.fillVertexInfo(new String[]{""+(random.nextInt(40) + 20),"M" });
            }
            directors.add(director);
            graph.add(director);
        }
        for (int i = 1; i <=200 ; i++) {
            MovieDirectorRelation edge1=new MovieDirectorRelation("MovieDirectorEdge"+i);
            MovieDirectorRelation edge2=new MovieDirectorRelation("MovieDirectorEdge"+(401-i));
            edge1.addVertices(Arrays.asList(directors.get(i-1),movies.get(i-1)));
            edge2.addVertices(Arrays.asList(directors.get(i-1),movies.get(400-i)));
            graph.addEdge(edge1);
            graph.addEdge(edge2);
        }
        for (int i = 1; i <=100 ; i++) {
            MovieDirectorRelation movieDirectorRelation=new MovieDirectorRelation("MovieDirectorEdge"+(400+i));
            movieDirectorRelation.addVertices(Arrays.asList(directors.get(i-1),movies.get(500-i)));
        }
        for (int i = 1; i <=500 ; i++) {
            MovieActorRelation edge1=new MovieActorRelation("MovieActorEdge"+i,i);
            MovieActorRelation edge2=new MovieActorRelation("MovieActorEdge"+(501-i),i);
            edge1.addVertices(Arrays.asList(movies.get(i-1),actors.get(i-1)));
            edge2.addVertices(Arrays.asList(movies.get(i-1),actors.get(1000-i)));
            graph.addEdge(edge1);
            graph.addEdge(edge2);
            SameMovieHyperEdge sameMovieHyperEdge=new SameMovieHyperEdge("SameMovieActor"+i);
            sameMovieHyperEdge.addVertices(Arrays.asList(actors.get(i-1),actors.get(1000-i)));
            graph.addEdge(sameMovieHyperEdge);
        }
        System.out.println(graph);
        Thread.sleep(1000000);
        PrintStream stream =System.out;
        PrintStream fileOutputStream=new PrintStream("src\\file\\ScannerOutPut");
        System.setOut(fileOutputStream);
        System.out.println("GraphType=\"MovieGraph\"\n" +
                "GraphName=\"MyfavouriteMovies\"\n" +
                "VertexType=\"Movie\",\"Actor\",\"Director\"");
        Set<Movie> movieSet=graph.getMovies();
        Set<Actor> actorSet=graph.getActors();
        Set<Director> directorSet=graph.getDirectors();
        Set<MovieActorRelation> movieActorRelationSet=graph.getMovieActorRelations();
        Set<MovieDirectorRelation> movieDirectorRelationSet=graph.getMovieDirectorRelations();
        Set<SameMovieHyperEdge> sameMovieHyperEdgeSet=graph.getSameMovieHyperEdges();
        for (Movie movie:movieSet
             ) {
            System.out.println("Vertex=<\""+movie.getLabel()+"\",\"Movie\",<\""+movie.getYear()+"\",\""+movie.getCountry()+"\",\""+movie.getIMDb()+"\">>");
        }
        for (Actor actor:actorSet
             ) {
            System.out.println("Vertex=<\""+actor.getLabel()+"\",\"Actor\",<\""+actor.getAge()+"\",\""+actor.getSex()+"\">>");
        }
        for (Director director:directorSet
             ) {
            System.out.println("Vertex=<\""+director.getLabel()+"\",\"Director\",<\""+director.getAge()+"\",\""+director.getSex()+"\">>");
        }
        System.out.println("EdgeType=\"MovieActorRelation\",\"MovieDirectorRelation\",\"SameMovieHyperEdge\"");
        for (MovieDirectorRelation edge:movieDirectorRelationSet
             ) {
            System.out.println("Edge=<\""+edge.getLabel()+"\",\"MovieDirectorRelation\",\""+edge.getWeight()+"\",\""+edge.getMovie().getLabel()+"\",\""+edge.getDirector().getLabel()+"\",\"No\">");
        }
        for (MovieActorRelation edge:movieActorRelationSet
             ) {
            System.out.println("Edge=<\""+edge.getLabel()+"\",\"MovieDirectorRelation\",\""+edge.getWeight()+"\",\""+edge.getMovie().getLabel()+"\",\""+edge.getActor().getLabel()+"\",\"No\">");
        }
        String modo="HyperEdge=<\"edge12\",\"SameMovieHyperEdge\",{\"actor1\",\"actor2\"}>";
        for (SameMovieHyperEdge edge:sameMovieHyperEdgeSet
             ) {
            StringBuilder sb=new StringBuilder();
            sb.append("HyperEdge=<\"edge12\",\"SameMovieHyperEdge\",{");
            Set<Vertex> actors1=edge.getVertices();
            for (Vertex actor:actors1
                 ) {
                actor=(Actor)actor;
                sb.append("\""+actor.getLabel()+"\"");
                sb.append(",");
            }
            int length=sb.length();
            sb.delete(length-1,length);
            sb.append("}>");
            System.out.println(sb.toString());
        }
        Thread.sleep(10000000);
    }
}
