package union_find;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: XuQihang
 * @date: 2025/2/26 19:26
 * @description:
 */

public class UnionFindForString {
    private Map<String,String> parent;

    public UnionFindForString() {
        parent = new HashMap<>();
    }

    public void union(String p, String q) {
        String rootP = find(p);
        String rootQ = find(q);

        if (rootP.equals(rootQ)) {
            return;
        }
        //两个联通分量合并成一个联通分量
        parent.put(rootP,rootQ);
    }

    public boolean connect(String p, String q) {
        String rootP = find(p);
        String rootQ = find(q);
        return rootP.equals(rootQ);
    }
    public String find(String word) {
        if (!parent.containsKey(word)) {
            parent.put(word, word);
        }
        if (!parent.get(word).equals(word)) {
            parent.put(word, find(parent.get(word)));  // 路径压缩
        }
        return parent.get(word);
    }
    public int count() {
        return parent.size();
    }
}

