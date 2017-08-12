package aode.ssm.blog.po;

import javax.persistence.Table;

/**
 * Created by 林进华 on 2017/8/4.
 */
@Table(name = "comment")
public class CommentCustom extends Comment {

    private int topicAid; // 评论的文章的id
    private int fromUid; // 评论人的id

    public int getTopicAid() {
        return topicAid;
    }

    public void setTopicAid(int topicAid) {
        this.topicAid = topicAid;
    }

    public int getFromUid() {
        return fromUid;
    }

    public void setFromUid(int fromUid) {
        this.fromUid = fromUid;
    }

    @Override
    public String toString() {
        return super.toString() + " |  CommentCustom{" +
                "topicAid=" + topicAid +
                ", fromUid=" + fromUid +
                '}';
    }
}
