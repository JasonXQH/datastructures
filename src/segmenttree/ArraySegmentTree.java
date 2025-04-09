package segmenttree;

import java.util.function.BinaryOperator;

public class ArraySegmentTree {
    // 用数组存储线段树结构
    private final int[] tree;
    // 元素个数
    private final int n;
    private final BinaryOperator<Integer> merger;
    public ArraySegmentTree(int[] nums, BinaryOperator<Integer> merger) {
        this.n = nums.length;
        this.merger = merger;
        // 分配 4 倍数组长度的空间，存储线段树
        this.tree = new int[4 * n];
        build(nums, 0, n - 1, 0);
    }
    // 定义：对 nums[l..r] 区间的元素构建线段树，rootIndex 是根节点
    private void build(int[] nums, int l, int r, int rootIndex) {
        if (l == r) {
            // 区间内只有一个元素，设置为叶子节点
            tree[rootIndex] = nums[l];
            return;
        }
        // 从中间切分，递归构建左右子树
        int mid = l + (r - l) / 2;
        int leftRootIndex = leftChild(rootIndex);
        int rightRootIndex = rightChild(rootIndex);
        // 递归构建 nums[l..mid]，根节点为 leftRootIndex
        build(nums, l, mid, leftRootIndex);
        // 递归构建 nums[mid+1..r]，根节点为 rightRootIndex
        build(nums, mid + 1, r, rightRootIndex);
        // 后序位置，左右子树已经构建完毕，更新当前节点的聚合值
        tree[rootIndex] = merger.apply(tree[leftRootIndex], tree[rightRootIndex]);
    }
    public void update(int index, int value) {
        update(0, n - 1, 0, index, value);
    }
    // 当前节点为 rootIndex，对应的区间为 [l, r]
    // 去子树更新 nums[index] 为 value
    private void update(int l, int r, int rootIndex, int index, int value) {
        if (l == r) {
            // 找到了目标叶子节点，更新值
            tree[rootIndex] = value;
            return;
        }
        int mid = l + (r - l) / 2;
        if (index <= mid) {
            // 若 index 较小，则去左子树更新
            update(l, mid, leftChild(rootIndex), index, value);
        } else {
            // 若 index 较大，则去右子树更新
            update(mid + 1, r, rightChild(rootIndex), index, value);
        }
        // 后序位置，左右子树已经更新完毕，更新当前节点的聚合值
        tree[rootIndex] = merger.apply(
                tree[leftChild(rootIndex)],
                tree[rightChild(rootIndex)]
        );
    }
    public int query(int qL, int qR) {
        if (qL < 0 || qR >= n || qL > qR) {
            throw new IllegalArgumentException("Invalid range: [" + qL + ", " + qR + "]");
        }
        return query(0, n - 1, 0, qL, qR);
    }
    private int query(int l, int r, int rootIndex, int qL, int qR) {
        if (qL == l && r == qR) {
            // 命中了目标区间，直接返回
            return tree[rootIndex];
        }
        int mid = l + (r - l) / 2;
        int leftRootIndex = leftChild(rootIndex);
        int rightRootIndex = rightChild(rootIndex);
        if (qR <= mid) {
            // node.l <= qL <= qR <= mid
            // 目标区间完全在左子树中
            return query(l, mid, leftRootIndex, qL, qR);
        } else if (qL > mid) {
            // mid < qL <= qR <= node.r
            // 目标区间完全在右子树中
            return query(mid + 1, r, rightRootIndex, qL, qR);
        } else {
            // node.l <= qL <= mid < qR <= node.r
            // 目标区间横跨左右子树
            // 将查询区间拆分成 [qL, mid] 和 [mid + 1, qR] 两部分，分别向左右子树查询
            return merger.apply(
                    query(l, mid, leftRootIndex, qL, mid),
                    query(mid + 1, r, rightRootIndex, mid + 1, qR)
            );
        }
    }
    private int leftChild(int pos) {
        return 2 * pos + 1;
    }
    private int rightChild(int pos) {
        return 2 * pos + 2;
    }
}
