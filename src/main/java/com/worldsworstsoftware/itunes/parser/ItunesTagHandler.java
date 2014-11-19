package com.worldsworstsoftware.itunes.parser;

import com.worldsworstsoftware.itunes.ItunesLibrary;
import com.worldsworstsoftware.util.PerformanceTimer;
import com.worldsworstsoftware.xmltagparser.Tag;
import com.worldsworstsoftware.xmltagparser.TagHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ItunesTagHandler implements TagHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    protected static final int MODE_PARSING_LIBRARY = 0;
    protected static final int MODE_PARSING_TRACKS = 1;
    protected static final int MODE_PARSING_PLAYLISTS = 2;

    protected int currentMode = MODE_PARSING_LIBRARY;

    protected PropertyTagHandler libraryPropertyTagHandler = null;
    protected PropertyTagHandler trackPropertyTagHandler = null;
    protected PropertyTagHandler playlistPropertyTagHandler = null;

    protected ItunesLibrary library = null;

    protected PerformanceTimer performanceTimer = null;

    public ItunesTagHandler(ItunesLibrary library) {
        this.library = library;
        this.libraryPropertyTagHandler = new LibraryPropertyTagHandler(library);
        this.playlistPropertyTagHandler = new PlaylistPropertyTagHandler(library);
        this.trackPropertyTagHandler = new TrackPropertyTagHandler
                (library);
        performanceTimer = new PerformanceTimer();
    }

    public void handleTag(Tag tag) {
        if (handleModeSwitch(tag) == true) {
            //this tag caused a parsing mode switch, the tag has been handled.
            return;
        }

		/*
		 * All properties in the library xml file are layed out with a property
		 * name surrounded by "key" tags, and then the value follows.. for example:
		 * 
		 * 	<key>Artist</key><string>A Perfect Circle</string>
		 * 
		 * So if this is the '<key>something</key>' bit, we call the handler's 
		 * handlePropertyChange() method, otherwise we call the handlePropertyValue() method. 
		 * 
		 */

        PropertyTagHandler propertyTagHandler = null;

        switch (currentMode) {
            case MODE_PARSING_LIBRARY:
                propertyTagHandler = libraryPropertyTagHandler;
                break;
            case MODE_PARSING_TRACKS:
                propertyTagHandler = trackPropertyTagHandler;
                break;
            case MODE_PARSING_PLAYLISTS:
                propertyTagHandler = playlistPropertyTagHandler;
                break;
            default:
                throw new RuntimeException("Unknown Mode:" + currentMode);
        }

        if (tag.getName().equals(TagType.KEY)) {
            propertyTagHandler.handlePropertyChange(tag.getInnerText());
        } else {
            propertyTagHandler.handlePropertyValue(tag);
        }
    }

    /**
     * There are three basic itunes library parsing modes:
     * 1) Parsing the library properties such as "Major Version"
     * 2) Parsing the tracks
     * 3) Parsing the playlists
     * <p/>
     * The switch from mode 1 to 2 happens with the tag/value string "<key>Tracks</key>"
     * The switch from mode 2 to 3 happens with the tag/value string "<key>Playlists</key>"
     *
     * @param tag
     * @return
     */
    protected boolean handleModeSwitch(Tag tag) {
        //first there's no point in looking for a mode switch if the tag name isn't "key" or "plist"
        if (!tag.getName().equals(TagType.KEY) && !tag.getName().equals(TagType.PLIST)) {
            return false;
        }

        switch (currentMode) {
            case MODE_PARSING_LIBRARY:
                if (tag.getInnerText().equals(LibraryProperty.TRACKS)) {
                    logger.debug("Parse time for Library Properties: " + performanceTimer.getTimeElapsedSinceLastReset());
                    performanceTimer.reset();
                    logger.debug("Now parsing tracks");

                    currentMode = MODE_PARSING_TRACKS;
                    return true;
                }
                break;
            case MODE_PARSING_TRACKS:
                if (tag.getInnerText().equals(LibraryProperty.PLAYLISTS)) {
                    logger.debug("Parse time for tracks: " + performanceTimer.getTimeElapsedSinceLastReset());
                    performanceTimer.reset();
                    logger.debug("Now parsing playlists");

                    currentMode = MODE_PARSING_PLAYLISTS;
                    return true;
                }
                break;
            case MODE_PARSING_PLAYLISTS:
                //there aren't any more mode switches, don't do anything..
                break;
            default:
                RuntimeException e = new RuntimeException("ItunesTagHandler Unknown Mode:" + currentMode);
                logger.error(e.getMessage(), e);
                throw e;
        }

        return false;
    }

}
