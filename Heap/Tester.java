import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Tester {
    public String[] heap;

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("/workspaces/CSIII/Heap/HeapData.in");
        Scanner scan = new Scanner(file);
        ArrayList<String> ary = new ArrayList<>();
        while (scan.hasNext()) {
            ary.add(scan.next());
        }
        Tester test = new Tester(ary);
        System.out.println(test.sPrint("", 1, 1));
        System.out.println(test.aPrint("", 1, 1,true));
        //System.out.println(ary);
    }

    public Tester(ArrayList<String> ary) {
        heap = new String[ary.size() + 1];
        for (int i = 1; i <= ary.size(); i++) {
            heap[i] = ary.get(i - 1);
        }
    }

    public String sPrint(String out, int index, int phase) {
        if (index == heap.length) {
            return out;
        }
        out += heap[index];
        if (index == (Math.pow(2, phase) - 1)) {
            out += "\n";
            return sPrint(out, index + 1, phase + 1);
        } else {
            return sPrint(out, index + 1, phase);
        }
    }

    public String aPrint(String out, int index, int phase, boolean first) {
        if (index == heap.length) {
            return out;
        }
        if (first) {
            out += spaces(spaceNum(phase+1)) + heap[index];
            first = false;
        } else {
            out += spaces(spaceNum(phase)) + heap[index];
        }
        if (index == (Math.pow(2, phase) - 1)) {
            out += "\n\n";
            return aPrint(out, index + 1, phase + 1,true);
        } else {
            return aPrint(out, index + 1, phase,false);
        }

    }

    private int spaceNum(int phase) {
        int val = (int)Math.round(((Math.log(heap.length) / Math.log(2)) + 1));
        return (int) Math.round(Math.pow(2, val - phase) - 1);
    }

    private String spaces(int n) {
        String out = "";
        for (int i = 0; i < n; i++) {
            out += " ";
        }
        return out;
    }
}
