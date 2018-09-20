package event.mapper;


import org.springframework.stereotype.Repository;

import event.bean.UserBean;

@Repository
public interface UserMapper {

	
	void insertUser(UserBean userBean);
}
