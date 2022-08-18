package com.tests;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

/**
 * @author Sreej
 */
public class AssertJDemo {

    @Test
    public void AssertJTest(){

        String temp="Hello World";
        Assertions.assertThat(temp).hasSize(11)
                .as("Checking Size").contains("Hello1")
                .as("Checking Content").doesNotContain("Sree")
                .isNotNull()
                .containsWhitespaces()
                .isNotBlank()
                .isNotEmpty();
    }
}
