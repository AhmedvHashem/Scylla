import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Test class to test the lock example 
// 5 threads are created with DisplayJob 
// and 5 thread are created with ReadJob. 
// Both these jobs use single TestResource named "test". 
public class ThreadLockTest {
	public static void main(String[] args) {
		TestResource test = new TestResource();
		Thread[] thread = new Thread[10];

		for (int i = 0; i < 5; i++) {
			thread[i] = new Thread(new DisplayJob(test), "Thread " + i);
		}
		for (int i = 5; i < 10; i++) {
			thread[i] = new Thread(new ReadJob(test), "Thread " + i);
		}
		for (int i = 0; i < 10; i++) {
			thread[i].start();
		}
	}

}

// DisplayJob class implementing Runnable interface.
// This uses TestResource object passed in the constructor.
// run method invokes displayRecord method on TestResource.
class DisplayJob implements Runnable {

	private TestResource test;

	DisplayJob(TestResource tr) {
		test = tr;
	}

	@Override
	public void run() {
		System.out.println("display job");
		test.displayRecord(new Object());
	}
}

// ReadJob class implementing Runnable interface.
// which uses TestResource object passed in the constructor.
// run method invokes readRecord method on TestResource.
class ReadJob implements Runnable {

	private TestResource test;

	ReadJob(TestResource tr) {
		test = tr;
	}

	@Override
	public void run() {
		System.out.println("read job");
		test.readRecord(new Object());
	}
}
// Class which has two locks and two methods.

class TestResource {

	// displayQueueLock is created to make
	// displayQueueLock thread safe.
	// When T1 aquires lock on testresource(o1)
	// object displayRecord method
	// T2 has to wait for lock to be released
	// by T1 on testresource(o1) object
	// displayRecord method. But T3, can execute
	// readRecord method with out waiting for lock
	// to be released by t1 as
	// readRecord method uses readQueueLock not
	// displayQueueLock.
	private final Lock displayQueueLock = new ReentrantLock();
	private final Lock readQueueLock = new ReentrantLock();

	// displayRecord uses displayQueueLock to
	// achieve thread safety.
	void displayRecord(Object document) {
		final Lock displayLock = this.displayQueueLock;
		displayLock.lock();
		try {
			Long duration = (long) (Math.random() * 10000);
			System.out.println(Thread.currentThread().getName() + ": TestResource: display a Job" + " during "
					+ (duration / 1000) + " seconds ::" + " Time - " + new Date());
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.printf("%s: The document has been dispalyed\n", Thread.currentThread().getName());
			displayLock.unlock();
		}
	}

	// readRecord uses readQueueLock to achieve thread safety.
	void readRecord(Object document) {
		final Lock readQueueLock = this.readQueueLock;
		readQueueLock.lock();
		try {
			long duration = (long) (Math.random() * 10000);
			System.out.println(Thread.currentThread().getName() + ": TestResource: reading a Job during "
					+ (duration / 1000) + " seconds :: Time - " + new Date());
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.printf("%s: The document has been read\n", Thread.currentThread().getName());
			readQueueLock.unlock();
		}
	}
}
