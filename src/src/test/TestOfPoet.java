package src.test;
import org.junit.Assert;
import org.junit.Test;
import src.edge.*;
import src.vertex.*;
import src.graph.*;

import java.util.*;

public class TestOfPoet {
    Word book=new Word("book");
    Person person=new Person("xiao");
    Word book1=new Word("book");
    Word keyboard=new Word("keyboard");
    Word computer=new Word("computer");
    Word desk=new Word("desk");
    @Test
    public void testWord(){
        Assert.assertEquals(book.equals(book1),true);
        Assert.assertEquals(book.equals(person),false);
        Assert.assertEquals(book.equals(keyboard),false);
        System.out.println(book.toString());
    }
    @Test
    public void testOfWordEdge(){
        WordNeighborhood edge=new WordNeighborhood("label1",3);
        List<Vertex> list=new ArrayList();
        list.add(book);
        list.add(keyboard);
        edge.addVertices(list);
        WordNeighborhood edge1=new WordNeighborhood("label2",4);
        List<Vertex> list1=new ArrayList<>();
        list1.add(book);
        list1.add(computer);
        edge1.addVertices(list1);
        System.out.println(edge.toString());
        System.out.println(edge1.toString());
        //检验equals方法
        Assert.assertEquals(false,edge.equals(edge1));
        Set<Word> set=new HashSet<>();
        Set<Word> set1=new HashSet<>();
        set.add(book);
        set1.add(keyboard);
        //检验sources和targets方法
        Assert.assertEquals(set,edge.sourceVertices());
        Assert.assertEquals(set1,edge.targetVertices());
        Assert.assertEquals(book.getLabel(),edge.source());
        Assert.assertEquals(keyboard.getLabel(),edge.target());
    }
    @Test
    public void testGraph(){
        WordNeighborhood edge=new WordNeighborhood("label1",3);
        List<Vertex> list=new ArrayList();
        list.add(book);
        list.add(keyboard);
        edge.addVertices(list);
        WordNeighborhood edge1=new WordNeighborhood("label2",4);
        List<Vertex> list1=new ArrayList<>();
        list1.add(book);
        list1.add(computer);
        edge1.addVertices(list1);
        WordNeighborhood edge2=new WordNeighborhood("label3",5);
        List<Vertex> list2=new ArrayList<>();
        list2.add(computer);
        list2.add(desk);
        edge2.addVertices(list2);
        WordNeighborhood edge3=new WordNeighborhood("label4",2);
        List<Vertex> list3=new ArrayList<>();
        list3.add(book);
        list3.add(desk);
        edge3.addVertices(list3);
        WordPoet graph=new WordPoet();
        graph.addVertex(book);
        graph.addVertex(keyboard);
        graph.addVertex(computer);
        graph.addVertex(desk);
        graph.addEdge(edge);
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        Map<Word,Double> map=new HashMap<>();
        Map<Word,Double> map1=new HashMap<>();
        map.put(book,2.0);
        map.put(computer,5.0);
        map1.put(desk,2.0);
        map1.put(keyboard,3.0);
        map1.put(computer,4.0);
        Assert.assertEquals(map,graph.sources(desk));
        Assert.assertEquals(map1,graph.targets(book));
        System.out.println(graph.sources(desk));
        System.out.println(graph.targets(book));
    }
}
