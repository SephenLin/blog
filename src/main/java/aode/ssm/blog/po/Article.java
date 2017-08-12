package aode.ssm.blog.po;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/**
 * Created by 林进华 on 2017/7/15.
 */
@Table(name = "article")
public class Article {
    private String title ; // 标题
    private String content ; // 内容
    private Date time ;
    private Date startTime ;  // 评论开始的时间
    private String isComment ; // 是否可以评论
    private Date endTime ; //评论结束的时间
    @Id
    private Integer id ;
    private String articleType ; //文章类型
    private int traffics ; // 文章浏览量
    @Transient
    private User user ; //用户
    @Transient
    private List<Type> types ; //文章关键字
    @Transient
    private List<Comment> comments ; //文章的评论
    @Transient
    private List<Image> images; // 文章图片
    private String introduce ; // 文章简介
    private String audit ; // 审核

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getIsComment() {
        return isComment;
    }

    public void setIsComment(String isComment) {
        this.isComment = isComment;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTraffics() {
        return traffics;
    }

    public void setTraffics(int traffics) {
        this.traffics = traffics;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getAudit() {
        return audit;
    }

    public void setAudit(String audit) {
        this.audit = audit;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", time=" + time +
                ", startTime=" + startTime +
                ", isComment='" + isComment + '\'' +
                ", endTime=" + endTime +
                ", id=" + id +
                ", articleType='" + articleType + '\'' +
                ", traffics=" + traffics +
                ", user=" + user +
                ", types=" + types +
                ", comments=" + comments +
                ", images=" + images +
                ", introduce='" + introduce + '\'' +
                ", audit='" + audit + '\'' +
                '}';
    }
}
