package io.hhplus.tdd.database;

import io.hhplus.tdd.point.UserPoint;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 해당 Table 클래스는 변경하지 않고 공개된 API 만을 사용해 데이터를 제어합니다.
 */
@Component
public class UserPointTable {
	//유저의 포인트 정보 저장 map	
    private Map<Long, UserPoint> table = new HashMap<>();

    // 특정 유저의 포인트 조회
    public UserPoint selectById(Long id) {
        try {
            // 쓰레드 대기
            Thread.sleep((long) (Math.random() * 200L));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            
        }

        UserPoint userPoint = table.getOrDefault(id, new UserPoint(id, 0L, System.currentTimeMillis()));
        return userPoint;
    }


    //
    public UserPoint insertOrUpdate(Long id, Long amount) throws InterruptedException {
    	//쓰레드 대기
    	 Thread.sleep((long) (Math.random() * 300L));

        UserPoint userPoint = new UserPoint(id, amount, System.currentTimeMillis());
        
        //포인트 사용 업데이트
        table.put(id, userPoint);

        return userPoint;
    }
}
