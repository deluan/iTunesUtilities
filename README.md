iTunes Utilities
================

[ ![Download](https://api.bintray.com/packages/deluan/maven/itunes-utilities/images/download.svg) ](https://bintray.com/deluan/maven/itunes-utilities/_latestVersion)

iTunes Utilities is an open-source Java library that provides cross-platform functionality related to parsing and
detecting the Itunes library xml file.

* updated to iTunes 12 by [Deluan Quintao](http://techbeats.deluan.com)
* written by Jason Baker ([jason@onejasonforsale.com](mailto:jason@onejasonforsale.com))

For more information, read the [original README](https://github.com/codercowboy/iTunesUtilities/blob/master/README.md)

Description
===========

The Itunes Utilities java library is a collection of java classes you can use in your own application to work with
iTunes' library data. This library is used in [iSonic](https://github.com/deluan/iSonic) to parse track and playlist
information out of iTunes library files.

Features:
* Fully-featured Java object representations of the Itunes library, playlists, and tracks.
* Easy to use itunes library parser.
* Itunes library auto-detector.

Usage Instructions
==================

### Gradle
```
repositories {
    ...
    jcenter()
}

dependencies {
    ...
    compile 'com.worldsworstsoftware:itunes-utilities:1.1'
}
```

### Maven
```
<repositories>
    <repository>
        <snapshots>
            <enabled>false</enabled>
        </snapshots>
        <id>central</id>
        <name>bintray</name>
        <url>http://jcenter.bintray.com</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.worldsworstsoftware</groupId>
    <artifactId>itunes-utilities</artifactId>
    <version>1.1</version>
    <type>jar</type>
</dependency>
```

Look at the code for the sample in [ItunesUtilitiesUsageSample.java](https://github.com/deluan/iTunesUtilities/blob/master/src/test/java/com/worldsworstsoftware/sample/ItunesUtilitiesUsageSample.java).
