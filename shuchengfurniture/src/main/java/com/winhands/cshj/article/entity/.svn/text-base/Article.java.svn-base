package com.winhands.cshj.article.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "t_article", schema = "public")
public class Article implements java.io.Serializable{ 
	/**
	 * 文章表
	 */
	private static final long serialVersionUID = 3344472339477372865L;
	@Id
	@Column(name = "id")
	private String id;//文章id
	@Column(name = "author_id")
	private String author_id;//作者id
	@Column(name = "author_name")
	private String author_name;//作者姓名
	@Column(name = "industry_name")
	private String industry_name;//行业名称
	@Column(name = "title")
	private String title;//文章标题
	@Column(name = "file_type_id")
	private String file_type_id;//文章分类id
	@Column(name = "file_type")
	private String file_type;//文章分类
	@Column(name = "small_pic_path")
	private String small_pic_path;//缩略图路径
	@Column(name = "small_pic_save_name")
	private String small_pic_save_name;//缩略图保存名
	@Column(name = "small_pic_prior_name")
	private String small_pic_prior_name;//缩略图原名
	@Column(name = "create_user_id")
	private String create_user_id;//创建人id
	@Column(name = "create_date")
	private Date create_date;//创建时间
	@Column(name = "is_online")
	private String is_online;//是否上线
	@Column(name = "online_date")
	private String online_date;//上线日期
	@Column(name = "read_limit")
	private String  read_limit;//阅读权限（1：会员  0：非会员）
	@Column(name = "is_promotion")
	private String is_promotion;//是否推广
	@Column(name = "is_read")
	private String is_read;//是否已读
	@Column(name = "summary")
	private String summary;//简介
	@Column(name = "comment_num")
	private String comment_num;//评论数
	@Column(name = "collect_num")
	private String collect_num;//收藏数
	@Column(name = "is_collect")
	private String is_collect;//是否收藏
	@Column(name = "is_jx")
	private String is_jx;//是否为精选文章
	@Column(name = "is_love")
	private String  is_love;//是否喜爱
	@Column(name = "is_answer")
	private String is_answer;//是否已答题   0 否  1是
	@Column(name = "read_num")
	private String read_num;//阅读数量
	@Column(name = "love_num")
	private String love_num;//喜爱数量
	@Column(name = "key_words")
	private String key_words;//关键词
	@Column(name = "order_sc")
	private int order_sc;//收藏榜置顶排序号
	@Column(name = "order_new")
	private int order_new;//最新置顶排序号
	@Column(name = "order_col")
	private int order_col;//精选置顶排序号
	@Column(name = "order_read")
	private int order_read;//阅读置顶排序号
	@Column(name = "order_hot")
	private int order_hot;//判断是否推送    1不推送    2  未推送   3 已推送
	@Column(name = "online_type")
	private String online_type;//0 定时发布  1 立即发布
	@Column(name = "spare_1")
	private String spare_1;
	@Column(name = "spare_2")
	private String spare_2;
	@Column(name = "spare_3")
	private String spare_3;//用于保存压缩包原始名
	@Column(name = "jx_type")
	private String jx_type;// 精选类型   0：小学版     1：  大学版
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(String author_id) {
		this.author_id = author_id;
	}
	public String getAuthor_name() {
		return author_name;
	}
	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}
	public String getFile_type_id() {
		return file_type_id;
	}
	public void setFile_type_id(String file_type_id) {
		this.file_type_id = file_type_id;
	}
	public String getFile_type() {
		return file_type;
	}
	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}
	public String getSmall_pic_path() {
		return small_pic_path;
	}
	public void setSmall_pic_path(String small_pic_path) {
		this.small_pic_path = small_pic_path;
	}
	public String getSmall_pic_save_name() {
		return small_pic_save_name;
	}
	public void setSmall_pic_save_name(String small_pic_save_name) {
		this.small_pic_save_name = small_pic_save_name;
	}
	public String getSmall_pic_prior_name() {
		return small_pic_prior_name;
	}
	public void setSmall_pic_prior_name(String small_pic_prior_name) {
		this.small_pic_prior_name = small_pic_prior_name;
	}
	public String getCreate_user_id() {
		return create_user_id;
	}
	public void setCreate_user_id(String create_user_id) {
		this.create_user_id = create_user_id;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public String getIs_online() {
		return is_online;
	}
	public void setIs_online(String is_online) {
		this.is_online = is_online;
	}
	public String getRead_limit() {
		return read_limit;
	}
	public void setRead_limit(String read_limit) {
		this.read_limit = read_limit;
	}
	public String getIs_promotion() {
		return is_promotion;
	}
	public void setIs_promotion(String is_promotion) {
		this.is_promotion = is_promotion;
	}
	public String getSpare_1() {
		return spare_1;
	}
	public void setSpare_1(String spare_1) {
		this.spare_1 = spare_1;
	}
	public String getSpare_2() {
		return spare_2;
	}
	public void setSpare_2(String spare_2) {
		this.spare_2 = spare_2;
	}
	public String getSpare_3() {
		return spare_3;
	}
	public void setSpare_3(String spare_3) {
		this.spare_3 = spare_3;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getComment_num() {
		return comment_num;
	}
	public void setComment_num(String comment_num) {
		this.comment_num = comment_num;
	}
	public String getCollect_num() {
		return collect_num;
	}
	public void setCollect_num(String collect_num) {
		this.collect_num = collect_num;
	}
	public String getIs_collect() {
		return is_collect;
	}
	public void setIs_collect(String is_collect) {
		this.is_collect = is_collect;
	}
	public String getIs_love() {
		return is_love;
	}
	public void setIs_love(String is_love) {
		this.is_love = is_love;
	}
	public String getRead_num() {
		return read_num;
	}
	public void setRead_num(String read_num) {
		this.read_num = read_num;
	}
	public String getLove_num() {
		return love_num;
	}
	public void setLove_num(String love_num) {
		this.love_num = love_num;
	}
	public String getKey_words() {
		return key_words;
	}
	public void setKey_words(String key_words) {
		this.key_words = key_words;
	}
	public int getOrder_sc() {
		return order_sc;
	}
	public void setOrder_sc(int order_sc) {
		this.order_sc = order_sc;
	}
	public int getOrder_new() {
		return order_new;
	}
	public void setOrder_new(int order_new) {
		this.order_new = order_new;
	}
	public int getOrder_col() {
		return order_col;
	}
	public void setOrder_col(int order_col) {
		this.order_col = order_col;
	}
	public int getOrder_hot() {
		return order_hot;
	}
	public void setOrder_hot(int order_hot) {
		this.order_hot = order_hot;
	}
	public String getOnline_date() {
		return online_date;
	}
	public void setOnline_date(String online_date) {
		this.online_date = online_date;
	}
	public String getOnline_type() {
		return online_type;
	}
	public void setOnline_type(String online_type) {
		this.online_type = online_type;
	}
	public String getIs_read() {
		return is_read;
	}
	public void setIs_read(String is_read) {
		this.is_read = is_read;
	}
	public String getIs_jx() {
		return is_jx;
	}
	public void setIs_jx(String is_jx) {
		this.is_jx = is_jx;
	}
	public String getIs_answer() {
		return is_answer;
	}
	public void setIs_answer(String is_answer) {
		this.is_answer = is_answer;
	}
	public int getOrder_read() {
		return order_read;
	}
	public void setOrder_read(int order_read) {
		this.order_read = order_read;
	}
	public String getJx_type() {
		return jx_type;
	}
	public void setJx_type(String jx_type) {
		this.jx_type = jx_type;
	}
	public String getIndustry_name() {
		return industry_name;
	}
	public void setIndustry_name(String industry_name) {
		this.industry_name = industry_name;
	}
	
	
	
}
