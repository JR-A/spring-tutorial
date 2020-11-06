package kr.spring.view;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

//BLOB 타입 이미지 표시 (byte배열->inputStream 생성) AbstractView 상속!
public class ImageView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		
		byte[] file = (byte[]) model.get("imageFile");
		String filename = (String) model.get("filename");
		
		String ext = filename.substring(filename.lastIndexOf("."));
		//확장자 체크
		if(ext.equalsIgnoreCase(".gif")) {
			ext = "image/gif";
		}else if(ext.equalsIgnoreCase(".png")) {
			ext = "image/png";
		}else {
			ext = "image/jpeg";
		}

		response.setContentType(ext);
		response.setContentLength(file.length);

		String userAgent = request.getHeader("User-Agent");
		boolean ie = userAgent.indexOf("MSIE") > -1;
		String fileName = null;
		if (ie) {
			fileName = URLEncoder.encode(filename, "utf-8");
		} else {
			fileName = new String(filename.getBytes("utf-8"),
					"iso-8859-1");
		}
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		OutputStream out = response.getOutputStream();	//출력스트림
		
		//바이트배열로 스트림 생성
		InputStream input = new ByteArrayInputStream(file);
		IOUtils.copy(input, out);
		/*
		 * int copy(InputStream input,OutputStream output)
		 * Copies bytes from an InputStream to an OutputStream.
		 * This method buffers the input internally, so there is no need to use a BufferedInputStream. 
		 */
		out.flush();		//출력스트림 비우기. 버퍼에 남은것 모두 출력
		out.close();
		input.close();
	}

}
