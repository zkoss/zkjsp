/**
 * 
 */
package org.zkoss.jspdemo.bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.zkoss.image.AImage;
import org.zkoss.image.Image;

/**
 * @author ian
 *
 */
public class Restaurant 
{
	private String name;
	private Image image;	
	private String description;
	private String content = "";

	private OpeningHours OpeningHours;
	private Location location;
	private Benchmark benchmark;
	
	private List<Comment> comments = new ArrayList<Comment>();
	private Set<String> tags = new HashSet<String>();

	
	public Benchmark getBenchmark() {
		return benchmark;
	}
	public void setBenchmark(Benchmark benchmark) {
		this.benchmark = benchmark;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public void addComment(Comment comment){
		comments.add(comment);
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public OpeningHours getOpeningHours() {
		return OpeningHours;
	}
	public void setOpeningHours(OpeningHours openingHours) {
		OpeningHours = openingHours;
	}
	public Set<String> getTags() {
		return tags;
	}
	public void setTags(Set<String> tags) {
		this.tags = tags;
	}
	public void addTag(String tag) {
		String[] strs = tag.split(",");
		for(String str:strs)
			this.tags.add(str);
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}

}
