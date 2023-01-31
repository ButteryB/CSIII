//� A+ Computer Science  -  www.apluscompsci.com
//Name -  Jonathon Lafleur
//Date -  1/23/2023
//Class - COMPSCI 3
//Lab  -  Binary Search Tree

import static java.lang.System.*;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinarySearchTree {
    private TreeNode root;
    private int size;

    public BinarySearchTree() {
        root = null;
    }

    public void add(Comparable val) {
        root = add(val, root);
    }

    private TreeNode add(Comparable val, TreeNode tree) {
        if (tree == null)
            tree = new TreeNode(val);

        Comparable treeValue = tree.getValue();
        int dirTest = val.compareTo(treeValue);

        if (dirTest < 0)
            tree.setLeft(add(val, tree.getLeft()));
        else if (dirTest > 0)
            tree.setRight(add(val, tree.getRight()));

        return tree;
    }

    public void inOrder() {
        inOrder(root);
        System.out.println("\n");
    }

    private void inOrder(TreeNode tree) {
        if (tree != null) {
            inOrder(tree.getLeft());
            System.out.print(tree.getValue() + " ");
            inOrder(tree.getRight());
        }
    }

    public void preOrder() {
        preOrder(root);
        System.out.println("\n");
    }
    private void preOrder(TreeNode tree){
        if(tree != null){
            System.out.print(tree.getValue() + " ");
            preOrder(tree.getLeft());
            preOrder(tree.getRight());
        }
    }
    // preOrder
    public void postOrder() {
        postOrder(root);
        System.out.println("\n");
    }
    private void postOrder(TreeNode tree){
        if(tree != null){
            postOrder(tree.getLeft());
            postOrder(tree.getRight());
            System.out.print(tree.getValue() + " ");
        }
    }
    // postOrder
    public void revOrder() {
        revOrder(root);
        System.out.println("\n");
    }
    private void revOrder(TreeNode tree){
        if(tree!=null){
            revOrder(tree.getRight());
            System.out.print(tree.getValue() + " ");
            revOrder(tree.getLeft());
        }
    }
    // revOrder
    public void levelOrder() {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode temp = queue.poll();
            if(temp!=null){
                System.out.print(temp.getValue() + " ");
                if(temp.getLeft()!=null){
                    queue.add(temp.getLeft());
                }
                if(temp.getRight()!=null){
                    queue.add(temp.getRight());
                }
            }
        }
        System.out.println("\n");
    }
    // levelOrder - use a queue

    public void zigzagOrder() {
        if (root == null) {
            return;
        }
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        s2.push(root);
        while (!s1.empty() || !s2.empty()) {
            while (!s1.empty()) {
                TreeNode temp = s1.pop();
                System.out.print(temp.getValue() + " ");
                if (temp.getRight() != null) {
                    s2.push(temp.getRight());
                }
                if (temp.getLeft() != null) {
                    s2.push(temp.getLeft());
                }
            }
            while (!s2.empty()) {
                TreeNode temp = s2.pop();
                System.out.print(temp.getValue() + " ");
                if (temp.getLeft() != null) {
                    s1.push(temp.getLeft());
                }
                if (temp.getRight() != null) {
                    s1.push(temp.getRight());
                }
            }
        }
        System.out.println("\n");
    }
    // zigzagOrder - hint below but could be solved in a different manner
    // loop thru a stack and load all nodes to a new stack(loading is based on direction)
    // set new stack to old and repeat
    public int getNumLevels(){
        return getNumLevels(root);
    }
    private int getNumLevels(TreeNode tree){
        if(tree == null){
            return 0;
        }
        return 1 + Math.max(getNumLevels(tree.getLeft()), getNumLevels(tree.getRight()));
    }
    // getNumLevels
    public int getNumLeaves(){
        return getNumLeaves(root);
    }
    private int getNumLeaves(TreeNode tree){
        if(tree == null){
            return 0;
        }
        if(tree.getLeft() == null && tree.getRight() == null){
            return 1;
        }
        return getNumLeaves(tree.getLeft()) + getNumLeaves(tree.getRight());
    }
    // getNumLeaves
    public int getWidth(){
        return getWidth(root);
    }
    private int getWidth(TreeNode tree){
        if(tree == null){
            return 0;
        }
        int wLeft = getWidth(tree.getLeft());
        int wRight = getWidth(tree.getRight());
        int wRoot = getNumLevels(tree.getLeft()) + getNumLevels(tree.getRight()) + 1;
        return Math.max(Math.max(wLeft,wRoot), Math.max(wRight, wRoot));
    }
    // getWidth - insure this works right for the 2nd test case

    public int getHeight(){
        return getHeight(root)-1;
    }
    private int getHeight(TreeNode tree){
        if(tree == null){
            return 0;
        }
        return 1 + Math.max(getHeight(tree.getLeft()), getHeight(tree.getRight()));
    }
    //getHeight(
    public void clear(){
        root = null;
    }
    //clear
    public int getNumNodes(){
        return getNumNodes(root);
    }
    private int getNumNodes(TreeNode tree){
        if(tree == null){
            return 0;
        }
        return 1 + getNumNodes(tree.getLeft()) + getNumNodes(tree.getRight());
    }
    // getNumNodes
    public boolean isFull(){
        return isFull(root);
    }
    private boolean isFull(TreeNode tree){
        if(tree == null){
            return true;
        }
        if(tree.getLeft() == null && tree.getRight() == null){
            return true;
        }
        if(tree.getLeft() != null && tree.getRight() != null){
            return isFull(tree.getLeft()) && isFull(tree.getRight());
        }
        return false;
    }
    // isFull
    public boolean contains(Comparable num){
        return contains(root,num);
    }
    private boolean contains(TreeNode tree,Comparable num){
        if(tree == null){
            return false;
        }
        if(tree.getValue() == num){
            return true;
        }
        return contains(tree.getLeft(), num) || contains(tree.getRight(), num);
    }
    // contains
    public TreeNode maxNode(){
        return maxNode(root);
    }
    private TreeNode maxNode(TreeNode tree){
        if(tree == null){
            return null;
        }
        TreeNode max = tree;
        TreeNode left = maxNode(tree.getLeft());
        TreeNode right = maxNode(tree.getRight());
        if(left != null && (int)left.getValue() > (int)max.getValue()){
            max = left;
        }
        if(right != null && (int)right.getValue() > (int)max.getValue()){
            max = right;
        }
        return max;
    }
    // maxNode
    public TreeNode minNode(){
        return minNode(root);
    }
    private TreeNode minNode(TreeNode tree){
        if(tree == null){
            return null;
        }
        TreeNode min = tree;
        TreeNode left = minNode(tree.getLeft());
        TreeNode right = minNode(tree.getRight());
        if(left != null && (int)left.getValue() < (int)min.getValue()){
            min = left;
        }
        if(right != null && (int)right.getValue() < (int)min.getValue()){
            min = right;
        }
        return min;
    }
    // minNode
    public int getSmallest(){
        return (int)minNode().getValue();
    }
    // getSmallest
    public int getLargest(){
        return (int)maxNode().getValue();
    }
    // getLargest
    public void remove(Comparable val) {
        root = remove(val, root);
    }

    private TreeNode remove(Comparable val, TreeNode tree) {
        if (tree == null)
            tree = new TreeNode(val);

        Comparable treeValue = tree.getValue();
        int dirTest = val.compareTo(treeValue);

        if (dirTest < 0)
            tree.setLeft(remove(val, tree.getLeft()));
        else if (dirTest > 0)
            tree.setRight(remove(val, tree.getRight()));
        else if(dirTest==0){
            if(tree.getLeft()==null){
                return tree.getRight();
            }
            else if(tree.getRight()==null){
                return tree.getLeft();
            }
            else{
                tree.setValue(minNode(tree.getRight()).getValue());
                tree.setRight(remove(tree.getValue(),tree.getRight()));
            }
        }
        return tree;
    }
    // remove - follow adds set up very closely and check powerpoint if needed
    // 1st case = no children
    // 2nd case = one child
    // 3rd case two children

    // ****BONUS****
    // display like a tree


    public String toString() {
        return toString(root);
    }

    private String toString(TreeNode tree) {
        if (tree == null)
            return "";
        return toString(tree.getLeft()) + tree.getValue() + " " + toString(tree.getRight());
    }
}