package com.cloud.morsechat.dao;

import com.cloud.morsechat.entity.model.MosFriend;
import com.cloud.morsechat.entity.model.MosUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.11.27
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
@Repository
public interface UserRepository extends JpaRepository<MosUser,Long>, JpaSpecificationExecutor<MosUser> {
    MosUser findByUsername(String username);
    MosUser findByHash(String hash);
    List<MosUser> findAllByNicknameLike(String nickname);
}
