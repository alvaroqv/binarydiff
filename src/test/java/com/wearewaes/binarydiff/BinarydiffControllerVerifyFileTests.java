package com.wearewaes.binarydiff;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.wearewaes.binarydiff.dto.FileDiffResponseDto;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class BinarydiffControllerVerifyFileTests {
	
    private static final Logger logger = LoggerFactory.getLogger(BinarydiffControllerVerifyFileTests.class);
	
	RestTemplate restTemplate;
	
	@Before
	public void beforeTest(){
		restTemplate = new RestTemplate();
	}

	@Test
	public void verifyFileTest() {
		logger.info("Begin filediff/api request!");
		String getUrl = "http://localhost:8080/filediff/api/v1/diff/swagger.JPG";

		ResponseEntity<FileDiffResponseDto> response = restTemplate.getForEntity(getUrl,FileDiffResponseDto.class);

		logger.info("Response file {}",response);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		
		assertNotNull(response.getBody().getFileId());
		assertTrue(response.getBody().isFilesAreEqual());
		assertTrue(response.getBody().isSameSize());
		assertThat(response.getBody().getSizeFileLeft());
		assertThat(response.getBody().getSizeFileRight());
	}

}
