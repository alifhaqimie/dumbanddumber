package sample.Model;

public class MenuTable {
    Integer idpatientstable;
    String menu;

    public MenuTable(Integer idpatientstable, String menu) {
        this.idpatientstable = idpatientstable;
        this.menu = menu;
    }

    public Integer getIdpatientstable() {
        return idpatientstable;
    }

    public void setIdpatientstable(Integer idpatientstable) {
        this.idpatientstable = idpatientstable;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }
}

