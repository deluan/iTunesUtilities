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

    protected Map<String, PropertyInfo> propertyMap = new HashMap<String, PropertyInfo>();

    protected DataTypeParser typeParser = new DataTypeParser();

    public BasePropertyTagHandler(ItunesLibrary library) {
        this.library = library;

        initializePropertyMap();
    }

    protected abstract void initializePropertyMap();

    protected abstract Object getTarget();

    protected void addPropertyToPropertyMap(String xmlName, String name, Class type) {
        addPropertyToPropertyMap(xmlName, name, type, null);
    }

    protected void addPropertyToPropertyMap(String xmlName, String name, Class type, String setterName) {
        PropertyInfo info = new PropertyInfo();
        info.name = name;
        info.type = type;
        info.setter = setterName;

        propertyMap.put(xmlName, info);
    }

    public void handlePropertyChange(String propertyName) {
        if (propertyMap.containsKey(propertyName)) {
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

            PropertyInfo info = propertyMap.get(currentProperty);
            if (info != null) {
                Object value = parsePropertyValue(info, propertyValue);
                setPropertyValue(info, value);
            }
        } catch (Exception e) {
            logger.error("Error occurred during library property parsing: " + e.getMessage(), e);
        } finally {
            currentProperty = NO_PROPERTY;
        }
    }

    protected Object parsePropertyValue(PropertyInfo info, Tag propertyValue) throws Exception {
        return typeParser.parse(currentProperty, propertyValue, info.type);
    }

    protected Object parsePropertyValue(String propertyName, Tag propertyValue) throws Exception {
        PropertyInfo info = propertyMap.get(propertyName);
        if (info != null) {
            return typeParser.parse(currentProperty, propertyValue, info.type);
        }
        return null;
    }

    private void setPropertyValue(PropertyInfo info, Object value) {
        Object target = getTarget();
        Mirror mirror = new Mirror();

        if (info.setter != null) {
            mirror.on(target).invoke().method(info.setter).withArgs(value);
        } else {
            mirror.on(target).invoke().setterFor(info.name).withValue(value);
        }
    }

    class PropertyInfo {
        String name;
        Class type;
        String setter;
    }

}
