package lexer;

/**
 * 词法单元类型枚举类
 */
public enum TokenType {
    EOF(1, "<EOF>"),
    ID(2, "ID"),
    NUM(3, "NUM"),
    REAL(4, "REAL"),
    LBRACE(5, "LBRACE"),
    RBRACE(6, "RBRACE"),
    LPAREN(7, "LPAREN"),
    RPAREN(8, "RPAREN"),
    LBRACK(9, "LBRACKET"),
    RBRACK(10, "RBRACKET"),
    IF(11, "IF"),
    ELSE(12, "ELSE"),
    WHILE(13, "WHILE"),
    DO(14, "DO"),
    BREAK(15, "BREAK"),
    TRUE(16, "TRUE"),
    FALSE(17, "FALSE"),
    INT(18, "INT"),
    FLOAT(19, "FLOAT"),
    BOOL(20, "BOOL"),
    OR(21, "OR"),
    AND(22, "AND"),
    EQ(23, "EQ"),
    NE(24, "NE"),
    LT(25, "LT"),
    LE(26, "LE"),
    GT(27, "GT"),
    GE(28, "GE"),
    ASSIGN(29, "ASSIGN"),
    ADD(30, "ADD"),
    MINUS(31, "MINUS"),
    MULTI(32, "MULTI"),
    DIV(33, "DIV"),
    BITOR(34, "BITOR"),
    BITAND(35, "BITAND"),
    NOT(36, "NOT"),
    SEMI(37, "SEMI");

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

    public static String getName(int type) {
        TokenType[] types = TokenType.values();
        for (TokenType tokenType : types) {
            if (type==tokenType.getType()) {
                return tokenType.getName();
            }
        }

        return null;
    }

    public static TokenType getType(int type) {
        TokenType[] types = TokenType.values();
        for (TokenType tokenType : types) {
            if (type==tokenType.getType()) {
                return tokenType;
            }
        }

        return null;
    }
}
