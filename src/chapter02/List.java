package chapter02;

public interface List<T extends Comparable<T>> {
    int size(); /*列表大小（列表中实际存储元素个数）*/

    boolean isEmpty(); /*列表是否为空*/

    int search(T data); /*查找值为data的第一个元素，并返回其索引值*/

    boolean contains(T data);/*是否存在值为data的元素*/

    T get(int index); /*返回第index个元素的值*/

    boolean insertAt(int index, T data); /*在第index个元素插入值为data的元素*/

    boolean insertAfter(int index, T data); /*在第index个元素后插入值为data的元素*/

    boolean remove(int index) /*删除第index个元素*/;

    boolean remove(T data); /*删除第1个值为data的元素*/

    void clear();/*列表清空*/
}