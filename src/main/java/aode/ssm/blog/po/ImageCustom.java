package aode.ssm.blog.po;

import javax.persistence.Transient;

/**
 * Created by 林进华 on 2017/7/22.
 */
public class ImageCustom extends Image {

    private String introduce; // 图片摘要
    @Transient
    private String authorName; // 作者
    private String title; // 标题

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
