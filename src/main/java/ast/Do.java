package ast;

public class Do extends Statement {
    private Statement stat;
    private BoolExpr expr;

    public Do(Statement stat, BoolExpr expr) {
        this.stat = stat;
        this.expr = expr;
    }

    public Statement getStat() {
        return stat;
    }

    public void setStat(Statement stat) {
        this.stat = stat;
    }

    public BoolExpr getExpr() {
        return expr;
    }

    public void setExpr(BoolExpr expr) {
        this.expr = expr;
    }
}
