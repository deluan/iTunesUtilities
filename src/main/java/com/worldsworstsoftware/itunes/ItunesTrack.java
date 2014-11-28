/*
 * Source code from iPlaylist Copier is (C) Jason Baker 2006
 * 
 * Please make an effort to document your additions to this source code file,
 * so future developers can give you credit where due.
 * 
 * Please include this copyright information in these source files when
 * redistributing source code. 
 *
 * Please make note of this copyright information in documentation for
 * binary redistributions that contain any or all of the source code. 
 *
 * If you are having any trouble understanding the meaning of this code
 * email jason directly at jason@onejasonforsale.com.
 *
 * Thanks, and happy coding!
 */


/*
 * TrackType.java
 *
 * Created on November 6, 2005, 3:25 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.worldsworstsoftware.itunes;

//TODO JAVADOC:

import java.util.Date;

/**
 *
 * @author jason
 */
public class ItunesTrack {
    
/*
	Track properties example xml:
	<key>401</key>
	<dict>
		<key>Track ID</key><integer>401</integer>
		<key>Name</key><string>The Hollow</string>
		<key>Artist</key><string>A Perfect Circle</string>
		<key>Album</key><string>Mer de Noms</string>
		<key>Genre</key><string>Progressive Rock</string>
		<key>Kind</key><string>MPEG audio file</string>
		<key>Size</key><integer>4299529</integer>
		<key>Total Time</key><integer>179043</integer>
		<key>Track Number</key><integer>1</integer>
		<key>Track Count</key><integer>12</integer>
		<key>Year</key><integer>2000</integer>
		<key>Date Modified</key><date>2006-07-21T21:48:32Z</date>
		<key>Date Added</key><date>2005-10-20T02:43:19Z</date>
		<key>Bit Rate</key><integer>192</integer>
		<key>Sample Rate</key><integer>44100</integer>
		<key>Comments</key><string>Track 1</string>
		<key>Play Count</key><integer>5</integer>
		<key>Play Date</key><integer>3236345492</integer>
		<key>Play Date UTC</key><date>2006-07-21T21:51:32Z</date>
		<key>Persistent ID</key><string>51C977107B2FE940</string>
		<key>Track Type</key><string>File</string>
		<key>Location</key><string>file://localhost/E:/itunes/A%20Perfect%20Circle/Mer%20de%20Noms/01%20The%20Hollow.mp3</string>
		<key>File Folder Count</key><integer>4</integer>
		<key>Library Folder Count</key><integer>1</integer>
		<key>Disabled</key><true/>
		<key>Skip Count</key><integer>1</integer>
		<key>Skip Date</key><date>2007-04-21T00:25:49Z</date>
		<key>Composer</key><string>Trent Reznor</string>
		<key>Album Artist</key><string>The Red Jumpsuit Apparatus</string>
		<key>Artwork Count</key><integer>1</integer>
		<key>Grouping</key><string>Alternative General</string>
		<key>Disc Number</key><integer>1</integer>
		<key>Disc Count</key><integer>2</integer>
		<key>BPM</key><integer>192</integer>
	</dict>
*/
	
	/* if you add new properties here, add them to the copy constructor.. */
    private int trackID = -1;
    private String name = null;
    private String artist = null;
    private String album = null;
    private String genre = null;
    private String kind = null;
    private long size = -1;
    private int totalTime = -1;
    private int trackNumber = -1;
    private int trackCount = -1;
    private int year = -1;
    private Date dateModified = null;
    private Date dateAdded = null;
    private int bitRate = -1;
    private int sampleRate = -1;
    private String comments = null;
    private int playCount = -1;
    private long playDate = -1;
    private Date playDateUTC = null;
    private String persistentID = null;
    private String trackType = null;
    private String location = null;
    private int fileFolderCount = -1;
    private int libraryFolderCount = -1;
    private boolean disabled = false;        	
	private int skipCount = -1;
	private Date skipDate = null;
	private String composer = null;
	private String albumArtist = null;
	private int artworkCount = -1;
	private String grouping = null;
	private int discNumber = -1;
	private int discCount = -1;
	private int BPM = -1;
	private int rating = -1;
	private int albumRating = -1;
	private boolean albumRatingComputed;
	private boolean compilation;
	private String sortName;
	private String sortArtist;
	private String sortAlbum;
	private String sortAlbumArtist;
	private String sortComposer;
	private boolean purchased;
	private boolean clean;
	private boolean partOfGaplessAlbum;
	private boolean hasVideo;
	private boolean HD;
	private boolean musicVideo;
	private int volumeAdjustment;
	private int startTime;
	private int stopTime;


	/** the track number in a given playlist */
	private int playlistTrackNumber = -1;

    /* if you add new properties here, add them to the copy constructor.. */

    public ItunesTrack() {        
    }
    
    public ItunesTrack(ItunesTrack obj)
    {
    	trackID = obj.trackID;
        name = copyString(obj.name);
        artist = copyString(obj.artist);
        album = copyString(obj.album);
        genre = copyString(obj.genre);
        kind = copyString(obj.kind);
        size = obj.size;
        totalTime = obj.totalTime;
        trackNumber = obj.trackNumber;
        trackCount = obj.trackCount;
        year = obj.year;
        dateModified = copyDate(obj.dateModified);
        dateAdded = copyDate(obj.dateAdded);
        bitRate = obj.bitRate;
        sampleRate = obj.sampleRate;
        comments = copyString(obj.comments);
        playCount = obj.playCount;
        playDate = obj.playDate;
        playDateUTC = copyDate(obj.playDateUTC);
        persistentID = copyString(obj.persistentID);
        trackType = copyString(obj.trackType);
        location = copyString(obj.location);
        fileFolderCount = obj.fileFolderCount;
        libraryFolderCount = obj.libraryFolderCount;
        playlistTrackNumber = obj.playlistTrackNumber;
		rating = obj.rating;
	}

	protected Date copyDate(Date value) {
		return (value == null) ? null : new Date(value.getTime());
	}

    protected String copyString(String value)
    {
    	if (value == null)
    	{
    		return null;
    	}
    	else
    	{
    		return new String(value);
    	}
    }

	public int getTrackID()
	{
		return trackID;
	}

	public void setTrackID(int trackID)
	{
		this.trackID = trackID;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getArtist()
	{
		return artist;
	}

	public void setArtist(String artist)
	{
		this.artist = artist;
	}

	public String getAlbum()
	{
		return album;
	}

	public void setAlbum(String album)
	{
		this.album = album;
	}

	public String getGenre()
	{
		return genre;
	}

	public void setGenre(String genre)
	{
		this.genre = genre;
	}

	public String getKind()
	{
		return kind;
	}

	public void setKind(String kind)
	{
		this.kind = kind;
	}

	public long getSize()
	{
		return size;
	}

	public void setSize(long size)
	{
		this.size = size;
	}

	public int getTotalTime()
	{
		return totalTime;
	}

	public void setTotalTime(int totalTime)
	{
		this.totalTime = totalTime;
	}

	public int getTrackNumber()
	{
		return trackNumber;
	}

	public void setTrackNumber(int trackNumber)
	{
		this.trackNumber = trackNumber;
	}

	public int getTrackCount()
	{
		return trackCount;
	}

	public void setTrackCount(int trackCount)
	{
		this.trackCount = trackCount;
	}

	public int getYear()
	{
		return year;
	}

	public void setYear(int year)
	{
		this.year = year;
	}

	public Date getDateModified()
	{
		return dateModified;
	}

	public void setDateModified(Date dateModified)
	{
		this.dateModified = dateModified;
	}

	public Date getDateAdded()
	{
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded)
	{
		this.dateAdded = dateAdded;
	}

	public int getBitRate()
	{
		return bitRate;
	}

	public void setBitRate(int bitRate)
	{
		this.bitRate = bitRate;
	}

	public int getSampleRate()
	{
		return sampleRate;
	}

	public void setSampleRate(int sampleRate)
	{
		this.sampleRate = sampleRate;
	}

	public String getComments()
	{
		return comments;
	}

	public void setComments(String comments)
	{
		this.comments = comments;
	}

	public int getPlayCount()
	{
		return playCount;
	}

	public void setPlayCount(int playCount)
	{
		this.playCount = playCount;
	}

	public long getPlayDate()
	{
		return playDate;
	}

	public void setPlayDate(long playDate)
	{
		this.playDate = playDate;
	}

	public Date getPlayDateUTC()
	{
		return playDateUTC;
	}

	public void setPlayDateUTC(Date playDateUTC)
	{
		this.playDateUTC = playDateUTC;
	}

	public String getPersistentID()
	{
		return persistentID;
	}

	public void setPersistentID(String persistentID)
	{
		this.persistentID = persistentID;
	}

	public String getTrackType()
	{
		return trackType;
	}

	public void setTrackType(String trackType)
	{
		this.trackType = trackType;
	}

	public String getLocation()
	{
		return location;
	}

	public void setLocation(String location)
	{
		this.location = location;
	}

	public int getFileFolderCount()
	{
		return fileFolderCount;
	}

	public void setFileFolderCount(int fileFolderCount)
	{
		this.fileFolderCount = fileFolderCount;
	}

	public int getLibraryFolderCount()
	{
		return libraryFolderCount;
	}

	public void setLibraryFolderCount(int libraryFolderCount)
	{
		this.libraryFolderCount = libraryFolderCount;
	}

	public int getPlaylistTrackNumber()
	{
		return playlistTrackNumber;
	}

	public void setPlaylistTrackNumber(int playlistTrackNumber)
	{
		this.playlistTrackNumber = playlistTrackNumber;
	}

	public boolean isDisabled()
	{
		return disabled;
	}

	public void setDisabled(boolean disabled)
	{
		this.disabled = disabled;
	}

	public int getSkipCount()
	{
		return skipCount;
	}

	public void setSkipCount(int skipCount)
	{
		this.skipCount = skipCount;
	}

	public Date getSkipDate()
	{
		return skipDate;
	}

	public void setSkipDate(Date skipDate)
	{
		this.skipDate = skipDate;
	}

	public String getComposer()
	{
		return composer;
	}

	public void setComposer(String composer)
	{
		this.composer = composer;
	}

	public String getAlbumArtist()
	{
		return albumArtist;
	}

	public void setAlbumArtist(String albumArtist)
	{
		this.albumArtist = albumArtist;
	}

	public int getArtworkCount()
	{
		return artworkCount;
	}

	public void setArtworkCount(int artworkCount)
	{
		this.artworkCount = artworkCount;
	}

	public String getGrouping()
	{
		return grouping;
	}

	public void setGrouping(String grouping)
	{
		this.grouping = grouping;
	}

	public int getDiscNumber()
	{
		return discNumber;
	}

	public void setDiscNumber(int discNumber)
	{
		this.discNumber = discNumber;
	}

	public int getDiscCount()
	{
		return discCount;
	}

	public void setDiscCount(int discCount)
	{
		this.discCount = discCount;
	}

	public int getBPM()
	{
		return BPM;
	}

	public void setBPM(int bpm)
	{
		BPM = bpm;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getAlbumRating() {
		return albumRating;
	}

	public void setAlbumRating(int albumRating) {
		this.albumRating = albumRating;
	}

	public boolean isAlbumRatingComputed() {
		return albumRatingComputed;
	}

	public void setAlbumRatingComputed(boolean albumRatingComputed) {
		this.albumRatingComputed = albumRatingComputed;
	}

	public boolean isCompilation() {
		return compilation;
	}

	public void setCompilation(boolean compilation) {
		this.compilation = compilation;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getSortArtist() {
		return sortArtist;
	}

	public void setSortArtist(String sortArtist) {
		this.sortArtist = sortArtist;
	}

	public String getSortAlbum() {
		return sortAlbum;
	}

	public void setSortAlbum(String sortAlbum) {
		this.sortAlbum = sortAlbum;
	}

	public String getSortAlbumArtist() {
		return sortAlbumArtist;
	}

	public void setSortAlbumArtist(String sortAlbumArtist) {
		this.sortAlbumArtist = sortAlbumArtist;
	}

	public boolean isPurchased() {
		return purchased;
	}

	public void setPurchased(boolean purchased) {
		this.purchased = purchased;
	}

	public boolean isClean() {
		return clean;
	}

	public void setClean(boolean clean) {
		this.clean = clean;
	}

	public boolean isPartOfGaplessAlbum() {
		return partOfGaplessAlbum;
	}

	public void setPartOfGaplessAlbum(boolean partOfGaplessAlbum) {
		this.partOfGaplessAlbum = partOfGaplessAlbum;
	}

	public String getSortComposer() {
		return sortComposer;
	}

	public void setSortComposer(String sortComposer) {
		this.sortComposer = sortComposer;
	}

	public int getVolumeAdjustment() {
		return volumeAdjustment;
	}

	public void setVolumeAdjustment(int volumeAdjustment) {
		this.volumeAdjustment = volumeAdjustment;
	}

	public int getStopTime() {
		return stopTime;
	}

	public void setStopTime(int stopTime) {
		this.stopTime = stopTime;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public boolean isHasVideo() {
		return hasVideo;
	}

	public void setHasVideo(boolean hasVideo) {
		this.hasVideo = hasVideo;
	}

	public boolean isHD() {
		return HD;
	}

	public void setHD(boolean HD) {
		this.HD = HD;
	}

	public boolean isMusicVideo() {
		return musicVideo;
	}

	public void setMusicVideo(boolean musicVideo) {
		this.musicVideo = musicVideo;
	}
}
