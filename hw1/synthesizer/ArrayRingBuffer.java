// TODO: Make sure to make this class a part of the synthesizer package
// package <package name>;
package synthesizer;
import edu.princeton.cs.algs4.Average;

import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        rb = (T[]) new Object[capacity];
        this.capacity = capacity;
        this.first = 0;
        this.last = 0;
        this.fillCount = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        if (x == null) {
            return;
        }
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        fillCount += 1;
        rb[last] = x;
        if (last == capacity - 1) {
            last = 0;
        }
        else {
            last += 1;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T temp = rb[first];
        rb[first] = null;
        if (first == capacity - 1) {
            first = 0;
        }
        else {
            first += 1;
        }
        fillCount -= 1;
        return temp;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.
    private class ArrayRingBufferiter<T> implements Iterator<T> {
        private int temp;
        private int count;

        public ArrayRingBufferiter() {
           temp = first;
           count = fillCount;
        }

        @Override
        public boolean hasNext() {
            if (count > 0) {
                count -= 1;
                return true;
            }
            else {
                return false;
            }
        }

        @Override
        public T next() {
           T item = (T) rb[temp];
           if (temp == capacity - 1) {
               temp = 0;
           }
           else {
               temp += 1;
           }
           return item;
        }
    }
    public Iterator<T> iterator() {
        return new ArrayRingBufferiter<>();
    }

    //public static void main(String[] args) {
        //ArrayRingBuffer<> arb = new ArrayRingBuffer<>();
    //}
}
