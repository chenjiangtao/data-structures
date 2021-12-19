package chapter08;


public class BlockSearching<T extends Comparable<T>, E> {
    public int BlockSearch(DataItem<T, E>[] dataItems, BlockNode<T>[] indexArray, T key) {
        int i, j;
        i = 1;
        //顺序查找索引顺序表Ib
        while ((i <= indexArray.length) && (key.compareTo(indexArray[i].maxKey)) > 0)
            i++;

        if (i > indexArray.length) {
            // 在索引顺序表里找不到适当的关键码
            return -1;
        } else {
            // 在索引顺序表里找到适当的关键码
            //j被置为本块的起始位置
            j = indexArray[i].firstIndex;
            /* 顺序比较本块关键码*/
            while ((j <= indexArray[i].firstIndex + indexArray[i].length - 1) && (key.compareTo(dataItems[j].key) != 0)) {
                j++;
            }

            if (j > indexArray[i].length + indexArray[i].firstIndex - 1) {
                //超出块范围，没有找到
                return -1;
            } else {
                //找到，返回记录位置
                return j;
            }
        }
    }

    public static void main(String[] args) {

    }
}
