class Producer implements Runnable {
	public int[] buffer;

	public Producer(int[] buffer) {
		this.buffer = buffer;
	}

	public void run() {
		while (true) {
			int value;
			// synchronize to avoid accessing data at same time as consumer
			synchronized (this) {

				// when buffer full, cannot produce
				while (JavaProject1.count == JavaProject1.BUFFER_SIZE) {
					try {
						buffer.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				// add int to array
				++JavaProject1.count;
				value = ((int) (10 * Math.random()));
				buffer[JavaProject1.addIndex] = value;
				System.out.println("Value added: " + value);
				JavaProject1.addIndex = (JavaProject1.addIndex + 1) % JavaProject1.BUFFER_SIZE;

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
