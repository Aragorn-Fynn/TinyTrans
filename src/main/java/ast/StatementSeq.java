package ast;

/**
 * 语句
 */
public class StatementSeq extends Node{

    private Statement current;
    private StatementSeq next;

    public StatementSeq(Statement stat, StatementSeq next) {
        this.current = stat;
        this.next = next;
    }

    public Statement getCurrent() {
        return current;
    }

    public void setCurrent(Statement current) {
        this.current = current;
    }

    public StatementSeq getNext() {
        return next;
    }

    public void setNext(StatementSeq next) {
        this.next = next;
    }
}
