package symtable;

/**
 * 整数类型
 */
public class IntType extends Symbol implements Type {
    public IntType() {
        super("int");
        super.setType(this);
    }
}
