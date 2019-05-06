package ast;

import lexer.Token;
import symtable.Type;

/**
 * 内置类型
 */
public class BuiltInTypeNode extends TypeNode {

    public BuiltInTypeNode(Token token, Type type) {
        super(token, type);
    }
}
