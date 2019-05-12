# TinyTrans

参考书籍《龙书》

实现一个简单的语法制导翻译器

1.词法分析-递归下降->token流

2.语法分析-递归下降->抽象语法树

3.静态类型检查-语法制导翻译、visitor->标注抽象语法树

4.中间代码生成-语法制导翻译、visitor->三地址代码

支持语法：

1. bool、int、float、多维数组

2. 表达式

3. 赋值语句、if-else、do-while、while语句、break语句

4. 嵌套作用域


需要改进：

1. 自动类型提升

2. 结构类型

3. 方法

4. switch

5. continue