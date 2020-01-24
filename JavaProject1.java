public class JavaProject1 {
	final static int BUFFER_SIZE = 5;
	public static int[] buffer = new int[BUFFER_SIZE];
	static public int count = 0;
	public static int addIndex = 0;
	public static int remIndex = 0;
	
	public static void main(String args[]) {

		// initialize the threads
		Thread p1 = new Thread(new Producer(buffer));
		Thread c1 = new Thread(new Consumer(buffer));

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
