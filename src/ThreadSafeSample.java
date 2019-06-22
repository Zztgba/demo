import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSafeSample {
    public AtomicInteger sharedState;
    public void  nonSafeAction() {
        while (sharedState.compareAndSet(100000,1) ) {
            int former = sharedState.addAndGet(1);
            int latter = sharedState.intValue();
            if (former != latter - 1) {
                System.out.printf("Observed data race, former is " +
                        former + ", " + "latter is " + latter);
            }
            System.out.println("right");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadSafeSample sample = new ThreadSafeSample();
        Thread threadA = new Thread(){
            @Override
            public void run(){
                sample.nonSafeAction();
            }
        };
        Thread threadB = new Thread(){
            @Override
            public void run(){
                sample.nonSafeAction();
            }
        };
        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
    }
}
