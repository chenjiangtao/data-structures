package chapter09;


public class DataItem<T extends Comparable<T>,E> implements Comparable<DataItem<T,E>> {
	public T key;
	public E data;

	public DataItem(){

	}

	public DataItem(T key,E data){
		this.key=key;
		this.data=data;
	}

	@Override
	public int compareTo(DataItem<T,E> dataItem) {
		return key.compareTo(dataItem.key);
	}
}
