package com.wearewaes.binarydiff.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.wearewaes.binarydiff.dto.FileDiffRequestDto;
import com.wearewaes.binarydiff.dto.FileDiffResponseDto;
import com.wearewaes.binarydiff.service.FileDiffService;

@RestController
@RequestMapping("filediff/api")
public class FileDiffController {
	
	
	@Autowired
	FileDiffService fileDiffService;
	
	@GetMapping("/v1/diff/{id}")
	public ResponseEntity<FileDiffResponseDto> verifyDiffById(@PathVariable String id)  {
		FileDiffResponseDto response = fileDiffService.verifyDiffById(id);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/v1/diff/{id}/left")
	public ResponseEntity<Object> createFileLeft(@PathVariable String id, @RequestBody String data) {
		FileDiffRequestDto dto = new FileDiffRequestDto(id,data);
		String idResponse = fileDiffService.saveLeftFile(dto);
		URI location =  ServletUriComponentsBuilder.
			fromCurrentRequest().
			path("/{idResponse}").
			buildAndExpand(id).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PostMapping("/v1/diff/{id}/right")
	public ResponseEntity<Object> createFileRight(@PathVariable String id, @RequestBody String data) {
		FileDiffRequestDto dto = new FileDiffRequestDto(id,data);
		String idResponse = fileDiffService.saveRightFile(dto);
		URI location =  ServletUriComponentsBuilder.
			fromCurrentRequest().
			path("/{idResponse}").
			buildAndExpand(id).toUri();
		
		return ResponseEntity.created(location).build();
	}

}
