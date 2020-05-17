package com.hcs.datastructure.hashtab;

import java.util.Scanner;

public class HashTabDemo {
    public static void main(String[] args) {

        HashTab hashTab = new HashTab(7);

        String key = "";
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("add:添加");
            System.out.println("list:显示");
            System.out.println("find:查找");
            System.out.println("exit:退出");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建 雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }

        }


    }
}

class HashTab{
    private EmpLinkedList[] empLinkedListArray;
    private int size;

    public HashTab(int size) {
        this.size = size;
        empLinkedListArray = new EmpLinkedList[size];
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }

    }

    public void add(Emp emp){
        int empLinkedListNO = hashFun(emp.id);

        empLinkedListArray[empLinkedListNO].add(emp);
    }

    public void list(){
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    //根据输入的id,查找雇员
    public void findEmpById(int id) {
        //使用散列函数确定到哪条链表查找
        int empLinkedListNO = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNO].findEmpById(id);
        if(emp != null) {//找到
            System.out.printf("在第%d条链表中找到 雇员 id = %d\n", (empLinkedListNO + 1), id);
        }else{
            System.out.println("在哈希表中，没有找到该雇员~");
        }
    }

    //散列函数
    public int hashFun(int id){
        return id%size;
    }
}

class Emp{
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class EmpLinkedList{
    private Emp head;

    public void add(Emp emp){
        if (head==null){
            head = emp;
            return;
        }

        Emp curEmp = head;
        while(curEmp.next!=null){
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }

    public void list(int no){
        if(head==null){
            System.out.println(no+"链表为空");
            return;
        }
        System.out.print(no+"链表信息为：");
        Emp curEmp = head;
        while(true){
            System.out.printf("=> id=%d name=%s\n",curEmp.id,curEmp.name);
            if(curEmp.next==null){
                break;
            }
            curEmp = curEmp.next;
        }

    }

    //根据id查找雇员
    //如果查找到，就返回Emp, 如果没有找到，就返回null
    public Emp findEmpById(int id) {
        //判断链表是否为空
        if(head == null) {
            System.out.println("链表为空");
            return null;
        }
        //辅助指针
        Emp curEmp = head;
        while(true) {
            if(curEmp.id == id) {//找到
                break;//这时curEmp就指向要查找的雇员
            }
            //退出
            if(curEmp.next == null) {//说明遍历当前链表没有找到该雇员
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;//后移
        }

        return curEmp;
    }
}
