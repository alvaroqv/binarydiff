package com.wearewaes.binarydiff.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.wearewaes.binarydiff.dto.FileDiffRequestDto;
import com.wearewaes.binarydiff.dto.FileDiffResponseDto;
import com.wearewaes.binarydiff.exception.FileErrorException;
import com.wearewaes.binarydiff.util.FileStorageProperties;
import com.wearewaes.binarydiff.util.FileUtil;

@Service
public class FileDiffService {
	
    private static final Logger logger = LoggerFactory.getLogger(FileDiffService.class);
	
    private final Path fileStorageLocation;

    @Autowired
    public FileDiffService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
        logger.info("Root Folder to save files: {}" ,fileStorageLocation.toString());

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileErrorException("File creating error", ex);
        }
    }

    /**
     * Method responsible for inform if the FILE is equal into both sides- LEFT folder and RIGHT folder
     * Using the file name / identification, it compare the file and return the result
     * @param String identification / name
     * @return DTO FileDiffResponseDto with the comparison result 
     */
	public FileDiffResponseDto verifyDiffById(String id)  {
		
		FileDiffResponseDto dto = new FileDiffResponseDto();
		byte[] left = null;
		byte[] right = null;
		try {
			left = loadFile(id, true);
			 right = loadFile(id, false);
			 dto.setFilesAreEqual(getCheckSum(left,right));
		} catch (FileNotFoundException | NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		dto.setFileId(id);
		dto.setSameSize(left.length == right.length);
		dto.setSizeFileLeft(left.length);
		dto.setSizeFileRight(right.length);
		
		
		return dto;
		}

	/**
	 * Method responsible for convert the Base64 data into saved file and save the file at the Left folder
	 * @param fileDto - DTO with String with file name identification and String with data in text format
	 * @return fileWithPath - String with the file name + file path
	 */
	public String saveLeftFile(FileDiffRequestDto fileDto) {
		return this.saveFile(fileDto, true);
	}

	/**
	 * Method responsible for convert the Base64 data into saved file and save the file at the Right folder
	 * @param fileDto - DTO with String with file name identification and String with data in text format
	 * @return fileWithPath - String with the file name + file path
	 */
	public String saveRightFile(FileDiffRequestDto fileDto) {
		return this.saveFile(fileDto, false);
	}
	
	/**
	 * Method responsible for convert the Base64 data into saved file
	 * @param fileDto - DTO with String with file name identification and String with data in text format
	 * @param left - Boolean to inform if it´s LEFT folder or RIGHT
	 * @return fileWithPath - String with the file name + file path
	 */
	private String saveFile(FileDiffRequestDto fileDto,  boolean left) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(fileDto.getName());
        String fileWithPath =  null;
        
        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileErrorException("Filename contains invalid path sequence:"+ fileName) ;
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = chooseSide(left);
            
            Files.createDirectories(targetLocation);
            logger.info("Complete Folder Path to saved files: {}", targetLocation.toString());
            
            fileWithPath = targetLocation.toString()+"/"+fileName; 
            logger.info("File with path: {}", fileWithPath);
            
            FileUtil.decodeBase64(fileDto.getData(),fileWithPath);

            return fileWithPath;
        } catch (IOException ex) {
            throw new FileErrorException("Could not store file: " + fileWithPath, ex);
        }

	}

	/**
	 * Inform the folder
	 * @param boolean true to left - false to right
	 * @return Path - folder path
	 */
	private Path chooseSide(boolean left) {
		String pathFolder = "left/";
		if(!left) 
			pathFolder = "right/";
		return this.fileStorageLocation.resolve(pathFolder);
	}
	
	/**
	 * Load file using the file name and choose the folder Left or Right - boolean true to LEFT false to RIGHT
	 * @param fileName - file name
	 * @param left - boolean choose the side
	 * @return byte[]  - file bytes
	 * @throws FileNotFoundException
	 */
	private byte[] loadFile(String fileName, boolean left) throws FileNotFoundException {
		 Path targetLocation = chooseSide(left);
		 Path filePath = targetLocation.resolve(fileName).normalize();
        try {
        	return Files.readAllBytes(filePath);
        } catch (IOException ex) {
            throw new FileNotFoundException("Not found file in path: "+filePath.toString());
        }
    }
	
	/**
	 * Generate MD5 hash to verify the file checksum 
	 * @param byte[] contentLeft - file from LEFT folder
	 * @param byte[] contentRight - file from RIGHT folder
	 * @return boolean if the hash has the same result
	 * @throws NoSuchAlgorithmException
	 */
	private  boolean getCheckSum(byte[] contentLeft, byte[] contentRight) throws NoSuchAlgorithmException {
		byte[] hashLeft = MessageDigest.getInstance("MD5").digest(contentLeft);
		byte[] hashRight = MessageDigest.getInstance("MD5").digest(contentRight);
		return MessageDigest.isEqual(hashLeft, hashRight);
    }
	

}
