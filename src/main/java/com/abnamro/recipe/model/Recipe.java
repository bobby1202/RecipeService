package com.abnamro.recipe.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;
import util.IngredientsConverter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;


@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue
    private Long recipeId;

    @Enumerated(EnumType.STRING)
    private DishType dishType;

    private Integer noOfServings;

    @Convert(converter = IngredientsConverter.class)
    @ColumnTransformer(write = "LOWER(?)")
    private Ingredients ingredients;

    @Lob
    @ColumnTransformer(write = "LOWER(?)")
    private String recipeInstructions;

    @Override
    public String toString()
    {
        return "{\"recipeId\":"+recipeId+",\"dishType\":\""+dishType+"\",\"noOfServings\":"+noOfServings+",\"ingredients\":"+ingredients+",\"recipeInstructions\":\""+recipeInstructions+"\"}";
    }
}
