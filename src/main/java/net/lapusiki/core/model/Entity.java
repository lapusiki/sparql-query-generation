package net.lapusiki.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class Entity {

    private List<String> values = new ArrayList<>();

    public Entity(String... values) {
        for (String value : values) {
            this.values.add(value);
        }
    }

    public Entity(List<String> values) {
        this.values = values;
    }

    public Entity() {
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entity entity = (Entity) o;

        return !(values != null ? !values.equals(entity.values) : entity.values != null);

    }

    @Override
    public int hashCode() {
        return values != null ? values.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "values=" + values +
                '}';
    }

}
