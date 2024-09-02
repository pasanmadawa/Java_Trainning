package org.example;

public class LinkedList {

    private Node head;
    private Node tail;
    private int size;

    public LinkedList(){

        this.size = 0;
    }

    public void firstInsert(int value){

        Node node = new Node(value);
        node.next = head;
        head = node;

        if( tail == null){
            tail = head;
        }

        size ++;
    }

    public void lastInsert(int value){

        if(tail == null){
            firstInsert(value);
            return;
        }

        Node node = new Node(value);
        tail.next = node;
        tail = node;
        size++;
    }

    public int deleteFirst(){
        int value = head.value;
        head = head.next;
        if(head == null){
            tail  = null ;
        }

        size--;
        return value;
    }

    public int deleteLast(){
        if(size <= 1){
            return deleteFirst();
        }

        Node secondLastIndex = get(size - 2);
        int value = tail.value;
        tail = secondLastIndex;
        tail.next = null;
        return value;


    }

    public int deleteIndex(int index){
        if(index == 0){
            return deleteFirst();
        }
        if(index == size- 1){
            return deleteLast();
        }

        Node deleteIndex = get(index - 1);
        int value = deleteIndex.next.value;
        deleteIndex.next = deleteIndex.next.next;

        return value;
    }

    public int deleteValue(int delValue){

        if(head.value == delValue){
            return deleteFirst();
        }

        Node value = head;
        while (value.next != null && value.next.value != delValue){
            value = value.next;
        }

        int removeValue = value.next.value;
        value.next = value.next.next;

        if(value.next == null){
            return deleteLast();
        }

        size --;
        return removeValue;


    }

    public  Node get(int index){
        Node node = head;
        for(int i = 0; i < index; i++){
            node = node.next;
        }
        return node;
    }

    public int searchIndex(int index){
        Node num = get(index);
        int value = num.value;

        return value;
    }

    public int searchValue(int value){
        Node search = head;


        while (search != null){
            if(search.value == value){
                return value;
            }

            search = search.next;

        }

        return -1;
    }
    public int search(int value){
        Node search = head;
        int index = 0;

        while (search != null){
            if(search.value == value){
                return index;
            }

            search = search.next;
            index++;

        }

        return -1;
    }



    public int getSize(){
        return size ;

    }

    public void display(){

        Node temp = head;
        while (temp != null){
            System.out.print(temp.value + " ");

            temp = temp.next;
        }
    }



    private class Node{

        private int value;
        private Node next;

        public Node(int value){

            this.value = value;
        }

        public Node(int value, Node next){
            this.value = value;
            this.next = next;
        }

        public int getValue() {

            return value;
        }

        public void setValue(int value) {

            this.value = value;
        }

        public Node getNext() {

            return next;
        }

        public void setNext(Node next) {

            this.next = next;
        }
    }
}
