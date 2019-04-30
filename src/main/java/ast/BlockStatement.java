package ast;

/**
 * 语句块
 */
public class BlockStatement extends Statement {
    private DeclareSeq decls;
    private StatementSeq stmts;

    public BlockStatement(DeclareSeq decls, StatementSeq stmts) {
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
}
