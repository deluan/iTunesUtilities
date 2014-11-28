package com.worldsworstsoftware.itunes.parser;

import com.worldsworstsoftware.itunes.ItunesLibrary;
import com.worldsworstsoftware.itunes.ItunesPlaylist;
import com.worldsworstsoftware.xmltagparser.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class PlaylistPropertyTagHandler extends BasePropertyTagHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    protected int playlistParseCount = 0;
    private ItunesPlaylist currentPlaylist;

    public PlaylistPropertyTagHandler(ItunesLibrary library) {
        super(library);
    }

    @Override
    protected void initializePropertyMap() {
        addPropertyToPropertyMap(PlaylistProperty.NAME, "name", String.class);
        addPropertyToPropertyMap(PlaylistProperty.PLAYLIST_ID, "playlistID", Integer.class);
        addPropertyToPropertyMap(PlaylistProperty.PLAYLIST_PERSISTENT_ID, "playlistPersistentId", String.class);
        addPropertyToPropertyMap(PlaylistProperty.VISIBLE, "visible", Boolean.class);
        addPropertyToPropertyMap(PlaylistProperty.ALL_ITEMS, "allItems", Boolean.class);
        addPropertyToPropertyMap(PlaylistProperty.SMART_INFO, "smartInfo", byte[].class);
        addPropertyToPropertyMap(PlaylistProperty.SMART_CRITERIA, "smartCriteria", byte[].class);
        addPropertyToPropertyMap(PlaylistProperty.MASTER, "master", Boolean.class);
        addPropertyToPropertyMap(PlaylistProperty.AUDIOBOOKS, "audiobooks", Boolean.class);
        addPropertyToPropertyMap(PlaylistProperty.MOVIES, "movies", Boolean.class);
        addPropertyToPropertyMap(PlaylistProperty.MUSIC, "music", Boolean.class);
        addPropertyToPropertyMap(PlaylistProperty.PARTY_SHUFFLE, "partyShuffle", Boolean.class);
        addPropertyToPropertyMap(PlaylistProperty.PODCASTS, "podcasts", Boolean.class);
        addPropertyToPropertyMap(PlaylistProperty.TV_SHOWS, "tvShows", Boolean.class);
        addPropertyToPropertyMap(PlaylistProperty.TRACK_ID, "trackID", Integer.class);
        addPropertyToPropertyMap(PlaylistProperty.PLAYLIST_ITEMS, "", null);
    }

    @Override
    protected Object getTarget() {
        return currentPlaylist;
    }

    public void handlePropertyValue(Tag propertyValue) {
        if (currentProperty.equals(PlaylistProperty.NAME)) {
            currentPlaylist = new ItunesPlaylist(library);

            //add a reference to the new playlist to the library.
            library.addPlaylist(currentPlaylist);

            if (++playlistParseCount % 200 == 0) {
                logger.debug("Now parsing playlists (" + playlistParseCount + " playlists parsed).");
            }
        }
        super.handlePropertyValue(propertyValue);
    }

}
