package sample.Model;

public class Storage {
    private Integer idelement;
    private String element;
    private String storagezone;
    private String storagetype;

    public Storage() {
    }

    public Storage( String element, String storagezone, String storagetype) {
        //this.idelement = idelement;
        this.element = element;
        this.storagezone = storagezone;
        this.storagetype = storagetype;
    }

    public Storage(Integer idelement, String element, String storagezone, String storagetype) {
        this.idelement = idelement;
        this.element = element;
        this.storagezone = storagezone;
        this.storagetype = storagetype;}


    public Integer getIdelement() {
        return idelement;
    }

    public void setIdelement(Integer idelement) {
        this.idelement = idelement;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public String getStoragezone() {
        return storagezone;
    }

    public void setStoragezone(String storagezone) {
        this.storagezone = storagezone;
    }

    public String getStoragetype() {
        return storagetype;
    }

    public void setStoragetype(String storagetype) {
        this.storagetype = storagetype;
    }
}
