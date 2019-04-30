package ast;

/**
 * 数组变量
 */
public class ArrayLocation extends Location {
    private Location loc;
    private Expr expr;
    private ArrayLocation next;

    public ArrayLocation(Location loc, BiExpr expr, ArrayLocation next) {
        this.loc = loc;
        this.expr = expr;
        this.next = next;
    }

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

    public Expr getExpr() {
        return expr;
    }

    public void setExpr(Expr expr) {
        this.expr = expr;
    }

    public ArrayLocation getNext() {
        return next;
    }

    public void setNext(ArrayLocation next) {
        this.next = next;
    }
}
