package com.trade.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.trade.helper.bean.FileMeta;
import com.trade.helper.bean.TradeBean;
import com.trade.helper.validator.GeneralValidator;
import com.trade.model.Tradeattachment;
import com.trade.model.Tradeevent;
import com.trade.service.HibernateEntityService;
import com.trade.util.Base64;

@Controller
@RequestMapping("/attach")
public class ImageController {

	@Autowired
	HibernateEntityService<Tradeattachment> TradeAttachmentService;
	
	@Autowired
	HibernateEntityService<Tradeattachment> tradeAttachmentService;
	
	@Autowired
	HibernateEntityService<Tradeevent> tradeEventService;

	@RequestMapping(value = "getImage/{id}", method = RequestMethod.GET)
	public void writeImage(@PathVariable("id") String attachmentId, HttpServletRequest request, HttpServletResponse response) {
		byte[] byteArray = null;
		try {
			int id = !"".equals(attachmentId) ? Integer.parseInt(attachmentId): -1;
			if (id != -1) {
				Tradeattachment tradeAttachment = TradeAttachmentService.getById(new Tradeattachment(), id);
				String fullPath = request.getServletContext().getRealPath("/WEB-INF/attachments"); 
		        File downloadFile = new File(fullPath + File.separator + tradeAttachment.getFilename());
				FileInputStream is = new FileInputStream(downloadFile);
				if (tradeAttachment != null) {
					byteArray = IOUtils.toByteArray(is);
					String contentType = "image/"+tradeAttachment.getFiletype();
					this.writeByteArray(byteArray, response, contentType);
				}
				if(is != null){
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void writeByteArray(byte[] bytes, HttpServletResponse response, String contentType) {

		ServletOutputStream out = null;		
		if (bytes != null && bytes.length > 0) {
			try {
				response.setContentType(contentType);
				response.setContentLength(bytes.length);
				out = response.getOutputStream();
				out.write(bytes);
				out.flush();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			finally{
				if(out!=null){
					try {
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	/*@RequestMapping(value="saveEventAttachment", method=RequestMethod.POST)
	public String saveEventAttachmentData(@RequestParam("file") MultipartFile file, @ModelAttribute("tradeBean") TradeBean tradeBean, HttpServletRequest request, RedirectAttributes attributes){
		File retFile =null;
		String filename =null;
		Array[][] myStringArray = new Array[retFile][filename];
		System.out.println("imageFile : " + file);
		InputStream inputStream = null;
		try {
			if(!isValidImageFile(file)){
				attributes.addFlashAttribute("error", "Upload Image of size maximum 2 MB");
				//attributes.addFlashAttribute("description", description);
			}else{
				int userId = Integer.parseInt(request.getSession().getAttribute("loggedUserId").toString());
				inputStream = file.getInputStream();

				Tradeattachment tradeAttachment = new Tradeattachment();
				tradeAttachment.setFiletype(FilenameUtils.getExtension(file.getOriginalFilename()));
				tradeAttachment.setImage(IOUtils.toByteArray(inputStream));
				tradeAttachment.setDescription(null);
				tradeAttachment.setLastupdtdate(new Date());
				tradeAttachment.setUpdatedby(userId);
				tradeAttachment.setTradeevent(tradeEventService.getById(new Tradeevent(), userId));

				tradeAttachmentService.add(tradeAttachment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(inputStream != null){
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		JSONArray userJSONArray = new JSONArray();
		JSONObject userJSON;
		try{
				userJSON = new JSONObject();
				userJSON.put("filename", file.getOriginalFilename());
				userJSON.put("filesize", file.getSize());
				userJSONArray.put(userJSON);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return userJSONArray.toString();
	}*/
	
	private boolean isValidImageFile(MultipartFile imageFile) {
		boolean flag = false;
		long size = imageFile.getSize();
		final String[] extensions = { "jpg", "jpeg", "png", "gif", "bmp" };
		try {
			if (FilenameUtils.isExtension(imageFile.getOriginalFilename(),
					extensions)) {
				BufferedImage image = ImageIO.read(imageFile.getInputStream());
				if (image.getWidth() <= 2500 && image.getHeight() <= 1500) {
					if (size > 0 && size <= 2097152) {
						flag = true;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	LinkedList<FileMeta> files = new LinkedList<FileMeta>();
    FileMeta fileMeta = null;
    
    @SuppressWarnings("unchecked")
    @RequestMapping(value="/upload", method = RequestMethod.POST)
    public @ResponseBody LinkedList<FileMeta> uploads(MultipartHttpServletRequest request, HttpServletResponse response) {

    	//1. build an iterator
    	Iterator<String> itr =  request.getFileNames();
    	MultipartFile mpf = null;
    	//List<FileMeta> files = new ArrayList<FileMeta>();
    	List<Tradeattachment> attachmentList = (List<Tradeattachment>) request.getSession().getAttribute("eventattachmentList");
    	/*if(request.getSession().getAttribute("filesList") !=null){
    		files = (List<FileMeta>) request.getSession().getAttribute("filesList");
    		//request.getSession().removeAttribute("filesList");
    	}*/
    	//2. get each file
    	while(itr.hasNext()){
    		//2.1 get next MultipartFile
    		mpf = request.getFile(itr.next());

    		if(attachmentList.isEmpty()){
    			files.removeAll(files);
    		}
    		//2.2 if files > 10 remove the first from the list
    		if(files.size() >= 10)
    			files.pop();

    		//2.3 create new fileMeta
    		fileMeta = new FileMeta();
    		//System.out.println("mpf : " + mpf.getOriginalFilename());
    		fileMeta.setFileName(mpf.getOriginalFilename());
    		fileMeta.setFileSize(mpf.getSize()/1024+" Kb");
    		fileMeta.setFileType(mpf.getContentType());
    		InputStream inputStream = null;
    		try {
    			fileMeta.setBytes(mpf.getBytes());

    			// copy file to local disk (make sure the path "e.g. D:/temp/files" exists)           
    			//FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream("D:/temp/files/"+mpf.getOriginalFilename()));
    			if(!isValidImageFile(mpf)){
    				//attributes.addFlashAttribute("error", "Upload Image of size maximum 2 MB");
    				//attributes.addFlashAttribute("description", description);
    				//return "invalid file";
    				//inputStream = mpf.getInputStream();
    				fileMeta.setStatus("Invalid image");
    			}else{
    				int userId = Integer.parseInt(request.getSession().getAttribute("loggedUserId").toString());
    				inputStream = mpf.getInputStream();

    				Tradeattachment tradeAttachment = new Tradeattachment();
    				tradeAttachment.setFiletype(FilenameUtils.getExtension(mpf.getOriginalFilename()));
    				//tradeAttachment.setImage(IOUtils.toByteArray(inputStream));
    				tradeAttachment.setDescription(null);
    				tradeAttachment.setLastupdtdate(new Date());
    				tradeAttachment.setUpdatedby(userId);
    				/*Tradeevent tradeEvent =  tradeEventService.getById(new Tradeevent(), 1);
        				tradeAttachment.setTradeevent(tradeEvent);
        				tradeAttachmentService.add(tradeAttachment);*/
    				attachmentList.add(tradeAttachment);
    				request.getSession().setAttribute("eventattachmentList", attachmentList);
    				fileMeta.setStatus("Completed");
    				//files.add(fileMeta);
    			}
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		finally{
    			if(inputStream != null){
    				try {
    					inputStream.close();
    				} catch (IOException e) {
    					e.printStackTrace();
    				}
    			}
    		}
    		//System.out.println("added : " + fileMeta.getFileName());
    		//2.4 add to files
    		files.add(fileMeta);
    	}
    	/*for(FileMeta meta : files){
    		System.out.println("list after : " + meta.getFileName());
    	}*/
    	//System.out.println("list before : " + files.size());
    	//System.out.println("----------------------------------");
    	//request.getSession().setAttribute("filesList",files);
    	// result will be like this
    	// [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]
    	return files;
    }
    
    /*@RequestMapping(value = "/get/{value}", method = RequestMethod.GET)
	 public void get(HttpServletResponse response,@PathVariable String value){
		 FileMeta getFile = filess.get(Integer.parseInt(value));
		 try {		
			 	response.setContentType(getFile.getFileType());
			 	response.setHeader("Content-disposition", "attachment; filename=\""+getFile.getFileName()+"\"");
		        FileCopyUtils.copy(getFile.getBytes(), response.getOutputStream());
		 }catch (IOException e) {
				e.printStackTrace();
		 }
	 }*/
    
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/uploadfile", method = RequestMethod.POST)
    public @ResponseBody List<FileMeta> uploadMultiple(@RequestParam("myfile") MultipartFile[] file,HttpServletRequest request) throws Exception {
    	List<FileMeta> files = (List<FileMeta>) request.getSession().getAttribute("filesList");    	
    	List<Tradeattachment> attachmentList = (List<Tradeattachment>) request.getSession().getAttribute("eventattachmentList");
    	Base64  base64 =  new Base64();
    	for(MultipartFile singlefile : file){
    		String fileName = base64.generateRandomString() + "." + FilenameUtils.getExtension(singlefile.getOriginalFilename());
	    	fileMeta = new FileMeta();
			fileMeta.setFileName(fileName);
			fileMeta.setOldName(singlefile.getOriginalFilename());
			fileMeta.setFileSize(singlefile.getSize()/1024+" Kb");
			fileMeta.setFileType(singlefile.getContentType());
			fileMeta.setStatus("Completed");
			
				
				//String path = request.getServletContext().getRealPath("/attachments");
				String path = request.getServletContext().getRealPath("/WEB-INF/attachments");
				InputStream is = singlefile.getInputStream();  
				byte[] buffer = new byte[(int) singlefile.getSize()];
				is.read(buffer);
				
				File f = new File(path + File.separator + fileName);   
				/*if(!f.isDirectory()){
		        	f.mkdir();
		        	System.out.println("directory created");
		        }*/
				//f.createNewFile();
				FileOutputStream fos = new FileOutputStream(f);  
				fos.write(buffer);  //This is where file write to folder
				fos.close();
				is.close();
			
				int userId = Integer.parseInt(request.getSession().getAttribute("loggedUserId").toString());

				Tradeattachment tradeAttachment = new Tradeattachment();
				tradeAttachment.setFiletype(FilenameUtils.getExtension(singlefile.getOriginalFilename()));
				tradeAttachment.setFilename(fileName);
				tradeAttachment.setDescription(null);
				tradeAttachment.setLastupdtdate(new Date());
				tradeAttachment.setUpdatedby(userId);
				
				attachmentList.add(tradeAttachment);
				request.getSession().setAttribute("eventattachmentList", attachmentList);
				
			files.add(fileMeta);
    	}
    	request.getSession().setAttribute("filesList",files);
    	return files;
    }
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/removeUploaded", method = RequestMethod.POST)
    public @ResponseBody boolean removeUploaded(HttpServletRequest request) {
    	//List<FileMeta> files = (List<FileMeta>) request.getSession().getAttribute("filesList");
    	//List<FileMeta> files = new ArrayList<FileMeta>();
    	//for(MultipartFile singlefile : file){
		String fullPath = request.getServletContext().getRealPath("/WEB-INF/attachments");
        File file = new File(fullPath + File.separator + request.getParameter("name"));
        boolean isDelete = file.delete();
        if(isDelete){
        	
        	List<Tradeattachment> attachmentList = (List<Tradeattachment>) request.getSession().getAttribute("eventattachmentList");
        	ArrayList<Tradeattachment> clone = (ArrayList<Tradeattachment>) ((ArrayList<Tradeattachment>) attachmentList).clone();
        	
        	for(Tradeattachment attachment : clone){
        		if(attachment.getFilename().equalsIgnoreCase(request.getParameter("name"))){
        			attachmentList.remove(attachment);
        		}
        	}
        	request.getSession().setAttribute("eventattachmentList",attachmentList);
        }
    		//System.out.println("removeFile : " + file.getOriginalFilename());
    	//String result = "File Deleted Successfull";
	    	/*fileMeta = new FileMeta();
			fileMeta.setFileName(file.getOriginalFilename());
			fileMeta.setFileSize(file.getSize()/1024+" Kb");
			fileMeta.setFileType(file.getContentType());
			fileMeta.setStatus("Completed");
			
			files.add(fileMeta);*/
    	//}
    	//request.getSession().setAttribute("filesList",files);
    	return isDelete;
    }
    
    @RequestMapping(value="/removeAttachment/{refId}", method = RequestMethod.POST)
    public @ResponseBody boolean removeAttached(@PathVariable("refId") String attachmentId,HttpServletRequest request) {
    	boolean isDelete=false;
    	if(attachmentId!=null){
			if(!attachmentId.equalsIgnoreCase("") && GeneralValidator.isValidNumber(attachmentId)){
				Tradeattachment attachment = tradeAttachmentService.getById(new Tradeattachment(), Integer.parseInt(attachmentId));
				if(attachment != null){
					String fullPath = request.getServletContext().getRealPath("/WEB-INF/attachments"); 
			        File file = new File(fullPath + File.separator + attachment.getFilename());		
			        //if(file.exists()){
				        isDelete = file.delete();
			        	tradeAttachmentService.remove(new Tradeattachment(), attachment.getId());
			        //}
				}
			}
    	}        
    	return isDelete;
    }
}
