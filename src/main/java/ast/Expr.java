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
    private String falseLabel;
    private String trueLabel;

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

    public String getFalseLabel() {
        return falseLabel;
    }

    public void setFalseLabel(String falseLabel) {
        this.falseLabel = falseLabel;
    }

    public String getTrueLabel() {
        return trueLabel;
    }

    public void setTrueLabel(String trueLabel) {
        this.trueLabel = trueLabel;
    }
}
