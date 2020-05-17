package com.hcs.datastructure.linkedlist;

public class Josephu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();

        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();

        //测试出圈
        circleSingleLinkedList.countBoy(1,2,5);
    }
}

//创建环形单向链表
class CircleSingleLinkedList{
    private Boy first = null;
    public  void addBoy(int nums){
        //nums数据校验
        if(nums<1){
            System.out.println("nums值不正确");
            return;
        }

        Boy curBoy = null;//辅助指针帮助构建环形链表
        for (int i = 1; i <= nums ; i++) {
            Boy boy = new Boy(i);
            if(i==1){
                first = boy;
                first.setNext(first);
                curBoy = first;
            }else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }

        }
    }

    //遍历环形链表
    public void showBoy(){
        if(first == null){
            System.out.println("链表为空");
        }
        Boy curBoy = first;
        while(true){
            System.out.printf("编号%d\n",curBoy.getNo());
            if(curBoy.getNext()==first){
                break;
            }
            curBoy = curBoy.getNext();

        }
    }

    /**
     *
     * @param startNo 表示从第几个小孩开始
     * @param countNum 表示数几下
     * @param nums 表示最初有几个
     */
    public void countBoy(int startNo,int countNum,int nums){
        //数据校验
        if(first==null||startNo<1||startNo>nums){
            System.out.println("参数输入有误");
        }
        //辅助指针
        Boy helper = first;
        //指向最后一个节点
        while(true){
            if(helper.getNext()==first){
                break;
            }
            helper = helper.getNext();
        }
        //移动到开始节点 移动k-1次
        for (int i = 0; i <startNo-1 ; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }

        while(true){
            if(helper==first){//说明圈中只有一个节点
                break;
            }
            //同时移动countNum-1
            for (int i = 0; i < countNum-1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.printf("%d出圈\n",first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("留在圈中的编号为%d\n",first.getNo());

    }
}


//boy类，表示一个节点
class Boy{
    private int no;
    private Boy next;

    public void setNext(Boy next) {
        this.next = next;
    }

    public Boy getNext() {
        return next;
    }

    public int getNo() {
        return no;
    }

    public Boy(int no) {
        this.no = no;
    }
}