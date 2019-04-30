package ast;

/**
 * 数组访问表达式
 */
public class Access extends Expr {
    private ID id;
    private Expr index;

    public Access(ID id, Expr index) {
        this.id = id;
        this.index = index;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public Expr getIndex() {
        return index;
    }

    public void setIndex(Expr index) {
        this.index = index;
    }
}
