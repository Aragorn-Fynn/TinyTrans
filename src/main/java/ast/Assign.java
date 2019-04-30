package ast;

/**
 * 赋值语句
 */
public class Assign extends Statement {
    private Expr loc;
    private Expr val;

    public Assign(Expr loc, Expr val) {
        this.loc = loc;
        this.val = val;
    }

    public Expr getLoc() {
        return loc;
    }

    public void setLoc(Expr loc) {
        this.loc = loc;
    }

    public Expr getVal() {
        return val;
    }

    public void setVal(Expr val) {
        this.val = val;
    }
}
