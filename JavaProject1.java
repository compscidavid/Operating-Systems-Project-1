
public class JavaProject1 {
	final static int BUFFER_SIZE = 10;
	static int[] buffer = new int[BUFFER_SIZE];
	static int count = 0;
	static int addIndex = 0;
	static int remIndex = 0;
	
	public static void main(String args[]) {

		// initialize the threads
		Thread p1 = new Thread(new Producer(buffer, BUFFER_SIZE, count, addIndex));
		Thread c1 = new Thread(new Consumer(buffer, BUFFER_SIZE, count, remIndex));

		// start the threads
		p1.start();
		c1.start();

		try {
			p1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			c1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

//		int randInt1 = (int)(10*Math.random());
	}

}
