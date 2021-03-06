package ast;

import lexer.Token;
import visitor.IVisitor;

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

    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(decl.toString());
        DeclareSeq next = this.next;
        while (next != null) {
            sb.append(next.getDecl().toString()).append("\n");
            next = next.getNext();
        }
        return sb.toString();
    }
}
