package blog.com.models.entity;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Entity;
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
public class Account {

	// account_id
	@Id
	@NonNull
	private String accountId;

	// account_password
	@NonNull
	private String accountPassword;

	// account_mailadress
	@NonNull
	private String accountMailadress;

	// register_date
	@CreatedDate
	private Date registerDate;
	
	// user_name
	@NonNull
	private String userName;
	
	// user_detail
	private String userDetail;

}
