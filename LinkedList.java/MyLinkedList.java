
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * This Class is my implementation of a singly-linkedList. 
 *  
 */
public class MyLinkedList<T> 
         implements HansenCollection<T>, Iterable<T>, Comparable<MyLinkedList<T>>
{
    // Points to the first value in the LinkedList
    ListNode<T> _head;
    
    // Points to the last value in the LinkedList
    ListNode<T> _tail;
    int _size;
    
    
    /**
     * Constructs an empty linked list
     */
    public MyLinkedList()
    {
        _head = null;
        _tail = null;
        _size = 0;
    }
    
    
    /**
     * Returns false as a linkedList can never be full
     * 
     * @return false as LinkedList is only full when the computer is out of memory
     */
    @Override
    public boolean isFull()
    {
        return false;
    }
    
    
    /**
     *  Returns true if the LinkedList is empty
     *  
     *  @return true if the count of elements is 0
     */
    @Override
    public boolean isEmpty()
    {
        return _size == 0;
    }  
    
    
    /**
     * Makes the LinkedList Empty
     */
    @Override
    public void makeEmpty()
    {
        _size = 0;
        _head = null;
        _tail = null;
    }
    
    
    /**
     * Returns the size of the Linked List
     * 
     * @return the count of the elements in the LinkedList
     */
    @Override
    public int size()
    {
        return _size;
    }
    
    
    /**
     * Adds a new element at the first position of the LinkedList
     * 
     * @param newElement Element to add to the LinkedList
     * 
     * @throws IllegalArgumentException if element to add is null
     */
    public void prependElement(T newElement) throws IllegalArgumentException
    {
        ListNode<T> current;
        ListNode<T> newNode;
    	
        // If newElement equals null throw IllegalArgumentException
        if (newElement == null) 
        {
            throw new IllegalArgumentException();
        }
        
        // Get current MyLinkedList _head
        current = _head;
        newNode = new ListNode<T>(newElement, current);
    	    
        // Set head of current to new Node
        _head = newNode;
    	    
        // Special Case: If the tail is null set new node to 
        // MyLinkedList _tail as well
        if(_tail == null) 
        {
            _tail = newNode;
        }
    	    
        _size++;
    }
    
    
    /**
     * Inserts a new element after an existing element in the Linked List
     * 
     * @param existingObject an element in the LinkedList
     * 
     * @param newObject an element to add to the linkedList
     * 
     * @throws NoSuchElementException if the existingObject isn't in LinkedList
     * 
     * @throws IllegalArgumentException if either parameter is null
     */
    public void insertAfter(T existingObject, T newObject) 
    		throws NoSuchElementException, IllegalArgumentException
    {
        ListNode <T> current = _head;
        ListNode <T> next;
        ListNode <T> newNode;
        boolean wasInserted = false;
    	
        //Throw Exception if existingObject or newObject are null
        if(existingObject == null || newObject == null) 
        {
            throw new IllegalArgumentException();
        }
    	    
        newNode = new ListNode<T>(newObject);
    	    
        //Case Insert after the head node
        if (current != null)
        {
            if(current.getContents()==existingObject)
            {
                // Gets the location of the node that the 
                // new node is being inserted between
                next = current.getNext();
	    	    		
                // set the next node attribute of the new node to "next"
                current.setNext(newNode);
	    	    		
                // Finally set the tail pointer of the current node is
                // set to the newNode
                newNode.setNext(next);
                wasInserted = true;
                _size++;
            }
        }
    	    
        // General Case Insertion
        if(!wasInserted)
        {
            // Shared code causes the current to be the
            // node immediately before the node that will be
            // inserted after
            current = getBeforeNode(existingObject);
            
            // ensures that the node obtained wasn't in the List
            // and was returned as a null
            if(current != null) 
            {
                 // Moves one position over and sets the current node
                 // to the node we are inserting after
                 next = current.getNext();
                 current = next;
                  
                 // Gets the location of the node that the 
                 // new node is being inserted between
                 next = current.getNext();
                 
                 // set the next node attribute of the new node to "next"
                 newNode.setNext(next);
                 
                 // Finally set the tail pointer of the current node is
                 // set to the newNode
                 current.setNext(newNode);
                 wasInserted = true;
                 _size++;
             }
        }
    	    
        // Case: The insertion was after the last node in LinkedList
        if(current == _tail )
        {
            _tail = newNode;
        }
        
        // if the element was not inserted throw NoSuchElementException 
        if(!wasInserted) 
        {
             throw new NoSuchElementException();
        }
    		
    }
    
    
    /**
     * Appends an element to the end of the LinkedList
     * 
     * @param newElement element to be added
     * 
     * @throws CollectionFullException to satisfy inheritance
     * 
     * @throws IllegalArgumentException if element to add is null
     */
    @Override
    public void addElement(T newElement) 
            throws CollectionFullException, IllegalArgumentException 
    {
        ListNode<T> newNode;
        
        //if newElement is null throw IllegalArgumentException
        //initialize new Node
        if (newElement == null) 
        {
            throw new IllegalArgumentException();
        }
        
        newNode = new ListNode<T>(newElement);
        
        // If the head is null set new node to MyLinkedList _head as well
        if(isEmpty()) 
        {
            _head = newNode;
        }
        // Set the tail of the current MyLinkedList _tail to the new node
        else
        {
         	_tail.setNext(newNode);
        }
        
        
        	 
        //set the MyLinkedList _tail to new node
        _tail = newNode;
        
        _size++;
        
    }

    
    /**
     * Removes the first occurrence of the element from the
     * LinkedList. 
     * 
     * @param The element to be removed
     * 
     * @throws NoSuchElementException if the element to remove is not in
     * LinkedList, if LinkedList is empty or the element to remove is null
     */
    @Override
    public void removeElement(T elementToRemove) 
            throws NoSuchElementException 
    {
    	    ListNode<T> prev = null;
        ListNode<T> current = _head;
        boolean found = false;
    		
        //If the linkedlist is empty or the element to Remove is Null
        if(isEmpty() || elementToRemove == null) 
        {
             throw new NoSuchElementException("Empty or illegal removal");
        }	
    		
        
        while(current != null && !found) 
        {
    		  if(current.getContents().equals(elementToRemove) ) 
    		  {
    			// Found something to remove
    			found = true;
    			_size--;
    			if(prev == null) 
    			{
    				
    				if(_head == _tail) 
    				{
    					_head = null;
    					_tail = null;
    				}
    				else 
    				{
    					_head = current.getNext();
    				}
    				
    			} 
    			else 
    			{
    				if (current.equals(_tail))
    				{
                     _tail = prev;
                     prev.setNext(null);
    			    }
    				else
    				{
    					prev.setNext(current.getNext());
    				}
    			}
    		  }
    		  // Maintain loop invariant: prev is the node before cur.
    		  if(!found) {
	    		  if(current.getNext() != _head) 
	    		  {
	    			    prev = current;
	    		  }
	    		  current = current.getNext();
    		  }
     	}
              
    		
        // If Element to remove wasn't located in the List 
        // throw NoSuchElementException 
        if (current == null) 
        {
            throw new NoSuchElementException("Wasn't found");
        }
        
    }

    
    /**
     * Finds and returns an element in the linkedList or 
     * returns null if the elementSought isn't in the LinkedList
     * 
     * @param The element sought in the LinkedList
     * 
     * @return The element found or null
     */
    @Override
    public T findElement(T elementSought) 
    {
        T results = null;
        ListNode<T> current = _head;
        boolean found = false;
        
        // If the elementSought isn't null
        if(elementSought != null && current != null) 
        {
            // While the elementSought is not found and the next node isn't null
            while (!found && current != null)
            {
	    	    
                // If the elementSought is equal to the contents of current
                // return the element found and stop the looping
                if(current.getContents().equals(elementSought))
                {
                    results = current.getContents();
                    found = true;
                }
    	    	    	     
                current = current.getNext(); 
            }
        }
        
        //return results which is either the element found or null
        return results;
    }

    
    /**
     * Returns true if the LinkedList contains the elementSought
     * 
     * @return true if the LinkedList contains the elementSought
     */
    @Override
    public boolean containsElement(T elementSought) 
    {
         return findElement(elementSought) != null;
    }
    
    
    /**
     * Implements the iterator interface
     */
    public Iterator<T> iterator() 
    {
        return new LinkedListIterator<>();
    }
    	
    
    // Private Class provides the implementation to the iterator
    private class LinkedListIterator<E> implements Iterator<E>
    {
        @SuppressWarnings("unchecked")
		ListNode<E> current = (ListNode<E>) _head;
        E results;

        // hasNext() ensures iterator ends
        // required for iterator implementation
        public boolean hasNext() 
        { 
            return !isEmpty() &&  current != null; 
        }
		
        // Method returns each of the elements from 
        // the Linked List
        public E next() 
        {
          	if(!hasNext()) 
          	{
          		throw new NoSuchElementException();
          	}
                
          	results = current.getContents();
            current = current.getNext();
            
            return results;
        }
        
        public void remove() 
        {
        		throw new UnsupportedOperationException();
        }
        
    }
    
    
    /**
     * Compares two LinkedLists for equality
     * 
     * @param o linked list that is compared
     * 
     * @throws ClassCastException if the elements are not comparable
     * 
     * @return integer setReturn which is negative if o is larger or
     * positive if o is smaller. 
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public int compareTo(MyLinkedList<T> o) throws ClassCastException
    {
        int setReturn = 0;
        ListNode<T> thisNode = this._head;
        ListNode<T> otherNode = o._head;
    		
        MyLinkedList<T> thisList = this;
        
        
            // the loop continues until the Linked Lists are proven equal
            // or immediately stopped when proven unequal
            while(thisNode != null && otherNode != null && setReturn == 0)
            {
                // Compares the values of the contents of the nodes
                // by casting them as Comparable. Throws ClassCastException
                // naturally, if the list types are incomparable
                setReturn = ( (Comparable) thisNode.getContents())
                        .compareTo((Comparable) otherNode.getContents());
    				
                 // If the value of the compareTo operation is 0,
                 // the comparison is run on the next nodes from both
                 // Lists
                 if(setReturn == 0) 
                 {
                     thisNode = thisNode.getNext();
                     otherNode = otherNode.getNext();
                 }
            }
            
            // Evaluates the lengths of the compared Lists first
            // if this List is larger returns a positive value
            if(setReturn == 0) 
            {
            	    if (thisList.size() > o.size()) 
                {
                    setReturn = 1;
                }  
                
                // If o list is larger, the other size is positive
                else if (thisList.size() < o.size()) 
                {
                    setReturn = -1;
                }
            }
    		
        return setReturn;
        
    }
    
    
    // Method returns the node before a node containing
    // the matching element when the matching node isn't
    // the head node
    private ListNode<T> getBeforeNode(T elementSought)
    {
        ListNode<T> found = null;
    		
        // Initialize results as null
        ListNode<T> current = _head;
        
        // If the elementSought isn't null
        if(elementSought != null && current != null) 
        {
            while (found == null && current.getNext() != null)
            {
	    	    
                // If the elementSought is equal to the contents of current
                // and stop the searching
                if(current.getNext().getContents()==elementSought)
                {
                    found = current;
                }
                
                // Else check the next node and continue
                else 
                {
                    current = current.getNext();
                }
            }
        }
        
        return found;
    }
	
    
	   /**
	    * An inner-class for use by a Linked List to hold the contents of
	    * the list. Note that this class and its methods are not public 
	    * nor private, their visibility is within the "package" and that
	    * includes any other classes defined in the same file. This class d
	    * efinition can appear INSIDE thedefinition of a Linked List class. 
	    *
	    * @Author David M. Hansen
	    * @Version 2.1
	    * @param <O> type of object contained by this node
	    */
	   static class ListNode <O> 
	   {
	      private O _contents; // The object held by this node
	      private ListNode<O> _next; // A reference to the next node

	      /**
	       * Create a new ListNode holding the given object and pointing 
	       * to the given node as the next node in the list
	       *
	       * @param objectToHold the object to store in this node
	       * @param nextNode the node this node should point to. Can be null
	       */
	      ListNode(O objectToHold, ListNode<O> nextNode) 
	      { 
	         _contents = objectToHold;
	         _next = nextNode;
	      }


	      /**
	       * Create a new ListNode holding the given object. No next node
	       *
	       * @param objectToHold the object to store in this node
	       */
	      ListNode(O objectToHold) 
	      { 
	         // Use the more general constructor passing null as the next
	         // node
	         this(objectToHold, null);
	      }


	      // Accessors

	      /**
	       * Return the object held by this node
	       *
	       * @return Object held by this node
	       */
	      O getContents() 
	      { 
	         return _contents;
	      }


	      /**
	       * Return the next node
	       *
	       * @return the next node
	       */
	      ListNode<O> getNext() 
	      { 
	         return _next;
	      }


	      // Mutators

	      /**
	       * Set the node this node is linked to 
	       *
	       * @param nextNode the node to point to as our next node.
	       */
	      void setNext(ListNode<O> nextNode) 
	      { 
	         _next = nextNode;
	      }

	   } // ListNode
}