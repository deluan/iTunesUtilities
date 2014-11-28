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
        addPropertyToPropertyMap("Major Version", "majorVersion", Integer.class);
        addPropertyToPropertyMap("Minor Version", "majorVersion", Integer.class);
        addPropertyToPropertyMap("Date", "date", Date.class);
        addPropertyToPropertyMap("Application Version", "applicationVersion", String.class);
        addPropertyToPropertyMap("Features", "features", Integer.class);
        addPropertyToPropertyMap("Show Content Ratings", "showContentRatings", Boolean.class);
        addPropertyToPropertyMap("Music Folder", "musicFolder", String.class);
        addPropertyToPropertyMap("Library Persistent ID", "libraryPersistentID", String.class);
    }
}
