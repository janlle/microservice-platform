package com.leone.tx.demo.mapper;

import com.leone.tx.demo.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-15
 **/
@Mapper
public interface AccountMapper {

    int deleteByPrimaryKey(Long accountId);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Long accountId);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

//    @Update("update t_account set money = money + #{total} where account = #{account} and money > #{total}")
    @Update("update t_account set money = money + #{total} where account = #{account}")
    int updateMoney(@Param("total") Integer total, @Param("account") String account);

    @Update("update t_account set money = money + #{money}, version = version + 1 where account_id = #{accountId} and version = #{version}")
    int updateMoneyVersion(Account account);
}