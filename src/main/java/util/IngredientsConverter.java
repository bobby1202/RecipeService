package util;


import com.abnamro.recipe.exception.RecipeServiceException;
import com.abnamro.recipe.model.Ingredients;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class IngredientsConverter implements AttributeConverter<Ingredients, String> {

    private final ObjectMapper objectMapper;

    public IngredientsConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String convertToDatabaseColumn(Ingredients ingredients) {
        try {
            return objectMapper.writeValueAsString(ingredients);
        } catch (JsonProcessingException e) {
            throw new RecipeServiceException("Error in convertToDatabaseColumn", e.getCause());
        }
    }

    @Override
    public Ingredients convertToEntityAttribute(String ingredientsValue) {
        try {
            return objectMapper.readValue(ingredientsValue, Ingredients.class);
        } catch (JsonProcessingException e) {
            throw new RecipeServiceException("Error in convertToEntityAttribute", e.getCause());
        }
    }
}
