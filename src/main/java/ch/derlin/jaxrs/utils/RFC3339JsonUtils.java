package ch.derlin.jaxrs.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * An example of custom serializer/deserializer. for an example of use, see {@link ch.derlin.jaxrs.beans.InputExample} :
 * <code>
 *     @JsonSerialize(using=RFC3339JsonUtils.Serializer)
 *     Date myDateField;
 * </code>
 *
 * @author: Lucy Linder
 * date 09.05.2016
 */
public class RFC3339JsonUtils {
    // note: to avoid lenient parsing, refer to http://stackoverflow.com/questions/16014488/simpledateformat-parse-ignores-the-number-of-characters-in-pattern/19503019#19503019
    private static final SimpleDateFormat RFC3339_FORMATTER = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");


    public static class Serializer extends JsonSerializer<Date> {


        @Override
        public void serialize(Date value, JsonGenerator gen,
                              SerializerProvider arg2)
                throws IOException, JsonProcessingException {
            gen.writeString(RFC3339_FORMATTER.format(value));
        }
    }

    public static class Deserializer extends JsonDeserializer<Date> {
        @Override
        public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            String text = jsonParser.getText();
            try {
                return RFC3339_FORMATTER.parse(text);
            } catch (ParseException e) {
                throw new IOException(String.format("invalid date: %s. should be a valid RFC3339 date.", text));
            }
        }
    }
}//end class
