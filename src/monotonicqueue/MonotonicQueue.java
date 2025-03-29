package monotonicqueue;

import java.util.LinkedList;

/**
 * @author: XuQihang
 * @date: 2025/3/12 12:37
 * @description:
 */
public class MonotonicQueue {
    LinkedList<Integer> queue = new LinkedList<>();
    LinkedList<Integer> maxq = new LinkedList<>();
    LinkedList<Integer> minq = new LinkedList<>();
    public void push(int x) {
        while(!maxq.isEmpty() && x > maxq.peekLast()) {
            maxq.pollLast();
        }
        while(!minq.isEmpty() && x < minq.peekLast()) {
            minq.pollLast();
        }
        maxq.add(x);
        minq.add(x);
        queue.add(x);
    }
    public int size(){
        return queue.size();
    }

    public int max(){
        return maxq.getFirst();
    }
    public int min(){
        return minq.getFirst();
    }

    public int pop(){
        int first = queue.removeFirst();
        if(first == maxq.peekFirst()){
            maxq.pollFirst();
        }
        if(first == minq.peekFirst()){
            minq.pollFirst();
        }
        return first;
    }
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
