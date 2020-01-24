class Consumer implements Runnable {
	public int[] buffer;

	public Consumer(int[] buffer) {
		this.buffer = buffer;
	}

	public void run() {
		while (true) {
			int value;
			// synchronize to avoid accessing data at same time as producer
			synchronized (this) {

				// when buffer full, cannot produce
				while (JavaProject1.count == 0) {
					try {
						buffer.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				// add int to array
				--JavaProject1.count;
				value = buffer[JavaProject1.remIndex];
				System.out.println("Value removed: " + value);
				JavaProject1.remIndex = (JavaProject1.remIndex + 1) % JavaProject1.BUFFER_SIZE;

				// wake up consumer
				buffer.notify();
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
