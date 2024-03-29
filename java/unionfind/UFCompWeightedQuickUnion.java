package unionfind;

import java.util.Scanner;

/**
 * 压缩路径加权 quickunion算法
 * 与加权 quickunion 区别是 find() 中加一个循环，把路径上的节点都指向根节点
 */
public class UFCompWeightedQuickUnion extends UF {

    private int[] height;

    public UFCompWeightedQuickUnion(int N) {
        super(N);
        height = new int[N];
        for (int i = 0; i < N; i++) {
            height[i] = i;
        }
    }

    @Override
    int find(int p) {
        int start = p;
//        让 p 取到根节点的值
        while (p != id[p]) {
            p = id[p];
        }
//        重新遍历，把路径上的节点都指向根节点
        while (start != p) {
            int temp = start;
            start = id[start];
            id[temp] = p;
        }
        return p;
    }

    @Override
    void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        int pH = height[pRoot];
        int qH = height[qRoot];
        if (pH > qH) {
            id[qRoot] = pRoot;
            height[pRoot]++;
        } else {
            id[pRoot] = qRoot;
            height[qRoot]++;
        }
        count--;
        return;
    }

}
