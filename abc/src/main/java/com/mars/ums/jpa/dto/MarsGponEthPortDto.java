package com.mars.ums.jpa.dto;

/**
 * EthPort对应实体数据传输层
 * Created  by sunam
 * 2018/12/25 11:46
 */
public class MarsGponEthPortDto {

    private int shelfId;
    private int slotId;
    private int portId;

    private int adminState;
    private int operationalState;
    private long ifIndex;
    private String ifDescr;
    private int ifType;
    private int ifMtu;
    private int ifSpeed;
    private String ifPhysAddress;

    private int flowCtrl;
    private int duplexMode;
    private int mediaType;
    private int loopbackMode;
    private int portType;
    private int stormUniCast;
    private int stormBoardCast;
    private int stormMultiCast;
    private int macLimit;
    private int unit;
    private int configIfSpeed;
    private int configIfMtu;

    public int getConfigIfMtu() {
        return configIfMtu;
    }

    public void setConfigIfMtu(int configIfMtu) {
        this.configIfMtu = configIfMtu;
    }

    public int getShelfId() {
        return shelfId;
    }

    public void setShelfId(int shelfId) {
        this.shelfId = shelfId;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public int getPortId() {
        return portId;
    }

    public void setPortId(int portId) {
        this.portId = portId;
    }

    public int getAdminState() {
        return adminState;
    }

    public void setAdminState(int adminState) {
        this.adminState = adminState;
    }

    public int getOperationalState() {
        return operationalState;
    }

    public void setOperationalState(int operationalState) {
        this.operationalState = operationalState;
    }

    public long getIfIndex() {
        return ifIndex;
    }

    public void setIfIndex(long ifIndex) {
        this.ifIndex = ifIndex;
    }

    public String getIfDescr() {
        return ifDescr;
    }

    public void setIfDescr(String ifDescr) {
        this.ifDescr = ifDescr;
    }

    public int getIfType() {
        return ifType;
    }

    public void setIfType(int ifType) {
        this.ifType = ifType;
    }

    public int getIfMtu() {
        return ifMtu;
    }

    public void setIfMtu(int ifMtu) {
        this.ifMtu = ifMtu;
    }

    public int getIfSpeed() {
        return ifSpeed;
    }

    public void setIfSpeed(int ifSpeed) {
        this.ifSpeed = ifSpeed;
    }

    public String getIfPhysAddress() {
        return ifPhysAddress;
    }

    public void setIfPhysAddress(String ifPhysAddress) {
        this.ifPhysAddress = ifPhysAddress;
    }

    public int getFlowCtrl() {
        return flowCtrl;
    }

    public void setFlowCtrl(int flowCtrl) {
        this.flowCtrl = flowCtrl;
    }

    public int getDuplexMode() {
        return duplexMode;
    }

    public void setDuplexMode(int duplexMode) {
        this.duplexMode = duplexMode;
    }

    public int getMediaType() {
        return mediaType;
    }

    public void setMediaType(int mediaType) {
        this.mediaType = mediaType;
    }

    public int getLoopbackMode() {
        return loopbackMode;
    }

    public void setLoopbackMode(int loopbackMode) {
        this.loopbackMode = loopbackMode;
    }

    public int getPortType() {
        return portType;
    }

    public void setPortType(int portType) {
        this.portType = portType;
    }

    public int getStormUniCast() {
        return stormUniCast;
    }

    public void setStormUniCast(int stormUniCast) {
        this.stormUniCast = stormUniCast;
    }

    public int getStormBoardCast() {
        return stormBoardCast;
    }

    public void setStormBoardCast(int stormBoardCast) {
        this.stormBoardCast = stormBoardCast;
    }

    public int getStormMultiCast() {
        return stormMultiCast;
    }

    public void setStormMultiCast(int stormMultiCast) {
        this.stormMultiCast = stormMultiCast;
    }

    public int getMacLimit() {
        return macLimit;
    }

    public void setMacLimit(int macLimit) {
        this.macLimit = macLimit;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public int getConfigIfSpeed() {
        return configIfSpeed;
    }

    public void setConfigIfSpeed(int configIfSpeed) {
        this.configIfSpeed = configIfSpeed;
    }
}
