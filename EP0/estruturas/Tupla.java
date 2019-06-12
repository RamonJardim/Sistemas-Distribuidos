package estruturas;

public class Tupla<T, S> {
    private T item1;
    private S item2;

    public Tupla(){}
    public Tupla(T item1, S item2){
        this.item1 = item1;
        this.item2 = item2;
    }

    public void setItem1(T item1) {
        this.item1 = item1;
    }

    public T getItem1() {
        return item1;
    }

    public void setItem2(S item2) {
        this.item2 = item2;
    }

    public S getItem2() {
        return item2;
    }
}
