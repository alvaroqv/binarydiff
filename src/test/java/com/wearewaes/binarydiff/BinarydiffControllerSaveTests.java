package com.wearewaes.binarydiff;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.wearewaes.binarydiff.util.FileUtil;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class BinarydiffControllerSaveTests {
	
    private static final Logger logger = LoggerFactory.getLogger(BinarydiffControllerSaveTests.class);
	
    @Autowired
    private TestRestTemplate restTemplate;
    
	@Test
	public void testSaveLeftFile() throws Exception {
			logger.info("Begin filediff/api request!");
			Path path = Paths.get("opt/swagger.JPG").toAbsolutePath().normalize();
			logger.info("Load file {}",path.toString());
			String base64 = FileUtil.encodeBase64(path.toString());
	
			ResponseEntity<String> postResponse = restTemplate.postForEntity("http://localhost:8080/filediff/api/v1/diff/swagger.JPG/left", base64, String.class);

			logger.info("Response file {}",postResponse);
			assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		    assertNotNull(postResponse);
	}
	
	@Test
	public void testSaveRightFile()  throws Exception{
		logger.info("Begin filediff/api request!");
		Path path = Paths.get("opt/swagger.JPG").toAbsolutePath().normalize();
		logger.info("Load file {}",path.toString());
		String base64 = FileUtil.encodeBase64(path.toString());

		ResponseEntity<String> postResponse = restTemplate.postForEntity("http://localhost:8080/filediff/api/v1/diff/swagger.JPG/right", base64, String.class);

		logger.info("Response file {}",postResponse);
		assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
	    assertNotNull(postResponse);
	}

}
