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

	// account_id
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long articleId;

	// account_password
	@NonNull
	private String articleName;

	// account_mailadress
	@NonNull
	private String articleDetail;

	// register_date
	@NonNull
	private String imageName;
	
	// user_name
	@CreatedDate
	private Date registerDate;
	
	// user_detail
	@CreatedDate
	private Date updateDate;

	// user_detail
	@NonNull
	private Integer displayCount;

	// user_detail
	@NonNull
	private String accountId;

}
