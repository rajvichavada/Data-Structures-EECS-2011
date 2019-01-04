
public class LinkedList {
	
	Node head;
	
	static class Node 
	{
		int data;
		Node next;
	}
	
	public static Node findMid (Node head)
	{
		Node middle = head;
		Node fast = head;
		
		while (fast != head || fast.next != head)
		{
			fast = fast.next.next;
			middle = middle.next;
		}
		return middle;
		
	}
	
	 public static void main(String args[]) 
	 {
		LinkedList link = new LinkedList();
		Node head = link.head;
		Node middle = findMid(head);
	 }
	

}
