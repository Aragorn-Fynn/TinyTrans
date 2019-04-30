package ast;

/**
 * while语句
 */
public class While extends Statement {
    private BiExpr expr;
    private Statement stat;

    public While(BiExpr expr, Statement stat) {
        this.expr = expr;
        this.stat = stat;
    }

    public BiExpr getExpr() {
        return expr;
    }

    public void setExpr(BiExpr expr) {
        this.expr = expr;
    }

    public Statement getStat() {
        return stat;
    }

    public void setStat(Statement stat) {
        this.stat = stat;
    }
}
