/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.noreply.studentApp;

/**
 *
 * @author Ajay
 */
public class Student {

    private String firstName="";
    private String lastName="";
    private String address="";
    private String gender="";
    private String pincode="";
    private String email="";
    private String dob="";
    private String contactNo="";

    public Student() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    @Override
    public String toString() {
        return "Student{" + "firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", gender=" + gender + ", pincode=" + pincode + ", email=" + email + ", dob=" + dob + ", contactNo=" + contactNo + '}';
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}
