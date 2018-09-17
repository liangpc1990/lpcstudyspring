package event.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import event.UserRegisterEvent;

@Component
public class RegisterUserEmailListener {
	@EventListener
	public void sendMail(UserRegisterEvent userRegisterEvent) {
		System.out.println("用户注册成功，发送邮件。");
	}

}
