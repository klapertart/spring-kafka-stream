package klapertart.lab.kafkastream.messages;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * @author kurakuraninja
 * @since 13/02/23
 */


public class ResponseError extends ResponseGeneric {
    @Getter
    @Setter
    private Map<String,String> error;

    public ResponseError(int status, String message, Map<String,String> error) {
        super(status,message);
        this.error = error;
    }


}
