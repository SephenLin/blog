package aode.ssm.blog.po;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by 林进华 on 2017/8/6.
 */
@Table(name = "admin")
public class Admin {

    @Id
    private Integer id;
    private String name;
    private String account;
    private String password;
    private String headUrl;
    private String headName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", headUrl='" + headUrl + '\'' +
                ", headUame='" + headName + '\'' +
                '}';
    }
}
