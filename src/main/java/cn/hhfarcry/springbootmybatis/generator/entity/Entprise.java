package cn.hhfarcry.springbootmybatis.generator.entity;

import java.util.Date;

public class Entprise {
    private String uuid;

    private Integer isDeleted;

    private Integer status;

    private String comment;

    private String createUserUuid;

    private Date createTime;

    private String updateUserUuid;

    private Date updateTime;

    private String entName;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public String getCreateUserUuid() {
        return createUserUuid;
    }

    public void setCreateUserUuid(String createUserUuid) {
        this.createUserUuid = createUserUuid == null ? null : createUserUuid.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUserUuid() {
        return updateUserUuid;
    }

    public void setUpdateUserUuid(String updateUserUuid) {
        this.updateUserUuid = updateUserUuid == null ? null : updateUserUuid.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName == null ? null : entName.trim();
    }
}