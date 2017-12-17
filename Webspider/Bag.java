import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Bag class is a data structure that contains objects of a
 * type and maintains a count of each. This bag was implemented
 * using a Hashmap<T, AtomicInteger>.
 *
 * @param <T>
 */
public class Bag<T> 
{
    private int _size;
    private HashMap<T, AtomicInteger> _bag;

    /**
     * Construct an empty bag
     */
    public Bag() 
    {
        _size = 0;
        _bag = new HashMap<>();
    }


    /**
     * Return true if the element sought is present 
     *
     * @param element to search for
     * @return true if the element is in the Bag 
     */
    public boolean contains(T element) 
    {
        // contains Key returns a boolean if element is in HashMap
        return _bag.containsKey(element);
    }


    /**
     * Return a count of the number of instances of the element
     *
     * @param element to search for
     * @return number of instances of the element in the Bag 
     */
    public int countOf(T element) 
    {
        // HashMap getOrDefault method returns the Atomic Integer in the Hashmap 
        // or some default value. In this case would be an atomic integer of
        // 0. AtomicInteger becomes an integer with the .get() method.
        return _bag.getOrDefault(element, new AtomicInteger()).get();
    }


    /**
     * Add the element to the Bag
     *
     * @param element to add to the Bag
     */
    public void add(T element) 
    {
    	
        // CASE: The element does not exist in the bag
        if (!contains(element)) 
        {
    	    	
            // Adds a new element with the default value of 1
            _bag.put(element, new AtomicInteger(1));
            _size++;
        }
    	    
        // CASE: The element already exists in the bag
        else
        {
    	    	
            // The AtomicInteger is self-iterated by a single value
            _bag.get(element).incrementAndGet();
            _size++;
        }    	  
    }


    /**
     * Remove at most one instance from the Bag 
     *
     * @param element to remove from the bag 
     * @return true if an element was removed, false otherwise
     */
    public boolean remove(T element)
    {
        boolean removed = false;
    	    
        // If there is an element that can be removed
        if (contains(element)) 
        {
    	    	
            // CASE: There is a single element in the Bag
            if (_bag.get(element).get() == 1) 
            {
                _bag.remove(element);
                _size--;
                removed = true;
            }
    	    	    
            // CASE: There is more than one of an element in the bag
            else 
            {
    	    	    	
                // Get the value of the element and self-decrement it
                _bag.get(element).decrementAndGet();
                _size--;
                removed = true;
            }  
        } 	      
        return removed;
    }


    /**
     * Return true if the Bag is empty
     *
     * @return true if the Bag is empty
     */
    public boolean isEmpty() 
    {
        return _size == 0;
    }


    /**
     * Return a count of the number of elements in the Bag
     *
     * @return number of elements in the Bag
     */
    public int size() 
    {
        return _size;
    }
 }
