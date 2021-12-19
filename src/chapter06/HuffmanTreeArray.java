package chapter06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.util.List;;


public class HuffmanTreeArray<T> {
    private HuffmanTreeArrayNode[] Htree;


    public HuffmanTreeArray(List<HuffmanTreeArrayNode<T>> leafNodes) {
        this.Htree = new HuffmanTreeArrayNode[2 * leafNodes.size() - 1];
        for (int i = 0; i < this.Htree.length; i++) {
            HuffmanTreeArrayNode<T> node = new HuffmanTreeArrayNode<T>();
            this.Htree[i] = node;
        }
        createHuffmanTree(leafNodes);
    }

    public HuffmanTreeArrayNode<T> getElement(int i) {
        if (i < 0 || i > this.Htree.length) {
            System.out.print("索引值越界！");
            return null;
        }
        return (HuffmanTreeArrayNode<T>) this.Htree[i];
    }

    public void createHuffmanTree(List<HuffmanTreeArrayNode<T>> leafNodes) {
        int treeIndex = 0;
        int leafCount = leafNodes.size();
        for (HuffmanTreeArrayNode<T> node : leafNodes) {
            this.Htree[treeIndex] = node;
            treeIndex++;
        }
        for (int i = leafCount; i < 2 * leafCount - 1; i++) {
            double min2 = Double.MAX_VALUE, min1 = Double.MAX_VALUE;
            int min2Index = 0, min1Index = 0;
            for (int j = 0; j < i; j++) {
                if (this.Htree[j].getParent() == -1) {
                    if (this.Htree[j].getWeight() < min1) {
                        min2Index = min1Index;
                        min2 = min1;
                        min1Index = j;
                        min1 = this.Htree[j].getWeight();
                    } else if (this.Htree[j].getWeight() < min2) {
                        min2Index = j;
                        min2 = this.Htree[j].getWeight();
                    }
                }
            }
            this.Htree[min1Index].setParent(i);
            this.Htree[min2Index].setParent(i);
            this.Htree[i].setWeight(this.Htree[min1Index].getWeight() + this.Htree[min2Index].getWeight());
            this.Htree[i].setLeftChild(min1Index);
            this.Htree[i].setRightChild(min2Index);
        }
        this.Htree[2 * leafCount - 2].setParent(-1);
    }

    public Map<T, String> buildHuffmanCodes() {
        int leafCount = (this.Htree.length + 1) / 2;
        Map<T, String> codes = new HashMap<T, String>();
        for (int i = 0; i < leafCount; i++) {
            String code = "";
            int parentIndex = this.Htree[i].getParent();
            while (parentIndex != -1) {
                if (i == this.Htree[parentIndex].getLeftChild()) {
                    code = "0" + code;       /* 左分支编码为0 */
                } else {
                    code = "1" + code;       /* 右分支编码为1 */
                }
                parentIndex = this.Htree[parentIndex].getParent();      /* 进到路径的上一个结点，直至根结点 */
            }
            codes.put((T) this.Htree[i].getData(), code);
        }
        return codes;
    }

    public void displayCodes(Map<T, String> codes) {
        System.out.println("编码方案如下：");

        for (Map.Entry<T, String> e : codes.entrySet()) {
            System.out.println(e.getKey().toString() + ":" + e.getValue());
        }
    }

    public static void main(String[] args) {
        List<HuffmanTreeArrayNode<String>> list = new ArrayList<HuffmanTreeArrayNode<String>>();
        HuffmanTreeArrayNode<String> node1 = new HuffmanTreeArrayNode<String>("1", 1);
        HuffmanTreeArrayNode<String> node3 = new HuffmanTreeArrayNode<String>("3", 3);
        HuffmanTreeArrayNode<String> node5 = new HuffmanTreeArrayNode<String>("5", 5);
        HuffmanTreeArrayNode<String> node7 = new HuffmanTreeArrayNode<String>("7", 7);
        list.add(node1);
        list.add(node3);
        list.add(node5);
        list.add(node7);
        HuffmanTreeArray<String> huffmanTree = new HuffmanTreeArray<String>(list);

        List<HuffmanTreeArrayNode<String>> codeList = new ArrayList<HuffmanTreeArrayNode<String>>();
        HuffmanTreeArrayNode<String> codeNodeA = new HuffmanTreeArrayNode<String>("A", 0.05);
        HuffmanTreeArrayNode<String> codeNodeB = new HuffmanTreeArrayNode<String>("B", 0.3);
        HuffmanTreeArrayNode<String> codeNodeC = new HuffmanTreeArrayNode<String>("C", 0.15);
        HuffmanTreeArrayNode<String> codeNodeD = new HuffmanTreeArrayNode<String>("D", 0.4);
        HuffmanTreeArrayNode<String> codeNodeE = new HuffmanTreeArrayNode<String>("E", 0.1);
        codeList.add(codeNodeA);
        codeList.add(codeNodeB);
        codeList.add(codeNodeC);
        codeList.add(codeNodeD);
        codeList.add(codeNodeE);
        HuffmanTreeArray<String> huffmanCodingTree = new HuffmanTreeArray<String>(codeList);
        Map<String, String> codings = huffmanCodingTree.buildHuffmanCodes();
        huffmanCodingTree.displayCodes(codings);
        return;
    }
}
