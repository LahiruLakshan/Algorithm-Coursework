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
}
