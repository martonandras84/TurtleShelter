package com.martonandras.turtleshelter.application.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Util class to deserialize ZonedDateTime values
 * Used by requestDTO classes
 *
 * @author Andras Marton (martonandras84@gmail.com)
 */
public class ZonedDateTimeDeserializer extends JsonDeserializer<ZonedDateTime> {

    @Override
    public ZonedDateTime deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {

        LocalDate localDate = LocalDate.parse(
                jsonParser.getText(),
                DateTimeFormatter.ISO_LOCAL_DATE);

        return localDate.atStartOfDay(ZoneOffset.UTC);
    }
}
