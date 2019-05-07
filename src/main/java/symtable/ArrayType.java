package symtable;

/**
 * 数组类型
 */
public class ArrayType extends Symbol implements Type {
    private Type elementType;
    public ArrayType(Type elementType) {
        super(elementType.getName()+"[]", "array");
        this.elementType = elementType;
    }

    public Type getElementType() {
        return elementType;
    }

    public void setElementType(Type elementType) {
        this.elementType = elementType;
    }

    public String getName() {
        return super.getType();
    }
}
