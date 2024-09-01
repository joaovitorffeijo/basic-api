package joaovitorffeijo.basic_api.util.diff;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class ObjectDiffBuilder {

    public ObjectDiffBuilder(Class<?> objectClass) {
        this.objectClass = objectClass;
    }

    private final Class<?> objectClass;
    private final List<ObjectDiffData.ObjectDiffProperty> diffProperties = new LinkedList<>();

    public <T> boolean checkDiffAndSet(String propertyName , T fromValue, T toValue, Consumer<T> setterFunc){
        boolean valueChanged = !Objects.equals(fromValue, toValue);

        if(valueChanged){
            setterFunc.accept(toValue);
            diffProperties.add(new ObjectDiffData.ObjectDiffProperty(propertyName, fromValue, toValue));

        }

        return valueChanged;
    }

    public ObjectDiffData build(){
        return new ObjectDiffData(objectClass, diffProperties);
    }


}
