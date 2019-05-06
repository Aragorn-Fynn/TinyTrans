package symtable;

/**
 * 符号基类
 */
public class Symbol {
    //名称
    private String name;
    //类型
    private String type;
    //包含当前symbol的作用域
    private Scope scope;

    public Symbol(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }
}
