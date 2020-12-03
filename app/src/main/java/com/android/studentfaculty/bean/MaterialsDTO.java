package com.android.studentfaculty.bean;

public class MaterialsDTO {
    private int materials_id;
    private String material_name;
    private String material_location;
    private String lang;

    public int getMaterials_id() {
        return materials_id;
    }

    public void setMaterials_id(int materials_id) {
        this.materials_id = materials_id;
    }

    public String getMaterial_name() {
        return material_name;
    }

    public void setMaterial_name(String material_name) {
        this.material_name = material_name;
    }

    public String getMaterial_location() {
        return material_location;
    }

    public void setMaterial_location(String material_location) {
        this.material_location = material_location;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
