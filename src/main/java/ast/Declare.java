package ast;

import lexer.Token;
import visitor.IVisitor;

/**
 * 声明语句
 */
public class Declare extends Statement {

    private TypeNode type;
    private ID id;

    public Declare(Token token, TypeNode type, ID id) {
        super(token);
        this.type = type;
        this.id = id;
    }

    public TypeNode getType() {
        return type;
    }

    public void setType(TypeNode type) {
        this.type = type;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    public String toString() {
        return type.toString()+" "+id.getName();
    }
}
