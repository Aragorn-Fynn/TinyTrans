package ast;

import lexer.Token;

/**
 * 声明序列
 */
public class DeclareSeq extends AST {

    private Declare decl;
    private DeclareSeq next;

    public DeclareSeq(Token token, Declare decl, DeclareSeq next) {
        super(token);
        this.decl = decl;
        this.next = next;
    }

    public Declare getDecl() {
        return decl;
    }

    public void setDecl(Declare decl) {
        this.decl = decl;
    }

    public DeclareSeq getNext() {
        return next;
    }

    public void setNext(DeclareSeq next) {
        this.next = next;
    }
}
