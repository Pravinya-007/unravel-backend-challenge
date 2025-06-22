package db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariCPMonitor {
    private final HikariDataSource dataSource;

    public HikariCPMonitor(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void logPoolStatus() {
        System.out.println("Active: " + dataSource.getHikariPoolMXBean().getActiveConnections());
        System.out.println("Idle: " + dataSource.getHikariPoolMXBean().getIdleConnections());
        System.out.println("Total: " + dataSource.getHikariPoolMXBean().getTotalConnections());
        System.out.println("Threads Awaiting: " + dataSource.getHikariPoolMXBean().getThreadsAwaitingConnection());
    }

    public static void main(String[] args) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:h2:mem:testdb");
        config.setUsername("sa");
        config.setPassword("");
        HikariDataSource ds = new HikariDataSource(config);

        HikariCPMonitor monitor = new HikariCPMonitor(ds);
        monitor.logPoolStatus();
    }
}