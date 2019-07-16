package cn.dmdream.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.dmdream.entity.SnNovel;

@Repository
public interface SnNovelMapper {

	List<SnNovel> findAll();
	
	List<SnNovel> findByCheck(@Param(value = "checkId") Integer checkId);
}
