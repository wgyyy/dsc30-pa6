import org.junit.Test;

import java.util.ArrayList;
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

    @Test
    public void intersection() {
        BSTree test1=new BSTree();
        test1.insert(10);
        test1.insert(5);
        test1.insert(11);
        test1.insert(3);
        test1.insert(1);
        Iterator<Integer> iter1=test1.iterator();
        BSTree test2=new BSTree();
        test2.insert(10);
        test2.insert(5);
        test2.insert(11);
        Iterator<Integer> iter2=test2.iterator();
        ArrayList<Integer> expect=new ArrayList<Integer>();
        expect.add(5);
        expect.add(10);
        expect.add(11);
        assertEquals(expect,test1.intersection(iter1,iter2));
        BSTree test3=new BSTree();
        test3.insert("h");
        test3.insert("e");
        test3.insert("l");
        test3.insert("l");
        test3.insert("o");
        Iterator<String> iter3=test3.iterator();
        BSTree test4=new BSTree();
        test4.insert("h");
        test4.insert("a");
        test4.insert("p");
        test4.insert("p");
        test4.insert("y");
        Iterator<String> iter4=test4.iterator();
        ArrayList<String> expect2=new ArrayList<String>();
        expect2.add("h");
        assertEquals(expect2,test3.intersection(iter3,iter4));
        BSTree test5=new BSTree();
        test5.insert('D');
        test5.insert('S');
        test5.insert('C');
        Iterator<Character> iter5=test5.iterator();
        BSTree test6=new BSTree();
        test6.insert('C');
        test6.insert('S');
        test6.insert('E');
        Iterator<Character> iter6=test6.iterator();
        ArrayList<Character> expect3=new ArrayList<Character>();
        expect3.add('C');
        expect3.add('S');
        assertEquals(expect3,test5.intersection(iter5,iter6));
    }

    @Test
    public void levelMax(){
        BSTree test1=new BSTree();
        test1.insert(10);
        test1.insert(5);
        test1.insert(11);
        test1.insert(3);
        test1.insert(1);
        assertEquals(10,test1.levelMax(0));
        assertEquals(11,test1.levelMax(1));
        assertEquals(3,test1.levelMax(2));
        assertNull(test1.levelMax(4));
    }
}