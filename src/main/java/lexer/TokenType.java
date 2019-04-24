package lexer;

/**
 * 词法单元类型枚举类
 */
public enum TokenType {
    EOF(1, "<EOF>"),
    ID(2, "ID"),
    NUM(3, "NUM"),
    LBRACKET(4, "LBRACKET"),
    RBRACKET(5, "RBRACKET"),
    RESERVE(6, "RESERVE"),
    RELATION(7, "RELATION"),
    OP_RELATION(8, "OP_RELATION"),
    ASSIGN(9, "ASSIGN"),
    OP_ARITH(10, "OP_ARITH"),
    NOT(11, "NOT"),
    SEMI(12, "SEMI");

    private int type;
    private String name;

    TokenType(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
