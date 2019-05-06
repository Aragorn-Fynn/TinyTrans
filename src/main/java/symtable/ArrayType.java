package symtable;

/**
 * 数组类型
 */
public class ArrayType extends Type {
    private Type elementType;
    public ArrayType(Type elementType) {
        super(elementType.getName()+"[]");
        this.elementType = elementType;
    }

    public Type getElementType() {
        return elementType;
    }

    public void setElementType(Type elementType) {
        this.elementType = elementType;
    }
}
