package com.example.demo.web.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.LoginUser;

/**
 * LoginUserMapper
 */
@Mapper
public interface LoginUserMapper {

    /**
     * 登録
     * @param loginUser LoginUser
     * @return 件数
     */
    int insertLoginUser(LoginUser loginUser);

    /**
     * 1件削除
     * @param userName String
     * @return 件数
     */
    int delete(String userName);

    /**
     * 1件検索
     * @param userName String
     * @return Item
     */
    LoginUser findByUserName(String userName);

    /**
     * 1件更新
     * @param loginUser LoginUser
     * @return 件数
     */
    int updateByUserName(LoginUser loginUser);
}
