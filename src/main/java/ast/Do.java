package ast;

import lexer.Token;
import visitor.IVisitor;

/**
 * do-while语句
 * 'do' stmt 'while' '(' bool ')' ';'
 */
public class Do extends Statement {
    private Statement stmt;
    private Expr bool;

    public Do(Token token, Statement stmt, Expr bool) {
        super(token);
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

    public void visit(IVisitor visitor) {
        visitor.visit(this);
    }
}
