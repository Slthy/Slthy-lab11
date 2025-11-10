import java.util.*;

public class Lab11_Thread extends Thread {
    private static ArrayList<String> data = new ArrayList<String>();  // shared storage
    private String name;
    private int runs; 

    public Lab11_Thread(String name, int runs) {  // constructor
      this.name = name;
      this.runs = runs;
    }

    public void run() { // java default function to start a thread
      for(int i = 0; i < runs; i++){
        try{
          Thread.sleep(20); // sleep 20ms
        } catch (Exception e){
            e.printStackTrace();  // catch exceptions
        }
        this.addItem(name + " " + i); // add entry to storage
      }
    }

    public synchronized void addItem(String s){ // synchronized make sure that race conditions are avoided
      System.out.println(s);  
      data.add(s);
    }

    public ArrayList<String> getData(){ // getter
      return data;
    }

    public void setData(ArrayList<String> data){  // setter
      this.data = data;
    }
}