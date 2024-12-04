package model;

public class Tenant {
 private int tenantID;
 private String tenantName; // Full name

 public Tenant(int tenantID, String firstName, String lastName) {
     this.tenantID = tenantID;
     this.tenantName = firstName + " " + lastName; // Concatenate firstName and lastName
 }

 public int getTenantID() {
     return tenantID;
 }

 public String getTenantName() {
     return tenantName;
 }
}
