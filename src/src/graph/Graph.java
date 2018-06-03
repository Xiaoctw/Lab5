package src.graph;

import java.util.Map;
import java.util.Set;

public interface Graph<L,E> {
    public static <L, E> Graph<L, E> empty() {
        return null;
    }

    ;

    public boolean addVertex(L v);

    public boolean removeVertex(L v);

    public Set<L> vertices();

    public Map<L, Double> sources(L target);

    public Map<L, Double> targets(L source);

    public boolean addEdge(E edge);

    public boolean removeEdge(E edge);

    public Set<E> edges();
}
