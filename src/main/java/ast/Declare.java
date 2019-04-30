package ast;

import lexer.Token;

/**
 * 声明语句
 */
public class Declare extends Node {
    private Type type;
    private Token id;

    public Declare(Type type, Token id) {
        this.type = type;
        this.id = id;
    }
}
