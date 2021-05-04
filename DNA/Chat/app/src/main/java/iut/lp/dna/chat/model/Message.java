package iut.lp.dna.chat.model;

public class Message {
    private Integer id;
    private String user;
    private  String text;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
