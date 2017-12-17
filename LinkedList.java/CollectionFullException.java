import java.io.Serializable;

/**
 * Indicates that an attempt was made to add an element to a collection
 * that is full and can not accept any more elements
 */

public class CollectionFullException extends Exception implements Serializable
{
   public CollectionFullException ()
   {
      super();
   }
   public CollectionFullException (String s)
   {
      super(s);
   }
}