package src.edge;


import src.vertex.Vertex;
import src.vertex.Word;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordNeighborhood extends Edge {
    List<Vertex> vertices;
    {
        vertices = (List<Vertex>) super.vertices;
        this.vertices=new ArrayList<>();
    }
    public WordNeighborhood(String label, double weight) {
        super(label, weight);
    }
    /*
    第一个元素作为source，第二个元素作为target
     */
    @Override
    public boolean addVertices(List<Vertex> vertices) {
        if (!(vertices.get(0) instanceof Word)){
            return false;
        }
        //首先要判断list是否为空
        if (this.vertices.size()==0){
            this.vertices.addAll(vertices);
            return true;
        }
        //相等的条件
        if (vertices.get(0).equals(this.vertices.get(0))&&vertices.get(1).equals(this.vertices.get(1))){
            return false;
        };
        this.vertices.addAll(vertices);
        return true;
    }

    @Override
    public Set<Vertex> sourceVertices() {
        Set<Vertex> set=new HashSet<>();
        set.add(vertices.get(0));
        return set;
    }
    public String source(){
        return this.vertices.get(0).getLabel();
    }
    public String target(){
        return this.vertices.get(1).getLabel();
    }
    @Override
    public Set<Vertex> targetVertices() {
        Set<Vertex> set=new HashSet<>();
        set.add(vertices.get(1));
        return set;
    }

    @Override
    public String toString() {
        //return vertices.get(0).toString()+""
        Word word=(Word) vertices.get(0);//这样做的原因是vertex类没有重写toString，Word重写了
        Word word1=(Word) vertices.get(1);
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(word.toString());
        stringBuilder.append("->");
        stringBuilder.append(word1.toString());
        stringBuilder.append(" "+getLabel());
        stringBuilder.append(" 权值"+getWeight());
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof WordNeighborhood){
            obj=(WordNeighborhood)obj;
            Word word=(Word) vertices.get(0);//这样做的原因是vertex类没有重写toString，Word重写了
            Word word1=(Word) vertices.get(1);
            String str1=word.toString();
            String str2=word1.toString();
            String str3=((Word)(((WordNeighborhood) obj).vertices.get(0))).toString();
            String str4=((Word)(((WordNeighborhood) obj).vertices.get(1))).toString();
            if (str1.equals(str3)&&str2.equals(str4)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return vertices.get(0).toString().length()+vertices.get(1).toString().length();
    }

    public List<Vertex> getVertices() {
        return vertices;
    }
}
