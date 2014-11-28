package com.worldsworstsoftware.itunes.parser;

import com.worldsworstsoftware.xmltagparser.Tag;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

class DataTypeParser {

    private Map<Class, Converter> converterMap = new HashMap<Class, Converter>();

    public DataTypeParser() {
        converterMap.put(Integer.class, new Converter<Integer>() {
            public Integer convert(String propertyName, Tag propertyValue) throws Exception {
                return parseInteger(propertyName, propertyValue);
            }
        });

        converterMap.put(Long.class, new Converter<Long>() {
            public Long convert(String propertyName, Tag propertyValue) throws Exception {
                return parseLong(propertyName, propertyValue);
            }
        });

        converterMap.put(Date.class, new Converter<String>() {
            public String convert(String propertyName, Tag propertyValue) throws Exception {
                return parseDate(propertyName, propertyValue);
            }
        });

        converterMap.put(String.class, new Converter<String>() {
            public String convert(String propertyName, Tag propertyValue) throws Exception {
                return parseString(propertyName, propertyValue);
            }
        });

        converterMap.put(Boolean.class, new Converter<Boolean>() {
            public Boolean convert(String propertyName, Tag propertyValue) throws Exception {
                return parseBoolean(propertyName, propertyValue);
            }
        });

        converterMap.put(byte[].class, new Converter<byte[]>() {
            public byte[] convert(String propertyName, Tag propertyValue) throws Exception {
                return parseBytes(propertyName, propertyValue);
            }
        });
    }

    public <T> T parse(String propertyName, Tag propertyValue, Class<T> type) throws Exception {
        Converter converter = converterMap.get(type);
        return type.cast(converter.convert(propertyName, propertyValue));
    }

    // TODO Turn all parse* methods to private
    public static Integer parseInteger(String propertyName, Tag propertyValue) throws Exception {
        if (!propertyValue.getName().equals(TagType.INTEGER)) {
            throwTypeMismatchError(propertyName, TagType.INTEGER, propertyValue);
        }

        try {
            return Integer.parseInt(propertyValue.getInnerText());
        } catch (NumberFormatException e) {
            throw new Exception("NumberFormatException occurred while trying to parse property \"" + propertyName + "\"'s integer value from the following string:" + propertyValue.getInnerText());
        }
    }

    public static Long parseLong(String propertyName, Tag propertyValue) throws Exception {
        if (!propertyValue.getName().equals(TagType.INTEGER)) {
            throwTypeMismatchError(propertyName, TagType.INTEGER, propertyValue);
            return Long.MIN_VALUE;
        }

        try {
            return Long.parseLong(propertyValue.getInnerText());
        } catch (NumberFormatException e) {
            throw new Exception("NumberFormatException occurred while trying to parse property \"" + propertyName + "\"'s integer value from the following string:" + propertyValue.getInnerText());
        }
    }

    public static String parseDate(String propertyName, Tag propertyValue) throws Exception {
        if (!propertyValue.getName().equals(TagType.DATE)) {
            throwTypeMismatchError(propertyName, TagType.DATE, propertyValue);
        }

        return propertyValue.getInnerText();
    }

    public static String parseString(String propertyName, Tag propertyValue) throws Exception {
        if (!propertyValue.getName().equals(TagType.STRING)) {
            throwTypeMismatchError(propertyName, TagType.STRING, propertyValue);
        }

        return propertyValue.getInnerText();
    }

    public static Boolean parseBoolean(String propertyName, Tag propertyValue) throws Exception {
        if (!propertyValue.getName().equals(TagType.TRUE) && !propertyValue.getName().equals(TagType.FALSE)) {
            throwTypeMismatchError(propertyName, "boolean value of 'true' or 'false'", propertyValue);
        }

        if (propertyValue.getName().equals(TagType.TRUE)) {
            return true;
        } else if (propertyValue.getName().equals(TagType.FALSE)) {
            return false;
        } else {
            throw new Exception("Could not parse boolean value for property \"" + propertyName + "\" from the following tag: " + propertyValue);
        }
    }

    public static byte[] parseBytes(String propertyName, Tag propertyValue) throws Exception {
        if (!propertyValue.getName().equals(TagType.DATA)) {
            throwTypeMismatchError(propertyName, TagType.DATA, propertyValue);
        }

        return propertyValue.getInnerText().getBytes();
    }

    public static void throwTypeMismatchError(String propertyName, String dataType, Tag propertyValue) throws Exception {
        throw new Exception("Parsing Exception while parsing property \"" + propertyName + "\", property value found is not " + dataType + ": " + propertyValue);
    }

    public interface Converter<T> {
        T convert(String propertyName, Tag propertyValue) throws Exception;
    }

}
