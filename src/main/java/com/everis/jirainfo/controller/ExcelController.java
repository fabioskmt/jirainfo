package com.everis.jirainfo.controller;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.everis.jirainfo.service.ExcelService;

@Controller
public class ExcelController {

	@Autowired
	private ExcelService excelService;
	
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
	    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	    multipartResolver.setMaxUploadSize(100000);
	    return multipartResolver;
	}
	
	@RequestMapping(value = "/")
	public String teste() {
		return "excel";
	}
	
	@RequestMapping(value = "/api/excel/leitura")
	public String leituraExcel(@RequestParam("arquivoExcel") MultipartFile file, ModelMap modelMap) {
		modelMap.addAttribute("file", file);
		
		File arquivoExcel = new File(file.getOriginalFilename());
		
		try {
			FileUtils.writeByteArrayToFile(arquivoExcel, file.getBytes());
			excelService.lerArquivo(arquivoExcel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "excel";
	}

//	@PostMapping("/api/excel/leitura")
//	public ResponseEntity<Boolean> leitura(@RequestBody String arquivoExcel) {
//		try {
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return ResponseEntity.status(HttpStatus.OK).body(true);
//	}
	
	
	
}
