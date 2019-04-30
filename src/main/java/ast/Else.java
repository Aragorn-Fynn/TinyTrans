package ast;

/**
 * if-else 语句
 */
public class Else extends Statement {
    private Expr bool;
    private Statement thenStmt;
    private Statement elseStmt;

    public Else(Expr bool, Statement thenStmt, Statement elseStmt) {
        this.bool = bool;
        this.thenStmt = thenStmt;
        this.elseStmt = elseStmt;
    }

    public Expr getBool() {
        return bool;
    }

    public void setBool(Expr bool) {
        this.bool = bool;
    }

    public Statement getThenStmt() {
        return thenStmt;
    }

    public void setThenStmt(Statement thenStmt) {
        this.thenStmt = thenStmt;
    }

    public Statement getElseStmt() {
        return elseStmt;
    }

    public void setElseStmt(Statement elseStmt) {
        this.elseStmt = elseStmt;
    }
}
