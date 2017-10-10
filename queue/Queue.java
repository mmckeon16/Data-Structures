package queue;
/**
 * A Queue implemented with two stacks implemented with linked lists implemented with nodes.
 * @author Mary McKeon 2017S CS284.
 *
 */
public class Queue<E> {
    private Stack<E> left;
    private Stack<E> right;
    private int size;

    /**
     *
     * @author YourName
     * Stack class, implemented with a linked list
     * @param <E> a generic element
     */
    private static class Stack<E> {
        private LinkedList<E> li;
        private int size = 0;


        /**
         * Very simple linked list
         * @author khayyamsaleem
         *
         * @param <E> a generic element
         */
        private static class LinkedList<E> {
            private Node<E> head = null;
            private int size = 0;

            /**
             * Node class
             * @author khayyamsaleem
             *
             * @param <E> a generic element
             */
            private static class Node<E> {
                private E data;
                private Node<E> next;

                /**
                 * very simple Node constructor
                 * @param data a generic element
                 */
                public Node(E data) {
                    this.data = data;
                }

                /**
                 * toString function for nodes
                 * @return string representation of a node
                 */
                @Override
                public String toString() {
                    return "(" + this.data.toString() + ")";
                }
            }
            /**
             * constructor for linked list
             */
            public LinkedList() {
                this.head = null;
                this.size = 0;
            }

            /**
             * inserts at head of linked list
             * @param data element to insert
             */
            public void insertFirst(E data) {
                Node<E> n = new Node<E>(data);
                n.next = this.head;
                this.head = n;
                this.size++;
            }

            /**
             * deletes first element of linked list
             * @return Node at index 0
             */
            public Node<E> deleteFirst() {
                Node<E> temp = this.head;
                this.head = this.head.next;
                this.size--;
                return temp;
            }

            /**
             * tells if list is empty
             * @return true if empty, false otherwise
             */
            public boolean isEmpty() {
                return (this.head == null);
            }

            /**
             * toString for linked list
             * @return string representation of list
             */
            @Override
            public String toString() {
                String s = "";
                Node<E> c = this.head;
                while (c != null) {
                    s += c.toString() + " ";
                    c = c.next;
                }
                return s;
            }
        }

        /**
         * Stack constructor
         */
        public Stack() {
            // TODO
        }

        /**
         * Pushes element onto stack
         * @param data element to push onto stack
         */
        public void push(E data) {
          //  this.insertFirst(data);
        }

        /**
         * pops element off stack
         * @return element that was popped
         */
        public E pop() {
            // TODO
        	return null;
        }

        /**
         * shows topmost element of stack
         * @return topmost element of stack
         */
        public E peek() {
            E returnValue = li.deleteFirst().data;
            this.push(returnValue);
            return returnValue;
        }

        /**
         * tells whether or not stack is empty
         * @return true if empty, false if not
         */
        public boolean isEmpty() {
            return li.isEmpty();
        }

        /**
         * toString for stack
         * @return string representation of stack
         */
        @Override
        public String toString() {
            return li.toString();
        }
    }

    /**
     * constructor for Queue
     */
    public Queue() {
        this.left = new Stack<E>();
        this.right = new Stack<E>();
        this.size = 0;
    }

    /**
     * pours elements from full stack into empty stack
     */
    private void swap() {
    	if (this.right.isEmpty()) {
	        while (!this.left.isEmpty()) {
	        	this.right.push(this.left.pop());
	        }
    	} else {
    		while (!this.right.isEmpty()) {
	        	this.left.push(this.right.pop());
	        }
    	}
    }

    /**
     * size of queue
     * @return size of queue
     */
    public int size() {
        return this.size;
    }

    /**
     * tells whether or not Queue is empty
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * adds element to queue
     * @param data element to add to queue
     * @return true if successful
     */
    public boolean offer(E data) {
    	if(this.left.isEmpty() && this.right.isEmpty()){
    		this.left.push(data);
    		return true;
    	} else if (!this.right.isEmpty()){
    		swap();
    		this.left.push(data);
    		return true;
    	} else {
    		this.left.push(data);
    		return true;
    	}
    }

    /**
     * removes first element in queue
     * @return first element in queue
     */
    public E poll() {
    	if (this.size == 0){
    		return null;
    	}
        this.swap();
        E returnValue = this.right.pop();
        this.swap();
        return returnValue;
    }

    /**
     *  Peek implementation for queue
     * @return the <code>element</code> at the front of the line
     */
    public E peek() {
    	if (this.size == 0){
    		return null;
    	}
        this.swap();
        E returnValue = this.right.peek();
        this.swap();
        return returnValue;
    }

    /**
     * toString for Queue
     * @return string representation of queue
     */
    @Override
    public String toString() {
        return this.left.toString();
    }

    public static void main(String[] args) {
    	Stack<Integer> stackofInts = new Stack<>();
    	for (int i = 1; i<10 ; i++){
    		stackofInts.push(i);
    	}
    	
    	Queue <Integer> queueOfInts = new Queue<>();
    	for (int i = 1; i<10 ; i++){
    		queueOfInts.offer(i);
    	}
    	
    	System.out.println(queueOfInts);
    }

}