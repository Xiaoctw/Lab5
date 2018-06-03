package src.edge;
import src.vertex.Person;
import src.vertex.Vertex;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PersonEdge extends Edge {
    List<Vertex> vertices;
    {
        super.vertices=new ArrayList<>();
        vertices = (List<Vertex>) super.vertices;
    }
    public PersonEdge(String label, double weight) {
        super(label, weight);
    }
    @Override
    public boolean addVertices(List<Vertex> vertices) {
        for (Vertex vertex:vertices
                ) {
            if (!(vertex instanceof Person)){
                return false;
            }
        }
        if (vertices.size()!=2){
            return false;
        }
        if (this.vertices.size()==0){
            this.vertices.addAll(vertices);
        }
        if (vertices.get(0).equals(this.vertices.get(0))&&vertices.get(1).equals(this.vertices.get(1))){
            return false;
        }
        this.vertices.addAll(vertices);
        return true;
    }

    @Override
    Set<Vertex> sourceVertices() {
        Set<Vertex> set=new HashSet<>();
        set.add(vertices.get(0));
        return set;
    }
    @Override
    Set<Vertex> targetVertices() {
        Set<Vertex> set=new HashSet<>();
        set.add(vertices.get(1));
        return set;
    }
    public String source(){
        return this.vertices.get(0).getLabel();
    }
    public String target(){
        return this.vertices.get(1).getLabel();
    }
    /*
返回“姓名->姓名”的字符串
 */
    @Override
    public String toString() {
        Person person=(Person)vertices.get(0);
        Person person1=(Person)vertices.get(1);
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(person.getLabel());//由于person类的getlabel与toString不同，因此要分着写
        stringBuilder.append("->");
        stringBuilder.append(person1.getLabel());
        return stringBuilder.toString();
    }

    public List<Vertex> getVertices() {
        return vertices;
    }
}