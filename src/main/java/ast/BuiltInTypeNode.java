package ast;

import lexer.Token;
import symtable.Type;
import visitor.IVisitor;

/**
 * 内置类型
 */
public class BuiltInTypeNode extends TypeNode {

    public BuiltInTypeNode(Token token, Type type) {
        super(token, type);
    }

    public void visit(IVisitor visitor) {
        visitor.visit(this);
    }
}
