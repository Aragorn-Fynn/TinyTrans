package symtable;

/**
 * float类型
 */
public class FloatType extends Symbol implements Type {

    public FloatType() {
        super("float");
        super.setType(this);
    }

    public int getWidth() {
        return 8;
    }
}
