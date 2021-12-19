package chapter09;

import java.lang.reflect.Array;


public class Sorting<T extends Comparable<T>, E> {
    public void insertSort(DataItem<T, E>[] dataItems, int low, int high) {
        for (int i = low + 1; i <= high; i++)
            if (dataItems[i].compareTo(dataItems[i - 1]) < 0) {// 小于时，需将r[i]插入有序表
                DataItem<T, E> temp = dataItems[i];
                dataItems[i] = dataItems[i - 1];
                int j = i - 2;
                for (; j >= low && temp.compareTo(dataItems[j]) < 0; j--)
                    dataItems[j + 1] = dataItems[j]; // 记录后移
                dataItems[j + 1] = temp; // 插入到正确位置
            }
    }

    public void binaryInsertSort(DataItem<T, E>[] dataItems, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            DataItem<T, E> temp = dataItems[i]; // 保存待插入元素
            int hi = i - 1;
            int lo = low; // 设置初始区间
            while (lo <= hi) { // 折半确定插入位置
                int mid = (lo + hi) / 2;
                if (temp.compareTo(dataItems[mid]) < 0)
                    hi = mid - 1;
                else
                    lo = mid + 1;
            }
            for (int j = i - 1; j > hi; j--)
                dataItems[j + 1] = dataItems[j]; // 移动元素
            dataItems[hi + 1] = temp; // 插入元素
        }
    }

    public void shellSort(DataItem<T, E>[] dataItems, int low, int high, int[] delta) {
        for (int k = 0; k < delta.length; k++)
            shellInsert(dataItems, low, high, delta[k]);// 一趟步长为delta[k]的直接插入排序
    }

    private void shellInsert(DataItem<T, E>[] r, int low, int high, int deltaK) {
        for (int i = low + deltaK; i <= high; i++)
            if (r[i].compareTo(r[i - deltaK]) < 0) { // 小于时，需将r[i] 插入有序表
                DataItem<T, E> temp = r[i];
                int j = i - deltaK;
                for (; j >= low && temp.compareTo(r[j]) < 0; j = j - deltaK)
                    r[j + deltaK] = r[j]; // 记录后移
                r[j + deltaK] = temp; // 插入到正确位置
            }
    }

    public void bubbleSort(DataItem<T, E>[] dataItems, int low, int high) {
        int n = high - low + 1;
        for (int i = 1; i < n; i++)
            for (int j = low; j <= high - i; j++)
                if (dataItems[j].compareTo(dataItems[j + 1]) > 0) {
                    DataItem<T, E> temp = dataItems[j];
                    dataItems[j] = dataItems[j + 1];
                    dataItems[j + 1] = temp;
                }
    }

    private int partition(DataItem<T, E>[] dataItems, int low, int high) {
        DataItem<T, E> pivot = dataItems[low]; // 使用r[low]作为枢轴元素
        while (low < high) { // 从两端交替向内扫描
            while (low < high && dataItems[high].compareTo(pivot) >= 0)
                high--;
            dataItems[low] = dataItems[high]; // 将比pivot 小的元素移向低端
            while (low < high && dataItems[low].compareTo(pivot) <= 0)
                low++;
            dataItems[high] = dataItems[low]; // 将比pivot 大的元素移向高端
        }
        dataItems[low] = pivot; // 设置枢轴
        return low; // 返回枢轴元素位置
    }

    public void quickSort(DataItem<T, E>[] dataItems, int low, int high) {
        if (low < high) {
            int pa = partition(dataItems, low, high);
            quickSort(dataItems, low, pa - 1);
            quickSort(dataItems, pa + 1, high);
        }
    }

    public void selectSort(DataItem<T, E>[] dataItems, int low, int high) {
        for (int k = low; k < high - 1; k++) { // 作n-1 趟选取
            int min = k;
            for (int i = min + 1; i <= high; i++) // 选择关键字最小的元素
                if (dataItems[i].compareTo(dataItems[min]) < 0)
                    min = i;
            if (k != min) {
                DataItem<T, E> temp = dataItems[k]; // 关键字最小的元素与元素r[k]交换
                dataItems[k] = dataItems[min];
                dataItems[min] = temp;
            }
        }
    }

    // 已知r[low..high]中除r[low]之外，其余元素均满足堆的定义
    private void heapAdjust(DataItem<T, E>[] dataItems, int low, int high) {
        DataItem<T, E> temp = dataItems[low];
        for (int j = 2 * low; j <= high; j = j * 2) {
            // 沿关键之较大的元素向下进行筛选j，指向关键之较大的元素
            if (j < high && dataItems[j].compareTo(dataItems[j + 1]) < 0)
                j++;
            // 比其孩子都大，则插入到low 所指位置
            if (temp.compareTo(dataItems[j]) >= 0)
                break;
            dataItems[low] = dataItems[j];
            low = j; // 向下筛选
        }
        dataItems[low] = temp;
    }

    public void heapSort(DataItem<T, E>[] dataItems) {
        int n = dataItems.length - 1;
        for (int i = n / 2; i >= 1; i--) // 初始化建堆
            heapAdjust(dataItems, i, n);
        for (int i = n; i > 1; i--) {// 不断输出堆顶元素并调整r[1..i-1]为新堆
            DataItem<T, E> temp = dataItems[1]; // 交换堆顶与堆底元素
            dataItems[1] = dataItems[i];
            dataItems[i] = temp;
            heapAdjust(dataItems, 1, i - 1); // 调整
        }
    }

    private void merge(DataItem<T, E>[] dataItems, int formertStartIndex, int formerEndIndex, int laterEndIndex) {
        @SuppressWarnings("unchecked")
        DataItem<T, E>[] mergedItems = (DataItem<T, E>[]) new Object[laterEndIndex - formertStartIndex + 1];
        int formerIndex = formertStartIndex;
        int laterIndex = formerEndIndex + 1;
        int mergedIndex = 0;
        while (formerIndex <= formerEndIndex && laterIndex <= laterEndIndex)
            if (dataItems[formerIndex].compareTo(dataItems[laterIndex]) < 0)
                mergedItems[mergedIndex++] = dataItems[formerIndex++];
            else
                mergedItems[mergedIndex++] = dataItems[laterIndex++];
        while (formerIndex <= formerEndIndex)
            mergedItems[mergedIndex++] = dataItems[formerIndex++];
        while (laterIndex <= laterEndIndex)
            mergedItems[mergedIndex++] = dataItems[laterIndex++];
        for (int i = 0; i < mergedItems.length; i++)
            dataItems[formertStartIndex + i] = mergedItems[i];
    }

    public void mergepass(DataItem<T, E>[] dataItems, int listSize) {
        int i = 1;
        while (i <= dataItems.length - 2 * listSize + 1) {
            merge(dataItems, i, i + listSize - 1, i + 2 * listSize - 1);
            i = i + 2 * listSize;
        }
        if (i + listSize - 1 < dataItems.length)
            merge(dataItems, i, i + listSize - 1, dataItems.length);
    }

    public void mergeSort(DataItem<T, E>[] dataItems) {
        int listSize = 1;
        while (listSize < dataItems.length) {
            mergepass(dataItems, listSize);
            listSize = listSize * 2;
        }
    }

    public void mergeSort(DataItem<T, E>[] dataItems, int low, int high) {
        if (low < high) {
            mergeSort(dataItems, low, (high + low) / 2);
            mergeSort(dataItems, (high + low) / 2 + 1, high);
            merge(dataItems, low, (high + low) / 2, high);
        }
    }

    private String getArrayKeys(DataItem<T, E>[] datas) {
        String keys = "keys:[";
        for (int i = 0; i < datas.length; i++) {
            keys = keys + datas[i].key;
            if (i != datas.length) {
                keys = keys + ",";
            }
        }
        keys = keys + "]";
        return keys;
    }

    public static void main(String[] args) {
        DataItem<Integer, String>[] datas = (DataItem<Integer, String>[]) Array.newInstance(DataItem.class, 10);
        Sorting<Integer, String> sorting = new Sorting<Integer, String>();
        datas[0] = new DataItem<Integer, String>(5, "value5");
        datas[1] = new DataItem<Integer, String>(8, "value8");
        datas[2] = new DataItem<Integer, String>(3, "value3");
        datas[3] = new DataItem<Integer, String>(4, "value4");
        datas[4] = new DataItem<Integer, String>(9, "value9");
        datas[5] = new DataItem<Integer, String>(11, "value11");
        datas[6] = new DataItem<Integer, String>(0, "value0");
        datas[7] = new DataItem<Integer, String>(3, "value3");
        datas[8] = new DataItem<Integer, String>(1, "value1");
        datas[9] = new DataItem<Integer, String>(6, "value6");

        System.out.println("排序之前");
        System.out.println(sorting.getArrayKeys(datas));
        sorting.quickSort(datas, 0, 9);
        System.out.println("排序之后：");
        System.out.println(sorting.getArrayKeys(datas));

    }
}
