package sample.Model;

public class MenuTable {
    Integer idpatientstable;
    String breakfast;
    String lunch;
    String dinner;

    public MenuTable() {
    }

    public MenuTable(Integer idpatientstable, String breakfast, String lunch, String dinner) {
        this.idpatientstable = idpatientstable;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
    }

    public Integer getIdpatientstable() {
        return idpatientstable;
    }

    public void setIdpatientstable(Integer idpatientstable) {
        this.idpatientstable = idpatientstable;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }
}

