package klapertart.lab.kafkastream.messages;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author kurakuraninja
 * @since 13/02/23
 */


public class ResponseInfo<T> extends ResponseGeneric {
    @Getter
    @Setter
    private List<T> info;

    public ResponseInfo(int status, String message, List<T> info) {
        super(status,message);
        this.info = info;
    }


}
