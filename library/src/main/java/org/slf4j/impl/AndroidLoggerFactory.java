package org.slf4j.impl;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

/**
 * Implementation of {@link ILoggerFactory} returning
 * the appropriate named {@link AndroidLoggerAdapter} instance.
 */
public class AndroidLoggerFactory implements ILoggerFactory {

    @Override
    public Logger getLogger(String name) {
        return new AndroidLoggerAdapter(name);
    }

}
