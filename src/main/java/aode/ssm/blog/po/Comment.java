package aode.ssm.blog.po;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/**
 * Created by 林进华 on 2017/8/4.
 */
@Table(name = "comment")
public class Comment {

    private int id; // 评论id
    @Transient
    private Article article; // 回复的文章
    @Transient
    private User fromUser; // 评论的人
    @Transient
    private List<Reply> replies; // 回复的集合
    private String content; // 回复的内容
    private Date time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", article=" + article +
                ", fromUser=" + fromUser +
                ", replies=" + replies +
                ", content='" + content + '\'' +
                ", time=" + time +
                '}';
    }
}
