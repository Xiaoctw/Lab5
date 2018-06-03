package src.vertex;

/*
路由器
 */
public class Router extends NetVertex {
    public Router(String label) {
        super(label);
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append("路由器名称：");
        sb.append(getLabel());
        sb.append("\nID为：");
        sb.append(ID);
        return sb.toString();
    }
    public String toStringAddToGraph(){
        StringBuilder sb=new StringBuilder();
        sb.append("路由器名称：");
        sb.append(getLabel());
        sb.append(" ID为：");
        sb.append(ID);
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Router)){
            return false;
        }
        try {//如果并没有设立ID的话，那么会自动返回false
            if (this.getLabel().equals(((Router) obj).getLabel()) && this.ID.equals(((Router) obj).ID)) {
                return true;
            }
        }catch (Exception e){
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.toString().length()+this.ID.length();
    }
}

