package chapter02;

public class CircleList<T> {
    private LinkedListNode<T> head;
    private LinkedListNode<T> rear;

    public LinkedListNode<T> getHead() {
        return head;
    }

    public LinkedListNode<T> getRear() {
        return rear;
    }

    public CircleList(){
        this.head = new LinkedListNode<T>();
        this.head.setData(null);
        this.head.setNext(this.head);
        this.rear = this.head;
    }

    public void add(LinkedListNode<T> node){
        this.getRear().setNext(node);
        node.setNext(this.getHead());
        this.rear = node;
    }
    public void display(){
        System.out.print("[");
        LinkedListNode<T> currentNode = this.head.getNext();
        while(currentNode!=this.head){
            T value = currentNode.getData();
            System.out.print(value.toString());
            currentNode = currentNode.getNext();
            if(currentNode!=this.head){
                System.out.print(",");
            }
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        LinkedListNode<Integer> node;
        int i,j;
        CircleList<Integer> clist=new CircleList();
        for(i=0;i<13;i++)
            clist.add(new LinkedListNode<Integer>(0,clist.getHead()));
        node=clist.getHead().getNext();
        node.setData(1);
        for(i=2;i<14;i++)
        {
            System.out.println("i="+i);
            clist.display();
            for(j=0;j<i;)
            {
                node=node.getNext();
                if(node==clist.getHead())//头结点排除在外
                    node=node.getNext();
                if(node.getData()==0)
                {
                    j++;
                }

            }
            node.setData(i);
            //node=node.getNext();
        }
        clist.display();

/*
        listA.mergeInUsingRear(listB);
        System.out.println("listA(after mergeInUsingRear)");
        listA.display();
*/
        return;
    }
}