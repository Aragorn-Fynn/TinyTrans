package ast;

import lexer.Token;
import symtable.Scope;
import symtable.Symbol;
import visitor.IVisitor;

/**
 * 变量表达式
 * ID
 */
public class ID extends Expr implements Address {

    //用于语义分析
    private Scope scope;
    private Symbol array;

    public ID(Token token) {
        super(token);
    }

    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }


    public String getName() {
        return this.getToken().getText();
    }

    public String toString() {
        return getName();
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public Symbol getArray() {
        return array;
    }

    public void setArray(Symbol array) {
        this.array = array;
    }
}
