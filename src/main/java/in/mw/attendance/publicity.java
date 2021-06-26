package in.mw.attendance;

/**
 * Created by jpulipati on 3/25/17.
 */
public class publicity {

    private String Upload1;
    private String Upload2;
    private String Itemprice;
    private String Oprice;
    private String Itemname;

    public publicity(){

    }

    public String getItemname() {
        return Itemname;
    }

    public void setItemname(String itemname) {
        Itemname = itemname;
    }

    public publicity(String itemname, String itemprice, String oprice) {
        Itemname = itemname;
        Itemprice = itemprice;
        Oprice = oprice;
    }

    public publicity(String upload1, String upload2, String itemname, String itemprice, String oprice) {
        Upload1 = upload1;
        Upload2 = upload2;
        Itemname = itemname;
        Itemprice = itemprice;
        Oprice = oprice;
    }

    public String getUpload1() {
        return Upload1;
    }

    public void setUpload1(String upload1) {
        Upload1 = upload1;
    }

    public String getUpload2() {
        return Upload2;
    }

    public void setUpload2(String upload2) {
        Upload2 = upload2;
    }

    public String getItemprice() {
        return Itemprice;
    }

    public void setItemprice(String itemprice) {
        Itemprice = itemprice;
    }

    public String getOprice() {
        return Oprice;
    }

    public void setOprice(String oprice) {
        Oprice = oprice;
    }
}
