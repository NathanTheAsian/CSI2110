/** 
 * Builds a doubly linked list of size 5 and prints it to the console.
 * 
 * @author Jochen Lang
 * @author Nathan Ng
 */

class DLinkList {
    DNode llist; //DNode object named llist
	DNode tail;

    DLinkList( int sz ) {
	if ( sz <= 0 ) {
	    llist = null;
	}
	else {
	    // start with list of size 1
	    llist = new DNode( "0", null, null ); 
	    DNode current = llist; // temp DNode for loop
	    // add further DNodes
	    for ( int i=1; i<sz; ++i ) {
		// create DNode and attach it to the list
		DNode DNode2Add = new DNode( Integer.toString(i), null , current); 
		current.setNext(DNode2Add);   // add first DNode
		current=DNode2Add;
		tail=DNode2Add;
	    }
	}
    }
    
    /**
     * Print all the elements of the list assuming that they are Strings
     */
    public void print() {
	/* Print the list */
	DNode current = llist; // point to the first DNode
	while (current != null) {
	    System.out.print((String)current.getElement() + " ");	
	    current = current.getNext(); // move to the next
	}
	System.out.println();	
    }

    public void deleteFirst() {
	if ( llist != null ) {
	    llist = llist.getNext();
	}
    }

    public void deleteLast() {
	DNode current = llist;
	if ( current == null ) return; // no node
	if ( current.getNext() == null ) { // only 1 node
	    llist = null;
		tail = null;
	    return;
	}
	tail = tail.getPrev();
	tail.getPrev().setNext(null);
	
    }

    // create and display a linked list
    public static void main(String [] args){
	/* Create the list */
	DLinkList llist = new DLinkList( 5 );
	/* Print the list */
	llist.print();
	llist.deleteFirst();
	llist.print();
	
	/* delete last and print 5 times */
	for ( int i=0; i< 5; ++i ) {
	    llist.deleteLast();
	    llist.print();
	}
    }
}
