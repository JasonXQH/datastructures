package union_find;

/**
 * @author: XuQihang
 * @date: 2025/2/26 13:29
 * @description:
 */
public class UnionFind {
    private int count;
    private  int[] parent;

    public UnionFind(int n) {
        this.count = n;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);

        if (rootP == rootQ) {
            return;
        }
        //两个联通分量合并成一个联通分量
        parent[rootP] = rootQ;
        count--;
    }

    public boolean connect(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        return rootP == rootQ;
    }
    public int find(int p) {
        if(parent[p] != p){
            parent[p] = find(parent[p]);
        }
        return parent[p];
    }
    public int count() {
        return count;
    }
}
