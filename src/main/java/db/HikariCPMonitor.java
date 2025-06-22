package db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Utility class to monitor and log the status of a HikariCP connection pool.
 */
public class HikariCPMonitor {

    private final HikariDataSource dataSource;

    /**
     * Initializes the monitor with a Hikari data source.
     *
     * @param dataSource the HikariDataSource to monitor
     */
    public HikariCPMonitor(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Logs key statistics about the HikariCP pool such as active, idle, total connections,
     * and threads waiting for a connection.
     */
    public void logPoolStatus() {
        System.out.println("Active: " + dataSource.getHikariPoolMXBean().getActiveConnections());
        System.out.println("Idle: " + dataSource.getHikariPoolMXBean().getIdleConnections());
        System.out.println("Total: " + dataSource.getHikariPoolMXBean().getTotalConnections());
        System.out.println("Threads Awaiting: " + dataSource.getHikariPoolMXBean().getThreadsAwaitingConnection());
    }

    /**
     * Main method to demonstrate HikariCP pool monitoring.
     * Creates an in-memory H2 database and logs its connection pool status.
     */
    public static void main(String[] args) {
        // Basic HikariCP configuration with H2 in-memory database
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:h2:mem:testdb");  // In-memory DB
        config.setUsername("sa");                 // Default H2 user
        config.setPassword("");                   // No password

        // Create data source and monitor
        HikariDataSource ds = new HikariDataSource(config);
        HikariCPMonitor monitor = new HikariCPMonitor(ds);

        // Log connection pool status
        monitor.logPoolStatus();
    }
}
