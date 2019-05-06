package ast;

import lexer.Token;
import visitor.IVisitor;

/**
 * 负数表达式
 * '-' unary
 */
public class Minus extends Expr {
    private Expr expr;

    public Minus(Token token, Expr expr) {
        super(token);
        this.expr = expr;
    }

    public Expr getExpr() {
        return expr;
    }

    public void setExpr(Expr expr) {
        this.expr = expr;
    }

    public void visit(IVisitor visitor) {
        visitor.visit(this);
    }
}
