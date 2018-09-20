package com.dzg.dao;

import com.dzg.beans.PageQuery;
import com.dzg.domain.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser findByKeyword(@Param(value = "keyword") String keyword);

    int countByMail(@Param("mail") String mail,@Param("id") Integer id);

    int countByTelephone(@Param("telephone") String telephone,@Param("id") Integer id);
    int countByDeptId(@Param("deptId") int id);

    List<SysUser> getPageByDeptId(@Param("deptId") int deptId, @Param("page") PageQuery page);
    List<SysUser> getByIdList(@Param("idList") List<Integer> IdList);

    List<SysUser> getAll();

}