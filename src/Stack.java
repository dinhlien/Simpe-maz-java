import java.util.EmptyStackException;

/**
 *
 * @author Linh
 */
public class Stack<T> implements Cloneable {
    private Node<T> top;    
    
    public Stack() {
        top = null;
    }      
    
    public T pop() {
        T answer;
        
        if (top == null) {
            throw new EmptyStackException();
        }
        
        answer = top.data;
        top = top.link;
                
        return answer;
    }
    
    public void push(T item) {
        top = new Node<T>(item, top);
    }
    
    public boolean isEmpty() {
        return (top == null);
    }
    
    public T peek() {
        if (top == null)
            throw new EmptyStackException();
        return top.data;
    }
    
    public int size() {
        Node<T> cursor;
        int size = 0;
        
        for (cursor = top; cursor != null; cursor = cursor.link)
            size++;
                
        return size;
    }

    public void display() {
       //base case
        if (top == null) {
           return;
        } 
        
        String result;
        result = pop().toString();
        
        display();
        
        System.out.println(result);        
    }

    private class Node<Z> {
        private Z data;
        private Node<Z> link;  

        //constructor
        public Node(Z initialData, Node<Z> initialLink){
            data = initialData;
            link = initialLink;
        }
    }
}