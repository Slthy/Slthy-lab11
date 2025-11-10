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


        threadA.setData(new ArrayList<String>()); // always use new array
        
        threadA.start();
        threadB.start();

        int count = 0;  // and set count to zero

        try {
            threadA.join();
            threadB.join();
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // interrupted
        }
        count = threadA.getData().size();

        assertEquals(true, count==200);
    }

    /*
        Complete the test case below that checks to see if the shared 
        ArrayList has at least 10 entries after 500ms of system time
    */

    @Test
    public void test2() {
        Lab11_Thread threadA = new Lab11_Thread("A2", 500);
        Lab11_Thread threadB = new Lab11_Thread("B2", 500);
        
        threadA.setData(new ArrayList<String>());

        threadA.start();
        threadB.start();
        
        int totalCount = 0;

        try {
            Thread.sleep(500);
            totalCount = threadA.getData().size() + threadB.getData().size();
            
            threadA.join();
            threadB.join();
        } catch (Exception e){
            e.printStackTrace();
        }

        assertEquals(true, totalCount >= 10);

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
        threadA.setData(new ArrayList<String>());
        try {
            threadA.join();
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("count: " + threadA.getData().size());
        assertEquals(true, threadA.getData().size()==10);

        threadB.start();

        try {
            threadB.join();
        } catch (Exception e){
            e.printStackTrace();
        }
        // first A, then B
        System.out.println("count: " + threadA.getData().size()); //i could also have used threadB
        assertEquals(true, threadA.getData().size()==20);
    }

}

/*







 */