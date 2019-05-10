package lexer;

import java.io.*;

public class FileLexer extends BaseLexer {

    private BufferedReader reader;
    private boolean eof;

    /**
     * 构造方法
     *
     */
    public FileLexer(String fileName) {
        super(7);
        try {
            File sourceFiile = new File(fileName);
            FileReader fileReader = new FileReader(sourceFiile);
            reader = new BufferedReader(fileReader);
            eof = false;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        fillBuffer(buffers[0]);
    }

    protected void fillBuffer(char[] buffer) {
        try {
            if (!eof) {
                int length = reader.read(buffer, 0, k);
                if (length < k) {
                    buffer[length]= EOF;
                    eof = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
