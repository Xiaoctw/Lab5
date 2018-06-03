package src.vertex;

public class Word extends Vertex {
    public Word(String label) {
        super(label);
    }
    /*
    danci这部分没有对应的属性
     */
    @Override
    public void fillVertexInfo(String[] args) {}
    /*
    对于word来说只需返回一个字符串
     */
    @Override
    public String toString() {
        return getLabel();
    }
    /*
    判断两个节点是否相同时需要比较字符串是否相等。
     */
    @Override
    public boolean equals(Object obj) {
        // return super.equals(obj);
        if (obj instanceof Word){
            obj=(Word)obj;
            if (((Word) obj).getLabel().equals(this.getLabel())){
                return true;
            }
        }
        return false;
    }
    /*
    此处就以字符串的长度作为hashcode的返回值
     */
    @Override
    public int hashCode() {
        //return super.hashCode();
        return getLabel().length();
    }
}

