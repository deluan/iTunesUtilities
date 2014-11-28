package com.worldsworstsoftware.itunes.parser;

import com.worldsworstsoftware.itunes.ItunesLibrary;
import com.worldsworstsoftware.itunes.ItunesTrack;
import com.worldsworstsoftware.xmltagparser.Tag;

import java.util.Date;
import java.util.Map;

class TrackPropertyTagHandler extends BasePropertyTagHandler {
    protected int trackParseCount = 0;
    protected ItunesTrack currentTrack;

    public TrackPropertyTagHandler(ItunesLibrary library) {
        super(library);
    }

    @Override
    protected void initializePropertyMap() {
        addPropertyToPropertyMap(TrackProperty.TRACK_ID, "trackID", Integer.class);
        addPropertyToPropertyMap(TrackProperty.NAME, "name", String.class);
        addPropertyToPropertyMap(TrackProperty.ARTIST, "artist", String.class);
        addPropertyToPropertyMap(TrackProperty.ALBUM, "album", String.class);
        addPropertyToPropertyMap(TrackProperty.GENRE, "genre", String.class);
        addPropertyToPropertyMap(TrackProperty.KIND, "kind", String.class);
        addPropertyToPropertyMap(TrackProperty.SIZE, "size", Long.class);
        addPropertyToPropertyMap(TrackProperty.TOTAL_TIME, "totalTime", Integer.class);
        addPropertyToPropertyMap(TrackProperty.TRACK_NUMBER, "trackNumber", Integer.class);
        addPropertyToPropertyMap(TrackProperty.TRACK_COUNT, "trackCount", Integer.class);
        addPropertyToPropertyMap(TrackProperty.YEAR, "year", Integer.class);
        addPropertyToPropertyMap(TrackProperty.DATE_MODIFIED, "dateModified", Date.class);
        addPropertyToPropertyMap(TrackProperty.DATE_ADDED, "dateAdded", Date.class);
        addPropertyToPropertyMap(TrackProperty.BIT_RATE, "bitRate", Integer.class);
        addPropertyToPropertyMap(TrackProperty.SAMPLE_RATE, "sampleRate", Integer.class);
        addPropertyToPropertyMap(TrackProperty.COMMENTS, "comments", String.class);
        addPropertyToPropertyMap(TrackProperty.PLAY_COUNT, "playCount", Integer.class);
        addPropertyToPropertyMap(TrackProperty.PLAY_DATE, "playDate", Long.class);
        addPropertyToPropertyMap(TrackProperty.PLAY_DATE_UTC, "playDateUTC", Date.class);
        addPropertyToPropertyMap(TrackProperty.PERSISTENT_ID, "persistentID", String.class);
        addPropertyToPropertyMap(TrackProperty.TRACK_TYPE, "trackType", String.class);
        addPropertyToPropertyMap(TrackProperty.LOCATION, "location", String.class);
        addPropertyToPropertyMap(TrackProperty.FILE_FOLDER_COUNT, "fileFolderCount", Integer.class);
        addPropertyToPropertyMap(TrackProperty.LIBRARY_FOLDER_COUNT, "libraryFolderCount", Integer.class);
        addPropertyToPropertyMap(TrackProperty.DISABLED, "disabled", Boolean.class);
        addPropertyToPropertyMap(TrackProperty.SKIP_COUNT, "skipCount", Integer.class);
        addPropertyToPropertyMap(TrackProperty.SKIP_DATE, "skipDate", Date.class);
        addPropertyToPropertyMap(TrackProperty.COMPOSER, "composer", String.class);
        addPropertyToPropertyMap(TrackProperty.ALBUM_ARTIST, "albumArtist", String.class);
        addPropertyToPropertyMap(TrackProperty.ARTWORK_COUNT, "artworkCount", Integer.class);
        addPropertyToPropertyMap(TrackProperty.GROUPING, "grouping", String.class);
        addPropertyToPropertyMap(TrackProperty.DISC_NUMBER, "discNumber", Integer.class);
        addPropertyToPropertyMap(TrackProperty.DISC_COUNT, "discCount", Integer.class);
        addPropertyToPropertyMap(TrackProperty.BPM, "BPM", Integer.class);
        addPropertyToPropertyMap(TrackProperty.RATING, "rating", Integer.class);
    }

    @Override
    protected Object getTarget() {
        return currentTrack;
    }

    public void handlePropertyValue(Tag propertyValue) {
        if (currentProperty.equals(TrackProperty.TRACK_ID)) {
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
