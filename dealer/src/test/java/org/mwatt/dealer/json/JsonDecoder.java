package org.mwatt.dealer.json;

import org.springframework.core.codec.DecodingException;

public interface JsonDecoder<T> {

    /**
     * Decodes a JSON string into an object.
     *
     * @param json the JSON string to be decoded
     * @return the decoded object
     * @throws DecodingException if an error occurs during decoding
     */
    T decode(String json) throws DecodingException;
}

