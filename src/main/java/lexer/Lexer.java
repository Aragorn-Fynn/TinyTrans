package lexer;

public abstract class Lexer {
    public static char EOF = (char)-1;

    int lexemBegin;
    int forward;
    int current;

    int k;
    char[][] buffers;

    /**
     * 构造方法
     * @param k 缓冲大小
     */
    public Lexer(int k) {
        lexemBegin = 0;
        forward = 0;
        current = 0;

        this.k = k;
        buffers = new char[2][k+1];
    }

    /**
     * 获取下一个字符
     * @return
     */
    public char nextChar() {
        if (buffers[current][forward]==EOF) {
            return EOF;
        }

        if (forward==k) {
            current=1-current;
            fillBuffer(buffers[current]);
            forward=0;
        }

        return buffers[current][forward++];
    }

    /**
     * 消耗lexemBegin到forward之间的字符
     */
    public void consume() {
        forward--;
        this.lexemBegin=forward;
    }

    protected abstract void fillBuffer(char[] buffer);

    protected abstract Token nextToken();
}
