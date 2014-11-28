package com.worldsworstsoftware.itunes.parser;

import com.worldsworstsoftware.itunes.ItunesLibrary;
import com.worldsworstsoftware.xmltagparser.Tag;
import net.vidageek.mirror.dsl.Mirror;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

class LibraryPropertyTagHandler implements PropertyTagHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    protected static final String NO_PROPERTY = "NO PROPERTY";


    protected ItunesLibrary library = null;
    protected String currentProperty = NO_PROPERTY;

    protected Map<String, String> propertyMap = new HashMap<String, String>();
    protected Map<String, Class> propertyTypeMap = new HashMap<String, Class>();

    protected DataTypeParser typeParser = new DataTypeParser();

    public LibraryPropertyTagHandler(ItunesLibrary library) {
        this.library = library;

        initializePropertyMap();
    }

    private void initializePropertyMap() {
        addPropertyToPropertyMap(LibraryProperty.MAJOR_VERSION, "majorVersion", Integer.class);
        addPropertyToPropertyMap(LibraryProperty.MINOR_VERSION, "majorVersion", Integer.class);
        addPropertyToPropertyMap(LibraryProperty.APPLICATION_VERSION, "applicationVersion", String.class);
        addPropertyToPropertyMap(LibraryProperty.FEATURES, "features", Integer.class);
        addPropertyToPropertyMap(LibraryProperty.SHOW_CONTENT_RATINGS, "showContentRatings", Boolean.class);
        addPropertyToPropertyMap(LibraryProperty.MUSIC_FOLDER, "musicFolder", String.class);
        addPropertyToPropertyMap(LibraryProperty.LIBRARY_PERSISTENT_ID, "libraryPersistentID", String.class);
    }

    private void addPropertyToPropertyMap(String value, String name, Class type) {
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
                Object value = typeParser.parse(currentProperty, propertyValue, type);
                new Mirror().on(library).invoke().setterFor(name).withValue(value);
            } else {
                logger.warn("Supported Itunes Library Property Was Not Handled Correctly: " + currentProperty);
            }
        } catch (Exception e) {
            logger.error("Error occured during library property parsing: " + e.getMessage(), e, true);
        } finally {
            currentProperty = NO_PROPERTY;
        }
    }

}
