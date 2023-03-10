import static java.lang.System.out;


public class Heap {

    private int[] data;
    private int size;

    public Heap() {
        this(10);
    }

    public Heap(int capacity) {
        data = new int[capacity];
        size = 0;
    }

    public void add(int value) {
        if (size == data.length) {
            doubleData();
        }
        data[size] = value;
        size++;
        swapUp(size - 1);
    }

    private void swapUp(int bot) {
        int parent = (bot - 1) / 2;
        if (data[parent] < data[bot]) {
            swap(parent, bot);
            swapUp(parent);
        }
    }

    public void remove() {
        if (size == 0) {
            return;
        }
        data[0] = data[size - 1];
        size--;
        swapDown(0, size - 1);
    }

    private void swapDown(int start, int stop) {
        int child1 = start * 2 + 1;
        int child2 = start * 2 + 2;
        if (child1 > stop) {
            return;
        }
        if (child2 > stop) {
            if (data[start] < data[child1]) {
                swap(start, child1);
            }
            return;
        }
        if (data[child1] > data[child2]) {
            if (data[start] < data[child1]) {
                swap(start, child1);
                swapDown(child1, stop);
            }
        } else {
            if (data[start] < data[child2]) {
                swap(start, child2);
                swapDown(child2, stop);
            }
        }
    }

    // simple helper method that swaps values at indices loc1 and loc2
    private void swap(int loc1, int loc2) {
        int temp = data[loc1];
        data[loc1] = data[loc2];
        data[loc2] = temp;
    }

    private void doubleData() {
        int[] temp = new int[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            temp[i] = data[i];
        }
        data = temp;
    }

    // part 2
    public void print() {
        out.println("\n\nPRINTING THE HEAP!\n\n");
        int[] temp = new int[size];
        for(int i = 0;i<size;i++){
            temp[i] = data[i];
        }
        out.println(java.util.Arrays.toString(temp));
    }

    @Override
    public String toString() {
        return java.util.Arrays.toString(data);
    }

    public static void main(String... a) {
        Heap heap = new Heap();

        // test add and remove here
        
        
        
        // uncomment to test in part2 
        // should print like a tree
        
        heap.add(1);
        heap.add(2);
        heap.add(8);
        heap.add(9);
        heap.add(10);
        heap.add(7);
        heap.add(75);
        heap.add(17);
        heap.add(5);

        heap.print();
        heap.remove();
        heap.print();
        heap.remove();
        heap.print();
        heap.remove();
        heap.print();
        heap.remove();
        heap.print();
        heap.remove();
        heap.print();
        heap.remove();
        heap.print();
        heap.remove();

        heap.print();
        heap.add(25);
        heap.print();
        heap.add(35);
        heap.print();
        heap.remove();
        heap.print();
    }
}
