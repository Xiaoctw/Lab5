package src.vertex;

public class NetVertex extends Vertex {
    String ID=null;
    public NetVertex(String label) {
        super(label);
    }
/*
ID为字符串，用“.”分隔开，每部分取值为[0,255]
 */
    @Override
    public void fillVertexInfo(String[] args) {
        if (args.length!=1){
            return;
        }
        ID=args[0];
    }
    public String toStringAddToGraph(){
        return null;
    }
}
