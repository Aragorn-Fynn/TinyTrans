package ast;

public class While extends Statement {
    private BoolExpr expr;
    private Statement stat;

    public While(BoolExpr expr, Statement stat) {
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
