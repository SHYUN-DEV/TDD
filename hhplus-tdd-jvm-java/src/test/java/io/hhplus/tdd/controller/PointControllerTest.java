package io.hhplus.tdd.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import io.hhplus.tdd.dto.PointHistoryResDTO;
import io.hhplus.tdd.dto.UserPointResDTO;
import io.hhplus.tdd.service.PointService;
import io.hhplus.tdd.point.TransactionType;


@SpringBootTest
@AutoConfigureMockMvc
public class PointControllerTest {

    @Autowired
    private MockMvc mockMvc;

    //@MockBean을 사용하여 PointService를 mock 객체로 주입
    @MockBean
    private PointService pointService;

    
    // 유저의 포인트 조회 컨트롤러 테스트 
    @Test
    public void testGetUserPoint() throws Exception {
       
    	UserPointResDTO userPointResDTO = new UserPointResDTO(1L, 100L, System.currentTimeMillis());
        when(pointService.getUserPoint(anyLong())).thenReturn(userPointResDTO);

      
        mockMvc.perform(get("/point/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.point").value(100L));
    }

    // 유저의 포인트 내역(충전/이용) 조회 컨트롤러 테스트 
    @Test
    public void testGetUserPointHistories() throws Exception {
       
        PointHistoryResDTO pointHistoryResDTO = new PointHistoryResDTO(1L, 100L, TransactionType.CHARGE, System.currentTimeMillis(), null);
        when(pointService.getUserPointHistories(anyLong())).thenReturn(Collections.singletonList(pointHistoryResDTO));

      
        mockMvc.perform(get("/point/{id}/histories", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].point").value(100L))
                .andExpect(jsonPath("$[0].type").value(TransactionType.CHARGE.toString()))
    			.andExpect(jsonPath("$[0].amount").value(100L));
    }
}

