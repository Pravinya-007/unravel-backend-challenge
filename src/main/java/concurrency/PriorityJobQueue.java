package concurrency;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * A thread-safe job queue where jobs are processed based on priority.
 * Higher priority values are dequeued before lower ones.
 */
public class PriorityJobQueue {

    // Thread-safe priority queue to store jobs
    private final PriorityBlockingQueue<Job> queue = new PriorityBlockingQueue<>();

    /**
     * Submits a new job to the queue.
     * @param job Job to be added
     */
    public void submit(Job job) {
        queue.put(job);
    }

    /**
     * Retrieves and removes the highest-priority job, blocking if the queue is empty.
     * @return The job with highest priority
     * @throws InterruptedException if interrupted while waiting
     */
    public Job take() throws InterruptedException {
        return queue.take();
    }

    /**
     * Represents a Job with priority and a task description.
     * Implements Comparable to enable ordering in PriorityBlockingQueue.
     */
    public static class Job implements Comparable<Job> {
        private final int priority; // Higher value means higher priority
        private final String task;

        public Job(int priority, String task) {
            this.priority = priority;
            this.task = task;
        }

        /**
         * Compare jobs by priority in descending order.
         * Higher priority jobs come before lower ones.
         */
        @Override
        public int compareTo(Job o) {
            return Integer.compare(o.priority, this.priority); // descending order
        }

        public String getTask() {
            return task;
        }
    }
}
