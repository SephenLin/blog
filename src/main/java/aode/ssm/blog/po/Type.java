package aode.ssm.blog.po;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.security.PrivateKey;
import java.util.List;

/**
 * Created by 林进华 on 2017/7/15.
 */
@Table(name = "type")
public class Type {

    private String label ; // 类型
    @Transient
    private List<Article> articles ;
    @Id
    private Integer id ;
    private int articleNumber ;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(int articleNumber) {
        this.articleNumber = articleNumber;
    }
}
