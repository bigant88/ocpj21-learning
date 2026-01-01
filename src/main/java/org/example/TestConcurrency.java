package org.example;

public class TestConcurrency {
    static class Calculation implements Runnable
    {
        int result;
        public void run()
        {
            synchronized(this){
                try{
                    Thread.sleep(5000); //LINE 10
                    result = 101;
                }catch(Exception e){ e.printStackTrace(); }
            }
        }
    }
    public static void main(String args[]) throws Exception
    {
        Calculation c = new Calculation();
        Thread.Builder tb = Thread.ofPlatform();
        Thread t = tb.unstarted(c);
        t.start();
        Thread.sleep(100);
        synchronized(c){  //LINE 21
            System.out.println("Result is "+c.result);
        }
    }

}
//Assuming that the calculation thread has just executed the line marked //LINE 10 and the main thread has just executed the line marked //LINE 21, what are the states of the two threads?
//main thread: BLOCKED
//calculation thread: TIMED_WAITING
