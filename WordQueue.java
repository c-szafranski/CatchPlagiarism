import java.util.ArrayList;

// -------------------------------------------------------------------------
/**
 * A single queue will only hold a variable number of words given by the user
 *
 * @author Chris Szafranski
 * @version Nov 18, 2015
 */
public class WordQueue<T>
{

    private int          size;
    private int          length; 
    private ArrayList<T> q;


    // ----------------------------------------------------------
    /**
     * Create a new WordQueue object.
     *
     * @param length
     *            of queue
     */
    public WordQueue(int length)
    {
        q = new ArrayList<T>(length);
        size = 0;
        this.length = length;
    }


// ----------------------------------------------------------
    /**
     * Returns Queue
     *
     * @return q of type Queue
     */
    public ArrayList<T> getQueue()
    {
        return q;
    }


    // ----------------------------------------------------------
    /**
     * Adds element to queue
     *
     * @param x
     */
    public void enque(T x)
    {
        if (size < length)
        {
          
            q.add(x); // adds element to end of list
            size++;
        }
        else
        {
            return;
        }
    }


    // ----------------------------------------------------------
    /**
     * deques element in tail of queue
     */
    public void deque()
    {
        if (size > 0)
        {
            q.remove(0);
            size--;
            if (size < 0)
                size = 0;

        }
        else
            return;

    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     *
     * @return size
     */
    public int getSize()
    {
        return this.size;

    }


    // ----------------------------------------------------------
    /**
     * Checks if queue is full
     *
     * @return boolean
     */
    public boolean isFull()
    {
        if (size >= length)
        {
            return true;
        }
        else
            return false;
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     *
     * @param x
     *            a word queeue
     * @return string of words in queue
     */
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < size; i++)
        {
            str.append(q.get(i));
            str.append(" ");
        }
        return str.toString();

    }


}
