
import java.util.Stack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 10174
 */
public class MyQueue {
Stack<Integer> input;
Stack<Integer> output;
    /** Initialize your data structure here. */
    public MyQueue() {
        input = new Stack<>();
        output = new Stack<>();
    }
    
    /** Push element x to the back of queue.
     * @param x */
    public void push(int x) {
        while(!output.isEmpty()){
            int i = output.pop();
            input.push(i);
        }
        output.push(x);
        while(!input.isEmpty()){
            int i = input.pop();
            output.push(i);
        }
    }
    
    /** Removes the element from in front of queue and returns that element.
     * @return  */
    public int pop() {
        return output.pop();
    }
    
    /** Get the front element.
     * @return  */
    public int peek() {
        return output.peek();
    }
    
    /** Returns whether the queue is empty.
     * @return  */
    public boolean empty() {
        return output.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */

