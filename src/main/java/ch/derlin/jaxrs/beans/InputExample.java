package ch.derlin.jaxrs.beans;

import ch.derlin.jaxrs.utils.RFC3339JsonUtils;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * this class uses several different bean validation annotations,
 * as well as custom jsonSerializer/Deserializer.
 *
 * See the {@link ch.derlin.jaxrs.ws.ExampleWebService} for a use case.
 *
 * date: 09.05.2016
 * @author Lucy Linder
 */
@XmlRootElement
public class InputExample {

    @NotNull
    @Size(min = 32, max = 32, message = "wrong size of token.")
    @XmlElement
    private String token;

    @NotNull
    @XmlElement
    @JsonSerialize(using= RFC3339JsonUtils.Serializer.class)
    @JsonDeserialize(using= RFC3339JsonUtils.Deserializer.class)
    java.util.Date timestamp;

    @NotNull
    @Min(value=0, message = "captor-id must be positive.")
    @XmlElement(name="captor-id")
    private int captorId;

    @Null
    @XmlElement
    @Pattern(regexp = "[0-9.]+", message = "the address field is incorrect")
    private String address;

    @NotNull
    @XmlElement
    private String value;

    @Null
    @XmlElement
    @Size(max=1024, message = "the comment is too long.")
    private String comment;

    // ----------------------------------------------------


    @Override
    public String toString() {
        return String.format("%d => %s : %s", captorId, timestamp, value);
    }
}//end class
