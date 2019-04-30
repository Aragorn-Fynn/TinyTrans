package ast;

public class Ifelse extends Statement {
    private BoolExpr expr;
    private Statement thenStat;
    private Statement elseStat;

    public Ifelse(BoolExpr expr, Statement thenStat, Statement elseStat) {
        this.expr = expr;
        this.thenStat = thenStat;
        this.elseStat = elseStat;
    }

    public BoolExpr getExpr() {
        return expr;
    }

    public void setExpr(BoolExpr expr) {
        this.expr = expr;
    }

    public Statement getThenStat() {
        return thenStat;
    }

    public void setThenStat(Statement thenStat) {
        this.thenStat = thenStat;
    }

    public Statement getElseStat() {
        return elseStat;
    }

    public void setElseStat(Statement elseStat) {
        this.elseStat = elseStat;
    }
}
