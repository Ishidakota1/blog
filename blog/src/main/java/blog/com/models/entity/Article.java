package blog.com.models.entity;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Article {

	// article_id
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long articleId;

	// article_name
	@NonNull
	private String articleName;

	// article_detail
	@NonNull
	private String articleDetail;

	// image_name
	@NonNull
	private String imageName;
	
	// register_date
	@NonNull
	private Date registerDate;
	
	// update_date
	private Date updateDate;

	// display_count
	@NonNull
	private Integer displayCount;

	// account_id
	@NonNull
	private String accountId;
	
	// article_date
	@NonNull
	private String articleDate;

}
