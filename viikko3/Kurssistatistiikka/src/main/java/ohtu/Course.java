package ohtu;

public class Course {

    private String id;
    private String name;
    private String term;
    private String url;

    public Course(String id, String name, String term, String url) {
        this.id = id;
        this.name = name;
        this.term = term;
        this.url = url;
    }

    public String getTerm() {
        return term;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

}
