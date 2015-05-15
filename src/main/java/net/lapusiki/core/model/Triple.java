package net.lapusiki.core.model;

/**
 * Created by kiv1n on 15.05.2015
 */
public class Triple<T,V,K> {

    private T object1;

    private V object2;

    private K object3;

    public Triple() {
    }

    public Triple(T object1, V object2, K object3) {
        this.object1 = object1;
        this.object2 = object2;
        this.object3 = object3;
    }

    public T getObject1() {
        return object1;
    }

    public void setObject1(T object1) {
        this.object1 = object1;
    }

    public V getObject2() {
        return object2;
    }

    public void setObject2(V object2) {
        this.object2 = object2;
    }

    public K getObject3() {
        return object3;
    }

    public void setObject3(K object3) {
        this.object3 = object3;
    }

    @Override
    public String toString() {
        return "Triple{" +
                "object1=" + object1 +
                ", object2=" + object2 +
                ", object3=" + object3 +
                '}';
    }
}
