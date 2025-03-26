import java.util.*;

/*
 * Defining an abstract Employee class with concrete Developer and Admin subclasses.
 * Each log entry is represented by a Log class with a timestamp and type (WARNING, ERROR, FATAL)
 * Logs are stored in lists, where outdated logs are removed based on a time threshold.
 * The Developer tracks warnings and triggers an alert when 5 warnings occur within 10 seconds
 * The Admin monitors errors and fatals, raising alerts if 4 errors happen within 5 seconds or 3 fatals within 4 seconds.
 */

public class Main {

    public enum LogType {
        WARNING, ERROR, FATAL
    }

    public static class Log {
        private final int logTime;
        private final LogType logType;

        public Log(int logTime, LogType logType) {
            this.logTime = logTime;
            this.logType = logType;
        }

        public int getLogTime() {
            return logTime;
        }

        public LogType getLogType() {
            return logType;
        }
    }

    public abstract static class Employee {
        protected final String empId;
        protected final String type;

        public Employee(String empId, String type) {
            this.empId = empId;
            this.type = type;
        }

        public abstract void addLogs(Log log);
    }

    public static class Developer extends Employee {
        private final List<Log> logList = new ArrayList<>();
        private static final int WARNING_THRESHOLD = 5;
        private static final int WARNING_TIME_LIMIT = 10;

        public Developer(String empId) {
            super(empId, "Developer");
        }

        @Override
        public void addLogs(Log log) {
            logList.add(log);

            if (logList.size() == WARNING_THRESHOLD) {
                if (logList.get(WARNING_THRESHOLD - 1).getLogTime()
                        - logList.get(0).getLogTime() <= WARNING_TIME_LIMIT) {
                    notifyEmp();
                }
                logList.remove(0);
            }
        }

        private void notifyEmp() {
            System.out.println("Developer Alert: 5 warnings in 10 sec!");
        }
    }

    public static class Admin extends Employee {
        private final List<Log> errorLogs = new ArrayList<>();
        private final List<Log> fatalLogs = new ArrayList<>();
        private static final int ERROR_THRESHOLD = 4;
        private static final int ERROR_TIME_LIMIT = 5;

        private static final int FATAL_THRESHOLD = 3;
        private static final int FATAL_TIME_LIMIT = 4;

        public Admin(String empId) {
            super(empId, "Admin");
        }

        @Override
        public void addLogs(Log log) {
            if (log.getLogType() == LogType.ERROR) {
                processLogs(errorLogs, log, ERROR_THRESHOLD, ERROR_TIME_LIMIT, "Error");
            }
            if (log.getLogType() == LogType.FATAL) {
                processLogs(fatalLogs, log, FATAL_THRESHOLD, FATAL_TIME_LIMIT, "Fatal");
            }
        }

        private void processLogs(List<Log> list, Log log, int threshold, int timeLimit, String logType) {
            list.add(log);

            if (list.size() == threshold) {
                if (list.get(threshold - 1).getLogTime() - list.get(0).getLogTime() <= timeLimit) {
                    notifyEmp(logType);
                }
                list.remove(0);
            }
        }

        private void notifyEmp(String logType) {
            System.out.println("Admin Alert: Too many " + logType + " logs in a short time!");
        }
    }

    public static class AlertSystem {
        private final List<Log> logList = new ArrayList<>();
        private final Developer devEmployee;
        private final Admin adEmployee;

        public AlertSystem(Developer devEmployee, Admin adEmployee) {
            this.devEmployee = devEmployee;
            this.adEmployee = adEmployee;
        }

        public void addLog(Log log) {
            logList.add(log);
            processLog(log);
        }

        private void processLog(Log log) {
            if (log.getLogType() == LogType.WARNING) {
                devEmployee.addLogs(log);
            } else {
                adEmployee.addLogs(log);
            }
        }
    }

    public static void main(String[] args) {
        Developer dev = new Developer("D001");
        Admin admin = new Admin("A001");
        AlertSystem alertSystem = new AlertSystem(dev, admin);

        alertSystem.addLog(new Log(1, LogType.WARNING));
        alertSystem.addLog(new Log(2, LogType.WARNING));
        alertSystem.addLog(new Log(3, LogType.WARNING));
        alertSystem.addLog(new Log(4, LogType.WARNING));
        alertSystem.addLog(new Log(5, LogType.WARNING));

        alertSystem.addLog(new Log(1, LogType.ERROR));
        alertSystem.addLog(new Log(2, LogType.ERROR));
        alertSystem.addLog(new Log(3, LogType.ERROR));
        alertSystem.addLog(new Log(4, LogType.ERROR));

        alertSystem.addLog(new Log(1, LogType.FATAL));
        alertSystem.addLog(new Log(2, LogType.FATAL));
        alertSystem.addLog(new Log(3, LogType.FATAL));
    }
}

