class Producer implements Runnable {
	static final int BUFFER_SIZE = 10;
	public int count;
	public int addIndex;
	public int[] buffer;

	public Producer(int[] buffer, final int BUFFER_SIZE, int count, int addIndex) {
		this.buffer = buffer;
		this.count = count;
		this.addIndex = addIndex;
	}

	synchronized public void run() {
		while (true) {
			int value;
			// when buffer full, cannot produce
			while (count == BUFFER_SIZE) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			// add int to array
			++count;
			value = ((int) (10 * Math.random()));
			buffer[addIndex] = value;
			System.out.println("Value added: " + value);
			addIndex = (addIndex + 1) % BUFFER_SIZE;

			// wake up consumer
			notify();

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
