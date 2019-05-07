package ast;

import lexer.Token;
import symtable.Type;
import visitor.IVisitor;

/**
 * 表达式基类
 */
public abstract class Expr extends AST {

    //用于语义检查
    public Type type;

    public Expr(Token token) {
        super(token);
    }
    public void visit(IVisitor visitor) {
        visitor.visit(this);
    }
}
