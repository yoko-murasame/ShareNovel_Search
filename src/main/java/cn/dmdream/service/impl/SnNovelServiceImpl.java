package cn.dmdream.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.HighlightOptions;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleHighlightQuery;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.HighlightEntry.Highlight;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.stereotype.Service;

import cn.dmdream.dao.SnNovelMapper;
import cn.dmdream.entity.SnNovel;
import cn.dmdream.service.SnNovelService;
import cn.dmdream.vo.PageModel;

@Service
public class SnNovelServiceImpl implements SnNovelService {

	@Autowired
	private SnNovelMapper novelMapper;
	@Autowired
	private SolrTemplate solrTemplate;
	
	
	public List<SnNovel> findAll() {
		return novelMapper.findAll();
	}


	@Override
	public List<SnNovel> findByCheck(Integer checkId) {
		return novelMapper.findByCheck(checkId);
	}


	@Override
	public boolean insertSolrAll() {
		
		try {
			List<SnNovel> list = novelMapper.findAll();
			solrTemplate.saveBeans("novel", list);
			solrTemplate.commit("novel");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public boolean insertSolrAllByCheck(Integer checkId) {
		try {
			List<SnNovel> list = novelMapper.findByCheck(checkId);
			solrTemplate.saveBeans("novel", list);
			solrTemplate.commit("novel");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}


	@Override
	public boolean deleteSolrAll() {
		try {
		    // 删除所有
		    Query query = new SimpleQuery("*:*");
		    solrTemplate.delete("novel", query);
		    // 根据id删除索引
		    //solrTemplate.deleteById(100000000000L + "");
		    solrTemplate.commit("novel");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}


	/**
	 * 分页查找所有,并且高亮显示
	 */
	public PageModel<SnNovel> findByKeywordByPage(String keyword, int pageSize, int page) {

		if(keyword == null || keyword.equals("")) return null;
	    // 创建solrQuery对象,封装条件
	    SimpleHighlightQuery query = new SimpleHighlightQuery();
	    //条件查询
	    Criteria criteria = new Criteria("novel_keywords").is(keyword);
	    //将条件添加到查询对象
	    query.addCriteria(criteria);
	    
	    //创建高亮对象,添加高亮操作
	    HighlightOptions highlightOptions = new HighlightOptions();
	    highlightOptions.addField("novel_title");
	    highlightOptions.addField("novel_Author");
	    highlightOptions.addField("novel_summary");
	    highlightOptions.setSimplePrefix("<font color='red'>");
	    highlightOptions.setSimplePostfix("</font>");
	    //启用多字段高亮
	    highlightOptions.addHighlightParameter("hl.preserveMulti", "true");
	    
	    //设置高亮查询
	    query.setHighlightOptions(highlightOptions);
	    
	    // 设置分页条件
	    // 设置分页查询起始位置
	    query.setOffset((long) ((page-1) * pageSize));
	    // 设置分页查询每页显示条数
	    query.setRows(pageSize);

	    // 执行查询
	   HighlightPage<SnNovel> highlightPage = solrTemplate.queryForHighlightPage("novel", query, SnNovel.class);

	    // 获取高亮分页记录
	    List<SnNovel> list = highlightPage.getContent();
	    
	    // 获取总记录数
	    long totalElements = highlightPage.getTotalElements();
	    
	    //循环搜索小说集合,获取高亮
	    for (SnNovel novel : list) {
	    	//获取高亮
			List<Highlight> highlights = highlightPage.getHighlights(novel);
			
			//判断高亮是否存在
			if(highlights != null  && highlights.size() > 0){
				//多个域字段,开始遍历
				for (Highlight highlight : highlights) {
					//获取高亮的域字段名字
					String fieldName = highlight.getField().getName();
					//获取高亮值[{}]
					List<String> snipplets = highlight.getSnipplets();
					//设置高亮字段
					if(fieldName.equals("novel_title")){
						novel.setNovelTitle(snipplets.get(0));
					}else if(fieldName.equals("novel_Author")){
						novel.setNovelAuthor(snipplets.get(0));
					}else if(fieldName.equals("novel_summary")){
						novel.setNovelSummary(snipplets.get(0));
					}
				}
			}
		}
		
	    PageModel<SnNovel> pageModel = new PageModel<SnNovel>();
	    PageModel.wrapPageModel(page, pageSize, (int) totalElements, highlightPage.getContent(), pageModel);
	    
		return pageModel;
		
	}
	
}
