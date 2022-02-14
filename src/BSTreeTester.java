import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class BSTreeTester {

    @org.junit.Test
    public void getRoot() {
        BSTree test=new BSTree();
        assertEquals(null,test.getRoot());
        test.insert(10);
        assertEquals(10,test.getRoot().getKey());
        test.insert(11);
        assertEquals(10,test.getRoot().getKey());
        BSTree test2=new BSTree();
        test2.insert("haha");
        assertEquals("haha",test2.getRoot().getKey());
    }

    @org.junit.Test
    public void getSize() {
        BSTree test=new BSTree();
        assertEquals(0,test.getSize());
        test.insert(10);
        assertEquals(1,test.getSize());
        test.insert(11);
        assertEquals(2, test.getSize());
        test.insert(11);
        assertEquals(2, test.getSize());
    }

    @Test(expected = NullPointerException.class)
    public void insertNPE(){
        BSTree test=new BSTree();
        test.insert(null);
    }

    @org.junit.Test
    public void insert() {
        BSTree test=new BSTree();
        test.insert(10);
        assertEquals(1,test.getSize());
        test.insert(11);
        assertEquals(2, test.getSize());
        test.insert(11);
        assertEquals(2, test.getSize());
    }

    @Test(expected = NullPointerException.class)
    public void findKeyNPE(){
        BSTree test=new BSTree();
        test.findKey(null);
    }

    @org.junit.Test
    public void findKey() {
        BSTree test=new BSTree();
        test.insert(10);
        assertEquals(true,test.findKey(10));
        test.insert(11);
        assertEquals(true, test.findKey(11));
        BSTree test2 = new BSTree();
        test2.insert("hahaha");
        assertEquals(true, test2.findKey("hahaha"));
    }

    @Test(expected = NullPointerException.class)
    public void insertDATANPE1(){
        BSTree test=new BSTree();
        test.insertData(null,1);
    }

    @Test(expected = NullPointerException.class)
    public void insertDATANPE2(){
        BSTree test=new BSTree();
        test.insert(10);
        test.insertData(10,null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void insertDATAIAE(){
        BSTree test=new BSTree();
        test.insert(10);
        test.insertData(2,1);
    }


    @org.junit.Test
    public void insertData() {
        BSTree test=new BSTree();
        test.insert(10);
        test.insertData(10, 1);
        LinkedList testList=new LinkedList();
        testList.add(1);
        assertEquals(testList,test.findDataList(10));
        test.insertData(10,2);
        testList.add(2);
        assertEquals(testList,test.findDataList(10));
        BSTree test2=new BSTree();
        test2.insert("haha");
        test2.insertData("haha","yeah!");
        LinkedList testList2=new LinkedList();
        testList2.add("yeah!");
        assertEquals(testList2,test2.findDataList("haha"));
    }

    @Test(expected = NullPointerException.class)
    public void findDataListNPE(){
        BSTree test=new BSTree();
        test.findDataList(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findDataListIAE(){
        BSTree test=new BSTree();
        test.insert(10);
        test.findDataList(100);
    }

    @org.junit.Test
    public void findDataList() {
        BSTree test=new BSTree();
        LinkedList testList=new LinkedList();
        test.insert(10);
        assertEquals(testList,test.findDataList(10));
        test.insertData(10,1);
        testList.add(1);
        assertEquals(testList,test.findDataList(10));
        BSTree test2=new BSTree();
        test2.insert("haha");
        test2.insertData("haha","yeah!");
        LinkedList testList2=new LinkedList();
        testList2.add("yeah!");
        assertEquals(testList2,test2.findDataList("haha"));
    }

    @org.junit.Test
    public void findHeight() {
        BSTree test=new BSTree();
        assertEquals(-1,test.findHeight());
        test.insert(10);
        assertEquals(0,test.findHeight());
        test.insert(5);
        assertEquals(1,test.findHeight());
        test.insert(11);
        test.insert(3);
        test.insert(1);
        assertEquals(3,test.findHeight());
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorNSEE(){
        BSTree test=new BSTree();
        Iterator<Integer> iter1=test.iterator();
        iter1.next();
    }


    @org.junit.Test
    public void iterator() {
        BSTree test=new BSTree();
        test.insert(10);
        test.insert(5);
        test.insert(11);
        test.insert(3);
        test.insert(1);
        Iterator<Integer> iter1=test.iterator();
        int actual=iter1.next();
        assertEquals(1,actual);
        actual=iter1.next();
        assertEquals(3,actual);
        iter1.next();
        iter1.next();
        actual=iter1.next();
        assertEquals(11,actual);
    }
}