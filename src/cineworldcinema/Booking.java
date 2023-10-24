
package cineworldcinema;

public class Booking {
    int number;
    String customerName,time,showDate,status,bookTime;

    public Booking(int number, String customerName, String time, String showDate, String status, String bookTime) {
        this.number = number;
        this.customerName = customerName;
        this.time = time;
        this.showDate = showDate;
        this.status = status;
        this.bookTime = bookTime;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getShowDate() {
        return showDate;
    }

    public void setShowDate(String showDate) {
        this.showDate = showDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBookTime() {
        return bookTime;
    }

    public void setBookTime(String bookTime) {
        this.bookTime = bookTime;
    }
    
    

}
