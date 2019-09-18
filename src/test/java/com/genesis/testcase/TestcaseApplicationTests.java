package com.genesis.testcase;

import com.genesis.testcase.beans.Contact;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestcaseApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestcaseApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void testCreateContact() {
		Contact contact = new Contact();
		contact.setFirstname("Test Firstname");
		contact.setLastname("Test Lastname");

		ResponseEntity<Contact> postResponse = restTemplate.postForEntity(getRootUrl() + "/contact", contact, Contact.class);
		Assert.assertNotNull(postResponse);
		Assert.assertNotNull(postResponse.getBody());
	}
}
