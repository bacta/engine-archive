package bacta.data.serialization;

import bacta.network.object.NetworkObject;
import bacta.network.service.object.ObjectService;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Registration;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Created by kburkhardt on 8/22/14.
 */
@Singleton
public class NetworkObjectSerializer<T extends NetworkObject> extends Serializer<T> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Map<Class<? extends NetworkObject>, List<Field>> serializableFieldsMap = new HashMap<>();

    protected final ObjectService objectService;

    @Inject
    public NetworkObjectSerializer(ObjectService objectService) {
        this.objectService = objectService;
        buildSerializationMetadata();
    }


    private void buildSerializationMetadata() {

        Reflections reflections = new Reflections();

        Set<Class<? extends NetworkObject>> networkObjects = reflections.getSubTypesOf(NetworkObject.class);

        Iterator<Class<? extends NetworkObject>> objectIter = networkObjects.iterator();
        while (objectIter.hasNext()) {

            Class<? extends NetworkObject> networkObject = objectIter.next();
            List<Field> fields = loadSerializableClass(networkObject);

            serializableFieldsMap.put(networkObject, fields);
        }

    }

    private List<Field> loadSerializableClass(Class<? extends NetworkObject> networkObject) {

        List<Field> fields = null;

        if(networkObject.getSuperclass() != null) {
            fields = loadSerializableClass((Class<? extends NetworkObject>) networkObject.getSuperclass());
        }

        if(fields == null) {
            fields = new ArrayList<>();
        }

        for (Field field : networkObject.getDeclaredFields()) {

            if(!Modifier.isTransient(field.getModifiers())) {
                field.setAccessible(true);
                fields.add(field);
            }
        }

        return fields;
    }

    @Override
    public void write(Kryo kryo, Output output, T object) {

        List<Field> fields = serializableFieldsMap.get(object.getClass());
        for(Field field : fields) {
            try {

                kryo.writeClassAndObject(output, field.get(object));

            } catch (IllegalAccessException e) {
                logger.error("Unable to serialize", e);
            } catch (IllegalArgumentException e) {
                logger.error("Offending class: " + object.getClass().getName() + " Field: " + field.getName());
            }
        }
    }

    @Override
    public T read(Kryo kryo, Input input, Class<T> type) {

        try {

            T newObject = type.newInstance();

            List<Field> fields = serializableFieldsMap.get(type);
            for(Field field : fields) {
                Registration reg = kryo.readClass(input);

                field.set(newObject, kryo.readObject(input, reg.getType()));
            }

            return newObject;

        } catch (IllegalAccessException e) {
            logger.error("Unable to serialize", e);
        } catch (InstantiationException e) {
            logger.error("Unable to instantiate", e);
        }

        return null;
    }
}
