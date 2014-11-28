package com.worldsworstsoftware.itunes.parser;

import com.worldsworstsoftware.itunes.ItunesLibrary;
import com.worldsworstsoftware.xmltagparser.Tag;
import net.vidageek.mirror.dsl.Mirror;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

abstract class BasePropertyTagHandler implements PropertyTagHandler {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected static final String NO_PROPERTY = "NO PROPERTY";


    protected ItunesLibrary library = null;
    protected String currentProperty = NO_PROPERTY;

    protected Map<String, String> propertyMap = new HashMap<String, String>();
    protected Map<String, Class> propertyTypeMap = new HashMap<String, Class>();

    protected DataTypeParser typeParser = new DataTypeParser();

    public BasePropertyTagHandler(ItunesLibrary library) {
        this.library = library;

        initializePropertyMap();
    }

    protected abstract void initializePropertyMap();

    protected abstract Object getTarget();

    protected void addPropertyToPropertyMap(String value, String name, Class type) {
        propertyMap.put(value, name);
        propertyTypeMap.put(value, type);
    }

    public void handlePropertyChange(String propertyName) {
        if (propertyMap.containsKey(propertyName)) {
            currentProperty = propertyName;
        } else {
            currentProperty = NO_PROPERTY;
            logger.warn("Unsupported Itunes Library Property: " + propertyName);
        }
    }


    public void handlePropertyValue(Tag propertyValue) {
        try {
            if (currentProperty.equals(NO_PROPERTY)) {
                return;
            }

            String name = propertyMap.get(currentProperty);
            if (name != null) {
                Class type = propertyTypeMap.get(currentProperty);
                if (type != null) {
                    Object value = typeParser.parse(currentProperty, propertyValue, type);
                    setPropertyValue(name, value);
                }
            } else {
                logger.warn("Supported Itunes Library Property Was Not Handled Correctly: " + currentProperty);
            }
        } catch (Exception e) {
            logger.error("Error occured during library property parsing: " + e.getMessage(), e);
        } finally {
            currentProperty = NO_PROPERTY;
        }
    }

    private void setPropertyValue(String name, Object value) {
        Mirror mirror = new Mirror();
        Field fieldList = mirror.on(getTarget().getClass()).reflect().field(name + "s");
        if (fieldList != null) {
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            mirror.on(getTarget()).invoke().method("add" + name).withArgs(value);
        } else {
            mirror.on(getTarget()).invoke().setterFor(name).withValue(value);
        }
    }

}