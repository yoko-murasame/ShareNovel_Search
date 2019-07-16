package cn.dmdream.entity;

import java.io.Serializable;
import java.util.Map;

import org.apache.solr.client.solrj.beans.Field;

public class SnNovel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Field("id")
	private String id;//True	小说主键id,唯一
	@Field("novel_title")
	private String novelTitle;//True	小说名称
	@Field("novel_Author")
	private String novelAuthor;//True	作者名称
	private String novelUpdatetime;//小说最后更新时间，数据库自动生成
	private Integer novelIsEnd;//是否完结 0:未完结 1:已完结
	@Field("novel_summary")
	private String novelSummary;//小说简介
	private Map<String,String> novelDownloadurl;//False	下载地址{'站内地址1':'url...','站外地址1':'url..'}
	private Integer novelCheck;//False	审核结果 0:为审核 1:通过 2:未通过
	@Field("novel_cover")
	private String novelCover;//True	小说封面
	public SnNovel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SnNovel(String id, String novelTitle, String novelAuthor, String novelUpdatetime, Integer novelIsEnd,
			String novelSummary, Map<String, String> novelDownloadurl, Integer novelCheck, String novelCover) {
		super();
		this.id = id;
		this.novelTitle = novelTitle;
		this.novelAuthor = novelAuthor;
		this.novelUpdatetime = novelUpdatetime;
		this.novelIsEnd = novelIsEnd;
		this.novelSummary = novelSummary;
		this.novelDownloadurl = novelDownloadurl;
		this.novelCheck = novelCheck;
		this.novelCover = novelCover;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNovelTitle() {
		return novelTitle;
	}
	public void setNovelTitle(String novelTitle) {
		this.novelTitle = novelTitle;
	}
	public String getNovelAuthor() {
		return novelAuthor;
	}
	public void setNovelAuthor(String novelAuthor) {
		this.novelAuthor = novelAuthor;
	}
	public String getNovelUpdatetime() {
		return novelUpdatetime;
	}
	public void setNovelUpdatetime(String novelUpdatetime) {
		this.novelUpdatetime = novelUpdatetime;
	}
	public Integer getNovelIsEnd() {
		return novelIsEnd;
	}
	public void setNovelIsEnd(Integer novelIsEnd) {
		this.novelIsEnd = novelIsEnd;
	}
	public String getNovelSummary() {
		return novelSummary;
	}
	public void setNovelSummary(String novelSummary) {
		this.novelSummary = novelSummary;
	}
	public Map<String, String> getNovelDownloadurl() {
		return novelDownloadurl;
	}
	public void setNovelDownloadurl(Map<String, String> novelDownloadurl) {
		this.novelDownloadurl = novelDownloadurl;
	}
	public Integer getNovelCheck() {
		return novelCheck;
	}
	public void setNovelCheck(Integer novelCheck) {
		this.novelCheck = novelCheck;
	}
	public String getNovelCover() {
		return novelCover;
	}
	public void setNovelCover(String novelCover) {
		this.novelCover = novelCover;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "SnNovel [id=" + id + ", novelTitle=" + novelTitle + ", novelAuthor=" + novelAuthor
				+ ", novelUpdatetime=" + novelUpdatetime + ", novelIsEnd=" + novelIsEnd + ", novelSummary="
				+ novelSummary + ", novelDownloadurl=" + novelDownloadurl + ", novelCheck=" + novelCheck
				+ ", novelCover=" + novelCover + "]";
	}
	
}
