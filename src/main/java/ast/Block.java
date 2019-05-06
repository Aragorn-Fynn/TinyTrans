package ast;

import lexer.Token;
import visitor.IVisitor;

public class Block extends Statement {
    private DeclareSeq decls;
    private StatementSeq stmts;

    public Block(Token token, DeclareSeq decls, StatementSeq stmts) {
        super(token);
        this.decls = decls;
        this.stmts = stmts;
    }

    public DeclareSeq getDecls() {
        return decls;
    }

    public void setDecls(DeclareSeq decls) {
        this.decls = decls;
    }

    public StatementSeq getStmts() {
        return stmts;
    }

    public void setStmts(StatementSeq stmts) {
        this.stmts = stmts;
    }

    public void visit(IVisitor visitor) {
        visitor.visit(this);
    }
}
