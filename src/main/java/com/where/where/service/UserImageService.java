package com.where.where.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.where.where.dto.GetUserImageDto;
import com.where.where.dto.UserImageDto;
import com.where.where.exception.BusinessException;
import com.where.where.mapper.ModelMapperService;
import com.where.where.model.UserImage;
import com.where.where.repository.UserImageRepository;
import com.where.where.service.user.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserImageService {
	private final UserImageRepository imageRepository;
	private final UserService userService;
	private final ModelMapperService modelMapperService;

	public UserImageDto uplaodImage(MultipartFile file, Long userId) throws IOException {
		UserImage img = new UserImage(file.getOriginalFilename(), file.getContentType(),
				compressBytes(file.getBytes()));
		img.equals(checkIfExistsByUserId(img, userId));

		img.setUser(userService.getByUserId(userId));

		imageRepository.save(img);

		return modelMapperService.forDto().map(img, UserImageDto.class);
	}

	private UserImage checkIfExistsByUserId(UserImage img, Long userId) {

		if (imageRepository.existsByUserId(userId)) {
			UserImage userImage = imageRepository.getByUserId(userId);
			img.setId(userImage.getId());
		}
		return img;
	}

	public GetUserImageDto getImageByUserId(Long id) {

		UserImage retrievedImage = imageRepository.getByUserId(id);
//		new UserImage(retrievedImage.getId(), retrievedImage.getUser(), retrievedImage.getName(),
//				retrievedImage.getType(), decompressBytes(retrievedImage.getPicByte()));
		return modelMapperService.forDto().map(retrievedImage, GetUserImageDto.class);
	}

	public static byte[] compressBytes(byte[] data) {

		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();

		} catch (IOException e) {
			throw new BusinessException(e.getMessage());
		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

		return outputStream.toByteArray();
	}

	public static byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (IOException ioe) {
		} catch (DataFormatException e) {
		}
		return outputStream.toByteArray();
	}

}
