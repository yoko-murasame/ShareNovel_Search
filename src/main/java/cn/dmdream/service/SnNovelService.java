package cn.dmdream.service;

import java.util.List;

import cn.dmdream.entity.SnNovel;
import cn.dmdream.vo.PageModel;

public interface SnNovelService {
	List<SnNovel> findAll();
	
	List<SnNovel> findByCheck(Integer checkId);
	
	boolean insertSolrAll();
	
	boolean insertSolrAllByCheck(Integer checkId);
	
	boolean deleteSolrAll();
	
	PageModel<SnNovel> findByKeywordByPage(String keyword , int pageSize, int page);
}
