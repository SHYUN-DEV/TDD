package io.hhplus.tdd.point;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.hhplus.tdd.dto.PointHistoryResDTO;
import io.hhplus.tdd.dto.UserPointResDTO;
import io.hhplus.tdd.service.PointService;
import java.util.List;

@Slf4j
@RequestMapping("/point")
@RestController
public class PointController {
	 private final PointService pointService;

	 @Autowired
	 public PointController(PointService pointService) {
	     this.pointService = pointService;
	 }
	
	
    /**
     * TODO - 특정 유저의 포인트를 조회하는 기능을 작성해주세요.
     * @throws InterruptedException 
     */
	
	 @GetMapping("{id}")
	 public UserPointResDTO point(@PathVariable Long id) throws InterruptedException {
    	
    	return pointService.getUserPoint(id);
        
	 }

    /**
     * TODO - 특정 유저의 포인트 충전/이용 내역을 조회하는 기능을 작성해주세요.
     */
    @GetMapping("{id}/histories")
    public List<PointHistoryResDTO> history(
    								@PathVariable Long id) {
       
        return pointService.getUserPointHistories(id);
    }

    
    /**
     * TODO - 특정 유저의 포인트를 충전하는 기능을 작성해주세요.
     * @throws InterruptedException 
     */
    @PatchMapping("{id}/charge")
    public UserPoint charge(
    						@PathVariable("id") Long id,
    						@RequestBody Long amount) throws InterruptedException {
        
    	return pointService.chargeUserPoint(id, amount);
    }

    
    /**
     * TODO - 특정 유저의 포인트를 사용하는 기능을 작성해주세요.
     * @throws InterruptedException 
     */
    @PatchMapping("{id}/use")
    public UserPoint use(
    					@PathVariable Long id,
    					@RequestBody Long amount) throws InterruptedException {
    	
    	return pointService.useUserPoint(id, amount);
    }
}
