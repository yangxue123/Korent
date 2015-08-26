package com.korent.entity;

import com.korent.component.Address;
import com.korent.genmu.GoodsStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lei on 2015/8/25.
 */

@Entity
@Table(name="recent")
public class RentGoods {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="rid")
    private Integer id;

    @Column(name="description", length=3000,nullable=false)
    private String description;          //租品的简单描述

    @Column(name="classify",length=255,nullable=false)
    private String classify;

    @Column(name="address",nullable=false)
    private Address address;

    @Enumerated(EnumType.ORDINAL)
    private GoodsStatus status;

    @Temporal(TemporalType.DATE)
    @Column(name="updateDate")
    private Date updateDate = new Date();      //该属性描述租品状态最新的更新时间

    /*记录租品的图片的存储路径*/
    @ElementCollection(targetClass=String.class)
    @CollectionTable(name="picturePath", joinColumns=@JoinColumn(name="rid", referencedColumnName="rid", nullable=false))
    @OrderColumn(name="id")
    @Column(name="path")
    private List<String> picturePathList = new ArrayList<String>();

    /*通过双向的管连映射，实现租品被关注功能*/
    @ManyToMany
    @JoinTable(name="follow",
    joinColumns=@JoinColumn(name="rid", referencedColumnName="rid"),
    inverseJoinColumns=@JoinColumn(name="uid", referencedColumnName="uid"))
    private List<User> follower = new ArrayList<User>();

    public RentGoods() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public GoodsStatus getStatus() {
        return status;
    }

    public void setStatus(GoodsStatus status) {
        this.status = status;
    }

    public List<String> getPicturePathList() {
        return picturePathList;
    }

    public void setPicturePathList(List<String> picturePathList) {
        this.picturePathList = picturePathList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public List<User> getFollower() {
        return follower;
    }

    public void setFollower(List<User> follower) {
        this.follower = follower;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}