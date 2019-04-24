package lexer;

public class TestLexer {

    public static void main(String[] args) {
        testStringLexer("public static void main(String[] args) {\r\ntestStringLexer(\"public static void main(String[] args) {\\r\\n}\");\n}");
    }

    private static void testStringLexer(String source) {
        Lexer lexer = new StringLexer(source);
        for (char c=lexer.nextChar(); c!=lexer.EOF; c=lexer.nextChar()) {
            System.out.print(c);
        }
    }
}
