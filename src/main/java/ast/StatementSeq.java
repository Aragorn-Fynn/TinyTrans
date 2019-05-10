package ast;

import lexer.Token;
import visitor.IVisitor;

/**
 * 语句序列
 * '{' decls stmts '}'
 */
public class StatementSeq extends Statement {
    private Statement cur;
    private StatementSeq next;

    public StatementSeq(Token token, Statement cur, StatementSeq next) {
        super(token);
        this.cur = cur;
        this.next = next;
    }

    public Statement getCur() {
        return cur;
    }

    public void setCur(Statement cur) {
        this.cur = cur;
    }

    public StatementSeq getNext() {
        return next;
    }

    public void setNext(StatementSeq next) {
        this.next = next;
    }

    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(cur.toString());
        StatementSeq next = this.next;
        while (next != null) {
            sb.append(next.getNext().toString()).append("\n");
            next = next.getNext();
        }
        return sb.toString();
    }
}
