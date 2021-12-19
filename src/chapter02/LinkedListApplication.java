package chapter02;

public class LinkedListApplication<T extends Comparable<T>> {
    //算法2-22：单链表的倒置
    public void reverse(LinkedList<T> list) {
        LinkedListNode<T> currentNode = list.getHead().getNext();
        LinkedListNode<T> tempNode;
        list.getHead().setNext(null);

        while (currentNode != null) {
            tempNode = currentNode;
            currentNode = currentNode.getNext();

            tempNode.setNext(list.getHead().getNext());
            list.getHead().setNext(tempNode);
        }
    }

    //P44 算法2-23：两个有序列表的合并(不改变list和anotherList)
    public LinkedList<T> mergeSortedList(LinkedList<T> one, LinkedList<T> another) {

        LinkedListNode<T> oneCurrentNode = one.getHead().getNext();
        LinkedListNode<T> anotheCurrentNode = another.getHead().getNext();

        LinkedList<T> mergedList = new LinkedList<T>();

        LinkedListNode<T> mergedListHeadNode = mergedList.getHead();
        LinkedListNode<T> mergedListRearNode = mergedList.getHead();

        while (oneCurrentNode != null && anotheCurrentNode != null) {
            LinkedListNode<T> node = new LinkedListNode<T>();
            if (oneCurrentNode.getData().compareTo(anotheCurrentNode.getData()) <= 0) {
                node.setData(oneCurrentNode.getData());
                oneCurrentNode = oneCurrentNode.getNext();
            } else {
                node.setData(anotheCurrentNode.getData());
                anotheCurrentNode = anotheCurrentNode.getNext();
            }
            node.setNext(null);
            mergedListRearNode.setNext(node);
            mergedListRearNode = mergedListRearNode.getNext();
        }

        while (oneCurrentNode != null) {
            LinkedListNode<T> node = new LinkedListNode<T>();
            node.setData(oneCurrentNode.getData());
            node.setNext(null);
            mergedListRearNode.setNext(node);
            mergedListRearNode = mergedListRearNode.getNext();
            oneCurrentNode = oneCurrentNode.getNext();
        }

        while (anotheCurrentNode != null) {
            LinkedListNode<T> node = new LinkedListNode<T>();
            node.setData(anotheCurrentNode.getData());
            node.setNext(null);
            mergedListRearNode.setNext(node);
            mergedListRearNode = mergedListRearNode.getNext();
            anotheCurrentNode = anotheCurrentNode.getNext();
        }

        return mergedList;
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.addFromHead(26);
        list.addFromHead(33);
        list.addFromHead(75);
        list.addFromRear(9);
        list.addFromRear(18);
        list.display();

        LinkedListApplication<Integer> application = new LinkedListApplication<Integer>();
        application.reverse(list);
        System.out.println("-------After Reverse---------");
        list.display();

        System.out.println("******************************************");
        LinkedList<Integer> listA = new LinkedList<Integer>(new Integer[]{2, 4, 4, 6});
        LinkedList<Integer> listB = new LinkedList<Integer>(new Integer[]{1, 3, 3, 5});
        LinkedList<Integer> listMerged = application.mergeSortedList(listA, listB);
        System.out.println("list A");
        listA.display();
        System.out.println("list B");
        listB.display();
        System.out.println("list merged");
        listMerged.display();
        return;
    }

}
