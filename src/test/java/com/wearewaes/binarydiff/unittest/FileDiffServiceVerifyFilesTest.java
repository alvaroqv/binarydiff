package com.wearewaes.binarydiff.unittest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.wearewaes.binarydiff.dto.FileDiffResponseDto;
import com.wearewaes.binarydiff.service.FileDiffService;
import com.wearewaes.binarydiff.util.FileStorageProperties;

@RunWith(MockitoJUnitRunner.class)
public class FileDiffServiceVerifyFilesTest {
	 
	@Mock
	FileStorageProperties fileStoragePropertiesMock;
	
	String fileId = "testFile.png";
	String data = "123456";
		
	@Test
	public void testFileDiff() {
		when(fileStoragePropertiesMock.getUploadDir()).thenReturn("opt/");
		FileDiffService fileDiff = new FileDiffService(fileStoragePropertiesMock);
		FileDiffResponseDto response = fileDiff.verifyDiffById(fileId);
		
		assertNotNull(response.getFileId());
		assertTrue(response.isFilesAreEqual());
		assertTrue(response.isSameSize());
		assertThat(response.getSizeFileLeft());
		assertThat(response.getSizeFileRight());
	}

}
