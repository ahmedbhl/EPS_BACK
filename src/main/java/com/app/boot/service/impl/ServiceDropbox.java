package com.app.boot.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.boot.model.DropboxItem;
import com.app.boot.utils.DropboxAction;
import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.Metadata;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ServiceDropbox {

	private static final Logger logger = LoggerFactory.getLogger(ServiceDropbox.class);

	@Autowired
	DbxClientV2 dropboxClient;

	public void uploadFile(MultipartFile file, String filePath) throws IOException, DbxException {
		ByteArrayInputStream inputStream = new ByteArrayInputStream(file.getBytes());
		Metadata uploadMetaData = dropboxClient.files().uploadBuilder(filePath).uploadAndFinish(inputStream);
		logger.info("upload meta data =====> {}", uploadMetaData.toString());
		inputStream.close();
	}

	public List<Map<String, Object>> getFileList(String target) throws IOException, DbxException {

		List<Metadata> entries = dropboxClient.files().listFolder(target).getEntries();
		List<Map<String, Object>> result = new ArrayList<>();

		for (Metadata entry : entries) {
			if (entry instanceof FileMetadata) {
				logger.info("{} is file", entry.getName());
			}
			String metaDataString = entry.toString();
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> map = new HashMap<>();
			map = mapper.readValue(metaDataString, new TypeReference<Map<String, Object>>() {
			});
			result.add(map);
		}

		return result;
	}

	public List<DropboxItem> getDropboxItems(String path) throws IOException, DbxException {
		List<Metadata> entries = dropboxClient.files().listFolder(path).getEntries();
		List<DropboxItem> result = new ArrayList<>();
		entries.stream().forEach(entry -> {
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> map = new HashMap<>();
			try {
				map = mapper.readValue(entry.toString(), new TypeReference<Map<String, Object>>() {
				});
			} catch (IOException e) {
				e.printStackTrace();
			}

			DropboxItem item = new DropboxItem();
			item.setId(map.get("id").toString());
			item.setName(map.get("name").toString());
			item.setPath(map.get("path_lower").toString());
			result.add(item);
		});
		return result;
	}

	public void downloadFile(HttpServletResponse response, DropboxAction.Download download)
			throws IOException, DbxException {
		response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
		response.setHeader("Content-Disposition",
				"attachment; filename=\"" + URLEncoder.encode(download.getFileName(), "UTF-8") + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");

		ServletOutputStream outputStream = response.getOutputStream();
		dropboxClient.files().downloadBuilder(download.getFilePath()).download(outputStream);

		response.getOutputStream().flush();
		response.getOutputStream().close();
	}

	public void deleteFile(DropboxAction.Delete delete) throws DbxException {
		dropboxClient.files().delete(delete.getFilePath());
	}

	public void createFolder(String folderName) throws DbxException {
		dropboxClient.files().createFolder(folderName);
	}

}