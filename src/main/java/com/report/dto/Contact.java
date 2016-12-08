package com.report.dto;

/**
 * DTO to fetch JSON list and make processes on.
 */
public class Contact implements Comparable<Contact> {


    private String name;
    private String lastName;
    private String phone;



    @Override
    public int compareTo(Contact otherContact) {

        int result = this.name.compareTo(otherContact.getName());
        if(result == 0)
            return this.lastName.compareTo(otherContact.getLastName());

        return result;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;                                                                                      // Early-Ref check to save comparison.
        if (!(o instanceof Contact)) return false;

        Contact contact = (Contact) o;

        if (!getName().equals(contact.getName())) return false;
        return getLastName().equals(contact.getLastName());
    }


    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getLastName().hashCode();
        return result;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
