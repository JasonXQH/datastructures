package stacktraversefortree;

import javax.swing.tree.TreeNode;
import java.util.Stack;

/**
 * @author: XuQihang
 * @date: 2025/2/27 16:29
 * @description:
 */
public class StackTraverse {
    private Stack<TreeNode> stack;


    private void pushLeftBranch(TreeNode p) {
        while(p!= null) {
            /*前序遍历*/
            stack.push(p);
            p = p.left;
        }
    }


    public void traverse(TreeNode root) {
        TreeNode visited = new TreeNode(-1) ;
        pushLeftBranch(root);

        while(!stack.isEmpty()) {
            TreeNode p = stack.peek();

            if((p.left==null||p.left == visited )&& p.right != visited){
                //中序遍历
                pushLeftBranch(p.right);
            }
            if(p.right==null||p.right == visited){
                //后序遍历
                visited = stack.pop();
            }
        }



    }
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val){
            this.val = val;
        }
    }

}
