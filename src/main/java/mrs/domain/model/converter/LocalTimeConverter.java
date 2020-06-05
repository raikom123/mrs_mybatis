package mrs.domain.model.converter;

import java.sql.Time;
import java.time.LocalTime;

import javax.persistence.AttributeConverter;

public class LocalTimeConverter implements AttributeConverter<LocalTime, Time> {

    @Override
    public Time convertToDatabaseColumn(LocalTime attribute) {
        if (attribute == null) {
            return null;
        }
        return Time.valueOf(attribute);
    }

    @Override
    public LocalTime convertToEntityAttribute(Time dbData) {
        if (dbData == null) {
            return null;
        }
        return dbData.toLocalTime();
    }

}
