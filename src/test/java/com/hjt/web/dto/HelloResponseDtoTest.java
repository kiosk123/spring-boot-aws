package com.hjt.web.dto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HelloResponseDtoTest {
    
    @Test
    public void lombokFunctionTest() {
        
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        assertEquals(dto.getName(), name);
        assertEquals(dto.getAmount(), amount);
        
    }
}
