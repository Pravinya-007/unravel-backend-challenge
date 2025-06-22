package concurrency;

import java.util.concurrent.PriorityBlockingQueue;

public class PriorityJobQueue {
    private final PriorityBlockingQueue<Job> queue = new PriorityBlockingQueue<>();

    public void submit(Job job) {
        queue.put(job);
    }

    public Job take() throws InterruptedException {
        return queue.take();
    }

    public static class Job implements Comparable<Job> {
        private final int priority;
        private final String task;

        public Job(int priority, String task) {
            this.priority = priority;
            this.task = task;
        }

        @Override
        public int compareTo(Job o) {
            return Integer.compare(o.priority, this.priority);
        }

        public String getTask() {
            return task;
        }
    }
}