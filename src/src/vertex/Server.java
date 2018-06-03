package src.vertex;

public class Server extends NetVertex{

    public Server(String label) {
        super(label);
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append("服务器名称：");
        sb.append(getLabel());
        sb.append("\nID为：");
        sb.append(ID);
        return sb.toString();
    }
    public String toStringAddToGraph(){
        StringBuilder sb=new StringBuilder();
        sb.append("服务器名称：");
        sb.append(getLabel());
        sb.append(" ID为：");
        sb.append(ID);
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Server)){
            return false;
        }
        try {//如果并没有设立ID的话，那么会自动返回false
            if (this.getLabel().equals(((Server) obj).getLabel()) && this.ID.equals(((Server) obj).ID)) {
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
