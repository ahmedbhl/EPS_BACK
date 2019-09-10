package com.app.boot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.boot.service.impl.ServiceDropbox;
import com.app.boot.utils.DropboxAction;
import com.app.boot.utils.DropboxAction.Response;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/library")
@SwaggerDefinition(tags = {
		@Tag(name = "LibraryRestController", description = "${swagger.library-rest-controller.description}") })
@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"),
		@ApiResponse(code = 408, message = "Request Time-out"),
		@ApiResponse(code = 500, message = "Internal Server Error	") })
public class LibraryController {

	@Autowired
	ServiceDropbox serviceDropbox;

	/*
	 * @PostMapping("/upload") // //new annotation since 4.3 public
	 * ResponseEntity<String> singleFileUpload(@RequestParam("file")
	 * MultipartFile file, RedirectAttributes redirectAttributes) {
	 * 
	 * if (file.isEmpty()) { return
	 * ResponseEntity.status(HttpStatus.CONFLICT).build(); }
	 * 
	 * try { // Get the file and save it somewhere byte[] bytes =
	 * file.getBytes(); Path path = Paths.get(file.getOriginalFilename());
	 * Files.write(path, bytes); serviceDropbox.uploadFile(file,
	 * file.getOriginalFilename()); return
	 * ResponseEntity.status(HttpStatus.OK).body(file.getOriginalFilename());
	 * 
	 * } catch (IOException | DbxException e) { return
	 * ResponseEntity.status(HttpStatus.CONFLICT).build(); } }
	 */

	@PostMapping("/upload")
	public String handleFileUplad(@RequestParam("file") MultipartFile file, @RequestParam("filePath") String filePath)
			throws Exception {
		serviceDropbox.uploadFile(file, "/root/text.txt");
		return "You successfully uploaded " + filePath + "!!";
	}

	@GetMapping("/list")
	public List<Map<String, Object>> index(
			@RequestParam(value = "target", required = false, defaultValue = "") String target) throws Exception {
		return serviceDropbox.getFileList(target);
	}

	@GetMapping("/browse")
	public Map<String, Object> brwose(
			@RequestParam(value = "target", required = false, defaultValue = "") String target) throws Exception {
		Map<String, Object> data = new HashMap<>();
		data.put("data", serviceDropbox.getDropboxItems(target));

		return data;
	}

	@GetMapping("/download")
	public void downloadFile(HttpServletResponse response, @RequestBody DropboxAction.Download download)
			throws Exception {
		serviceDropbox.downloadFile(response, download);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Response> deleteFile(@RequestBody DropboxAction.Delete delete, BindingResult result)
			throws Exception {
		serviceDropbox.deleteFile(delete);

		DropboxAction.Response response = new DropboxAction.Response(200, "success");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
