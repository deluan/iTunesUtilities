package com.worldsworstsoftware.itunes.parser;

import com.worldsworstsoftware.itunes.ItunesLibrary;
import com.worldsworstsoftware.itunes.ItunesTrack;
import com.worldsworstsoftware.xmltagparser.Tag;

import java.util.Date;

class TrackPropertyTagHandler extends BasePropertyTagHandler {
    public static final String TRACK_ID = "Track ID";

    protected int trackParseCount = 0;
    protected ItunesTrack currentTrack;

    public TrackPropertyTagHandler(ItunesLibrary library) {
        super(library);
    }

    @Override
    protected void initializePropertyMap() {
        addPropertyToPropertyMap(TRACK_ID, "trackID", Integer.class);
        addPropertyToPropertyMap("Name", "name", String.class);
        addPropertyToPropertyMap("Artist", "artist", String.class);
        addPropertyToPropertyMap("Album", "album", String.class);
        addPropertyToPropertyMap("Genre", "genre", String.class);
        addPropertyToPropertyMap("Kind", "kind", String.class);
        addPropertyToPropertyMap("Size", "size", Long.class);
        addPropertyToPropertyMap("Total Time", "totalTime", Integer.class);
        addPropertyToPropertyMap("Track Number", "trackNumber", Integer.class);
        addPropertyToPropertyMap("Track Count", "trackCount", Integer.class);
        addPropertyToPropertyMap("Year", "year", Integer.class);
        addPropertyToPropertyMap("Date Modified", "dateModified", Date.class);
        addPropertyToPropertyMap("Date Added", "dateAdded", Date.class);
        addPropertyToPropertyMap("Bit Rate", "bitRate", Integer.class);
        addPropertyToPropertyMap("Sample Rate", "sampleRate", Integer.class);
        addPropertyToPropertyMap("Comments", "comments", String.class);
        addPropertyToPropertyMap("Play Count", "playCount", Integer.class);
        addPropertyToPropertyMap("Play Date", "playDate", Long.class);
        addPropertyToPropertyMap("Play Date UTC", "playDateUTC", Date.class);
        addPropertyToPropertyMap("Persistent ID", "persistentID", String.class);
        addPropertyToPropertyMap("Track Type", "trackType", String.class);
        addPropertyToPropertyMap("Location", "location", String.class);
        addPropertyToPropertyMap("File Folder Count", "fileFolderCount", Integer.class);
        addPropertyToPropertyMap("Library Folder Count", "libraryFolderCount", Integer.class);
        addPropertyToPropertyMap("Disabled", "disabled", Boolean.class);
        addPropertyToPropertyMap("Skip Count", "skipCount", Integer.class);
        addPropertyToPropertyMap("Skip Date", "skipDate", Date.class);
        addPropertyToPropertyMap("Composer", "composer", String.class);
        addPropertyToPropertyMap("Album Artist", "albumArtist", String.class);
        addPropertyToPropertyMap("Artwork Count", "artworkCount", Integer.class);
        addPropertyToPropertyMap("Grouping", "grouping", String.class);
        addPropertyToPropertyMap("Disc Number", "discNumber", Integer.class);
        addPropertyToPropertyMap("Disc Count", "discCount", Integer.class);
        addPropertyToPropertyMap("BPM", "BPM", Integer.class);
        addPropertyToPropertyMap("Rating", "rating", Integer.class);
        addPropertyToPropertyMap("Album Rating", "albumRating", Integer.class);
        addPropertyToPropertyMap("Album Rating Computed", "albumRatingComputed", Boolean.class);
    }

    @Override
    protected Object getTarget() {
        return currentTrack;
    }

    public void handlePropertyValue(Tag propertyValue) {
        if (currentProperty.equals(TRACK_ID)) {
            try {
                currentTrack = new ItunesTrack();

                // the library's internal map of tracks is keyed off of the
                // track id, so let's set that first
                currentTrack.setTrackID((Integer) parsePropertyValue(currentProperty, propertyValue));

                // add a reference to the track to the library's list..
                library.addTrack(currentTrack);

                logTracksParsed();

                currentProperty = NO_PROPERTY;

            } catch (Exception e) {
                logger.error("Error occurred during library property parsing: " + e.getMessage(), e);
            }
        } else {
            super.handlePropertyValue(propertyValue);
        }
    }

    private void logTracksParsed() {
        trackParseCount++;
        if (trackParseCount % 200 == 0) {
            logger.debug("Now parsing tracks (" + trackParseCount + " tracks parsed).");
        }
    }
}
