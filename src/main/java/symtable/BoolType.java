package symtable;

/**
 * Bool类型
 */
public class BoolType extends Symbol implements Type {

    public BoolType() {
        super("bool");
        super.setType(this);
    }

    public int getWidth() {
        return 1;
    }
}
