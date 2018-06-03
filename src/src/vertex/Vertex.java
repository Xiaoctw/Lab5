package src.vertex;
public abstract class Vertex {
    private String label=null;

    public Vertex(String label) {
        this.label = label;
    }
    /*
    为特定的应用中的具体节点添加详细的属性信息。
     */
    abstract public void fillVertexInfo(String[] args);

    public String getLabel() {
        return label;
    }
}