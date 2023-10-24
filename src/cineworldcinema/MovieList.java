
package cineworldcinema;

public class MovieList {
    int Serial;
    String name,directorName,status;

    public MovieList(int Serial, String name, String directorName, String status) {
        this.Serial = Serial;
        this.name = name;
        this.directorName = directorName;
        this.status = status;
    }

    public int getSerial() {
        return Serial;
    }

    public void setSerial(int Serial) {
        this.Serial = Serial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
