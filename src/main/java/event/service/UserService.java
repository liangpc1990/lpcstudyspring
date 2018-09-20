package event.service;

import java.util.Random;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import event.UserRegisterEvent;
import event.bean.People;
import event.bean.UserBean;
import event.mapper.PeopleMapper;

@Service
public class UserService {
	@Autowired
	ApplicationContext applicationContext;
	
	@Resource(name="peopleMapper")
	PeopleMapper peopleMapper;

	/**
	 * 用户注册方法
	 * 
	 * @param user
	 */
	public void register(UserBean user) {
		// ../省略其他逻辑

		// 发布UserRegisterEvent事件
		People p = new People();
		p.setAge("12");
		p.setCity("ty");
		p.setId(new Random().nextInt());
		p.setName("lpc");
		peopleMapper.insert(p);
		applicationContext.publishEvent(new UserRegisterEvent(this, user));
	}
}
