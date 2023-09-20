package com.chenmasoft.kingdeetofeishu.dao.entity;

public class CheckList {
    private String materialNumber;
    private String materialName;
    private String specification;
    private String checkDate;
    private String orgNumber;
    private String isPass;
    private String unitNumber;
    private String bagNumber;
    private String bagNote;
    private String billTypeNumber;
    private String supplierNumber;
    private String auxPropId;
    private String lotNumber;
    private String extAuxUnitNumber;
    private String qty6;
    private String baseUnitNumber;
    private String actReceiveQty;
    private String sourceBillNo;
    private String checkerNumber;
    private String fid;
    private String receiveFid;
    private String receiveFEntryId;
    private CheckListArray[] checkListArray;

    public String getAuxPropId() {
        return auxPropId;
    }

    public void setAuxPropId(String auxPropId) {
        this.auxPropId = auxPropId;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getExtAuxUnitNumber() {
        return extAuxUnitNumber;
    }

    public void setExtAuxUnitNumber(String extAuxUnitNumber) {
        this.extAuxUnitNumber = extAuxUnitNumber;
    }

    public String getQty6() {
        return qty6;
    }

    public void setQty6(String qty6) {
        this.qty6 = qty6;
    }

    public String getBaseUnitNumber() {
        return baseUnitNumber;
    }

    public void setBaseUnitNumber(String baseUnitNumber) {
        this.baseUnitNumber = baseUnitNumber;
    }

    public String getActReceiveQty() {
        return actReceiveQty;
    }

    public void setActReceiveQty(String actReceiveQty) {
        this.actReceiveQty = actReceiveQty;
    }

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public String getOrgNumber() {
        return orgNumber;
    }

    public void setOrgNumber(String orgNumber) {
        this.orgNumber = orgNumber;
    }


    public CheckListArray[] getCheckListArray() {
        return checkListArray;
    }

    public void setCheckListArray(CheckListArray[] checkListArray) {
        this.checkListArray = checkListArray;
    }

    public String getIsPass() {
        return isPass;
    }

    public void setIsPass(String isPass) {
        this.isPass = isPass;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public String getBagNumber() {
        return bagNumber;
    }

    public void setBagNumber(String bagNumber) {
        this.bagNumber = bagNumber;
    }

    public String getBagNote() {
        return bagNote;
    }

    public void setBagNote(String bagNote) {
        this.bagNote = bagNote;
    }


    public String getBillTypeNumber() {
        return billTypeNumber;
    }

    public void setBillTypeNumber(String billTypeNumber) {
        this.billTypeNumber = billTypeNumber;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getSupplierNumber() {
        return supplierNumber;
    }

    public void setSupplierNumber(String supplierNumber) {
        this.supplierNumber = supplierNumber;
    }

    public String getSourceBillNo() {
        return sourceBillNo;
    }

    public void setSourceBillNo(String sourceBillNo) {
        this.sourceBillNo = sourceBillNo;
    }

    public String getCheckerNumber() {
        return checkerNumber;
    }

    public void setCheckerNumber(String checkerNumber) {
        this.checkerNumber = checkerNumber;
    }


    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getReceiveFid() {
        return receiveFid;
    }

    public void setReceiveFid(String receiveFid) {
        this.receiveFid = receiveFid;
    }

    public String getReceiveFEntryId() {
        return receiveFEntryId;
    }

    public void setReceiveFEntryId(String receiveFEntryId) {
        this.receiveFEntryId = receiveFEntryId;
    }
}
