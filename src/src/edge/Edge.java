package src.edge;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import src.vertex.Vertex;
/**
 * This is a javadoc.
 * @author xiao
 * @since lab5
 * @see javax.swing.JMenu
 * */
public abstract class Edge {
    /**
     */
    protected Collection<Vertex> vertices = null;
    /**
     */
    private String label = null;
    /**
     */
    private double weight = -1;
    /**
     *
     * @param label 边的标记
     * @param weight 边的权值，如果是无向边权值为-1
     */
    public Edge(final String label, final double weight) {
        this.label = label;
        this.weight = weight;
    }

    /**
     * @return 返回权值
     */
    public double getWeight() {
        return weight;
    }

    /**
     * @return 返回边的标记
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param vertices 需要增加的顶点的集合
     * @return 返回是否添加成功
     */
    abstract public boolean addVertices(List<Vertex> vertices);

    /**
     * @param v 需要判断是否在这条边中的顶点
     * @return 返回是否存在
     */
    public boolean containVertex(final Vertex v) {
        if (vertices.contains(v)) {
            return true;
        }
        return false;
    }

    /**
     * @param weight 设置权值
     */
    public void setWeight(final double weight) {
        this.weight = weight;
    }
    /**
     * @return 顶点集合
     */
    abstract Set<Vertex> sourceVertices();
    /**
     * @return 顶点的集合
     */
    abstract Set<Vertex> targetVertices();
}