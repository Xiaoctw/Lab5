package src.application;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import src.edge.MovieActorRelation;
import src.edge.MovieDirectorRelation;
import src.edge.SameMovieHyperEdge;
import src.graph.MovieGraph;
import src.vertex.Actor;
import src.vertex.Director;
import src.vertex.Movie;
import src.vertex.Vertex;

import java.io.*;
import java.util.*;

import static org.junit.Assert.*;

public class Lab5AppTest {
    private int time1;
    private int time2;
    private int time3;
    private MovieGraph graph=new MovieGraph();
    @Before
    public void setUp(){
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
    }

    @Test
    public void testScanner() throws FileNotFoundException {
        long timesc0=System.currentTimeMillis();
        GraphPoetApp app=new GraphPoetApp();
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
            StringBuilder sb = new StringBuilder();
            sb.append("HyperEdge=<\"edge12\",\"SameMovieHyperEdge\",{");
            Set<Vertex> actors1 = edge.getVertices();
            for (Vertex actor : actors1
                    ) {
                actor = (Actor) actor;
                sb.append("\"" + actor.getLabel() + "\"");
                sb.append(",");
            }
            int length = sb.length();
            sb.delete(length - 1, length);
            sb.append("}>");
            System.out.println(sb.toString());
        }
        System.setOut(stream);
        long timesc1=System.currentTimeMillis();
        time1=(int)(timesc1-timesc0);
    }

    @Test
    public void testWritter() throws IOException {
        long timewr=System.currentTimeMillis();
        FileWriter fw=new FileWriter("src\\file\\WritterOutPut",true);
        BufferedWriter bufferedWriter=new BufferedWriter(fw);
        bufferedWriter.write("GraphType=\"MovieGraph\"\n" +
                "GraphName=\"MyfavouriteMovies\"\n" +
                "VertexType=\"Movie\",\"Actor\",\"Director\"\n");
        Set<Movie> movieSet=graph.getMovies();
        Set<Actor> actorSet=graph.getActors();
        Set<Director> directorSet=graph.getDirectors();
        Set<MovieActorRelation> movieActorRelationSet=graph.getMovieActorRelations();
        Set<MovieDirectorRelation> movieDirectorRelationSet=graph.getMovieDirectorRelations();
        Set<SameMovieHyperEdge> sameMovieHyperEdgeSet=graph.getSameMovieHyperEdges();
        for (Movie movie:movieSet
                ) {
            bufferedWriter.write("Vertex=<\""+movie.getLabel()+"\",\"Movie\",<\""+movie.getYear()+"\",\""+movie.getCountry()+"\",\""+movie.getIMDb()+"\">>\n");
        }
        for (Actor actor:actorSet
                ) {
            bufferedWriter.write("Vertex=<\""+actor.getLabel()+"\",\"Actor\",<\""+actor.getAge()+"\",\""+actor.getSex()+"\">>\n");
        }
        for (Director director:directorSet
                ) {
            bufferedWriter.write("Vertex=<\""+director.getLabel()+"\",\"Director\",<\""+director.getAge()+"\",\""+director.getSex()+"\">>\n");
        }
        bufferedWriter.write("EdgeType=\"MovieActorRelation\",\"MovieDirectorRelation\",\"SameMovieHyperEdge\"\n");
        for (MovieDirectorRelation edge:movieDirectorRelationSet
                ) {
            bufferedWriter.write("Edge=<\""+edge.getLabel()+"\",\"MovieDirectorRelation\",\""+edge.getWeight()+"\",\""+edge.getMovie().getLabel()+"\",\""+edge.getDirector().getLabel()+"\",\"No\">\n");
        }
        for (MovieActorRelation edge:movieActorRelationSet
                ) {
            bufferedWriter.write("Edge=<\""+edge.getLabel()+"\",\"MovieDirectorRelation\",\""+edge.getWeight()+"\",\""+edge.getMovie().getLabel()+"\",\""+edge.getActor().getLabel()+"\",\"No\">\n");
        }
        for (SameMovieHyperEdge edge:sameMovieHyperEdgeSet
                ) {
            StringBuilder sb = new StringBuilder();
            sb.append("HyperEdge=<\"edge12\",\"SameMovieHyperEdge\",{");
            Set<Vertex> actors1 = edge.getVertices();
            for (Vertex actor : actors1
                    ) {
                actor = (Actor) actor;
                sb.append("\"" + actor.getLabel() + "\"");
                sb.append(",");
            }
            int length = sb.length();
            sb.delete(length - 1, length);
            sb.append("}>\n");
            bufferedWriter.write(sb.toString());
        }
        bufferedWriter.close();
        fw.close();
        long timewr1=System.currentTimeMillis();
        time2=(int)(timewr1-timewr);
    }

    @Test
    public void testStream() throws FileNotFoundException {
        long timest=System.currentTimeMillis();
        File file=new File("src\\file\\StreamOutPut");
        PrintStream printStream=new PrintStream(new FileOutputStream(file));
//        printStream.println("fsfs");
//        printStream.println("rew");
        printStream.println("GraphType=\"MovieGraph\"\n" +
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
            printStream.println("Vertex=<\""+movie.getLabel()+"\",\"Movie\",<\""+movie.getYear()+"\",\""+movie.getCountry()+"\",\""+movie.getIMDb()+"\">>");
        }
        for (Actor actor:actorSet
                ) {
           printStream.println("Vertex=<\""+actor.getLabel()+"\",\"Actor\",<\""+actor.getAge()+"\",\""+actor.getSex()+"\">>");
        }
        for (Director director:directorSet
                ) {
           printStream.println("Vertex=<\""+director.getLabel()+"\",\"Director\",<\""+director.getAge()+"\",\""+director.getSex()+"\">>");
        }
        printStream.println("EdgeType=\"MovieActorRelation\",\"MovieDirectorRelation\",\"SameMovieHyperEdge\"");
        for (MovieDirectorRelation edge:movieDirectorRelationSet
                ) {
            printStream.println("Edge=<\""+edge.getLabel()+"\",\"MovieDirectorRelation\",\""+edge.getWeight()+"\",\""+edge.getMovie().getLabel()+"\",\""+edge.getDirector().getLabel()+"\",\"No\">");
        }
        for (MovieActorRelation edge:movieActorRelationSet
                ) {
            printStream.println("Edge=<\""+edge.getLabel()+"\",\"MovieDirectorRelation\",\""+edge.getWeight()+"\",\""+edge.getMovie().getLabel()+"\",\""+edge.getActor().getLabel()+"\",\"No\">");
        }
     //   String modo="HyperEdge=<\"edge12\",\"SameMovieHyperEdge\",{\"actor1\",\"actor2\"}>";
        for (SameMovieHyperEdge edge:sameMovieHyperEdgeSet
                ) {
            StringBuilder sb = new StringBuilder();
            sb.append("HyperEdge=<\"edge12\",\"SameMovieHyperEdge\",{");
            Set<Vertex> actors1 = edge.getVertices();
            for (Vertex actor : actors1
                    ) {
                actor = (Actor) actor;
                sb.append("\"" + actor.getLabel() + "\"");
                sb.append(",");
            }
            int length = sb.length();
            sb.delete(length - 1, length);
            sb.append("}>");
           printStream.println(sb.toString());
        }
        long timest2=System.currentTimeMillis();
        time3=(int) (timest2-timest);
    }

//    @After
//    public void tearDown() throws Exception {
//        Demo demo=new Demo(time1,time2,time3);
//        demo.setVisible(true);
//        System.out.println("success");
//    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Lab5AppTest test=new Lab5AppTest();
        test.setUp();
        test.testScanner();
        Thread.sleep(100000);
        test.testWritter();
        test.testStream();
        Demo demo=new Demo(test.time1,test.time2,test.time3);
        demo.setVisible(true);
    }

}