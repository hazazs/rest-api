package hu.hazazs.rest.api.log;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * Filters out the unnecessary log entry from Hibernate, since the default HikariCP is used:<br/><br/>
 * HHH10001005: Database info:
 * <ul>
 *   <li>Database JDBC URL [Connecting through datasource 'HikariDataSource (HikariPool-1)']</li>
 *   <li>Database driver: undefined/unknown</li>
 *   <li>Database version: 13.20</li>
 *   <li>Autocommit mode: undefined/unknown</li>
 *   <li>Isolation level: undefined/unknown</li>
 *   <li>Minimum pool size: undefined/unknown</li>
 *   <li>Maximum pool size: undefined/unknown</li>
 * </ul>
 */
public class LogFilter extends Filter<ILoggingEvent> {

    private static final String HIBERNATE_MESSAGE = "HHH10001005: Database info:";
    private static final String HIBERNATE_LOGGER_NAME = "org.hibernate.orm.connections.pooling";

    @Override
    public FilterReply decide(ILoggingEvent event) {
        return event.getLevel().equals(Level.INFO)
            && event.getMessage().contains(HIBERNATE_MESSAGE)
            && event.getLoggerName().equals(HIBERNATE_LOGGER_NAME) ?
                FilterReply.DENY :
                FilterReply.ACCEPT;
    }

}