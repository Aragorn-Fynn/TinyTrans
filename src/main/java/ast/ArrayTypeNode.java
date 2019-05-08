package ast;

import lexer.Token;
import symtable.ArrayType;
import visitor.IVisitor;

/**
 * 数组类型节点
 */
public class ArrayTypeNode extends TypeNode {
    private TypeNode typeNode;
    private Int index;

    public ArrayTypeNode(Token token, TypeNode type, Int index) {
        super(token, new ArrayType(type.getType()));
        this.typeNode = type;
        this.index = index;
    }

    public TypeNode getTypeNode() {
        return typeNode;
    }

    public void setTypeNode(TypeNode typeNode) {
        this.typeNode = typeNode;
    }

    public Int getIndex() {
        return index;
    }

    public void setIndex(Int index) {
        this.index = index;
    }

    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}
