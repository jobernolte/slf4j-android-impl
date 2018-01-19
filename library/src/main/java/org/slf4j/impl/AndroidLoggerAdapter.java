package org.slf4j.impl;

import android.util.Log;

import org.slf4j.Logger;
import org.slf4j.event.Level;
import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.helpers.MessageFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * A wrapper over {@link Logger com.noveogroup.android.log.Logger}
 * in conforming to the {@link org.slf4j.Logger org.slf4j.Logger} interface.
 *
 * @author jobernolte
 */
public class AndroidLoggerAdapter extends MarkerIgnoringBase {

    private final String tag;

    /**
     * Creates new instance of {@link AndroidLoggerAdapter}.
     *
     * @param tag
     */
    AndroidLoggerAdapter(String tag) {
        this.tag = tag;
    }

    private boolean isEnabled(Level level) {
        switch (level) {
            case TRACE: {
                return Log.isLoggable(tag, Log.VERBOSE);
            }
            case DEBUG: {
                return Log.isLoggable(tag, Log.DEBUG);
            }
            case INFO: {
                return Log.isLoggable(tag, Log.INFO);
            }
            case WARN: {
                return Log.isLoggable(tag, Log.WARN);
            }
            default:
            case ERROR: {
                return Log.isLoggable(tag, Log.ERROR);
            }
        }
    }

    private void logImpl(Level level, String msg, Throwable t) {
        // logger.print(level, msg, null);
        if (t != null) {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            t.printStackTrace(printWriter);
            msg = msg + "\n" + stringWriter.toString();
        }
        switch (level) {
            case TRACE: {
                Log.v(tag, msg);
                break;
            }
            case DEBUG: {
                Log.d(tag, msg);
                break;
            }
            case INFO: {
                Log.i(tag, msg);
                break;
            }
            case WARN: {
                Log.w(tag, msg);
                break;
            }
            default:
            case ERROR: {
                Log.e(tag, msg);
                break;
            }
        }
    }

    private void log(Level level, String msg) {
        logImpl(level, msg, null);
    }

    private void log(Level level, String format, Object arg) {
        Throwable t = arg instanceof Throwable ? (Throwable) arg : null;
        logImpl(level, MessageFormatter.format(format, arg).getMessage(), t);
    }

    private void log(Level level, String format, Object arg1, Object arg2) {
        Throwable t = arg2 instanceof Throwable ? (Throwable) arg2 : null;
        logImpl(level, MessageFormatter.format(format, arg1, arg2).getMessage(), t);
    }

    private void log(Level level, String format, Object... arguments) {
        Throwable t = null;
        if (arguments != null && arguments.length > 0 && arguments[arguments.length
                - 1] instanceof Throwable) {
            t = (Throwable) arguments[arguments.length - 1];
        }
        logImpl(level, MessageFormatter.arrayFormat(format, arguments).getMessage(), t);
    }

    @Override
    public boolean isTraceEnabled() {
        return isEnabled(Level.TRACE);
    }

    @Override
    public void trace(String msg) {
        log(Level.TRACE, msg);
    }

    @Override
    public void trace(String format, Object arg) {
        log(Level.TRACE, format, arg);
    }

    @Override
    public void trace(String format, Object arg1, Object arg2) {
        log(Level.TRACE, format, arg1, arg2);
    }

    @Override
    public void trace(String format, Object... arguments) {
        log(Level.TRACE, format, arguments);
    }

    @Override
    public void trace(String msg, Throwable t) {
        logImpl(Level.TRACE, msg, t);
    }

    @Override
    public boolean isDebugEnabled() {
        return isEnabled(Level.DEBUG);
    }

    @Override
    public void debug(String msg) {
        log(Level.DEBUG, msg);
    }

    @Override
    public void debug(String format, Object arg) {
        log(Level.DEBUG, format, arg);
    }

    @Override
    public void debug(String format, Object arg1, Object arg2) {
        log(Level.DEBUG, format, arg1, arg2);
    }

    @Override
    public void debug(String format, Object... arguments) {
        log(Level.DEBUG, format, arguments);
    }

    @Override
    public void debug(String msg, Throwable t) {
        logImpl(Level.DEBUG, msg, t);
    }

    @Override
    public boolean isInfoEnabled() {
        return isEnabled(Level.INFO);
    }

    @Override
    public void info(String msg) {
        log(Level.INFO, msg);
    }

    @Override
    public void info(String format, Object arg) {
        log(Level.INFO, format, arg);
    }

    @Override
    public void info(String format, Object arg1, Object arg2) {
        log(Level.INFO, format, arg1, arg2);
    }

    @Override
    public void info(String format, Object... arguments) {
        log(Level.INFO, format, arguments);
    }

    @Override
    public void info(String msg, Throwable t) {
        logImpl(Level.INFO, msg, t);
    }

    @Override
    public boolean isWarnEnabled() {
        return isEnabled(Level.WARN);
    }

    @Override
    public void warn(String msg) {
        log(Level.WARN, msg);
    }

    @Override
    public void warn(String format, Object arg) {
        log(Level.WARN, format, arg);
    }

    @Override
    public void warn(String format, Object arg1, Object arg2) {
        log(Level.WARN, format, arg1, arg2);
    }

    @Override
    public void warn(String format, Object... arguments) {
        log(Level.WARN, format, arguments);
    }

    @Override
    public void warn(String msg, Throwable t) {
        logImpl(Level.WARN, msg, t);
    }

    @Override
    public boolean isErrorEnabled() {
        return isEnabled(Level.ERROR);
    }

    @Override
    public void error(String msg) {
        log(Level.ERROR, msg);
    }

    @Override
    public void error(String format, Object arg) {
        log(Level.ERROR, format, arg);
    }

    @Override
    public void error(String format, Object arg1, Object arg2) {
        log(Level.ERROR, format, arg1, arg2);
    }

    @Override
    public void error(String format, Object... arguments) {
        log(Level.ERROR, format, arguments);
    }

    @Override
    public void error(String msg, Throwable t) {
        logImpl(Level.ERROR, msg, t);
    }

}
