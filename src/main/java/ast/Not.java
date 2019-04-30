package ast;

/**
 * 非表达式
 */
public class Not extends Expr {
    private Expr expr;

    public Not(Expr expr) {
        this.expr = expr;
    }

    public Expr getExpr() {
        return expr;
    }

    public void setExpr(Expr expr) {
        this.expr = expr;
    }
}
