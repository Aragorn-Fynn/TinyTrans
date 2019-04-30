package ast;

/**
 * 赋值语句
 */
public class Assign extends Statement {
    private Location left;
    private Expr right;

    public Assign(Location left, BiExpr right) {
        this.left = left;
        this.right = right;
    }

    public Location getLeft() {
        return left;
    }

    public void setLeft(Location left) {
        this.left = left;
    }

    public Expr getRight() {
        return right;
    }

    public void setRight(Expr right) {
        this.right = right;
    }
}
