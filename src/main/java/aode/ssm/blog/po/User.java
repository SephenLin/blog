package aode.ssm.blog.po;

import javafx.scene.chart.PieChart;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/**
 * Created by 林进华 on 2017/7/15.
 */
@Table(name = "user")
public class User {

    private String name ; // 名字
    private String account ; // 账号
    private String password ; // 密码
    private String phone ; // 电话号码
    private String email ; // 电子邮件
    private String headUrl ; // 头像相对路径
    private String headName ; // 头像名字
    private int traffics ; // 浏览量
    private Date time ; // 创建时间
    private String introduction ; // 自我介绍
    private int level;
    @Id
    private Integer id ;
    @Transient
    private List<Article> artice ; // 文章
    @Transient
    private List<Reply> replies ; // 回复

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getHeadName() {
        return headName;
    }

    public void setHeadName(String headName) {
        this.headName = headName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getTraffics() {
        return traffics;
    }

    public void setTraffics(int traffics) {
        this.traffics = traffics;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Article> getArtice() {
        return artice;
    }

    public void setArtice(List<Article> artice) {
        this.artice = artice;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", headUrl='" + headUrl + '\'' +
                ", headName='" + headName + '\'' +
                ", traffics=" + traffics +
                ", time=" + time +
                ", introduction='" + introduction + '\'' +
                ", id=" + id +
                ", artice=" + artice +
                ", level=" + level +
                '}';
    }
}
