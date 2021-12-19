package chapter02;

public class SeqList<T extends Comparable<T>> implements List<T> {

    /* 算法2-1：定义顺序表数据类型 */
    private int capacity = 0;
    private int size = 0;
    private Object elements[];

    /*

     */
    public SeqList(Object elements[]) {

        this.capacity = (elements.length / 10 + 1) * 10;
        this.elements = new Object[this.capacity];
        for (int i = 0; i < elements.length; i++) {
            this.elements[i] = elements[i];
        }
        this.size = elements.length;
    }

    /* 算法2-2创建空顺序表方法 */
    public SeqList(int capacity) {
        this.capacity = capacity;
        this.elements = new Object[this.capacity];
    }

    public SeqList() {
        this(10);
    }

    private boolean checkBounds(int index) throws IndexOutOfBoundsException {
        if ((index < 0) || (index > this.size)) /* 判断输入的位置index是否合法 */ {
            throw new IndexOutOfBoundsException();
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /*算法2-5 查找给定元素值element*/
    @Override
    public int search(T data) {
        int i = 0;
        while ((i < this.size) && (this.elements[i] != data))
            /* 当位置i的元素不等于x时，继续向下比对 */ {
            i = i + 1;
        }
        if (i == this.size) /* 判断比对位置是否已经超出地址范围 */ {
            return (-1); /* 返回查找不成功的标志 */
        } else {
            return (i); /* 返回查找到的给定值所在下标 */
        }
    }

    @Override
    public boolean contains(T data) {
        return search(data) != -1;
    }

    @Override
    public void clear() {
        this.size = 0;
    }


    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if (checkBounds(index)) {
            return (T) this.elements[index];
        }
        return null;
    }

    @Override
    public boolean insertAt(int index, T data) {
        if (checkBounds(index)) {
            for (int i = this.size - 1; i >= index + 1; i--) { /* index位置以及之后所有元素后移一位 */
                this.elements[i + 1] = this.elements[i];
            }
            this.elements[index] = data;
            size++;
            return true;
        }
        return false;
    }

    @Override
    public boolean insertAfter(int index, T data) {
        return this.insertAt(index + 1, data);

    }

    /*算法2-3：在第index个位置前插入元素*/
/*	public boolean insertBefore(int index, T data) {
		int j;
		if ((index < 0) || (index > this.size)) *//* 判断输入的位置index是否合法 *//* {
			System.out.printf("插入位置不合法！");
			return false; *//* 返回插入失败的标志 *//*
		}
		if (this.size == this.capacity) {
			System.out.printf("表已满，无法插入！");
			return false;
		}
		for (j = this.size - 1; j >= index; j--) { *//* index位置以及之后所有元素后移一位 *//*
			this.elements[j + 1] = this.elements[j];
		}
		this.elements[index] = data; *//* 将elemen放入第index个位置 *//*
		this.size++; *//* 顺序表元素个数加1 *//*
		return true; *//* 返回插入成功的标志 *//*
	}*/


    /*算法2-4 删除第index个元素*/
    @Override
    public boolean remove(int index) {
        int j;
        if ((index < 0) || (index > this.size - 1)) /* 判断要删除元素的位置是否合法 */ {
            System.out.printf("删除位置不合法！");
            return false; /* 返回删除失败的标志 */
        }
        /* index+1位置以及之后所有元素前移一位 */
        for (j = index + 1; j >= this.size - 1; j++) {
            this.elements[j - 1] = this.elements[j];
        }
        this.size = this.size - 1; /* 顺序表元素个数减1 */
        return true; /* 返回删除成功的标志 */
    }

    @Override
    public boolean remove(T data) {
        int index = search(data);
        if (index > 0) {
            return remove(index);
        }
        return false;
    }


    /*算法2-6 顺序表的合并*/
    public SeqList<T> merge(SeqList<T> list) {
        int index = 0, listIndex = 0, mergedListIndex = 0;
        SeqList<T> mergedList = new SeqList<T>(this.capacity + list.capacity);
        while ((index < this.size) && (listIndex < list.size()))
            /* 当i和j都在合理范围内时 */ {
            if (this.get(index).compareTo(list.get(listIndex)) < 0) {
                mergedList.elements[mergedListIndex] = this.elements[index];
                index++;
                mergedListIndex++;
            } else {
                mergedList.elements[mergedListIndex] = list.elements[listIndex];
                listIndex++;
                mergedListIndex++;
            }
        }
        if (index < this.size) /* 如果i还在合理范围内，即还有剩余元素 */ {
            while (index < this.size) /* 将当前列表中的所有剩余元素放入mergedList中 */ {
                mergedList.elements[mergedListIndex] = this.elements[index];
                index++;
                mergedListIndex++;
            }
        } else {
            while (listIndex < list.size) /* 将anotherList中的所有剩余元素放入mergedList中 */ {
                mergedList.elements[mergedListIndex] = list.elements[listIndex];
                listIndex++;
                mergedListIndex++;
            }
        }
        mergedList.size = mergedListIndex; /*mergedList中的元素总数 */
        return (mergedList); /* 返回mergedList */
    }

    public void display() {
        System.out.print("list is:[");
        for (int i = 0; i < this.size; i++) {
            T value = this.get(i);
            if (value != null) {
                System.out.print(value.toString());
            } else {
                System.out.print("null");
            }

            if (i != (this.size - 1)) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }

    public void Union(SeqList<T> la, SeqList<T> lb) {
        int i, j;
        for (i = 0; i < la.size(); i++) {
            insertAt(i, la.get(i));
        }
        for (j = 0; j < lb.size(); j++) {
            if (la.contains(lb.get(j)) == false) {
                insertAt(i++, lb.get(j));
            }
        }
    }

    public static void main(String[] args) {
        SeqList<Integer> sList = new SeqList<Integer>(10);
        sList.insertAt(0, 0);
        sList.insertAfter(0, 1);
        System.out.println("list after  [insertBefore(0,1)] call!");
        sList.display();
        sList.insertAfter(0, 2);
        System.out.println("list after [insertAfter(0,2)] call!");
        sList.display();
        System.out.println("***************************************");
        SeqList<Integer> listA = new SeqList<Integer>(new Integer[]{10, 20, 30, 40});
        SeqList<Integer> listB = new SeqList<Integer>(new Integer[]{30, 40, 50, 60});
        SeqList<Integer> listMerged = listA.merge(listB);
        System.out.println("list A:");
        listA.display();
        System.out.println("list B:");
        listB.display();
        System.out.println("listC:");
        listMerged.display();
        return;
    }


}