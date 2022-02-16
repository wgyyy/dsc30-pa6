/*
 * Name: Gaoying Wang
 * PID:  A16131629
 */

import com.sun.java.swing.plaf.windows.WindowsPopupMenuSeparatorUI;

import java.util.*;

/**
 * Binary search tree implementation.
 * 
 * @author Gaoying Wang
 * @since  ${2022-0210}
 */
public class BSTree<T extends Comparable<? super T>> implements Iterable {

    /* * * * * BST Instance Variables * * * * */

    private int nelems; // number of elements stored
    private BSTNode root; // reference to root node

    /* * * * * BST Node Inner Class * * * * */

    protected class BSTNode {

        T key;
        LinkedList<T> dataList;
        BSTNode left;
        BSTNode right;

        /**
         * A constructor that initializes the BSTNode instance variables.
         *
         * @param left     Left child
         * @param right    Right child
         * @param dataList Linked list of related info
         * @param key      Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, LinkedList<T> dataList, T key) {
            this.key = key;
            this.dataList = dataList;
            this.left = left;
            this.right = right;
        }

        /**
         * A constructor that initializes BSTNode variables. Note: This constructor is
         * used when you want to add a key with no related information yet. In this
         * case, you must create an empty LinkedList for the node.
         *
         * @param left  Left child
         * @param right Right child
         * @param key   Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, T key) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.dataList=new LinkedList<T>();
        }

        /**
         * Return the key
         *
         * @return The key
         */
        public T getKey() {
            return this.key;
        }

        /**
         * Return the left child of the node
         *
         * @return The left child of the node
         */
        public BSTNode getLeft() {
            return this.left;
        }

        /**
         * Return the right child of the node
         *
         * @return The right child of the node
         */
        public BSTNode getRight() {
            return this.right;
        }

        /**
         * Return the linked list of the node
         *
         * @return The linked list of the node
         */
        public LinkedList<T> getDataList() {

            return this.dataList;
        }

        /**
         * Setter for left child of the node
         *
         * @param newleft New left child
         */
        public void setleft(BSTNode newleft) {

            this.left=newleft;
        }

        /**
         * Setter for right child of the node
         *
         * @param newright New right child
         */
        public void setright(BSTNode newright) {

            this.right=newright;
        }

        /**
         * Setter for the linked list of the node
         *
         * @param newData New linked list
         */
        public void setDataList(LinkedList<T> newData) {

            this.dataList=newData;
        }

        /**
         * Append new data to the end of the existing linked list of the node
         *
         * @param data New data to be appended
         */
        public void addNewInfo(T data) {

            this.dataList.add(data);
        }

        /**
         * Remove 'data' from the linked list of the node and return true. If the linked
         * list does not contain the value 'data', return false.
         *
         * @param data Info to be removed
         * @return True if data was found, false otherwise
         */
        public boolean removeInfo(T data) {

            return this.dataList.remove(data);
        }

    }

    /* * * * * BST Methods * * * * */

    /**
     * 0-arg constructor that initializes root to null and nelems to 0
     */
    public BSTree() {
        this.root=null;
        this.nelems=0;
    }

    /**
     * Return the root of BSTree. Returns null if the tree is empty
     *
     * @return The root of BSTree, null if the tree is empty
     */
    public BSTNode getRoot() {
        if (this.nelems==0){
            return null;
        }else{
            return this.root;
        }
    }

    /**
     * Return the BST size
     *
     * @return The BST size
     */
    public int getSize() {
        return this.nelems;
    }

    private void nodeInsertion(BSTNode insertNode, BSTNode input){
        if (insertNode.getKey().compareTo(input.getKey())<0){
            if (input.getLeft()==null) {
                input.setleft(insertNode);
            } else {
                nodeInsertion(insertNode,input.getLeft());
            }
        }else{
            if (input.getRight()==null) {
                input.setright(insertNode);
            } else {
                nodeInsertion(insertNode,input.getRight());
            }
        }
    }

    private boolean FindNode(T target, BSTNode input){
        if (input.getLeft() == null && input.getRight() == null){
            if ((input.getKey()).compareTo(target)==0){
                return true;
            }else{
                return false;
            }
        } else {
            if ((target).compareTo(input.getKey())<0){
                if(input.getLeft()==null){
                    return false;
                }else if (target.compareTo(input.getLeft().getKey())==0) {
                    return true;
                } else{
                        return FindNode(target,input.getLeft());
                }
            } else {
                if(input.getRight()==null){
                    return false;
                }else if (target.compareTo(input.getRight().getKey())==0) {
                    return true;
                }else{
                    return FindNode(target,input.getRight());
                }
            }
        }
    }

    private BSTNode GetNode(T target, BSTNode input){
        if (input.getKey().compareTo(target)==0){
            return input;
        }else {
            if (input.getKey().compareTo(target)<0){
                return GetNode(target,input.getRight());
            }else{
                return GetNode(target,input.getLeft());
            }
        }
    }

    private void setRoot(T key){
        BSTNode newNode=new BSTNode(null,null,key);
        this.root= newNode;
    }


    /**
     * Insert a key into BST
     * 
     * @param key
     * @return true if insertion is successful and false otherwise
     */
    public boolean insert(T key) {
        if (key == null){
            throw new NullPointerException();
        }
        BSTNode newNode = new BSTNode(null, null, key);
        if (this.getRoot()==null) {
            this.setRoot(key);
            this.nelems++;
            return true;
        }else if (this.findKey(key)) {
            return false;
        } else {
            nodeInsertion(newNode, this.getRoot());
            this.nelems++;
            return true;
        }

    }


    /**
     * Return true if the 'key' is found in the tree, false otherwise
     *
     * @param key To be searched
     * @return True if the 'key' is found, false otherwise
     * @throws NullPointerException If key is null
     */
    public boolean findKey(T key) {
        if (key == null){
            throw new NullPointerException();
        }else {
            return this.FindNode(key, this.getRoot());
        }
    }

    /**
     * Insert 'data' into the LinkedList of the node whose key is 'key'
     *
     * @param key  Target key
     * @param data To be added to key's LinkedList
     * @throws NullPointerException     If eaither key or data is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public void insertData(T key, T data) {
        if(!this.findKey(key)){
            throw new IllegalArgumentException();
        } else if (key == null || data == null){
            throw new NullPointerException();
        } else{
            BSTNode N=this.GetNode(key, this.getRoot());
            N.getDataList().add(data);
        }
    }

    /**
     * Return the LinkedList of the node with key value 'key'
     *
     * @param key Target key
     * @return LinkedList of the node whose key value is 'key'
     * @throws NullPointerException     If key is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public LinkedList<T> findDataList(T key) {
        if (key == null){
            throw new NullPointerException();
        }else if(!this.findKey(key)){
            throw new IllegalArgumentException();
        }else{
            BSTNode N=this.GetNode(key, this.getRoot());
            return N.getDataList();
        }
    }

    /**
     * Return the height of the tree
     *
     * @return The height of the tree, -1 if BST is empty
     */
    public int findHeight() {
        return findHeightHelper(this.root);
    }

    /**
     * Helper for the findHeight method
     *
     * @param root Root node
     * @return The height of the tree, -1 if BST is empty
     */
    private int findHeightHelper(BSTNode root) {
        if (root == null){
            return -1;
        }
        int left = findHeightHelper(root.left);
        int right = findHeightHelper(root.right);
        if (left > right){
            return left + 1;
        }else{
            return right + 1;
        }
    }

    /* * * * * BST Iterator * * * * */

    public class BSTree_Iterator implements Iterator<T> {
        public Stack<BSTNode> Ite;
        public BSTree_Iterator() {
            this.Ite=new Stack();
            BSTNode current=root;
            if (root!=null) {
                while (current.left != null) {
                    Ite.push(current);
                    current = current.left;
                }
                Ite.push(current);
            }
        }

        public boolean hasNext() {
            if(Ite.isEmpty()) {
                return false;
            } else {
                return true;
            }
        }

        public T next() {
            if (!this.hasNext()){
                throw new NoSuchElementException();
            }
            BSTNode return_Node=this.Ite.pop();
            T return_value=return_Node.getKey();
            if (!(return_Node.getRight()==null)){
                this.Ite.push(return_Node.getRight());
                return_Node=return_Node.getRight();
                while (!(return_Node.getLeft()==null)){
                    this.Ite.push(return_Node.getLeft());
                    return_Node=return_Node.getLeft();
                }
            }
            return return_value;
        }
    }

    public Iterator<T> iterator() {
        return new BSTree_Iterator();
    }

    /* * * * * Extra Credit Methods * * * * */

    public ArrayList<T> intersection(Iterator<T> iter1, Iterator<T> iter2) {
        ArrayList<T> first=new ArrayList<T>();
        ArrayList<T> second=new ArrayList<T>();
        ArrayList<T> result=new ArrayList<T>();
        while (iter1.hasNext()){
            first.add(iter1.next());
        }
        while (iter2.hasNext()){
            second.add(iter2.next());
        }
        for (int i=0; i<first.size();i++){
            if (second.contains(first.get(i))){
                result.add(first.get(i));
            }
        }
        return result;
    }

    public T levelMax(int level) {
        if (level > this.findHeight()) {
            return null;
        }
        BSTNode current = this.getRoot();
        for (int i = 0; i < level; i++) {
            if (findHeightHelper(current.getRight()) < level - i - 1) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }
        return current.getKey();
    }
}
