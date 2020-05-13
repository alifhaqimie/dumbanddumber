package sample.Model;

public class OrderTable {
    String commande,datecommande,quantity;
    String NCommande;

    public OrderTable(String NCommande, String datecommande, String commande, String quantity) {

        this.NCommande = NCommande;
        this.datecommande = datecommande;
        this.commande = commande;
        this.quantity = quantity;
    }

    public String getNCommande() {
        return NCommande;
    }

    public void setNCommande(String NCommande) {
        this.NCommande = NCommande;
    }

    public String getDatecommande() {
        return datecommande;
    }

    public void setDatecommande(String datecommande) {
        this.datecommande = datecommande;
    }

    public String getCommande() {
        return commande;
    }

    public void setCommande(String commande) {
        this.commande = commande;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}

