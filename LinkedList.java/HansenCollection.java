
import java.util.NoSuchElementException;

public interface HansenCollection < T >
{
	public void addElement(T newElement) 
	      throws CollectionFullException, IllegalArgumentException;
	public void removeElement(T elementToRemove) throws NoSuchElementException;
	public T findElement(T elementSought);
	public boolean containsElement(T elementSought);
	public boolean isFull();
	public boolean isEmpty();
	public void makeEmpty();
	public int size();
}

