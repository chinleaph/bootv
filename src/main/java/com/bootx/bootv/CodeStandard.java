package com.bootx.bootv;

/**
 * *********************
 *
 * java代码规范示例
 *
 * *********************
 *
 * #######
 * 包名统一使用小写，点分隔符之间有且仅有一个自然语义的英语单词。包名统一使用单数形式，但是类名如果有复数含义，类名可以使用复数形式。
   正例： 应用工具类包名为 com . alibaba . open . util 、类名为 MessageUtils（ 此规则参考spring 的框架结构 ）
 *
 * #######
 * 杜绝完全不规范的缩写，避免望文不知义
 *
 * #######
 * 接口和实现类的命名有两套规则：
    1 ） 【强制】对于 Service 和 DAO 类，基于 SOA 的理念，暴露出来的服务一定是接口，内部的实现类用 Impl 的后缀与接口区别。
        正例： CacheServiceImpl 实现 CacheService 接口。
    2 ） 【推荐】 如果是形容能力的接口名称，取对应的形容词做接口名 （ 通常是– able 的形式 ） 。
        正例： AbstractTranslator 实现  Translatable 。
 *
 * #######
 * 【推荐】如果使用到了设计模式，建议在类名中体现出具体模式
 *
 * #######
 * 【推荐】接口类中的方法和属性不要加任何修饰符号 （public 也不要加 ） ，保持代码的简洁
    性，并加上有效的 Javadoc 注释。尽量不要在接口里定义变量，如果一定要定义变量，肯定是
    与接口方法相关，并且是整个应用的基础常量。
 *
 * #######
 * 【参考】枚举类名建议带上 Enum 后缀，枚举成员名称需要全大写，单词间用下划线隔开。
 */

//类名使用UpperCamelCase风格，单词首字母大写
public class CodeStandard {
    //常量命名全部大写，单词间用下划线隔开，力求语义表达完整清楚，不要嫌名字长。
    public static String CURRENT_VERSSION = "1.0.16";
    //中括号是数组类型的一部分，数组定义如下： String[] args，不要使用String args[]
    private String[] rules;
    // POJO 类中布尔类型的变量，都不要加 is ，否则部分框架解析会引起序列化错误
    private boolean finalVersion = false;
    //方法名，参数名，成员变量，局部变量都统一使用lowerCamelCase风格，首字母小写，后面单词首字母大写
    private int privateNum = 1;
    public boolean hasNode = false;
    public String sayHelloToSb(String fullName){
        return "Hello," + fullName + ".I'm glad to see you again!";
    }
}

//抽象类命名使用 Abstract 或 Base 开头
abstract class BaseRules{
    abstract void print();
}

// 异常类命名使用 Exception 结尾
class ValidException{
    private String errorMsg;

    public String getErrorMsg() {
        return errorMsg;
    }
}

// 测试类命名以它要测试的类的名称开始，以 Test 结尾
class CodeStandardTest{
    public void test() {
        System.out.println("Begin test:");
    }
}