package org.mwatt.deck.json;

import org.springframework.core.codec.EncodingException;

public interface JsonEncoder<T> {

    /**
     * Encodes an object into a JSON string.
     *
     * @param object the object to be encoded
     * @return the JSON string representation of the object
     * @throws EncodingException if an error occurs during encoding
     */
    String encode(T object) throws EncodingException;
}

