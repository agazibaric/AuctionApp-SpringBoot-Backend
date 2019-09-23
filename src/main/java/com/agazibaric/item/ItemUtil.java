package com.agazibaric.item;

import javax.persistence.AttributeConverter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

public class ItemUtil {

    /**
     * Method checks if item's auction time has expired.
     *
     * @param item item whose expiration time is checked
     * @return <code>true</code> if item's auction time has expired,
     *         <code>false</code> otherwise
     */
    public static boolean isItemExpired(Item item) {
        return item.getCreationTime().plus(item.getDuration()).isBefore(LocalDateTime.now());
    }

    public class PathConverter implements AttributeConverter<Path, String> {

        @Override
        public String convertToDatabaseColumn(Path attribute) {
            return attribute.toString();
        }

        @Override
        public Path convertToEntityAttribute(String dbData) {
            return Paths.get(dbData);
        }

    }

    public static String getFullImageName(Item item, String fileName) {
        return String.format("%s-%s", item.getName(), fileName);
    }

}
