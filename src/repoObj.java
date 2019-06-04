import java.util.ArrayList;
import java.util.List;

public class repoObj {
    String title;
    String description;
    List<String> tags;
    String time;
    String language;
    String stars;

    public repoObj (String title, String description,List<String> tags, String time, String language, String stars) {
        this.title = title;
        this.description = description;
        this.tags = tags;
        this.time = time;
        this.language = language;
        this.stars = stars;
    }
}
