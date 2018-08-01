package com.wearewaes.binarydiff.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.xml.bind.DatatypeConverter;

import com.wearewaes.binarydiff.exception.FileErrorException;

public class FileUtil {
	
	/**
	 * 
	 * @param Data into String format 
	 * @return Data hash using MD5 into String format
	 * @throws NoSuchAlgorithmException
	 */
    public static String getCheckSum(String base64Data) throws NoSuchAlgorithmException {
    	byte[] imageByteArray = Base64.getDecoder().decode(base64Data);
    	byte[] newHash = MessageDigest.getInstance("MD5").digest(imageByteArray);
		return DatatypeConverter.printHexBinary(newHash);
    }


    /**
     * Read file from the Path and encode Data into String Base64
     * @param String with imagePath
     * @return String Base64 Data format
     * @throws Exception
     */
    public static String encodeBase64(String filePath) throws Exception{
	    File file = new File(filePath);
	    try (FileInputStream dataInFile = new FileInputStream(file)) {
	        // Reading a Image file from file system
	    	String base64Image = "";
	        byte imageData[] = new byte[(int) file.length()];
	        dataInFile.read(imageData);
	        base64Image = Base64.getEncoder().encodeToString(imageData);
	        return base64Image;
	    } catch (FileNotFoundException e) {
	    	throw new FileNotFoundException(e.getMessage());
	    } catch (IOException ioe) {
	    	throw new FileErrorException(ioe.getMessage());
	    }
	}
	
    /**
     * Decode Data String Base64 into byte[] and save the data 
     * @param String with image Path
     * @return String Base64 Data format
     * @throws Exception
     */
	public static void decodeBase64(String base64data, String pathFile) throws FileNotFoundException {
	    try (FileOutputStream imageOutFile = new FileOutputStream(pathFile)) {
	        // Converting a Base64 String into Image byte array
	        byte[] imageByteArray = Base64.getDecoder().decode(base64data);
	        imageOutFile.write(imageByteArray);
	    } catch (FileNotFoundException e) {
	    	throw new FileNotFoundException(e.getMessage());
	    } catch (IOException ioe) {
	    	throw new FileErrorException(ioe.getMessage());
	    }
	}


}