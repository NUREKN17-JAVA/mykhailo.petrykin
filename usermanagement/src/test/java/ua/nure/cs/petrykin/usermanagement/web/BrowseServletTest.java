package ua.nure.cs.petrykin.usermanagement.web;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import ua.nure.cs.petrykin.usermanagement.domain.User;

public class BrowseServletTest extends MockServletTestCase {

	protected void setUp() throws Exception {
		super.setUp();
		createServlet(BrowseServlet.class);
	}
	public void testBrowse() {
		User user = new User(new Long(1000), "John","Doe",new Date());
		List<User> list = Collections.singletonList(user);
		getMockUserDao().expectAndReturn("findAll", list);
		doGet();
		@SuppressWarnings("unchecked")
		Collection<User> collection = (Collection<User>) getWebMockObjectFactory().getMockSession().getAttribute("users");
		assertNotNull("Could not find list of users in session",collection);
		assertSame(list,collection);
		
	}
}
