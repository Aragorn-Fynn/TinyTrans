package ast;

public class Assign extends Statement {
    private Location left;
    private BoolExpr right;

    public Assign(Location left, BoolExpr right) {
        this.left = left;
        this.right = right;
    }

    public Location getLeft() {
        return left;
    }

    public void setLeft(Location left) {
        this.left = left;
    }

    public BoolExpr getRight() {
        return right;
    }

    public void setRight(BoolExpr right) {
        this.right = right;
    }
}
