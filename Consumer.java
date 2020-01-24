class Consumer implements Runnable {
	static final int BUFFER_SIZE = 10;
	public int count;
	public int remIndex;
	public int[] buffer;

	public Consumer(int[] buffer, final int BUFFER_SIZE, int count, int remIndex) {
		this.buffer = buffer;
		this.count = count;
		this.remIndex = remIndex;
	}

	synchronized public void run() {
		int value;
		// when buffer full, cannot produce
		while (count == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// add int to array
		--count;
		value = buffer[remIndex];
		System.out.println("Value removed: " + value);
		remIndex = (remIndex + 1) % BUFFER_SIZE;

		// wake up consumer
		notify();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
