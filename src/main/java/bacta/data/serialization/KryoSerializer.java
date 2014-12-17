package bacta.data.serialization;

import bacta.network.object.NetworkObject;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Registration;
import com.esotericsoftware.kryo.io.UnsafeInput;
import com.esotericsoftware.kryo.io.UnsafeOutput;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Created by kburkhardt on 7/25/14.
 */
public abstract class KryoSerializer implements NetworkSerializer {

    private static final Logger logger = LoggerFactory.getLogger(KryoSerializer.class);
    private final ThreadLocal<Kryo> kryos;


    @Inject
    public KryoSerializer(final Injector injector) {

        kryos = new ThreadLocal<Kryo>() {
            protected Kryo initialValue() {
                Kryo kryo = new Kryo();

                kryo.setRegistrationRequired(true);

                kryo.register(NetworkObject.class, injector.getInstance(NetworkObjectSerializer.class));
                registerTypes(kryo, injector);

                return kryo;
            }
        };
        kryos.get();
    }

    public abstract void registerTypes(Kryo kryo, Injector injector);

    @Override
    public final byte[] serialize(Object object) {
        try {
            Kryo kryo = kryos.get();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            UnsafeOutput output = new UnsafeOutput(stream);
            kryo.writeClass(output, object.getClass());
            kryo.writeObject(output, object);
            output.flush();
            logger.trace(object.getClass().getName());
            logger.trace(bytesToHex(output.getBuffer(), ' '));
            output.close();
            return stream.toByteArray();
        } catch (Exception exception) {
            logger.error("Error with class " + object.getClass().getName());
            throw new RuntimeException(exception);
        }
    }

    @Override
    public final Object deserialize(byte[] bytes) {
        try {
            Kryo kryo = kryos.get();
            ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
            UnsafeInput input = new UnsafeInput(stream);
            Registration registration = kryo.readClass(input);
            logger.trace(registration.getType().getName());
            logger.trace(bytesToHex(bytes, ' '));
            Object result = kryo.readObject(input, registration.getType());
            input.close();
            return result;
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    private String bytesToHex(byte[] bytes, char seperator) {
        final char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        int length = seperator != 0 ? 3 : 2;
        char[] hexChars = new char[(bytes.length * length)];
        int v;
        for ( int j = 0; j < bytes.length; j++ ) {
            v = bytes[j] & 0xFF;
            hexChars[j * length] = hexArray[v >>> 4];
            hexChars[j * length + 1] = hexArray[v & 0x0F];

            if (seperator != 0)
                hexChars[j * length + 2] = seperator;
        }
        return new String(hexChars);
    }
}
