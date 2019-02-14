/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author nikolaos
 */
public class Record {

    private boolean selected;
    private Integer id;
    private String name;
    private String phone;
    private Character gender;

    public Record() {
    }

    public Record(boolean selected, Integer id, String name, String phone, Character gender) {
        this.selected = selected;
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.gender = gender;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Record{" + "selected=" + selected + ", id=" + id + ", name=" + name + ", phone=" + phone + ", gender=" + gender + '}';
    }

}
