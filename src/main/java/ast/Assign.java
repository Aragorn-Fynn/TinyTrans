package ast;

import lexer.Token;
import visitor.IVisitor;

/**
 * 赋值语句
 * loc '=' bool ';'
 */
public class Assign extends Statement {
    private Expr loc;
    private Expr val;

    public Assign(Token token, Expr loc, Expr val) {
        super(token);
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

    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    public String toString() {
        return loc.toString()+"="+val.toString();
    }
}
