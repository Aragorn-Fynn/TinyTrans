package ast;

import lexer.Token;
import symtable.Type;
import visitor.IVisitor;

/**
 * 表达式基类
 */
public abstract class Expr extends AST {

    //用于语义检查
    private Type type;
    private Address addr;

    public Expr(Token token) {
        super(token);
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Address getAddr() {
        return addr;
    }

    public void setAddr(Address addr) {
        this.addr = addr;
    }
}
