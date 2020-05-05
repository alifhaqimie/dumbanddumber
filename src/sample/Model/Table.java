package sample.Model;

public class Table {
    String fullname,Etatpatient,Menu,Regime;

    public Table(String fullname, String etatpatient, String menu, String regime) {
        this.fullname = fullname;
        Etatpatient = etatpatient;
        Menu = menu;
        Regime = regime;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEtatpatient() {
        return Etatpatient;
    }

    public void setEtatpatient(String etatpatient) {
        Etatpatient = etatpatient;
    }

    public String getMenu() {
        return Menu;
    }

    public void setMenu(String menu) {
        Menu = menu;
    }

    public String getRegime() {
        return Regime;
    }

    public void setRegime(String regime) {
        Regime = regime;
    }

}
