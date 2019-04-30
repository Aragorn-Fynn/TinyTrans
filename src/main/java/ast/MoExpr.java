package ast;

import lexer.Token;

/**
 * 单目表达式
 */
public class MoExpr extends Expr {
    private Token operator;
    private Expr expr;

    public MoExpr(Token operator, Expr expr) {
        this.operator = operator;
        this.expr = expr;
    }

    public Token getOperator() {
        return operator;
    }

    public void setOperator(Token operator) {
        this.operator = operator;
    }

    public Expr getExpr() {
        return expr;
    }

    public void setExpr(Expr expr) {
        this.expr = expr;
    }
}
