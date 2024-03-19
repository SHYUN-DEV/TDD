package io.hhplus.tdd.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.hhplus.tdd.dto.PointHistoryResDTO;
import io.hhplus.tdd.dto.UserPointResDTO;
import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.database.UserPointTable;
import io.hhplus.tdd.point.PointHistory;
import io.hhplus.tdd.point.UserPoint;


@Service
public class PointService {
	
	private final UserPointTable userPointTable;
	private final PointHistoryTable pointHistoryTable;

	@Autowired
	public PointService(UserPointTable userPointTable, PointHistoryTable pointHistoryTable) {
		this.userPointTable = userPointTable;
		this.pointHistoryTable = pointHistoryTable;
	}
    
	
	//유저 포인트 정보 조회
    public UserPointResDTO getUserPoint(Long userId) {
        
        	UserPoint userPoint = null;
			
        	try {
				userPoint = userPointTable.selectById(userId);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
        	return UserPointResDTO.of(userPoint);
    }

    //유저 포인트 이력조회
	public List<PointHistoryResDTO> getUserPointHistories(Long userId) {
		List<PointHistory> userPointHistories = pointHistoryTable.selectAllByUserId(userId);
		List<PointHistoryResDTO> pointHistoryResDTOs = new ArrayList<>();
		    
		    for (PointHistory pointHistory : userPointHistories) {
		        PointHistoryResDTO userPointHistoryResDTO = PointHistoryResDTO.of(pointHistory);
		        pointHistoryResDTOs.add(userPointHistoryResDTO);
		    }
		    
		    return pointHistoryResDTOs;
	}

	//유저 포인트 사용
 	public UserPointResDTO useUserPoint(Long userId, Long amount) {
		
		UserPoint userPoint = null;
		
		try {
			userPoint = userPointTable.insertOrUpdate(userId, amount);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return UserPointResDTO.of(userPoint);
	}
	
  
    
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    
    //유저 포인트 이력 조회
//	public List<PointHistory> getUserPointHistories(Long id) {
//		return pointRepository.getUserPointHistories(userId);
//		return null;
//	}
    
    
//    
//    // 포인트 이력 조회
//    public List<PointHistory> getPointHistories(Long userId) {
//        return pointRepository.findAllByUserId(userId);
//    }
//
//    // 포인트 충전 기능
//    public UserPoint chargePoint(Long userId, Long amount) {
//        // 포인트 이력 추가
//        PointHistory pointHistory = new PointHistory(null, userId, TransactionType.CHARGE, amount, System.currentTimeMillis());
//        pointRepository.save(pointHistory);
//        return null; 
//
//    }
}

