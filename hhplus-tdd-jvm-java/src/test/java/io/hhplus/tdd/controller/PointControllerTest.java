package io.hhplus.tdd.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;



import io.hhplus.tdd.dto.PointHistoryResDTO;
import io.hhplus.tdd.dto.UserPointResDTO;
import io.hhplus.tdd.service.PointService;
import io.hhplus.tdd.point.PointController;
import io.hhplus.tdd.point.TransactionType;
import io.hhplus.tdd.point.UserPoint;


@SpringBootTest
@AutoConfigureMockMvc
public class PointControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PointService pointService;
    
    @InjectMocks
    private PointController pointController;

    @BeforeEach
    void setUp() {
        // 테스트 전에 필요한 초기화 작업 수행
    }

//    // 유저의 포인트 조회 테스트
//    @Test
//    @DisplayName(value = "유저 포인트 조회")
//    public void testGetUserPoint() throws Exception {
//        UserPointResDTO userPointResDTO = new UserPointResDTO(1L, 100L, System.currentTimeMillis());
//        when(pointService.getUserPoint(anyLong())).thenReturn(userPointResDTO);
//
//        mockMvc.perform(get("/point/{id}", 1L))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1L))
//                .andExpect(jsonPath("$.point").value(100L));
//    }
//
//    // 유저의 포인트 내역(충전/이용) 조회 테스트
//    @Test
//    @DisplayName(value = "유저 포인트내역 조회")
//    public void testGetUserPointHistories() throws Exception {
//        PointHistoryResDTO pointHistoryResDTO = new PointHistoryResDTO(1L, 100L, TransactionType.CHARGE, System.currentTimeMillis(), null);
//        when(pointService.getUserPointHistories(anyLong())).thenReturn(Collections.singletonList(pointHistoryResDTO));
//
//        mockMvc.perform(get("/point/{id}/histories", 1L))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].id").value(1L))
//                .andExpect(jsonPath("$[0].point").value(100L))
//                .andExpect(jsonPath("$[0].type").value(TransactionType.CHARGE.toString()))
//                .andExpect(jsonPath("$[0].amount").value(100L));
//    }

    // 포인트 충전 테스트
    @Test
    @DisplayName(value = "유저 충전 테스트")
    public void testChargeUserPoint() throws Exception {
        Long userId = 1L;
        Long amount = 150L;
        UserPoint expectedResult = new UserPoint(userId, 150L, System.currentTimeMillis()); // 예상 결과

        when(pointService.chargeUserPoint(userId, amount)).thenReturn(expectedResult);

        mockMvc.perform(patch("/point/{id}/charge", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(amount.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userId))
                .andExpect(jsonPath("$.point").value(expectedResult.point()));

        verify(pointService).chargeUserPoint(userId, amount);
    }

//    //포인트 사용 테스트
//    @Test
//    @DisplayName(value = "포인트 사용 테스트")
//    public void testUseUserPoint() throws Exception {
//        Long userId = 1L;
//        Long amount = 30L;
//        UserPoint expectedResult = new UserPoint(userId, 70L, System.currentTimeMillis()); // 예상 결과
//
//        when(pointService.useUserPoint(userId, amount)).thenReturn(expectedResult);
//
//        mockMvc.perform(patch("/point/{id}/use", userId)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(amount.toString()))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(userId))
//                .andExpect(jsonPath("$.point").value(expectedResult.point()));
//
//        // Verify that the service method was called with the correct arguments
//        verify(pointService).useUserPoint(userId, amount);
//    }
}



