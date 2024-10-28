package org.escuelaing.eci.repository.preference;

public class PreferenceDto {
    
    private final String foodType;
    private final Integer value;

    public PreferenceDto() {

        this.foodType = "";
        this.value = 0;
    }

    public PreferenceDto(String foodType, Integer value) {
        this.foodType = foodType;
        this.value = value;
    }



    public String getFoodType() {
        return foodType;
    }

    public Integer getValue() {
        return value;
    }
}
