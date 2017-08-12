package aode.ssm.blog.po;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/**
 * Created by 林进华 on 2017/8/4.
 */
@Table(name = "reply")
public class Reply {

    private int id; // 回复的id
    @Transient
    private Comment comment; // 回复评论的id
    @Transient
    private User fromUser; // 回复的人
    @Transient
    private User ToUser; // 目标用户
    private String content; // 回复的内容
    private Date time; // 回复的时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return ToUser;
    }

    public void setToUser(User toUser) {
        ToUser = toUser;
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
        return "Reply{" +
                "id=" + id +
                ", comment=" + comment +
                ", fromUser=" + fromUser +
                ", ToUser=" + ToUser +
                ", content='" + content + '\'' +
                ", time=" + time +
                '}';
    }
}
