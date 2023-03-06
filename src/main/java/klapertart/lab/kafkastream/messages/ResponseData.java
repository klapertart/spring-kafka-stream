package klapertart.lab.kafkastream.messages;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author kurakuraninja
 * @since 13/02/23
 */


public class ResponseData<T> extends ResponseGeneric {
    @Getter
    @Setter
    private List<T> data;

    public ResponseData(int status, String message, List<T> data) {
        super(status,message);
        this.data = data;
    }


}
