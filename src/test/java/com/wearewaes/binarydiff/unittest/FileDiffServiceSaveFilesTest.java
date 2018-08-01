package com.wearewaes.binarydiff.unittest;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.wearewaes.binarydiff.dto.FileDiffRequestDto;
import com.wearewaes.binarydiff.service.FileDiffService;
import com.wearewaes.binarydiff.util.FileStorageProperties;

@RunWith(MockitoJUnitRunner.class)
public class FileDiffServiceSaveFilesTest {
	 
	@Mock
	FileStorageProperties fileStoragePropertiesMock;
	
	String fileId = "testFile.png";
	String data = "123456";
	
	@Test
	public void testSaveLeftFile() {
		when(fileStoragePropertiesMock.getUploadDir()).thenReturn("opt/");
		FileDiffService fileDiff = new FileDiffService(fileStoragePropertiesMock);
		FileDiffRequestDto dtoLeft = new FileDiffRequestDto();
		dtoLeft.setName(fileId);
		dtoLeft.setData(data);
		String path = fileDiff.saveLeftFile(dtoLeft);
		File file = new File(path);
		assertTrue(file.exists());
	}
	
	@Test
	public void testSaveRightFile() {
		when(fileStoragePropertiesMock.getUploadDir()).thenReturn("opt/");
		FileDiffService fileDiff = new FileDiffService(fileStoragePropertiesMock);
		FileDiffRequestDto dtoRight = new FileDiffRequestDto();
		dtoRight.setName(fileId);
		dtoRight.setData(data);
		String path = fileDiff.saveRightFile(dtoRight);
		File file = new File(path);
		assertTrue(file.exists());
	}
	


}
