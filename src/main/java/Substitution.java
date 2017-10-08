public class Substitution {
    private final String src;
    private final String dst;

    public Substitution(String src, String dst) {
        this.src = src;
        this.dst = dst;
    }

    String substitute(String str){
        return str.replace(src, dst);
    }
}
