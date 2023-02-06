package mititelu.laura.sfg.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryTest {

    Category category;

    @BeforeEach
    public void setUp(){
        category = new Category();
    }

    @Test
    void getId() {
        Long idValue = 4l;
        Long expectedId = 4l;

        category.setId(idValue);

        assertEquals(expectedId, category.getId());

    }



}