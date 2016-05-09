package ch.derlin.jaxrs.beans;

import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author: Lucy Linder
 * @date: 09.05.2016
 */

@XmlRootElement
public class InputExample {

    @NotNull
    @Size(min = 32, max = 32)
    @XmlElement
    private String token;

    @NotNull
    @XmlElement
    private String timestamp;

    @NotNull
    @Min(value=0, message = "captor-id is positive")
    @XmlElement(name="captor-id")
    private int captorId;

    @Null
    @XmlElement
    @Pattern(regexp = "[0-9.]+", message = "the address is incorrect")
    private String address;

    @NotNull
    @XmlElement
    private String value;

    @Null
    @XmlElement
    @Size(max=1024)
    private String comment;
}//end class
