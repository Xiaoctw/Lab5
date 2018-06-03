package src.vertex;

public class Person extends Vertex {
    private String sex;
    private int age;
    /*
    对于person来说，label是对应的姓名
     */
    public Person(String label) {
        super(label);
    }
    @Override
    public void fillVertexInfo(String[] args) {
        this.sex=args[0];
        this.age=Integer.parseInt(args[1]);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("姓名：");
        stringBuilder.append(getLabel());
        stringBuilder.append("\n");
        stringBuilder.append("性别：");
        stringBuilder.append(sex);
        stringBuilder.append("\n");
        stringBuilder.append("年龄："+age);
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
    public String toStringAddToGraph(){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("姓名：");
        stringBuilder.append(getLabel());
        // stringBuilder.append("\n");
        stringBuilder.append(" 性别：");
        stringBuilder.append(sex);
        //stringBuilder.append("\n");
        stringBuilder.append(" 年龄："+age);
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
    /*
    当且仅当这几个条件同时满足时，才返回true
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Person)){
            return false;
        }
        try {
            boolean labelSame=getLabel().equals(((Person)obj).getLabel());
            boolean sexSame=sex.equals(((Person)obj).sex);
            boolean ageSame=(age==((Person) obj).age);
            return labelSame&&sexSame&&ageSame;
        }catch (NullPointerException e){
            return false;
        }


    }

    @Override
    public int hashCode() {
        return this.age+this.sex.length()+this.getLabel().length();
    }
}
