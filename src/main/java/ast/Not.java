package ast;

import lexer.Token;

/**
 * 非表达式
 * '!' unary
 */
public class Not extends Expr {
    private Expr expr;

    public Not(Token token, Expr expr) {
        super(token);
        this.expr = expr;
    }

    public Expr getExpr() {
        return expr;
    }

    public void setExpr(Expr expr) {
        this.expr = expr;
    }
}
