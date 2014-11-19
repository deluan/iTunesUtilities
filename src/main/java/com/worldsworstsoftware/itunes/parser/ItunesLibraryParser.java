package com.worldsworstsoftware.itunes.parser;

import com.worldsworstsoftware.itunes.ItunesLibrary;
import com.worldsworstsoftware.util.PerformanceTimer;
import com.worldsworstsoftware.xmltagparser.SimpleXMLTagParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//TODO: javadoc

public class ItunesLibraryParser {
    private static Logger logger = LoggerFactory.getLogger(ItunesLibraryParser.class);

    public static ItunesLibrary parseLibrary(String itunesLibraryFilePath) {

        ItunesLibrary library = new ItunesLibrary();

        ItunesTagHandler tagHandler = new ItunesTagHandler(library);

        SimpleXMLTagParser parser = new SimpleXMLTagParser(itunesLibraryFilePath, tagHandler, false);

        PerformanceTimer timer = new PerformanceTimer();

        logger.debug("Parsing has started. Parsing library file \"" + itunesLibraryFilePath + "\".");
        logger.debug("Now parsing library properties.");
        try {
            parser.parse();
        } catch (Exception e) {
            throw new RuntimeException("Error occurred during library parsing: " + e.getMessage(), e);
        }

        logger.debug("Now parsing tracks (" + "(" + library.getTracks().size() + " tracks and " + library.getPlaylists().size() + " playlists parsed.)" + " tracks parsed).");
        logger.debug("Total Parsing Time: " + timer.getTimeElapsedSinceLastReset());


        return library;
    }
}
