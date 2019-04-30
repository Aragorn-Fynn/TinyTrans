package ast;

/**
 * 语句序列
 */
public class StatementSeq extends Statement {
    private Statement cur;
    private StatementSeq next;

    public StatementSeq(Statement cur, StatementSeq next) {
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
}
