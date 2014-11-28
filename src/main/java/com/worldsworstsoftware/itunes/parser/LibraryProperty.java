package com.worldsworstsoftware.itunes.parser;

class LibraryProperty
{
	/*
		example library properties:
		<key>Major Version</key><integer>1</integer>
		<key>Minor Version</key><integer>1</integer>
		<key>Application Version</key><string>7.0.1</string>
		<key>Features</key><integer>1</integer>
		<key>Show Content Ratings</key><true/>
		<key>Music Folder</key><string>file://localhost/E:/itunes/</string>
		<key>Library Persistent ID</key><string>4EC2FAC25152379E</string>
	 */

	public static final String MAJOR_VERSION = "Major Version";
	public static final String MINOR_VERSION = "Minor Version";
	public static final String APPLICATION_VERSION = "Application Version";
	public static final String FEATURES = "Features";
	public static final String SHOW_CONTENT_RATINGS = "Show Content Ratings";
	public static final String MUSIC_FOLDER = "Music Folder";
	public static final String LIBRARY_PERSISTENT_ID = "Library Persistent ID";
	public static final String TRACKS = "Tracks";
	public static final String PLAYLISTS = "Playlists";
}
