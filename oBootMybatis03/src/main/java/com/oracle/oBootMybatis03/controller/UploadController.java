package com.oracle.oBootMybatis03.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.RequestPath;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.oracle.oBootMybatis03.model.Dept;



@Controller
public class UploadController {
		private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
		
		// upLoadForm 시작 화면
		@RequestMapping(value = "upLoadFormStart")
		public String upLoadFormStart(Model model) {
			System.out.println("upLoadFormStart");
			return "upLoadFormStart";
		}
		
		@RequestMapping(value = "uploadForm", method = RequestMethod.GET)
		public void uploadForm() {
			System.out.println("uploadForm GET Start");
			System.out.println();
		}
		
		@RequestMapping(value = "uploadForm", method = RequestMethod.POST)
		public String uploadForm(HttpServletRequest request, MultipartFile file1, Model model)
			throws Exception{
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload/");
		
		System.out.println("uploadForm POST Start");
		logger.info("originalName: " + file1.getOriginalFilename());
//		logger.info("title: " + title);
		logger.info("size: " + file1.getSize());
		logger.info("contentType: " + file1.getContentType());
		String savedName = uploadFile(file1.getOriginalFilename(), file1.getBytes(), uploadPath);
		logger.info("savedName: " + savedName);
		model.addAttribute("savedName", savedName);
		return "uploadResult";
		}
		
		private String uploadFile(String originalName, byte[] fileData, String uploadPath)
				throws Exception {
			UUID uid = UUID.randomUUID();
			// requestPath = requestPath + "/resources/image";
			System.out.println("uploadPath->"+uploadPath);
			// Directory 생성
			File fileDirectory = new File(uploadPath);
			if (!fileDirectory.exists()) {
				fileDirectory.mkdirs();
				System.out.println("업로드용 폴더 생성 : " + uploadPath);
			}
			String savedName = uid.toString() + "_" + originalName;
			logger.info("savedName: " + savedName);
//			String path1 = "C:\\spring\\springSrc39\\.metadata\\.plugins\\org.eclipse.swt.server.core\\tmp0\\wt
			File target = new File(uploadPath, savedName);
//			File target = new File(RequestPath, savedName);
			FileCopyUtils.copy(fileData, target);		// org.springframework.util.FileCopyUtils
			
			return savedName;
			}
		
		@RequestMapping(value = "uploadFileDelete", method = RequestMethod.GET)
		public String uploadFileDelete(HttpServletRequest request, Model model)
				throws Exception {
			String uploadPath = request.getSession().getServletContext().getRealPath("/upload/");
			String deleteFile = uploadPath + "e44013f5-5e29-4191-a748-5d21b1d918b3_jung1.jpg";
			logger.info("deleteFile: " + deleteFile);
			System.out.println("uploadFileDelete POST Start");
			int delResult = upFileDelete(deleteFile);
			logger.info("deleteFile result->" + delResult);
			model.addAttribute("deleteFile", deleteFile);
			model.addAttribute("delResult", delResult);
			return "uploadResult";
		}
		
		private int upFileDelete(String deleteFileName)		throws Exception {
			int result = 0;
			logger.info("upFileDelete result-> " + deleteFileName);
			File file = new File(deleteFileName);
			if( file.exists()) {
				if(file.delete()) {
					System.out.println("파일삭제 성공");
					result = 1;
				}
				else {
					System.out.println("파일삭제 실패");
					result = 0;
				}
			}
			else {
				System.out.println("파일이 존재하지 않습니다.");
				result = -1;
			}
			return result;
		}
	}

