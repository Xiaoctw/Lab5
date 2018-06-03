package src.application;

import org.junit.Test;
import src.edge.MovieActorRelation;
import src.edge.MovieDirectorRelation;
import src.edge.SameMovieHyperEdge;
import src.vertex.Actor;
import src.vertex.Director;
import src.vertex.Movie;
import src.vertex.Person;
import src.graph.*;
import src.vertex.*;
import src.edge.*;
import src.graph.MoiveGraphFactory;
import src.exception.*;
import sun.rmi.runtime.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.Logger;

import static javafx.application.Platform.exit;

/**
 * @author xiao
 * @since lab5
 */
public class MovieGraphApp {
    /**
     *
     * @throws FileNotFoundException
     */
    public MovieGraphApp() throws FileNotFoundException {
        Logger logger=Logger.getLogger("log");
        Set<MovieVertex> set = new HashSet<>();
        String pattern = "GraphType=.*";
        String pattern1 = "GraphName=.*";
        String pattern2 = "VertexType=.*";
        String sample3 = "Vertex=<\"movie1\",\"Movie\",<\"2014\",\"China\",\"8.5\">>";
        String pattern3 = "Vertex=<\".*\",\"Movie\",<\".*\",\".*\",\".*\">>";
        String sample4 = "Vertex=<\"actor1\",\"Actor\",<\"20\",\"F\">>";
        String pattern4 = "Vertex=<\".*\",\"Actor\",<\".*\",\"[FM]\">>";
        String sample5 = "Vertex=<\"director1\",\"Director\",<\"60\",\"M\">>";
        String pattern5 = "Vertex=<\".*\",\"Director\",<\".*\",\"[FM]\">";
        String pattern6 = "EdgeType=.*";
        String sample7 = "Edge=<\"edge1\",\"MovieDirectorRelation\",\"-1\",\"movie1\",\"director1\",\"No\">";
        String pattern7 = "Edge=<\".*\",\"MovieDirectorRelation\",\".*\",\".*\",\".*\",\"No\"";
        String pattern9 = "Edge=<\".*\",\"MovieActorRelation\",\".*\",\".*\",\".*\",\"No\"";
        String sample8 = "HyperEdge=<\"edge12\",\"SameMovieHyperEdge\",{\"actor1\",\"actor2\"}>";
        String pattern8 = "HyperEdge=<\".*\",\"SameMovieHyperEdge\",.\".*\",\".*\".>";
        System.out.println("Vertex=<\"movie1\",\"Movie\",<\"2014\",\"China\",\"8.5\">>".matches(pattern3));
        FileInputStream stream = new FileInputStream("src\\file\\movieGraph");
        MoiveGraphFactory factory = new MoiveGraphFactory();
        NetworkTopologyFactory factory1 = new NetworkTopologyFactory();
        ConcreteGraph graph = null;
        Scanner in = new Scanner(stream);
        boolean flag = false;
            while (in.hasNext()) {
                try {
               flag=false;
                String line = in.nextLine().trim();
                if (line.matches(pattern)) {
                    String graphType = line.substring(11, line.length() - 1);
                    if (graphType.equals("MovieGraph")) {
                        graph = (MovieGraph) factory.createGraph();
                    } else if (graphType.equals("NetworkTopology")) {
                        graph = (NetworkTopology) factory1.createGraph();
                    } else if (graphType.equals("GraphPoet")) {
                        graph = new WordPoet();
                    }
                    logger.info("创建图："+graphType);
                    flag = true;
                }
                if (line.matches(pattern1)) {
                    String name = line.substring(10, line.length() - 1);
                    graph.setName(name);
                    flag = true;
                }
                if (line.matches(pattern2)) {
                    String types = line.substring(11, line.length() - 1);
                    flag = true;
                }
                if (line.matches(pattern3)) {
                    List<String> strings = Arrays.asList(line.split("\""));
                    String movieName = strings.get(1);
                    String year = strings.get(5);
                    String country = strings.get(7);
                    String IMDb = strings.get(9);
                    Movie movie = factory.createMovie(movieName, new String[]{year, country, IMDb});
                    set.add(movie);
                    logger.info("添加电影"+movieName);
                    graph.addVertex(movie);
                    flag = true;
                }
                if (line.matches(pattern4)) {
                    List<String> strings = Arrays.asList(line.split("\""));
                    String actorName = strings.get(1);
                    String age = strings.get(5);
                    String sex = strings.get(7);
                    Actor actor = factory.createActor(actorName, new String[]{age, sex});
                    set.add(actor);
                    logger.info("添加演员"+actorName);
                    graph.addVertex(actor);
                    flag = true;
                }
                if (line.matches(pattern5)) {
                    List<String> strings = Arrays.asList(line.split("\""));
                    String directorName = strings.get(1);
                    String age = strings.get(5);
                    String sex = strings.get(7);
                    Director director = factory.createDirector(directorName, new String[]{age, sex});
                    set.add(director);
                    logger.info("添加导演"+directorName);
                    graph.addVertex(director);
                    flag = true;
                }
                if (line.matches(pattern6)) {
                    String edgeTypes = line.substring(10, line.length() - 1);
                    flag = true;
                }
                if (line.matches(pattern7)) {
                    List<String> strings = Arrays.asList(line.split("\""));
                    String edgeName = strings.get(1);
                    String weight = strings.get(5);
                    String movie = strings.get(7);
                    String dir = strings.get(9);
                    Movie movie1 = null;
                    Director director = null;
                    for (MovieVertex vertex : set
                            ) {
                        if (vertex.getLabel().equals(movie)) {
                            movie1 = (Movie) vertex;
                        }
                        if (vertex.getLabel().equals(dir)) {
                            director = (Director) vertex;
                        }
                    }
                    if (movie1 == null || director == null) {
                        throw new NotDefinedException();
                    }
                    MovieDirectorRelation edge = new MovieDirectorRelation(edgeName);
                    edge.addVertices(Arrays.asList(director, movie1));
                    graph.addEdge(edge);
                    logger.info("添加边："+director.getLabel()+"和"+movie1.getLabel());
                    flag = true;
                }
                if (line.matches(pattern9)) {
                    List<String> strings = Arrays.asList(line.split("\""));
                    String edgeName = strings.get(1);
                    String weight = strings.get(5);
                    String movie = strings.get(7);
                    String actor = strings.get(9);
                    Movie movie1 = null;
                    Actor actor1 = null;
                    for (MovieVertex vertex : set
                            ) {
                        if (vertex.getLabel().equals(movie)) {
                            movie1 = (Movie) vertex;
                        }
                        if (vertex.getLabel().equals(actor)) {
                            actor1 = (Actor) vertex;
                        }
                    }
                    if (movie1 == null || actor1 == null) {
                        throw new NotDefinedException();
                    }
                    MovieActorRelation relation = new MovieActorRelation(edgeName, Double.parseDouble(weight));
                    relation.addVertices(Arrays.asList(movie1, actor1));
                    logger.info("添加边"+movie1.getLabel()+"和"+actor1.getLabel());
                    graph.addEdge(relation);
                    flag = true;
                }
                if (line.matches(pattern8)) {
                    List<String> strings = Arrays.asList(line.split("\""));
                    List<Vertex> actors = new ArrayList<>();
                    int numberOfVertex = strings.size() - 8;
                    String edgeName = strings.get(1);
                    for (int i = 1; i <= numberOfVertex; i++) {
                        boolean found = false;
                        String vertex = strings.get(strings.size() - 2 * i);
                        for (Vertex v : set
                                ) {
                            if (v.getLabel().equals(vertex)) {
                                actors.add((Actor) v);
                                found = true;
                            }
                        }
                        if (!found) {
                            throw new NotDefinedException();
                        }
                    }
                    StringBuilder sb=new StringBuilder();
                    sb.append("添加边:");
                    for (Vertex ac:actors
                         ) {
                        sb.append(ac.getLabel()+" ");
                    }
                    SameMovieHyperEdge edge = new SameMovieHyperEdge(edgeName);
                    logger.info(sb.toString());
                    edge.addVertices(actors);
                    graph.addEdge(edge);
                    flag = true;
                }
                if (!flag) {
                    if (line.substring(7).equals("Vertex=")) {
                        if ('<' != line.charAt(7)) {
                            throw new NotGrammatialException();
                        }
                        if (line.contains("Actor") && (line.split(",")).length != 4) {
                            throw new NumberOfAttributesException();
                        }
                    }
                    if (line.substring(5).equals("Edge=")) {
                        if (line.charAt(5) != '<') {
                            throw new NotGrammatialException();
                        }
                        if (line.charAt(15) != 'M' || line.charAt(15) != 'S') {
                            throw new TypeMismatchException1();
                        }
                    }
                    if (line.substring(10).equals("HyperEdge=")) {
                        if (!(graph instanceof MovieGraph)){
                            throw new HyperEdgeException();
                        }
                        if (line.split(",").length<=4){
                            throw new HyperEdgeNumberException();
                        }
                        if (line.charAt(10) != '<') {
                            throw new NotGrammatialException();
                        }
                    }
                    if ((line.charAt(9) != 'D' || line.charAt(9) != 'A' || line.charAt(9) != 'M')&&(graph instanceof MovieGraph)) {
                        throw new TypeMismatchException();
                    }
                    if (graph instanceof SocialNetwork){
                        for (Edge edge:graph.edges()
                             ) {
                            if (edge.getWeight()>1||edge.getWeight()<=0){
                                throw new WeightException();
                            }
                        }
                    }
                }

        } catch (NotGrammatialException e) {
                    logger.info("输入格式错误");
                    flag=true;
        } catch (NotDefinedException e) {
                    logger.info("边中含有未定义的顶点");
                    System.out.println("文件格式错误，您应该选择其他文件");
                    exit();
        flag=true;
        } catch (NumberOfAttributesException e) {
                    logger.info("参数数量错误");
                    flag=true;
        } catch (TypeMismatchException e) {
                    logger.info("类型不匹配");
                    System.out.println("文件格式错误，您应该选择其他文件");
                    exit();
                    flag=true;
        }catch (HyperEdgeException e){
                    logger.info("超边中顶点数量太少");
                    flag=true;
        }catch (TypeMismatchException1 e) {
                    logger.info("格式类型不匹配");
                    flag=true;
        }catch (HyperEdgeNumberException e){
            System.out.println("文件格式错误，您应该选择其他文件");
            exit();
        } catch (WeightException e){
                    logger.info("权值不符合要求");
            System.out.println("文件格式错误，您应该选择其他文件");
            exit();
        }finally{
            try {

                Set<Edge> edges1 = new HashSet<>();
                Set<Edge> edges = graph.edges();
                for (Edge edge : edges
                        ) {
                    if ((graph instanceof NetworkTopology)) {
                        if (!(edge instanceof NetworkConnection)) {
                            edges1.add(edge);
                        }
                    }else {
                        if (edge instanceof NetworkConnection){
                            edges1.add(edge);
                        }
                    }

                }
                edges.removeAll(edges1);
                if (graph instanceof NetworkTopology) {
                    logger.info("无向图中出现了有向边");
                    throw new DIrectedGraphHasNoneException();
                }else {
                    throw new NoneDirectGraphHasDirException();
                }
            } catch (DIrectedGraphHasNoneException e) {
                System.out.println("文件格式错误，您应该选择其他文件");
                exit();
            }catch (NoneDirectGraphHasDirException e){
                flag=true;
            }
        }
        try {
                 if (!flag){
                     throw new REException();
                 }
        }catch (REException e){
                 System.out.println("文件格式错误，您应该选择其他文件");
                 exit();
        }
    }
    }

    /**
     *
     */
    @Test
    public void CreateMovieGraph(){
      //  MovieGraph graph=new MovieGraph();
        MoiveGraphFactory factory=new MoiveGraphFactory();
        MovieGraph graph= (MovieGraph) factory.createGraph("filepath");
        Actor actor1=new Actor("actor1");
        Actor actor2=new Actor("actor2");
        Actor actor3=new Actor("actor3");
        Actor actor4=new Actor("actor4");
        Actor actor5=new Actor("actor5");
        Actor actor6=new Actor("actor6");
        Actor actor7=new Actor("actor7");
        Person xiao=new Person("xiao");
        Movie movie1=new Movie("movie1");
        Movie movie2=new Movie("movie2");
        Movie movie3=new Movie("movie3");
        Director director1=new Director("director1");
        Director director2=new Director("director2");
        MovieDirectorRelation movieDirectorRelation1=new MovieDirectorRelation("edge1");
        MovieDirectorRelation movieDirectorRelation2=new MovieDirectorRelation("edge2");
        MovieDirectorRelation movieDirectorRelation3=new MovieDirectorRelation("edge3");
        MovieActorRelation movieActorRelation1=new MovieActorRelation("edge4",1.0);
        MovieActorRelation movieActorRelation2=new MovieActorRelation("edge5",2.0);
        MovieActorRelation movieActorRelation3=new MovieActorRelation("edge6",1.5);
        MovieActorRelation movieActorRelation4=new MovieActorRelation("edge7",2.1);
        MovieActorRelation movieActorRelation5=new MovieActorRelation("edge8",1.8);
        MovieActorRelation movieActorRelation6=new MovieActorRelation("edge9",1.9);
        MovieActorRelation movieActorRelation7=new MovieActorRelation("edge10",1.6);
        MovieActorRelation movieActorRelation8=new MovieActorRelation("edge11",1.5);
        SameMovieHyperEdge sameMovieHyperEdge1=new SameMovieHyperEdge("edge12");
        SameMovieHyperEdge sameMovieHyperEdge2=new SameMovieHyperEdge("edge13");
        SameMovieHyperEdge sameMovieHyperEdge3=new SameMovieHyperEdge("edge14");
        xiao.fillVertexInfo(new String[]{"male","19"});
        actor1.fillVertexInfo(new String[]{"20","female"});
        actor2.fillVertexInfo(new String[]{"30","male"});
        actor3.fillVertexInfo(new String[]{"29","female"});
        actor4.fillVertexInfo(new String[]{"40","male"});
        actor5.fillVertexInfo(new String[]{"29","female"});
        actor6.fillVertexInfo(new String[]{"28","male"});
        actor7.fillVertexInfo(new String[]{"30","female"});
        movie1.fillVertexInfo(new String[]{"2014","China","8.5"});
        movie2.fillVertexInfo(new String[]{"2018","America","6.8"});
        movie3.fillVertexInfo(new String[]{"2017","Japan","8.8"});
        director1.fillVertexInfo(new String[]{"60","male"});
        director2.fillVertexInfo(new String[]{"47","male"});
        movieDirectorRelation1.addVertices(Arrays.asList(director1,movie1));
        movieDirectorRelation2.addVertices(Arrays.asList(director2,movie2));
        movieDirectorRelation3.addVertices(Arrays.asList(director2,movie3));
        movieActorRelation1.addVertices(Arrays.asList(actor1,movie1));
        movieActorRelation2.addVertices(Arrays.asList(movie1,actor2));
        movieActorRelation3.addVertices(Arrays.asList(movie2,actor2));
        movieActorRelation4.addVertices(Arrays.asList(movie2,actor3));
        movieActorRelation5.addVertices(Arrays.asList(movie2,actor4));
        movieActorRelation6.addVertices(Arrays.asList(movie3,actor5));
        movieActorRelation7.addVertices(Arrays.asList(actor3,actor6));
        movieActorRelation8.addVertices(Arrays.asList(actor7,movie3));
        sameMovieHyperEdge1.addVertices(Arrays.asList(actor1,actor2));
        sameMovieHyperEdge2.addVertices(Arrays.asList(actor2,actor3,actor4));
        sameMovieHyperEdge3.addVertices(Arrays.asList(actor5,actor6,actor7));
        graph.addVertices(actor1,actor2,actor3,actor4,actor5,actor6,actor7);
        graph.addVertices(director1,director2);
        graph.addVertices(movie1,movie2,movie3);
        graph.addEdges(movieActorRelation1,movieActorRelation2,movieActorRelation3,movieActorRelation4,movieActorRelation5,movieActorRelation6,movieActorRelation7,movieActorRelation8);
        graph.addEdges(movieDirectorRelation1,movieDirectorRelation2,movieDirectorRelation3);
        graph.addEdges(sameMovieHyperEdge1,sameMovieHyperEdge2,sameMovieHyperEdge3);
        Logger logger=Logger.getLogger("logger");
        logger.info("打印日志");
        System.out.println(sameMovieHyperEdge1);
    }
    @Test
    public void testPattern() throws FileNotFoundException {
        Set<MovieVertex> set=new HashSet<>();
        String pattern="GraphType=.*";
        String pattern1="GraphName=.*";
        String pattern2="VertexType=.*";
        String sample3="Vertex=<\"movie1\",\"Movie\",<\"2014\",\"China\",\"8.5\">>";
        String pattern3="Vertex=<\".*\",\"Movie\",<\".*\",\".*\",\".*\">>";
        String sample4="Vertex=<\"actor1\",\"Actor\",<\"20\",\"F\">>";
        String pattern4="Vertex=<\".*\",\"Actor\",<\".*\",\"[FM]\">>";
        String sample5="Vertex=<\"director1\",\"Director\",<\"60\",\"M\">>";
        String pattern5="Vertex=<\".*\",\"Director\",<\".*\",\"[FM]\">";
        String pattern6="EdgeType=.*";
        String sample7="Edge=<\"edge1\",\"MovieDirectorRelation\",\"-1\",\"movie1\",\"director1\",\"No\">";
        String pattern7="Edge=<\".*\",\"MovieDirectorRelation\",\".*\",\".*\",\".*\",\"No\"";
        String pattern9="Edge=<\".*\",\"MovieActorRelation\",\".*\",\".*\",\".*\",\"No\"";
        String sample8="HyperEdge=<\"edge12\",\"SameMovieHyperEdge\",{\"actor1\",\"actor2\"}>";
        String pattern8="HyperEdge=<\".*\",\"SameMovieHyperEdge\",.\".*\",\".*\".>";
        System.out.println("Vertex=<\"movie1\",\"Movie\",<\"2014\",\"China\",\"8.5\">>".matches(pattern3));
        FileInputStream stream=new FileInputStream("src\\file\\movieGraph");
        MoiveGraphFactory factory=new MoiveGraphFactory();
        MovieGraph graph = null;
        Scanner in=new Scanner(stream);
        while (in.hasNext()){
            String line=in.nextLine().trim();
            if (line.matches(pattern)){
                String graphType=line.substring(11,line.length()-1);
                if (graphType.equals("MovieGraph"));
                graph= (MovieGraph) factory.createGraph();
            }
            if (line.matches(pattern1)){
                String name=line.substring(10,line.length()-1);
                graph.setName(name);
            }
            if (line.matches(pattern2)){
                String types=line.substring(11,line.length()-1);
            }
            if (line.matches(pattern3)){
                List<String> strings=Arrays.asList(line.split("\""));
                String movieName=strings.get(1);
                String year=strings.get(5);
                String country=strings.get(7);
                String IMDb=strings.get(9);
                Movie movie=factory.createMovie(movieName, new String[]{year, country, IMDb});
                set.add(movie);
                graph.addVertex(movie);
            }
            if (line.matches(pattern4)){
                List<String> strings=Arrays.asList(line.split("\""));
                String actorName=strings.get(1);
                String age=strings.get(5);
                String sex=strings.get(7);
                Actor actor=factory.createActor(actorName,new String[]{age,sex});
                set.add(actor);
                graph.addVertex(actor);
            }
            if (line.matches(pattern5)){
                List<String> strings=Arrays.asList(line.split("\""));
                String directorName=strings.get(1);
                String age=strings.get(5);
                String sex=strings.get(7);
                Director director=factory.createDirector(directorName,new String[]{age,sex});
                set.add(director);
                graph.addVertex(director);
            }
            if (line.matches(pattern6)){
                String edgeTypes=line.substring(10,line.length()-1);
            }
            if (line.matches(pattern7)){
                List<String> strings=Arrays.asList(line.split("\""));
                String edgeName=strings.get(1);
                String weight=strings.get(5);
                String movie=strings.get(7);
                String dir=strings.get(9);
                Movie movie1=null;
                Director director=null;
                for (MovieVertex vertex:set
                     ) {
                    if (vertex.getLabel().equals(movie)){
                        movie1=(Movie) vertex;
                    }
                    if (vertex.getLabel().equals(dir)){
                        director=(Director) vertex;
                    }
                }
                MovieDirectorRelation edge=new MovieDirectorRelation(edgeName);
                edge.addVertices(Arrays.asList(director,movie1));
                graph.addEdge(edge);
            }
            if (line.matches(pattern9)){
                List<String> strings=Arrays.asList(line.split("\""));
                String edgeName=strings.get(1);
                String weight=strings.get(5);
                String movie=strings.get(7);
                String actor=strings.get(9);
                Movie movie1=null;
                Actor actor1=null;
                for (MovieVertex vertex:set
                     ) {
                    if (vertex.getLabel().equals(movie)){
                        movie1= (Movie) vertex;
                    }
                    if (vertex.getLabel().equals(actor)){
                        actor1=(Actor)vertex;
                    }
                }
                MovieActorRelation relation=new MovieActorRelation(edgeName,Double.parseDouble(weight));
                relation.addVertices(Arrays.asList(movie1,actor1));
                graph.addEdge(relation);
            }
            if (line.matches(pattern8)){
                List<String> strings=Arrays.asList(line.split("\""));
                List<Vertex> actors=new ArrayList<>();
                int numberOfVertex=strings.size()-8;
                String edgeName=strings.get(1);
                for (int i = 1; i <=numberOfVertex ; i++) {
                    String vertex=strings.get(strings.size()-2*i);
                    for (Vertex v:set
                         ) {
                        if (v.equals(vertex)){
                            actors.add((Actor) v);
                        }
                    }
                }
                SameMovieHyperEdge edge=new SameMovieHyperEdge(edgeName);
                edge.addVertices(actors);
                graph.addEdge(edge);
            }
        }
    }


    }

