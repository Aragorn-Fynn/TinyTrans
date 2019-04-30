package ast;

/**
 * do while语句
 */
public class Do extends Statement {
    private Statement stat;
    private BiExpr expr;

    public Do(Statement stat, BiExpr expr) {
        this.stat = stat;
        this.expr = expr;
    }

    public Statement getStat() {
        return stat;
    }

    public void setStat(Statement stat) {
        this.stat = stat;
    }

    public BiExpr getExpr() {
        return expr;
    }

    public void setExpr(BiExpr expr) {
        this.expr = expr;
    }
}
