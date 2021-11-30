package com.cloud.morsechat.service.rest;


import com.cloud.morsechat.entity.model.MosFriend;
import com.cloud.morsechat.vo.RestResponse;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.11.29
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
public interface FriendService {
    RestResponse<List<MosFriend>> list(Long uid, HttpSession sess);
}
