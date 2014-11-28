package com.worldsworstsoftware.itunes.parser;

import com.worldsworstsoftware.itunes.ItunesLibrary;
import com.worldsworstsoftware.xmltagparser.Tag;
import net.vidageek.mirror.dsl.Mirror;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

abstract class BasePropertyTagHandler implements PropertyTagHandler {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected static final String NO_PROPERTY = "NO PROPERTY";


    protected ItunesLibrary library = null;
    protected String currentProperty = NO_PROPERTY;

    protected Map<String, String> propertyNameMap = new HashMap<String, String>();
    protected Map<String, Class> propertyTypeMap = new HashMap<String, Class>();
    protected Map<String, String> propertySetterMap = new HashMap<String, String>();

    protected DataTypeParser typeParser = new DataTypeParser();

    public BasePropertyTagHandler(ItunesLibrary library) {
        this.library = library;

        initializePropertyMap();
    }

    protected abstract void initializePropertyMap();

    protected abstract Object getTarget();

    protected void addPropertyToPropertyMap(String xmlName, String name, Class type) {
        propertyNameMap.put(xmlName, name);
        propertyTypeMap.put(xmlName, type);
    }

    protected void addPropertyToPropertyMap(String xmlName, String name, Class type, String setterName) {
        propertyNameMap.put(xmlName, name);
        propertyTypeMap.put(xmlName, type);
        propertySetterMap.put(name, setterName);
    }

    public void handlePropertyChange(String propertyName) {
        if (propertyNameMap.containsKey(propertyName)) {
            currentProperty = propertyName;
        } else {
            currentProperty = NO_PROPERTY;
            if (!stringIsNumeric(propertyName)) {
                logger.debug("Unsupported Itunes Library Property: " + propertyName);
            }
        }
    }

    private boolean stringIsNumeric(String value) {
        try {
            Long.parseLong(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void handlePropertyValue(Tag propertyValue) {
        try {
            if (currentProperty.equals(NO_PROPERTY)) {
                return;
            }

            String name = propertyNameMap.get(currentProperty);
            if (name != null) {
                Object value = parsePropertyValue(currentProperty, propertyValue);
                if (value != null) {
                    setPropertyValue(name, value);
                }
            } else {
                logger.warn("Supported Itunes Library Property Was Not Handled Correctly: " + currentProperty);
            }
        } catch (Exception e) {
            logger.error("Error occurred during library property parsing: " + e.getMessage(), e);
        } finally {
            currentProperty = NO_PROPERTY;
        }
    }

    protected Object parsePropertyValue(String propertyName, Tag propertyValue) throws Exception {
        Class type = propertyTypeMap.get(propertyName);
        if (type != null) {
            return typeParser.parse(currentProperty, propertyValue, type);
        }
        return null;
    }

    private void setPropertyValue(String name, Object value) {
        Object target = getTarget();
        Mirror mirror = new Mirror();

        if (propertySetterMap.containsKey(name)) {
            mirror.on(target).invoke().method(propertySetterMap.get(name)).withArgs(value);
        } else {
            mirror.on(target).invoke().setterFor(name).withValue(value);
        }
    }

}
