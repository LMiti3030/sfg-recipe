package mititelu.laura.sfg.repositories;

import mititelu.laura.sfg.domain.UnitOfMeasure;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;
    //Spring injects a valid bean here

    @Test
    void should_ReturnDescription_WhenInputTeaspoon() {
        //given
        String expected = "Teaspoon";
        String actual ="";

        //when
        Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        if(uomOptional.isPresent()){
            actual = uomOptional.get().getDescription();
        }

        //then
        assertEquals(expected, actual);

    }

    @Test
    void  should_ReturnDescription_WhenInputCup() {
        //given
        String expected = "Cup";
        String actual ="";

        //when
        Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByDescription("Cup");
        if(uomOptional.isPresent()){
            actual = uomOptional.get().getDescription();
        }

        //then
        assertEquals(expected, actual);

    }
}