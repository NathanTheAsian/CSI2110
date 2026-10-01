public class TryStack2 {

    protected Integer[] array;
    protected Stack<Integer> stack;

    /**
     * Default constructor.
     */
    public TryStack2() {
        setArray(50);
    }

    public TryStack2(int n) {
        setArray(n);
    }

    public TryStack2(int n, LinkedStack<Integer> stack) {
        setArray(n);
        this.stack = stack;
    }

    /**
     * Gets the array.
     *
     * @return the array
     */
    public Integer[] getArray() {
        return this.array;
    }

    /**
     * Sets the array.
     *
     * @param n size of the array
     */
    public void setArray(int n) {
        this.array = new Integer[n];

        for (int i = 0; i < n; i++) {
            getArray()[i] = i * 2;
        }
    }

    /**
     * Gets the stack.
     *
     * @return the stack
     */
    public Stack<Integer> getStack() {
        return this.stack;
    }

    /**
     * Sets the stack to a LinkedStack.
     *
     * @param n stack capacity parameter placeholder
     */
    public void setStack(int n) {
        stack = new LinkedStack<>();
    }

    /**
     * Reverses the array using a stack.
     */
    protected void reverseArray() {
        for (int i = 0; i < getArray().length; i++) {
            this.stack.push(getArray()[i]);
        }
        System.out.println("\nInspecting stack (top..bottom): " + this.stack);

        for (int i = 0; i < getArray().length; i++) {
            getArray()[i] = this.stack.pop();
        }
    }

    /**
     * Prints an array of any size.
     */
    protected void printArray() {
        System.out.println();

        for (int elems : getArray()) {
            System.out.print(elems + "\t");
        }
        System.out.println();
    }

    protected void runSimulation() {
        printArray();
        reverseArray();
        printArray();
    }

    /**
     * Main driver method that will reverse an array.
     *
     * @param args
     */
    public static void main(String[] args) {
        int n = args.length > 0 ? Integer.valueOf(args[0]) : 50;

        TryStack2 tryStack = new TryStack2(n, new LinkedStack<>());
        tryStack.runSimulation();
    }
}
