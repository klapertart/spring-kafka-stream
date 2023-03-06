package klapertart.lab.kafkastream.messages;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author kurakuraninja
 * @since 13/02/23
 */

@Data
@AllArgsConstructor
public class ResponseGeneric {
    private int status;
    private String messages;
}
