import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.*;
import java.io.*;
import java.net.*;
import java.time.*;

public class Lab11_Tests {
    /*
        Complete the test case below that checks to see that threads A and 
B have both contributed 100 entries respectively
        to the shared ArrayList when they have both finished running.
    */
    @Test
    public void test1() {
        Lab11_Thread threadA = new Lab11_Thread("A1", 100);
        Lab11_Thread threadB = new Lab11_Thread("B1", 100);

        threadA.start();
        threadB.start();

        int countA = 0;
        int countB = 0;

        try {
            threadA.join();
            threadB.join();
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // interrupted
        }
        countA = threadA.getData().size();
        countB = threadB.getData().size();
        assertEquals(true, countA==100 && countB==100);
    }

    /*
        Complete the test case below that checks to see if the shared 
ArrayList has at least 10 entries after 500ms of system time
    */
    @Test
    public void test2() {

        Lab11_Thread threadA = new Lab11_Thread("A2", 500);
        Lab11_Thread threadB = new Lab11_Thread("B2", 500);

        threadA.start();
        threadB.start();
        
        int countA = 0;
        int countB = 0;

        try {
            Thread.sleep(500);
            countA = threadA.getData().size();
            countB = threadB.getData().size();
            
            threadA.join();
            threadB.join();
        } catch (Exception e){
            e.printStackTrace();
        }
        assertEquals(true, countA>=10 && countB>=10);

    }

    /*
        Complete the test case below that checks to see if thread A 
finishes adding its 10 entries before thread B was allowed to 
        add anything to the shared ArrayList
    */
    @Test
    public void test3() {
        Lab11_Thread threadA = new Lab11_Thread("A3", 10);
        Lab11_Thread threadB = new Lab11_Thread("B3", 10);

        threadA.start();
        
        try {
            threadA.join();
        } catch (Exception e){
            e.printStackTrace();
        }
        int countA = threadA.getData().size();
        int countB = threadB.getData().size();

        assertEquals(true, countA==10 && countB==0);

        threadB.start();

        try {
            threadB.join();
        } catch (Exception e){
            e.printStackTrace();
        }

        countA = threadA.getData().size();
        countB = threadB.getData().size();
        assertEquals(true, countA==10 && countB==10);
    }
}