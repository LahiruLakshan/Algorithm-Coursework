public class DList {
    public static class ListNode
    {
        int data;
        ListNode next, previous;

        ListNode(int data)
        {
            this.data=data;
            next=null;
            previous =null;
        }
    }
    private static ListNode first,last;
    /* function to remove duplicates from a
    sorted doubly linked list */
    public static void removeDuplicates() {
        ListNode n = first;
        while (n != null ){
            while (n. next != null && n. next . data == n. data ){
                // remove the next node from the list
                n. next = n. next . next ;
                if(n. next == null ) // n is now the last node
                    last = n;
                else
                    n. next . previous = n;
            }
            n = n. next ;
        }
    }

    /* Function to insert a node at the beginning
	of the Doubly Linked List */
    public static ListNode push(int data)
    {
        ListNode newListNode =	new ListNode(data);

        newListNode.previous =null;
        newListNode.next =first;
        if(first!=null)
        {
            first.previous = newListNode;
        }
        first= newListNode;
        return first;
    }

    public static void printList(ListNode first)
    {
        if (first == null)
            System.out.println("Doubly Linked list empty");

        while (first != null)
        {
            System.out.print(first.data+" ") ;
            first = first.next;
        }
    }

    public static void main(String[] args) {
        first=push( 8);
        first=push( 7);
        first=push( 4);
        first=push( 4);
        first=push( 4);
        first=push( 3);
        first=push( 1);
        first=push( 1);


        System.out.println("Original Doubly LinkedList");
        printList(first);

        removeDuplicates();
        System.out.println("\nDoubly linked list after removing duplicates:");
        printList(first);
    }
}
