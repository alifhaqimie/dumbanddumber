package sample.Model;

public class Quantity {
    String element;

    int initial;
    int consumed;
    int ordered;
    int present;

    public Quantity() {
    }

    public Quantity(String element, int initial, int consumed, int ordered, int present) {
        this.element = element;

        this.initial = initial;
        this.consumed = consumed;
        this.ordered = ordered;
        this.present = present;
    }
    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }
    public int getInitial() {
        return initial;
    }

    public void setInitial(int initial) {
        this.initial = initial;
    }

    public int getConsumed() {
        return consumed;
    }

    public void setConsumed(int consumed) {
        this.consumed = consumed;
    }

    public int getOrdered() {
        return ordered;
    }

    public void setOrdered(int ordered) {
        this.ordered = ordered;
    }

    public int getPresent() {
        return present;
    }

    public void setPresent(int present) {
        this.present = present;
    }
}

