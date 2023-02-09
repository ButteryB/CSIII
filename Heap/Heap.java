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
        if((start*2+1)>size){
            return;
        }
        int child1 = start*2+1;
        int child2 = start*2+2;
        if(data[child1]>data[start]){
            swap(child1,start);
            swapDown(child1,0);
        }else if(child2>= size){
            return;
        }else if(data[child2]>data[start]){
            swap(child2,start);
            swapDown(child2,0);
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
        out.println();
    }

    @Override
    public String toString() {
        return "[" + java.util.Arrays.toString(data) + "]";
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
