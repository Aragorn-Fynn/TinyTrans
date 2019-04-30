package ast;

import lexer.Token;

public class ArrayType extends Type {
    private BasicType type;
    private Token num;
    private ArrayType next;

    public ArrayType(BasicType type, Token num, ArrayType next) {
        this.type = type;
        this.num = num;
        this.next = next;
    }

    public BasicType getType() {
        return type;
    }

    public void setType(BasicType type) {
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
