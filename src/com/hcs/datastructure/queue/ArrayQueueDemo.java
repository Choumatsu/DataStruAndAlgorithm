package com.hcs.datastructure.queue;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        while (loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列中取出数据");
            System.out.println("h(head):显示队列头的数据");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key){
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数：");
                    int val = scanner.nextInt();
                    try {
                    arrayQueue.addQueue(val);

                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'g':
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.println(res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.println(res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
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


class ArrayQueue{
    private int maxSize;//最大容量
    private int front;//指向队头
    private int rear;//指向队尾
    private int[] arr;

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1;
        rear = -1;
    }

    //判断队列是否满
    public boolean isFull(){
        return rear == maxSize-1;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n)  {
        //判断是否满
        if (isFull()){
            throw new RuntimeException("队列已满！");
        }

        arr[++rear] = n;

    }

    //取数据
    public int getQueue()  {
        //判断空
        if (isEmpty()){
            throw new RuntimeException("队列为空！");
        }
        return arr[++front];

    }
    //显示队列的所有值
    public void showQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，无法遍历");
        }

        for (int i = front+1; i <= rear; i++) {
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }

    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空");

        }
        return arr[front+1];
    }
}