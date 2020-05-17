package com.hcs.datastructure.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {

        //中缀表达式转后缀表达式

        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("中缀:"+infixExpressionList);
        List<String> suffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println("后缀:"+suffixExpressionList);

        System.out.println("res="+calculate(suffixExpressionList));

        //先定义逆波兰表达式
        //(3+4)*5-6 => 3 4 + 5 * 6 -
        /*String suffixExpression = "30 4 + 5 * 6 -";

        List<String> rpnList = getListString(suffixExpression);
//        System.out.println("rpnList="+rpnList);

        int res = calculate(rpnList);
        System.out.println("结果为："+res);*/
    }

    //中缀表达式转List
    public static List<String> toInfixExpressionList(String s){
        List<String> ls = new ArrayList<>();
        int i = 0;//指针，用于遍历
        String str;//对多位数的拼接
        char c;//每遍历一个字符，放入c

        while(i<s.length()){
            if ((c=s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add(""+c);
                i++;
            }else {
                str = "";
                while(i < s.length() && (c=s.charAt(i)) >= 48 && (c=s.charAt(i)) <= 57){
                    str += c;//拼接
                    i++;
                }
                ls.add(str);
            }

        }
        return ls;

    }

    //中缀list转后缀list
    public static List<String> parseSuffixExpressionList(List<String> ls){
        //定义两个栈,第二个没有出栈操作，可用list替换
        Stack<String> s1 = new Stack<>();
        List<String> s2 = new ArrayList<>();

        //遍历ls
        for(String str:ls){
            if(str.matches("\\d+")){
                s2.add(str);
            }else if (str.equals("(")){
                s1.push(str);
            }else if (str.equals(")")){
                //如果是），则依次弹出s1栈顶的运算符，病压入s2，直至遇到（，此时将括号丢弃
                while(!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();//弹出(
            }else{
                //str优先级小于等于栈顶优先级，将s1栈顶运算符压入s2，再与新栈顶运算符比较
                while(s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(str)){
                    s2.add(s1.pop());
                }
                //还需要将str压入栈
                s1.push(str);
            }
        }
        //将s1中剩余的运算符加入到s2中
        while(s1.size()!=0){
            s2.add(s1.pop());

        }
        return s2;

    }

    //将逆波兰表达式放入ArrayList
    public static List<String> getListString(String suffixExpression){
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String ele: split) {
            list.add(ele);
        }
        return list;
    }

    public static int calculate(List<String> ls){
        //创建栈
        Stack<String> stack = new Stack<>();
        //遍历ls
        for(String item: ls){
            //使用正则表达式取出数
            if(item.matches("[0-9]+")){//匹配多位数
                //入栈
                stack.push(item);
            }else {
                //出栈两个数再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());

                int res = 0;

                if(item.equals("+")){
                    res = num1 + num2;
                }else if(item.equals("-")){
                    res = num1 - num2;
                }else if(item.equals("*")){
                    res = num1 * num2;
                }else if(item.equals("/")){
                    res = num1/num2;
                }else {
                    throw new RuntimeException("运算符有误!");
                }
                //res入栈
                stack.push(res+"");

            }

        }
        //最后留在栈中的是运算结果
        return Integer.parseInt(stack.pop());

    }

}

//优先级的类
class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //返回对应的优先级
    public static int getValue(String operation){
        int res = 0;
        switch (operation){
            case "+":
                res = ADD;
                break;
            case "-":
                res = SUB;
                break;
            case "*":
                res = MUL;
                break;
            case "/":
                res = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return res;
    }

}