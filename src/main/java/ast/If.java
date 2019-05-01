package ast;

import lexer.Token;

/**
 * if语句
 * 'if' '(' bool ')' stmt
 */
public class If extends Statement {
    private Expr bool;
    private Statement stmt;

    public If(Token token, Expr bool, Statement stmt) {
        super(token);
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
