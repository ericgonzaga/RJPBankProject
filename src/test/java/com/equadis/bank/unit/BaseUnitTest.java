package com.equadis.bank.unit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

@TestInstance(Lifecycle.PER_CLASS)
public class BaseUnitTest {

    @Mock
    private ModelMapper mapper;

    @BeforeAll
    private void setup() {
        MockitoAnnotations.openMocks(this);
        Mockito.when(this.mapper).thenReturn(this.modelMapper());
    }

    private ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
