package ast;

/**
 * 负数表达式
 */
public class Minus extends Expr {
    private Expr expr;

    public Minus(Expr expr) {
        this.expr = expr;
    }

    public Expr getExpr() {
        return expr;
    }

    public void setExpr(Expr expr) {
        this.expr = expr;
    }
}
