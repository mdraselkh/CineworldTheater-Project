package cineworldcinema;

public class historyList {

    int Serial, ticketid, totalprice;

    String film, screen, showtime, showdate, seat;

    public historyList(int Serial, int ticketid, int totalprice, String film, String screen, String showtime, String showdate, String seat) {
        this.Serial = Serial;
        this.ticketid = ticketid;
        this.totalprice = totalprice;
        this.film = film;
        this.screen = screen;
        this.showtime = showtime;
        this.showdate = showdate;
        this.seat = seat;
    }

    public int getSerial() {
        return Serial;
    }

    public void setSerial(int Serial) {
        this.Serial = Serial;
    }

    public int getTicketid() {
        return ticketid;
    }

    public void setTicketid(int ticketid) {
        this.ticketid = ticketid;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public String getFilm() {
        return film;
    }

    public void setFilm(String film) {
        this.film = film;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getShowtime() {
        return showtime;
    }

    public void setShowtime(String showtime) {
        this.showtime = showtime;
    }

    public String getShowdate() {
        return showdate;
    }

    public void setShowdate(String showdate) {
        this.showdate = showdate;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

}
