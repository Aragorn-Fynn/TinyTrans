package ast;

/**
 * 中间代码产生的临时变量
 */
public class Temp implements Address {

    private static int count=0;
    private String name;

    public Temp() {
        count++;
        this.name = "t"+count;
    }

    public String getName() {
        return name;
    }
}
