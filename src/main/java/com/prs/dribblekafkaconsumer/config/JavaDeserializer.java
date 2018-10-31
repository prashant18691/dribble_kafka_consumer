package com.prs.dribblekafkaconsumer.config;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;
import org.apache.kafka.common.serialization.Deserializer;


public class JavaDeserializer implements Deserializer {
    @Override public void configure(final Map map, final boolean b) {

    }

    @Override public Object deserialize(final String s, final byte[] data) {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = null;
        try {
            is = new ObjectInputStream(in);
            return is.readObject();
        } catch (IOException e) {
            throw new IllegalStateException("Can't serialize object: " + data, e);
        }catch (ClassNotFoundException e) {
            throw new IllegalStateException("Class not found " + data, e);
        }
    }

    @Override public void close() {

    }
}
