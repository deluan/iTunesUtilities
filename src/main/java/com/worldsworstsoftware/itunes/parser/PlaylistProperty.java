package com.worldsworstsoftware.itunes.parser;

class PlaylistProperty
{
	/*
		example playlist properties:
		<key>Name</key><string>Library</string>
		<key>Playlist ID</key><integer>4881</integer>
		<key>Playlist Persistent ID</key><string>4EC2FAC25152379F</string>
		<key>Visible</key><false/>
		<key>All Items</key><true/>
		<key>Smart Info</key>
		<data>
		AQEAAwAAAAIAAAAZAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
		AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
		AAAAAA==
		</data>
		<key>Smart Criteria</key>
		<data>
		U0xzdAABAAEAAAABAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
		AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
		AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABAAAAIAAAAAAAAAAAAAAAAAAAAAAAAA
		AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABELa4tri2uLa7//////////wAAAAAAAVGA
		La4tri2uLa4AAAAAAAAAAAAAAAAAAAABAAAAAAAAAAAAAAAAAAAAAAAAAAA=
		</data>
		<key>Playlist Items</key>
		<key>Master</key><true/>
		<key>Audiobooks</key><true/>
		<key>Movies</key><true/>
		<key>Music</key><true/>
		<key>Party Shuffle</key><true/>
		<key>Podcasts</key><true/>
		<key>TV Shows</key><true/>
		
		<key>Track ID</key><integer>4002</integer>
	*/

	public static final String NAME = "Name";
	public static final String PLAYLIST_ID = "Playlist ID";
	public static final String PLAYLIST_PERSISTENT_ID = "Playlist Persistent ID";
	public static final String VISIBLE = "Visible";
	public static final String ALL_ITEMS = "All Items";
	public static final String SMART_INFO = "Smart Info";
	public static final String SMART_CRITERIA = "Smart Criteria";
	public static final String PLAYLIST_ITEMS = "Playlist Items";
	public static final String MASTER = "Master";
	public static final String AUDIOBOOKS = "Audiobooks";
	public static final String MOVIES = "Movies";
	public static final String MUSIC = "Music";
	public static final String PARTY_SHUFFLE = "Party Shuffle";
	public static final String PODCASTS = "Podcasts";
	public static final String TV_SHOWS = "TV Shows";
	public static final String TRACK_ID = "Track ID";
}
