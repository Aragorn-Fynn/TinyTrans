package ast;

/**
 * if else 语句
 */
public class Ifelse extends Statement {
    private BiExpr expr;
    private Statement thenStat;
    private Statement elseStat;

    public Ifelse(BiExpr expr, Statement thenStat, Statement elseStat) {
        this.expr = expr;
        this.thenStat = thenStat;
        this.elseStat = elseStat;
    }

    public BiExpr getExpr() {
        return expr;
    }

    public void setExpr(BiExpr expr) {
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
