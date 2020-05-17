package com.hcs.datastructure.stack;

import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {

        ArrayStack arrayStack = new ArrayStack(4);
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);

        while(loop){
            System.out.println("show：显示栈");
            System.out.println("exit：退出");
            System.out.println("push：添加数据");
            System.out.println("pop：出栈");
            System.out.println("请输入你的选择：");
            key = scanner.next();
            switch (key){
                case "show":
                    arrayStack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数：");
                    int val = scanner.nextInt();
                    arrayStack.push(val);
                    break;
                case "pop":
                    try{
                        int res = arrayStack.pop();
                        System.out.println("出站的数据："+res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

class ArrayStack{
    private int maxSize;
    private int[] stack;
    private int top = -1;//栈顶

    public ArrayStack(int maxSize) {
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

}