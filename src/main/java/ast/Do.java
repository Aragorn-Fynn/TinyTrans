package ast;

/**
 * do-while语句
 */
public class Do extends Statement {
    private Statement stmt;
    private Expr bool;

    public Do(Statement stmt, Expr bool) {
        this.stmt = stmt;
        this.bool = bool;
    }

    public Statement getStmt() {
        return stmt;
    }

    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }

    public Expr getBool() {
        return bool;
    }

    public void setBool(Expr bool) {
        this.bool = bool;
    }
}
