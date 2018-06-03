package src.vertex;

public class Computer extends  NetVertex{
    /*
    label为主机名字
     */
    public Computer(String label) {
        super(label);
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append("计算机名称：");
        sb.append(getLabel());
        sb.append("\nID为：");
        sb.append(ID);
        return sb.toString();
    }
    public String toStringAddToGraph(){
        StringBuilder sb=new StringBuilder();
        sb.append("计算机名称：");
        sb.append(getLabel());
        sb.append(" ID为：");
        sb.append(ID);
        return sb.toString();
    }
    @Override
    public int hashCode() {
        return this.toString().length()+this.ID.length();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Computer)){
            return false;
        }
        obj=(Computer)obj;
        try {//如果并没有设立ID的话，那么会自动返回false
            if (this.getLabel().equals(((Computer) obj).getLabel()) && this.ID.equals(((Computer) obj).ID)) {
                return true;
            }
        }catch (Exception e){
            return false;
        }
        return false;
    }
}
