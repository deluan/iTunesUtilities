package com.worldsworstsoftware.itunes.parser;

import com.worldsworstsoftware.itunes.ItunesLibrary;
import com.worldsworstsoftware.itunes.ItunesPlaylist;
import com.worldsworstsoftware.xmltagparser.Tag;

class PlaylistPropertyTagHandler extends BasePropertyTagHandler {
    private static final String NAME = "Name";

    protected int playlistParseCount = 0;
    protected ItunesPlaylist currentPlaylist;

    public PlaylistPropertyTagHandler(ItunesLibrary library) {
        super(library);
    }

    @Override
    protected void initializePropertyMap() {
        addPropertyToPropertyMap(NAME, "name", String.class);
        addPropertyToPropertyMap("Playlist ID", "playlistID", Integer.class);
        addPropertyToPropertyMap("Playlist Persistent ID", "playlistPersistentId", String.class);
        addPropertyToPropertyMap("Visible", "visible", Boolean.class);
        addPropertyToPropertyMap("All Items", "allItems", Boolean.class);
        addPropertyToPropertyMap("Smart Info", "smartInfo", byte[].class);
        addPropertyToPropertyMap("Smart Criteria", "smartCriteria", byte[].class);
        addPropertyToPropertyMap("Master", "master", Boolean.class);
        addPropertyToPropertyMap("Audiobooks", "audiobooks", Boolean.class);
        addPropertyToPropertyMap("Movies", "movies", Boolean.class);
        addPropertyToPropertyMap("Music", "music", Boolean.class);
        addPropertyToPropertyMap("Party Shuffle", "partyShuffle", Boolean.class);
        addPropertyToPropertyMap("Podcasts", "podcasts", Boolean.class);
        addPropertyToPropertyMap("TV Shows", "tvShows", Boolean.class);
        addPropertyToPropertyMap("Folder", "folder", Boolean.class);
        addPropertyToPropertyMap("iTunesU", "iTunesU", Boolean.class);
        addPropertyToPropertyMap("Purchased Music", "purchasedMusic", Boolean.class);
        addPropertyToPropertyMap("Parent Persistent ID", "parentPersistentID", String.class);
        addPropertyToPropertyMap("Distinguished Kind", "distinguishedKind", Integer.class);
        addPropertyToPropertyMap("Track ID", "trackID", Integer.class, "addTrackID");
        addPropertyToPropertyMap("Playlist Items", "", null);
    }

    @Override
    protected Object getTarget() {
        return currentPlaylist;
    }

    public void handlePropertyValue(Tag propertyValue) {
        if (currentProperty.equals(NAME)) {
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
