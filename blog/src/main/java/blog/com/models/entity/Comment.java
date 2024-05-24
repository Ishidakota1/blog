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
public class Comment {

	// comment_id
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long commentId;

	// comment_detail
	@NonNull
	private String commentDetail;

	// register_date
	@NonNull
	private Date registerDate;

	// article_id
	@NonNull
	private Long articleId;
	
	// account_id
	@NonNull
	private String accountId;
}
