package parser;

import lexer.FileLexer;
import lexer.Lexer;

public class TestParser {
    public static void main(String[] args) {
        String fileName = "E:\\workspace\\TinyTrans\\src\\main\\resources\\test";
        testParser(fileName);
    }

    private static void testParser(String fileName) {
        Lexer lexer = new FileLexer(fileName);
        Parser parser = new LLParser(lexer);
        parser.parse();
    }
}
