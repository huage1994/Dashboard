package work.model;

/**
 * Created by I320997 on 1/17/2017.
 */
public class JenkinsStatus {

    public String url;
    public String color;

    public JenkinsStatus(String url, String color) {
        this.url = url;
        this.color = color;
    }

    @Override
    public String toString() {
        return "JenkinsStatus{" +

                ", url='" + url + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
