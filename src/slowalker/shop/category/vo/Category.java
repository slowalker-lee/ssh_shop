package slowalker.shop.category.vo;

import java.util.HashSet;
import java.util.Set;

import slowalker.shop.categorysecond.vo.CategorySecond;

public class Category {
	private Integer cid;
	private String cname;
	//一级分类对应的二级分类的集合
	private Set<CategorySecond> categorySeconds = new HashSet<>();
	public Set<CategorySecond> getCategorySeconds() {
		return categorySeconds;
	}
	public void setCategorySeconds(Set<CategorySecond> categorySeconds) {
		this.categorySeconds = categorySeconds;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	
	
}
