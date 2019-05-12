package ast;

import lexer.Token;
import symtable.Type;

/**
 * 类型节点基类
 */
public abstract class TypeNode extends AST {
    private Type type;

    public TypeNode(Token token, Type type) {
        super(token);
        this.type = type;
    }


    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public abstract int getWidth();

}
