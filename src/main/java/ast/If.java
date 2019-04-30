package ast;

/**
 * if语句
 */
public class If extends Statement {
    private Expr bool;
    private Statement stmt;

    public If(Expr bool, Statement stmt) {
        this.bool = bool;
        this.stmt = stmt;
    }

    public Expr getBool() {
        return bool;
    }

    public void setBool(Expr bool) {
        this.bool = bool;
    }

    public Statement getStmt() {
        return stmt;
    }

    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }
}
