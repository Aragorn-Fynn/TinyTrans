package lexer;

public class TestLexer {

    public static void main(String[] args) {
        //testStringLexer("public static void main(String[] args) {\r\ntestStringLexer(\"public static void main(String[] args) {\\r\\n}\");\n}");

        String program = "{\n" +
                "\tint i;int j; float v; float x; float[1000] a;\n" +
                "\twhile(true) {\n" +
                "\t\tdo i=i+1; while(a[i]<v);\n" +
                "\t\tdo j=j-1; while(a[j]>v);\n" +
                "\t\tif( i >= j ) break;\n" +
                "\t\tx=a[i]; a[i]=a[j]; a[j]=x\n" +
                "\t}\n" +
                "}";
        testToken(program);
    }

    private static void testToken(String program) {
        Lexer lexer = new StringLexer(program);
        for(Token token=lexer.nextToken(); token.getType()!=TokenType.EOF; token=lexer.nextToken()) {
            System.out.println(token);
        }
    }

    private static void testStringLexer(String source) {
        Lexer lexer = new StringLexer(source);
        for (char c=lexer.nextChar(); c!=lexer.EOF; c=lexer.nextChar()) {
            System.out.print(c);
        }
    }
}
