package com.cos.photogramstart.repository;

import com.cos.photogramstart.domain.subscribe.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubScribeRepository extends JpaRepository<Subscribe, Long> {

    @Modifying//insert,delete,update를 네이티브 쿼리로 작성하려면 @Modifying 어노테이션 필요
    @Query(value = "INSERT INTO subscribe(fromUserId, toUserId, createDate) VALUES(:fromUserId,:toUserId,now())", nativeQuery = true)
    void mSubscribe(Long fromUserId, Long toUserId); //(1(변경된 행의 개수가 리턴됨), -1

    @Modifying
    @Query(value = "DELETE FROM subscribe WHERE fromUserId =:fromUserId AND toUserId =:toUserId", nativeQuery = true)
    void mUnSubscribe(Long fromUserId, Long toUserId); //1 ,-1
}
