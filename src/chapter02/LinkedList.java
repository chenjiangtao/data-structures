package chapter02;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class LinkedList<T extends Comparable<T>> implements List<T> {

    private LinkedListNode<T> head;

    public LinkedList() {
        this(null);
    }

    public LinkedList(T intialDatas[]) {
        this.head = new LinkedListNode<T>();
        this.head.setData(null);
        this.head.setNext(null);
        if (intialDatas != null) {
            LinkedListNode<T> currentNode = this.head;
            for (int i = 0; i < intialDatas.length; i++) {
                LinkedListNode<T> node = new LinkedListNode<T>();
                node.setData(intialDatas[i]);
                node.setNext(null);
                currentNode.setNext(node);
                currentNode = currentNode.getNext();
            }
        }
    }

    public LinkedListNode<T> getHead() {
        return this.head;
    }

    public void setHead(LinkedListNode<T> head) {
        this.head = head;
    }

    public LinkedListNode<T> searchRearNode() {
        LinkedListNode<T> rearNode = this.head;
        while (rearNode.getNext() != null) {
            rearNode = rearNode.getNext();
        }
        return rearNode;
    }


    public void addFromHead(LinkedListNode<T> node) {
        if (this.head.getNext() == null) {
            this.head.setNext(node);
        } else {
            node.setNext(this.head.getNext());
            this.head.setNext(node);
        }
    }


    public void addFromHead(T nodeData) {
        LinkedListNode<T> node = new LinkedListNode<T>();
        node.setData(nodeData);
        this.addFromHead(node);
    }

    public void addFromRear(LinkedListNode<T> node) {
        LinkedListNode<T> rearNode = searchRearNode();
        rearNode.setNext(node);
    }

    public void addFromRear(T nodeData) {
        LinkedListNode<T> node = new LinkedListNode<T>();
        node.setData(nodeData);
        node.setNext(null);
        this.addFromRear(node);

    }

    public boolean insert(int i, T nodeData) {
        LinkedListNode<T> currentNode = this.head;
        int index = 0;
        while (currentNode != null) {
            if (index == i - 1) {
                break;
            }
            currentNode = currentNode.getNext();
            index++;
        }
        if (index == i - 1) {
            LinkedListNode<T> node = new LinkedListNode<T>();
            node.setData(nodeData);
            node.setNext(currentNode.getNext());
            currentNode.setNext(node);
            return true;
        } else {
            return false;
        }
    }

    public boolean remove(LinkedListNode<T> node) {
        LinkedListNode<T> priorNode = this.head;
        LinkedListNode<T> currentNode = this.head.getNext();
        while ((currentNode != null) && (currentNode.getData().compareTo(node.getData()) != 0)) {
            priorNode = priorNode.getNext();
            currentNode = currentNode.getNext();
        }
        if (currentNode == null) {
            return false;
        } else {
            priorNode.setNext(currentNode.getNext());
            return true;
        }
    }

    @Override
    public boolean remove(int index) {
        if (index < 1) {
            System.out.println("下标i错误！");
        }
        int i = 1;
        LinkedListNode<T> priorNode = this.head;
        LinkedListNode<T> currentNode = this.head.getNext();
        while ((currentNode != null) && (i < index)) {
            priorNode = priorNode.getNext();
            currentNode = currentNode.getNext();
        }
        if (i == index) {
            priorNode.setNext(currentNode.getNext());
            return true;
        } else {
            System.out.println("下标i错误(超出列表长度)！");
            return false;
        }
    }

    @Override
    public boolean remove(T data) {
        LinkedListNode<T> priorNode = this.head;
        LinkedListNode<T> currentNode = this.head.getNext();
        while ((currentNode != null) && currentNode.getData().compareTo(data) != 0) {
            priorNode = priorNode.getNext();
            currentNode = currentNode.getNext();
        }
        if (currentNode != null) {
            priorNode.setNext(currentNode.getNext());
            return true;
        } else {
            System.out.println("找不到data=" + data + "的结点");
            return false;
        }
    }

    public void display() {
        System.out.print("[");
        LinkedListNode<T> currentNode = this.head.getNext();
        while (currentNode != null) {
            T value = currentNode.getData();
            System.out.print(value.toString());
            currentNode = currentNode.getNext();
            if (currentNode != null) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }

    public LinkedListNode<T> search(LinkedListNode<T> node) {
        LinkedListNode<T> currentNode = this.head.getNext();
        while (currentNode != null && currentNode.getData().compareTo(node.getData()) != 0) {
            currentNode = currentNode.getNext();
        }
        return currentNode;
    }

    public static LinkedList<Integer> createList() {
        LinkedList<Integer> listLinked = new LinkedList<Integer>();//创建空的单链表
        LinkedListNode<Integer> tail = listLinked.head.getNext();//取得链表头结点指针域

        System.out.print("请输入长度n:");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println("输入值");

        for (int i = 0; i < n; i++) {
            System.out.print("请输入第" + i + "个值:");
            int value = scanner.nextInt();
            //为value创建一个结点并用头插法插入到链表中
            LinkedListNode<Integer> node = new LinkedListNode<Integer>();
            node.setData(value);
            listLinked.addFromHead(node);
        }
        System.out.println("链表构建完毕。链表如下：");
        return listLinked;
    }

    @Override
    public int size() {
        int size = 0;
        for (LinkedListNode<T> node = this.getHead().getNext(); node != null; node = node.getNext()) {
            size++;
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        return this.getHead().getNext() == null;
    }

    @Override
    public boolean contains(T data) {
        return false;
    }

    @Override
    public void clear() {
        this.getHead().setNext(null);
    }

    @Override
    public T get(int index) {
        LinkedListNode<T> node = getNode(index);
        return node == null ? null : node.getData();
    }

    @Override
    public boolean insertAt(int index, T data) {
        return false;
    }

    public LinkedListNode<T> getNode(int index) {
        int i = 0;
        LinkedListNode<T> node = this.getHead().getNext();
        while (i < index && node != null) {
            node = node.getNext();
            i++;
        }
        return node;
    }

    public boolean insertBefore(int index, T data) {
        LinkedListNode<T> node = getNode(index - 1);
        if (node == null) {
            return false;
        } else {
            LinkedListNode<T> newNode = new LinkedListNode<T>(data, node.getNext());
            node.setNext(newNode);
        }
        return true;
    }

    @Override
    public boolean insertAfter(int index, T data) {
        LinkedListNode<T> node = getNode(index);
        if (node == null) {
            return false;
        } else {
            LinkedListNode<T> newNode = new LinkedListNode<T>(data, node.getNext());
            node.setNext(newNode);
        }
        return true;
    }

    public boolean removeAt(int index) {
        LinkedListNode<T> priorNode = getNode(index - 1);
        LinkedListNode<T> node = getNode(index);
        if (priorNode != null && node != null) {
            priorNode.setNext(node.getNext());
        }
        return false;
    }


    @Override
    public int search(T data) {
        return 0;
    }

    public Iterator<T> iterator() {
        return new ListLinkedIterator(0);
    }

    private class ListLinkedIterator implements Iterator<T> {
        private LinkedListNode<T> lastReturned;
        private LinkedListNode<T> next;
        private int nextIndex;

        ListLinkedIterator(int index) {
            // assert isPositionIndex(index);
            next = (index == size()) ? null : getNode(index);
            nextIndex = index;
        }

        @Override
        public boolean hasNext() {
            return nextIndex < size();
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();

            lastReturned = next;
            next = next.getNext();
            nextIndex++;
            return lastReturned.getData();
        }
    }

    //已知一个带头结点的单链表L,
    // 设计一个算法实现链表逆置，要求不增加额外存储空间
    public void ReverseList() {
        LinkedListNode<T> p1, p2, p3;
        if (head.getNext() == null)
            return;
        p1 = head.getNext();
        if (p1.getNext() == null)
            return;
        p2 = p1.getNext();
        p3 = p2.getNext();
        p1.setNext(null);
        while (p2 != null) {
            p2.setNext(p1);
            p1 = p2;
            p2 = p3;
            if (p3 != null)
                p3 = p3.getNext();
        }
        head.setNext(p1);
    }

    //【例2.8】已知带头结点的单链表L,
    // 设计算法实现查找链表倒数第K个结点。
    // 若查找成功，返回指示指针,否则返回NULL
    public LinkedListNode<T> getReverseK(int k) {
        LinkedListNode<T> p1, p2;
        int count = 0;
        if (head.getNext() == null)
            return null;
        p1 = head.getNext();
        p2 = p1;
        if (p1.getNext() == null && k == 1)
            return p1;
        while (count < k - 1) {
            if (p2 != null)
                p2 = p2.getNext();
            count++;
        }
        if (p2 == null)
            return null;
        while (p2.getNext() != null) {
            p1 = p1.getNext();
            p2 = p2.getNext();
        }
        return p1;
    }

    //A和B是两个带头结点的单链表，其中元素递增有序，
    // 设计一个算法将A和B归并程一个递增有序的链表C,
    // 要求不能增加额外存储空间
    public void MergetList(LinkedListNode<T> head2) {
        LinkedListNode<T> p1, p2, newhead, tail;
        p1 = head.getNext();
        p2 = head2.getNext();
        if (p1 == null || p2 == null) {
            if (p1 == null)
                head = head2;
        }

        if (p1.getData().compareTo(p2.getData()) <= 0)
            newhead = head;
        else
            newhead = head2;
        tail = newhead;
        while (p1 != null && p2 != null) {
            if (p1.getData().compareTo(p2.getData()) <= 0) {
                tail.setNext(p1);
                tail = p1;
                p1 = p1.getNext();
            } else {
                tail.setNext(p2);
                tail = p2;
                p2 = p2.getNext();
            }
        }
        if (p1 != null)
            tail.setNext(p1);
        if (p2 != null)
            tail.setNext(p2);

        head = newhead;
    }

    //有一个带头结点的单链表L=(a1,b1,a2,b2,…an,bn)
//设计算法将其分拆程两个带头结点的链表L1=(a1,a2,…an),
// L2=(bn,bn-1,…b2,b1)
    public LinkedList<T> SplitList() {
        LinkedList<T> list = new LinkedList<T>();
        LinkedListNode<T> L2 = new LinkedListNode<T>();
        LinkedListNode<T> p1, p2 = null, tail;
        p1 = head.getNext();
        if (p1 != null)
            p2 = p1.getNext();
        list.setHead(L2);
        tail = head;
        while (p1 != null && p2 != null) {
            tail.setNext(p1);
            tail = p1;
            if (p2 != null)
                p1 = p2.getNext();
            p2.setNext(L2.getNext());
            L2.setNext(p2);
            if (p1 != null)
                p2 = p1.getNext();
        }
        if (p1 != null) {
            tail.setNext(p1);
            tail = p1;
        }
        tail.setNext(null);
        return list;
    }

    public static void main(String[] args) {
        Integer[] number = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        Integer[] number2 = {20, 40, 60, 100};
        LinkedList<Integer> listLinked = new LinkedList<Integer>(number);
        LinkedList<Integer> listLinked2 = listLinked.SplitList();
        listLinked.display();
        listLinked2.display();

	     /*
        LinkedListNode<Integer> node=listLinked.getReverseK(4);
        System.out.println(node.getData());

        ListLinkedNode<Integer> node = new ListLinkedNode<Integer>();
        node.setData(200);
        node.setNext(null);
        listLinked.remove(node);
        listLinked.remove(1);

        LinkedList<Integer> listLinked = new LinkedList<Integer>();
        listLinked.addFromHead(1);
        listLinked.addFromHead(2);
        listLinked.addFromHead(3);

        Iterator<Integer> iter = listLinked.iterator();
        while(iter.hasNext()){
            System.out.println(iter.next());
        }


        listLinked.display();
        return;*/
    }
}