package io.hhplus.tdd.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.database.UserPointTable;
import io.hhplus.tdd.dto.PointHistoryResDTO;
import io.hhplus.tdd.dto.UserPointResDTO;
import io.hhplus.tdd.point.UserPoint;
import io.hhplus.tdd.point.PointHistory;
import io.hhplus.tdd.point.TransactionType;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class PointServiceTest {

    @Mock
    private UserPointTable userPointTable;

    @Mock
    private PointHistoryTable pointHistoryTable;

    @InjectMocks
    private PointService pointService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("유저 포인트 정보 조회 테스트")
    void testGetUserPoint() throws InterruptedException {
        
        Long userId = 1L;
        UserPoint userPoint = new UserPoint(userId, 100L, System.currentTimeMillis());
        when(userPointTable.selectById(userId)).thenReturn(userPoint);

        
        UserPointResDTO result = pointService.getUserPoint(userId);

        
        assertNotNull(result);
        assertEquals(userId, result.id());
        assertEquals(100L, result.point());
    }

    @Test
    @DisplayName("유저 포인트 이력 조회 테스트")
    void testGetUserPointHistories() {
       
        Long userId = 1L;
        PointHistory pointHistory = new PointHistory(1L, userId, TransactionType.CHARGE, 100L, System.currentTimeMillis());
        when(pointHistoryTable.selectAllByUserId(userId)).thenReturn(Collections.singletonList(pointHistory));

        
        List<PointHistoryResDTO> result = pointService.getUserPointHistories(userId);

        
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(userId, result.get(0).userId());
        assertEquals(100L, result.get(0).amount());
    }

    @Test
    @DisplayName("유저 포인트 충전 테스트")
    void testChargeUserPoint() throws InterruptedException {
        // Given
        Long userId = 1L;
        Long amount = 50L;
        UserPoint userPoint = new UserPoint(userId, 100L, System.currentTimeMillis());
        when(userPointTable.selectById(userId)).thenReturn(userPoint);

        // When
        UserPoint result = pointService.chargeUserPoint(userId, amount);

        // Then
        assertNotNull(result);
        assertEquals(userId, result.id());
        assertEquals(150L, result.point());
        verify(pointHistoryTable).insert(eq(userId), eq(150L), eq(TransactionType.CHARGE), anyLong());
        verify(userPointTable).insertOrUpdate(eq(userId), eq(150L));
    }

    @Test
    @DisplayName("유저 포인트 사용 테스트")
    void testUseUserPoint() throws InterruptedException {
        
        Long userId = 1L;
        Long amount = 50L;
        UserPoint userPoint = new UserPoint(userId, 100L, System.currentTimeMillis());
        when(userPointTable.selectById(userId)).thenReturn(userPoint);

        
        UserPoint result = pointService.useUserPoint(userId, amount);

        
        assertNotNull(result);
        assertEquals(userId, result.id());
        assertEquals(50L, result.point());
        verify(pointHistoryTable).insert(eq(userId), eq(50L), eq(TransactionType.USE), anyLong());
        verify(userPointTable).insertOrUpdate(eq(userId), eq(50L));
    }
}

