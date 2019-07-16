package cn.dmdream.search.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.dmdream.entity.SnNovel;
import cn.dmdream.service.SnNovelService;
import cn.dmdream.vo.JsonMsg;
import cn.dmdream.vo.PageModel;

@RestController
@CrossOrigin
public class NovelSearchController {

	@Autowired
	private SnNovelService novelService;

	@RequestMapping("/hello")
	public String showHello() {
		return "hello ,这里是星象小说,热部署测试1212";
	}

	@RequestMapping("/findAll")
	public List<SnNovel> showAll() {
		return novelService.findAll();
	}

	@RequestMapping("/findByCheck/{checkId}")
	public List<SnNovel> findByCheckId(@PathVariable Integer checkId) {
		return novelService.findByCheck(checkId);
	}

	@RequestMapping("/insertSolrAll")
	public JsonMsg insertSolrAll() {
		boolean isok = novelService.insertSolrAll();
		JsonMsg jsonMsg= null;
		if(isok){
			jsonMsg = JsonMsg.makeSuccess("成功添加所有数据到solr", null);
		}else{
			jsonMsg = JsonMsg.makeFail("solr数据添加失败,请清空solr重试!", null);
		}
		return jsonMsg;
	}
	
	@RequestMapping("/insertSolrAllByCheck/{checkId}")
	public JsonMsg insertSolrAllByCheck(@PathVariable Integer checkId) {
		boolean isok = novelService.insertSolrAllByCheck(checkId);
		JsonMsg jsonMsg= null;
		if(isok){
			jsonMsg = JsonMsg.makeSuccess("成功添加状态值为:"+checkId+" 的所有数据到solr", null);
		}else{
			jsonMsg = JsonMsg.makeFail("solr数据添加失败,请清空solr重试!", null);
		}
		return jsonMsg;
	}

	@RequestMapping("/deleteSolrAll")
	public JsonMsg deleteSolrAll() {
		boolean isok = novelService.deleteSolrAll();
		JsonMsg jsonMsg= null;
		if(isok){
			jsonMsg = JsonMsg.makeSuccess("成功清空仓库", null);
		}else{
			jsonMsg = JsonMsg.makeFail("solr数据添加失败,请清空solr重试!", null);
		}
		return jsonMsg;
	}

	@RequestMapping("/findByKeywordByPage")
	public JsonMsg findByKeywordByPage(String keyword,String pageSize,String page) {
		System.out.println("接收到了参数" + keyword + " " + pageSize + " " + page);
		JsonMsg jsonMsg = null;
		PageModel<SnNovel> pageModel = novelService.findByKeywordByPage(keyword, Integer.parseInt(pageSize), Integer.parseInt(page));
		if(pageModel==null){
			jsonMsg = JsonMsg.makeFail("无查询结果", null);
		}else{
			jsonMsg = JsonMsg.makeSuccess("查询成功", pageModel);
		}
		return jsonMsg;
	}
}
