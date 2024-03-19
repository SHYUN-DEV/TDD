//package io.hhplus.tdd.repository;
//
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import io.hhplus.tdd.database.PointHistoryTable;
//import io.hhplus.tdd.database.UserPointTable;
//import io.hhplus.tdd.dto.UserPointResDTO;
//import io.hhplus.tdd.point.UserPoint;
//
//
//
//@Repository
//public interface PointRepository extends JpaRepository{
//   
//	
//	
//	
//	
//	
//	
//	
//	
//	private final UserPointTable userPointTable;
//    private final PointHistoryTable pointHistoryTable;
//
//    public PointRepository(UserPointTable userPointTable, PointHistoryTable pointHistoryTable) {
//        this.userPointTable = userPointTable;
//        this.pointHistoryTable = pointHistoryTable;
//    }
//
//   
//	public Object findById(Long userId) {
//		return
//	}
//	
//	
//    // 특정 사용자의 포인트 조회
//    public default UserPointResDTO getUserPoint(Long userId) throws InterruptedException {
//        return userPointTable.selectById(userId);
//    }
//
//	
////
////	public UserPoint findById(Long userId) {
////		// TODO Auto-generated method stub
////		return null;
////	}
////
////
////	public List<PointHistory> findAllByUserId(Long userId) {
////		// TODO Auto-generated method stub
////		return null;
////	}
////
////
////	public void save(PointHistory pointHistory) {
////		// TODO Auto-generated method stub
////		
////	}
//
//   
//}
//
//
