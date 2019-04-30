package ast;

/**
 * 声明序列
 */
public class DeclareSeq extends Node {
    private Declare current;
    private DeclareSeq next;

    public DeclareSeq(Declare decl, DeclareSeq next) {
        this.current = decl;
        this.next = next;
    }

    public Declare getCurrent() {
        return current;
    }

    public void setCurrent(Declare current) {
        this.current = current;
    }

    public DeclareSeq getNext() {
        return next;
    }

    public void setNext(DeclareSeq next) {
        this.next = next;
    }
}
