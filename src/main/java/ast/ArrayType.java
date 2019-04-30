package ast;

import lexer.Token;

/**
 * 数组类型
 */
public class ArrayType extends Type {
    private Type type;
    private Token num;
    private ArrayType next;

    public ArrayType(Type type, Token num, ArrayType next) {
        this.type = type;
        this.num = num;
        this.next = next;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Token getNum() {
        return num;
    }

    public void setNum(Token num) {
        this.num = num;
    }

    public ArrayType getNext() {
        return next;
    }

    public void setNext(ArrayType next) {
        this.next = next;
    }
}
