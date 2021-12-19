package chapter08;

public class Searching<T extends Comparable<T>, E> {
    /**
     * 在数组a中顺序查找数据元素elem的关键码k是否存在，存在时返回关键码标号，不存在时返回-1
     * @param dataItems
     * @param key
     * @return
     */
    public int seqSearch(DataItem<T, E>[] dataItems, T key) {
        int n = dataItems.length;
        int i = 0;
        while (i < n && dataItems[i].key.compareTo(key) != 0) {
            i++;
        }
        if (dataItems[i].key.compareTo(key) == 0) {
            return i;
        } else {
            return -1;
        }
    }

    public int sequenceSearch(DataItem<T, E>[] dataItems, T key) {
        int i = dataItems.length - 1;
        dataItems[0].key = key;
        while (dataItems[i].key.compareTo(key) != 0) {
            i--;
        }
        return i;
    }

    public int binarySearch(DataItem<T, E>[] dataItems, int low, int high, T key) {
        while (low <= high) {
            int mid = (low + high) / 2;
            if (dataItems[mid].key.compareTo(key) == 0) {
                return mid;
            } else if (dataItems[mid].key.compareTo(key) > 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

}
