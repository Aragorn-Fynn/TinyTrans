package lexer;

public class TestFileLexer {
    public static void main(String[] args) {

        String fileName = "E:\\workspace\\TinyTrans\\src\\main\\resources\\test";
        testToken(fileName);
    }

    private static void testToken(String fileName) {
        Lexer lexer = new FileLexer(fileName);
        for(Token token=lexer.nextToken(); token.getType()!=TokenType.EOF.getType(); token=lexer.nextToken()) {
            System.out.println(token);
        }
    }

}
