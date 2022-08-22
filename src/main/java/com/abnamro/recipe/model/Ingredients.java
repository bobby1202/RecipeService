package com.abnamro.recipe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ingredients {
    private String include;
    private String exclude;

    @Override
    public String toString()
    {
        return "{\"include\":\""+include+"\", \"exclude\":\""+exclude+"\"}";
    }
}
