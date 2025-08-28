
public class LLStack<T>
{
	protected LLNode<T> top;
	
	public LLStack()
	{
		top = null;
	}
	
	public void push(T element)
	{
		LLNode<T> newNode = new LLNode<T>(element);
		newNode.setLink(top);
		top = newNode;
	}
	
	public void pop()
	{
		if (isEmpty())
			throw new StackUnderflowException("Pop attempted on an empty stack.");
		else
			top = top.getLink();	
	}
	
	public T top()
	{
		if (isEmpty())
			throw new StackOverflowException("Top attempted on an empty stack.");
		else
			return top.getInfo();	
	}
	
	public boolean isEmpty()
	{
		return ( top == null);
	}

	
}
