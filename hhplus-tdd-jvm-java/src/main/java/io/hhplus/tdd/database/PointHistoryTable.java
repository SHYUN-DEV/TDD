package io.hhplus.tdd.database;

import io.hhplus.tdd.point.PointHistory;
import io.hhplus.tdd.point.TransactionType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 해당 Table 클래스는 변경하지 않고 공개된 API 만을 사용해 데이터를 제어합니다.
 */
@Component
public class PointHistoryTable {
	
	//포인트 이력 저장
    private List<PointHistory> table = new ArrayList<>();

    //포인트 이력관리를 위한 아이디
    private Long cursor = 1L;

    //포인트 이력 추가
    public PointHistory insert(
            					Long id,
            					Long amount,
            					TransactionType transactionType,
            					Long updateMillis) throws InterruptedException {
        
    	//쓰레드 대기
    	Thread.sleep(Long.parseLong(String.valueOf(Math.random())) * 300L);

        PointHistory history = new PointHistory(cursor++, id, transactionType, amount, updateMillis);
        table.add(history);

        return history;
    }

    
    //특정 아이디값의 포인트이력 가져오기 
    public List<PointHistory> selectAllByUserId(Long userId) {
        return table.stream()
                .filter(it -> it.userId().equals(userId))
                .toList();
    }
}
