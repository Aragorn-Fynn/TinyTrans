package ast;

import visitor.IVisitor;

/**
 * 数组访问表达式
 * loc '[' bool ']'
 */
public class Access extends Expr {
    private ID id;
    private Expr index;

    public Access(ID id, Expr index) {
        super(null);
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

    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    public String toString() {
        return id.getName()+"["+index.toString()+"]";
    }
}
