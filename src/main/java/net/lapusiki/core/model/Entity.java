package net.lapusiki.core.model;

/**
 */
public class Entity {

    private String value;

    public Entity(String value) {
        this.value = value;
    }

    public Entity() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entity entity = (Entity) o;

        return !(value != null ? !value.equals(entity.value) : entity.value != null);

    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "value='" + value + '\'' +
                '}';
    }
}
