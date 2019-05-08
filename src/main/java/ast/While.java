package ast;

import lexer.Token;
import visitor.IVisitor;

/**
 * while 语句
 * 'while' '(' bool ')' stmt
 */
public class While extends Statement {
    private Expr bool;
    private Statement stmt;

    public While(Token token, Expr bool, Statement stmt) {
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

    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}
