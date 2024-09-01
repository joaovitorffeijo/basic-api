package joaovitorffeijo.basic_api.util.diff;

import java.util.List;

public class ObjectDiffData {
    public static class ObjectDiffProperty{

        public ObjectDiffProperty(String propertyName, Object fromValue, Object toValue) {
            this.propertyName = propertyName;
            this.fromValue = fromValue;
            this.toValue = toValue;
        }

        private final String propertyName;
        private final Object fromValue;
        private final Object toValue;

        public String getPropertyName() {
            return propertyName;
        }

        public Object getFromValue() {
            return fromValue;
        }

        public Object getToValue() {
            return toValue;
        }
    }

    public ObjectDiffData(Class<?> objectClass, List<ObjectDiffProperty> diffProperties) {
        this.objectClass = objectClass;
        this.diffProperties = diffProperties;
    }

    private final Class<?> objectClass;
    private final List<ObjectDiffProperty> diffProperties;

    public Class<?> getObjectClass() {
        return objectClass;
    }

    public List<ObjectDiffProperty> getDiffProperties() {
        return diffProperties;
    }
}
