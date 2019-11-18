package com.jstef.flight_service.Repository;

import com.jstef.flight_service.Entity.User;
import com.jstef.flight_service.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@DataJpaTest
public class UserJPATestClass {

	@Autowired
	private TestEntityManager manager;

	@Autowired
	private UserRepository userRepository;

	@Test
	public void findByLoginTest() {
		User user = new User("John","Smith","jsmith","jsmith@gmail.com","fancy");
		manager.persist(user);
		manager.flush();
		User desired = userRepository.findByLogin(user.getLogin());
		assertEquals(user.getFirstName(),desired.getFirstName());
		assertEquals(user.getLastName(),desired.getLastName());
		assertEquals(user.getLogin(),desired.getLogin());
		assertEquals(user.getEmail(),desired.getEmail());
		assertEquals(user.getPassword(),desired.getPassword());
	}

}
