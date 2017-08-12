package aode.ssm.blog.po;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by 林进华 on 2017/7/18.
 */
@Table(name = "image")
public class Image {
    @Transient
    private Article article; // 图片所属的文章
    private String url;
    private String name;
    private String audit;
    @Id
    private Integer id;

    public String getAudit() {
        return audit;
    }

    public void setAudit(String audit) {
        this.audit = audit;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
