package com.jstef.flight_service.Repository;

import com.jstef.flight_service.Entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@DataJpaTest
public class UserJPATestClass {

	@Autowired
	private TestEntityManager manager;

	@Autowired
	private UserRepository userRepository;

	@Test
	public void shouldFindUserForFindByLogin() {
		User user = new User("John","Smith","jsmith","jsmith@gmail.com","fancy");
		manager.persist(user);
		manager.flush();
		User desired = userRepository.findByLogin(user.getLogin());
		assertEquals(user,desired);
	}

	@Test
	public void shouldReturnNoResultForInvalidLogin(){
		User user = userRepository.findByLogin("not-existing");
		assertNull(user);
	}

}
