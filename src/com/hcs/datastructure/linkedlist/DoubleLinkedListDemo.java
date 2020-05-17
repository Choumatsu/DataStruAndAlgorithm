package com.hcs.datastructure.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode2 hero1 = new HeroNode2(1,"宋江","及时雨");
        HeroNode2 hero2 = new HeroNode2(2,"卢俊义","玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3,"吴用","智多星");
        HeroNode2 hero4 = new HeroNode2(4,"林冲","豹子头");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        doubleLinkedList.list();

        //测试修改
        HeroNode2 newHeroNode = new HeroNode2(4,"公孙胜","入云龙");
        System.out.println("修改后");
        doubleLinkedList.update(newHeroNode);
        doubleLinkedList.list();

        //测试删除
        System.out.println("删除后");
        doubleLinkedList.delete(3);
        doubleLinkedList.list();

    }
}

class DoubleLinkedList{
    //初始化头结点,不存放具体的数据
    private HeroNode2 head = new HeroNode2(0,"","");

    public HeroNode2 getHead(){
        return this.head;
    }

    //遍历双向链表
    public void list(){
        if(head.next == null){
            System.out.println("链表为空!");
            return;
        }
        HeroNode2 temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //添加
    public void add(HeroNode2 heroNode){
        HeroNode2 temp = head;
        while (true){
            if(temp.next==null){
                break;
            }
            temp = temp.next;

        }
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //修改
    public void update(HeroNode2 newHeroNode){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }

        HeroNode2 temp = head.next;
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
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }

        HeroNode2 temp = head;
        boolean flag = false;
        while(true){
            if(temp == null){
                break;
            }
            if(temp.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if(flag){
            temp.pre.next = temp.next;
            if(temp.next!=null){
                temp.next.pre = temp.pre;
            }

        }else {
            System.out.println("没有该节点："+no);
        }
    }
}

class HeroNode2{
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickname) {
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