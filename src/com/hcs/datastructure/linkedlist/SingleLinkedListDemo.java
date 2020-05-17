package com.hcs.datastructure.linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {

        HeroNode hero1 = new HeroNode(1,"宋江","及时雨");
        HeroNode hero2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3 = new HeroNode(3,"吴用","智多星");
        HeroNode hero4 = new HeroNode(4,"林冲","豹子头");

        SingleLinkedList singleLinkedList = new SingleLinkedList();

//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);

        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero2);

        singleLinkedList.list();
        //System.out.println("修改");

        //测试修改
        //HeroNode newHero = new HeroNode(2,"卢","玉麒麟~");
        //singleLinkedList.update(newHero);

       // singleLinkedList.list();
       // System.out.println("修改");

        //测试删除
        //singleLinkedList.delete(1);

        //singleLinkedList.list();

        //测试倒数第k个
        //System.out.println("倒数第k个为："+findLastIndexNode(singleLinkedList.getHead(),4));

        //测试翻转
//        reverse(singleLinkedList.getHead());
//        System.out.println("反转后：");
//        singleLinkedList.list();
//        System.out.println("反转后：");
//        reverseList(singleLinkedList.getHead());
//        singleLinkedList.list();
        System.out.println("翻转打印1");
        listFromRear(singleLinkedList.getHead());
        System.out.println("翻转打印2");
        reversePrint(singleLinkedList.getHead());
    }

    //查找倒数k个节点
    public static HeroNode findLastIndexNode(HeroNode head,int index){
        if (head.next == null){

            return null;
        }

        int size = getLength(head);

        if(index <= 0||index>size){
            return null;
        }

        HeroNode cur = head.next;


        for (int i = 0; i < size-index; i++) {
            cur = cur.next;
        }

        return cur;
    }


    //链表长度
    public static int getLength(HeroNode head){
        if (head.next == null){

            return 0;
        }

        int len = 0;
        while (head.next != null){
            head = head.next;
            len++;
        }
        return len;
    }

    //翻转链表
    public static void reverse(HeroNode head){
        if (head.next == null||head.next.next==null){

            return ;
        }

        HeroNode no1 = head.next;

        HeroNode pre = head.next;
        HeroNode cur = pre.next;

        while(cur.next != null){
            HeroNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        cur.next = pre;
        no1.next = null;

        head.next = cur;



    }
    //翻转链表2
    public static void reverseList(HeroNode head){
        if (head.next == null||head.next.next==null){

            return ;
        }

        HeroNode cur = head.next;
        HeroNode next = null;//指向当前节点的下一个节点

        HeroNode reverseHead = new HeroNode(0,"","");


        while(cur != null){
            next = cur.next;

            cur.next = reverseHead.next;
            reverseHead.next = cur;

            cur = next;
        }

        head.next = reverseHead.next;
    }

    //从尾到头打印链表1
    public static void listFromRear(HeroNode head){
        if (head.next==null){
            return;
        }
        HeroNode cur = head.next;
        listFromRear(cur);
        System.out.println(cur);
    }

    //从尾到头打印链表2
    public static void reversePrint(HeroNode head){
        if (head.next==null){
            return;
        }
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        while(cur!=null){
            stack.push(cur);
            cur = cur.next;
        }

        while(stack.size()>0){
            System.out.println(stack.pop());
        }
    }
}


class SingleLinkedList{
    //初始化头结点,不存放具体的数据
    private HeroNode head = new HeroNode(0,"","");

    //添加节点
    public void add(HeroNode heroNode){
        HeroNode temp = head;
        while (true){
            if(temp.next==null){
                break;
            }
            temp = temp.next;

        }
        temp.next = heroNode;
    }

    //按顺序添加
    public void addByOrder(HeroNode heroNode){

        HeroNode temp = head;
        boolean flag = false;

        while (true){
            if(temp.next == null){
                break;
            }

            if(temp.next.no > heroNode.no){
                break;
            }

            if(temp.next.no == heroNode.no){
                flag = true;
            }

            temp = temp.next;
        }


        if(flag){
            System.out.println("编号存在！");

        }else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }

    }

    //修改
    public void update(HeroNode newHeroNode){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }

        HeroNode temp = head.next;
        boolean flag = false;//表示是否找到该节点
        while(true){
            if(temp == null){
                break;
            }
            if(temp.no == newHeroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        }else{
            System.out.println("没有找到编号为"+newHeroNode.no+"的节点");
        }

    }

    //删除
    public void delete(int no){
        HeroNode temp = head;
        boolean flag = false;
        while(true){
            if(temp.next == null){
                break;
            }
            if(temp.next.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if(flag){
            temp.next = temp.next.next;
        }else {
            System.out.println("没有该节点："+no);
        }
    }


    //显示链表
    public void list(){
        if(head.next == null){
            System.out.println("链表为空!");
            return;
        }
        HeroNode temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    public HeroNode getHead(){
        return this.head;
    }


}

class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}