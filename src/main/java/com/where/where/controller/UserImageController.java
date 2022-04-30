package com.where.where.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.where.where.dto.GetUserImageDto;
import com.where.where.dto.UserImageDto;
import com.where.where.repository.UserImageRepository;
import com.where.where.service.UserImageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/userImages")
public class UserImageController {
	private final UserImageService userImageService;

	@Autowired
	UserImageRepository imageDbRepository;

	@GetMapping("/getByUserId/{id}")
	public ResponseEntity<GetUserImageDto> getByUserId(@PathVariable Long id) {
		return new ResponseEntity<GetUserImageDto>(userImageService.getImageByUserId(id), HttpStatus.OK);
	}

	@RequestMapping(path = "/upload/{id}", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<UserImageDto> uplaodImage(@RequestPart("file") MultipartFile file, @PathVariable Long id)
			throws IOException {
		return new ResponseEntity<UserImageDto>(userImageService.uplaodImage(file, id), HttpStatus.OK);

	}

}
