package mititelu.laura.sfg.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    @Lob
    private String directions;

    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;
    @Lob
     private Byte[] image;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    @ToString.Exclude
    //mappedBy + specify the property in the child class
    //this recipe will get stored on the child or the set of ingredients on each object of Ingredient is going to be a property called recipe
    //recipe owns the relationship
    private Set<Ingredient> ingredients = new HashSet<>();

     @OneToOne(cascade = CascadeType.ALL)
     //if we delete a recipe, we want the note deleted
     private Notes notes;


     @ManyToMany
     @JoinTable(name="recipe_category",
             joinColumns = @JoinColumn(name ="recipe_id"),
             inverseJoinColumns = @JoinColumn(name = "category_id"))
     @ToString.Exclude
     private Set<Category> categories = new HashSet<>();


    public void setNotes(Notes notes) {
        this.notes = notes;
        notes.setRecipe(this);
    }

    public Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Recipe recipe = (Recipe) o;
        return id != null && Objects.equals(id, recipe.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
