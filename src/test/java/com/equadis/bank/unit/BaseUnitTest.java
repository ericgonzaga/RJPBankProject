package com.equadis.bank.unit;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

@TestInstance(Lifecycle.PER_CLASS)
public class BaseUnitTest {

    @Mock
    private ModelMapper mapper;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
