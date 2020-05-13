package sample.Model;

public class Chef {
    String Numero;
    String date,Ordre,Quantity;

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String numero) {
        Numero = numero;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrdre() {
        return Ordre;
    }

    public void setOrdre(String ordre) {
        Ordre = ordre;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public Chef(String numero, String date, String ordre, String quantity) {
        Numero = numero;
        this.date = date;
        Ordre = ordre;
        Quantity = quantity;
    }
}
