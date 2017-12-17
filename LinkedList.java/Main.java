
public class Main {
	public static void main(String[] args)
	{
		MyLinkedList<Integer> test1 = new MyLinkedList<>();
		try {
			System.out.print("Prepend 1 \n");
			test1.prependElement(1);
			for (int i: test1) 
			{
				System.out.print(i);
			}
			
			System.out.print("\nRemove 1 \n");
			test1.removeElement(1);
			for (int i: test1) 
			{
				System.out.print(i);
			}

			System.out.print("\nAppend 2 \n");
			test1.addElement(2);
			for (int i: test1) 
			{
				System.out.print(i);
			}
			
			System.out.print("\nInsertAfter 2, value 3 \n");
			test1.insertAfter(2,3);
			for (int i: test1) 
			{
				System.out.print(i);
			}
			
			System.out.print("\nInsert After 3, Value 4 \n");
	        test1.insertAfter(3,4);
	        for (int i: test1) 
			{
				System.out.print(i);
			}
	        
	        System.out.print("\nPrepend 5 \n");
	        test1.prependElement(5);
	        for (int i: test1) 
			{
				System.out.print(i);
			}
	        
	        System.out.print("\nRemove 5 \n");
	        test1.removeElement(5);
	        for (int i: test1) 
			{
				System.out.print(i);
			}

	        System.out.print("\nInsert After 2, Value 5 \n");
	        test1.insertAfter(2,5);
	        for (int i: test1) 
			{
				System.out.print(i);
			}
	        
	        System.out.print("\nInsert After 2, Value 5 \n");
	        test1.insertAfter(2,5);
	        for (int i: test1) 
			{
				System.out.print(i);
			}

	        System.out.print("\nRemove 5 \n");
	        test1.removeElement(5);
	        for (int i: test1) 
			{
				System.out.print(i);
			}

	        System.out.print("\nRemove 4 \n");
	        test1.removeElement(4);
	        for (int i: test1) 
			{
				System.out.print(i);
			}
	        
	        System.out.print("\nAdd 5 \n");
	        test1.addElement(5);
	        for (int i: test1) 
			{
				System.out.print(i);
			}
	        
	        System.out.print("\nPrepend 1 \n");
	        test1.prependElement(1);
	        for (int i: test1) 
			{
				System.out.print(i);
			}

	        System.out.print("\nInsert After 3, Value 4 \n");
	        test1.insertAfter(3,4);
	        for (int i: test1) 
			{
				System.out.print(i);
			}
	        
	        System.out.print("\nAdd 6 \n");
	        test1.addElement(6);
	        for (int i: test1) 
			{
				System.out.print(i);
			}
	        System.out.print("\n");
		}
		catch(Exception E) 
		{
			System.err.println("What Are ya doing ya ape");
		}
		for (int i: test1) 
		{
			System.out.print(i);
		}
		System.out.print("\nThe Size is:" +test1.size());
	}
}
