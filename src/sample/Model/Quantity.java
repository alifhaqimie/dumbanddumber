package sample.Model;

import java.time.LocalDate;

public class Quantity {
    LocalDate date;
    String element;
    int initial;
    int consumed;
    int ordered;
    int present;

    public Quantity() {
    }

    public Quantity(LocalDate date, String element, int initial, int consumed, int ordered, int present) {
        this.date = date;
        this.element = element;
        this.initial = initial;
        this.consumed = consumed;
        this.ordered = ordered;
        this.present = present;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

