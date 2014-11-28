package com.worldsworstsoftware.itunes.parser;

import com.worldsworstsoftware.itunes.ItunesLibrary;

import java.util.Date;

class LibraryPropertyTagHandler extends BasePropertyTagHandler {

    public LibraryPropertyTagHandler(ItunesLibrary library) {
        super(library);
    }

    @Override
    protected Object getTarget() {
        return library;
    }

    protected void initializePropertyMap() {
        addPropertyToPropertyMap(LibraryProperty.MAJOR_VERSION, "majorVersion", Integer.class);
        addPropertyToPropertyMap(LibraryProperty.MINOR_VERSION, "majorVersion", Integer.class);
        addPropertyToPropertyMap(LibraryProperty.DATE, "date", Date.class);
        addPropertyToPropertyMap(LibraryProperty.APPLICATION_VERSION, "applicationVersion", String.class);
        addPropertyToPropertyMap(LibraryProperty.FEATURES, "features", Integer.class);
        addPropertyToPropertyMap(LibraryProperty.SHOW_CONTENT_RATINGS, "showContentRatings", Boolean.class);
        addPropertyToPropertyMap(LibraryProperty.MUSIC_FOLDER, "musicFolder", String.class);
        addPropertyToPropertyMap(LibraryProperty.LIBRARY_PERSISTENT_ID, "libraryPersistentID", String.class);
    }
}
