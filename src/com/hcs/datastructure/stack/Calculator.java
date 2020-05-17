package com.hcs.datastructure.stack;

public class Calculator {
    public static void main(String[] args) {
        String expression = "70+2*6-4";
        //定义数栈，符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);

        int index = 0;//用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';//每次扫描得到的放入char
        String keepNum = "";//用于拼接多位数

        while(true){
            ch = expression.substring(index,index+1).charAt(0);

            if(operStack.isOper(ch)){
                if(!operStack.isEmpty()){
                    if(operStack.priority(ch)<=operStack.priority(operStack.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1,num2,oper);
                        //运算结果入数栈
                        numStack.push(res);
                        //当前操作符入符号栈
                        operStack.push(ch);
                    }else {
                        //优先级大，直接入栈
                        operStack.push(ch);
                    }
                }else {
                    //为空直接入符号栈
                    operStack.push(ch);
                }
            }else{//如果是数，直接入数栈
                //numStack.push(ch-48);
                keepNum += ch;

                //如果ch已经是最后一位
                if(index==expression.length()-1) {
                    numStack.push(Integer.parseInt(keepNum));


                    //判断下一个是不是数字
                }else{
                    if (operStack.isOper(expression.substring(index+1,index+2).charAt(0))) {
                        //如果是操作符
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }
            //让index+1，判断是否扫描到最后
            index++;
            if(index >= expression.length()){
                break;
            }
        }

        //扫描完毕，从栈中取出数和符号计算
        while(true){
            //若符号栈为空则，返回结果
            if(operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1,num2,oper);
            numStack.push(res);
        }
        System.out.printf("表达式%s=%d",expression,numStack.pop());


    }
}

class ArrayStack2{
    private int maxSize;
    private int[] stack;
    private int top = -1;//栈顶

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    public boolean isFull(){
        return top == maxSize-1;
    }

    public boolean isEmpty(){
        return top == -1;
    }
    //入栈
    public void push(int value){
        if(isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }
    //出栈
    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }
    //遍历，从栈顶开始显示
    public void list(){
        if(isEmpty()){
            System.out.println("栈空");
        }
        for (int i = top; i >=0 ; i--) {
            System.out.printf("stack[%d] = %d\n",i,stack[i]);
        }
    }

    //返回栈顶的值，不出栈
    public int peek(){
        return stack[top];
    }

    //返回运算符优先级
    public int priority(int oper){
        if(oper=='*'||oper=='/'){
            return 1;
        }else if (oper=='+'||oper=='-'){
            return 0;
        }else {
            return -1;//假设表达式只有加减乘除
        }


    }

    //判断是否是操作符
    public boolean isOper(char val){
        return val=='+'||val=='-'||val=='*'||val=='/';
    }
    public int cal(int num1,int num2,int oper){
        int res = 0;
        switch (oper){
            case '+':
                res = num1+num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;

    }

}