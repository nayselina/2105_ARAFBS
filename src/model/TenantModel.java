package model;

public class TenantModel {
    private int tenantID;
    private String tenantName;
    private String unitCode;

    public TenantModel(int tenantID, String tenantName, String unitCode) {
        this.tenantID = tenantID;
        this.tenantName = tenantName;
        this.unitCode = unitCode;
    }

    public int getTenantID() {
        return tenantID;
    }

    public String getTenantName() {
        return tenantName;
    }

    public String getUnitCode() {
        return unitCode;
    }
}
