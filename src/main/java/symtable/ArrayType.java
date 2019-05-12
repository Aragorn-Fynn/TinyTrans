package symtable;

/**
 * 数组类型
 */
public class ArrayType extends Symbol implements Type {
    private Type elementType;
    private int elementWidth;
    public ArrayType(Type elementType, int elementWidth) {
        super(elementType.getName()+"[]");
        super.setType(this);
        this.elementType = elementType;
        this.elementWidth = elementWidth;
    }

    public Type getElementType() {
        return elementType;
    }

    public void setElementType(Type elementType) {
        this.elementType = elementType;
    }

    public String getName() {
        return elementType.getName()+"[]";
    }

    public int getWidth() {
        return elementWidth;
    }

}
