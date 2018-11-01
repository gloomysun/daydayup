package com.ly.txjta.entity;


import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "t_sys_user")
@DynamicInsert
@DynamicUpdate
public class User implements Serializable {
    private static final long serialVersionUID = -2485725404415853576L;
    private int id;
    private String loginName;// 登录名
    private String password;// 密码
    private String name;    // 姓名
    private Date birthday;     //出生年月
    private String email;    // 邮箱

    public User() {
        super();
    }

    public User(int id) {
        this();
        this.id = id;
    }

    public User(String loginName, String password) {
        this();
        this.loginName = loginName;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //此时culumn中name属性不宜用loginName，建议使用loginname，否则在部分连接池应用下会自动被转为login_Name
    @Column(name = "loginname", length = 5)
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
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

    public String getEmail() {
        return email;
    }

    @Temporal(TemporalType.DATE)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", loginName=" + loginName + ", password=" + password + ", name=" + name
                + ", birthday=" + birthday + ", email=" + email + "]";
    }

}