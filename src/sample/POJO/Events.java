package sample.POJO;

public class Events {

    private String NamEventsColum;
    private String DataEventsColum;

    public Events(String NamEventsColum, String DataEventsColum) {
        this.NamEventsColum = NamEventsColum;
        this.DataEventsColum = DataEventsColum;
    }

    public Events() {

    }

    public String getNamEventsColum() {
        return NamEventsColum;
    }

    public void setNamEventsColumr(String NamEventsColum) {
        this.NamEventsColum = NamEventsColum;
    }

    public String getDataEventsColum() {
        return DataEventsColum;
    }

    public void setDataEventsColum(String DataEventsColum) {
        this.DataEventsColum = DataEventsColum;
    }
}
