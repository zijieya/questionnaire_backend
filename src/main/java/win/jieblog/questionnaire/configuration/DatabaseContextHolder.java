package win.jieblog.questionnaire.configuration;

import win.jieblog.questionnaire.enums.DatabaseType;

public class DatabaseContextHolder {
    private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<DatabaseType>();

    public static DatabaseType getDatabaseType() {
        return contextHolder.get() == null ? DatabaseType.WRITE : contextHolder.get();
    }

    public static void setDatabaseType(DatabaseType dbType) {
        if (dbType == null) throw new NullPointerException("dbType must not be null");
        contextHolder.set(dbType);
    }

    public static void clearDbType() {
        contextHolder.remove();
    }
}
