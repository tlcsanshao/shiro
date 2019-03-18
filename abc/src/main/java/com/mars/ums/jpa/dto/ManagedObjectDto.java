package com.mars.ums.jpa.dto;

public class ManagedObjectDto {
    public long moid;

    protected String name;

    protected String displayName;

    protected String className = "ManagedObject";

    protected int status;

    protected long statusChangeTime;
    protected String type;

    protected String parentName;

    protected int toDiscovery = 0;

    protected long parentId =  -1;

    protected String ip;

    protected int deviceType = -1;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public long getMoid() {
        return moid;
    }

    public void setMoid(long moid) {
        this.moid = moid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getStatusChangeTime() {
        return statusChangeTime;
    }

    public void setStatusChangeTime(long statusChangeTime) {
        this.statusChangeTime = statusChangeTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public int getToDiscovery() {
        return toDiscovery;
    }

    public void setToDiscovery(int toDiscovery) {
        this.toDiscovery = toDiscovery;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }

    @Override
    public String toString() {
        return "ManagedObjectDto{" +
                "moid=" + moid +
                ", name='" + name + '\'' +
                ", displayName='" + displayName + '\'' +
                ", className='" + className + '\'' +
                ", status=" + status +
                ", statusChangeTime=" + statusChangeTime +
                ", type='" + type + '\'' +
                ", parentName='" + parentName + '\'' +
                ", toDiscovery=" + toDiscovery +
                ", parentId=" + parentId +
                ", ip='" + ip + '\'' +
                ", deviceType=" + deviceType +
                '}';
    }
}
