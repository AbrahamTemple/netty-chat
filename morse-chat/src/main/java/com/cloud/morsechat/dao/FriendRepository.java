package com.cloud.morsechat.dao;

import com.cloud.morsechat.entity.model.MosFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.11.27
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
@Repository
public interface FriendRepository extends JpaRepository<MosFriend,Long>, JpaSpecificationExecutor<MosFriend> {
}
