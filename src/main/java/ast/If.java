package ast;

public class If extends Statement {
    private BoolExpr expr;
    private Statement stat;

    public If(BoolExpr expr, Statement stat) {
        this.expr = expr;
        this.stat = stat;
    }

    public BoolExpr getExpr() {
        return expr;
    }

    public void setExpr(BoolExpr expr) {
        this.expr = expr;
    }

    public Statement getStat() {
        return stat;
    }

    public void setStat(Statement stat) {
        this.stat = stat;
    }
}
