package com.file.upload;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import javax.ws.rs.*;

@Path("/uploadservice")
public class ServiceClass {
	
	
	@GET
	@Path("/score")
	@Produces("application/json")
	public String getScore() {
	   String pattern = 
	      "{ \"wins\":\"%s\", \"losses\":\"%s\", \"ties\": \"%s\"}";
	   return String.format(pattern,  "2", "3", "4" );   
	}

	@PUT
	@Path("/fileupload")
	@Produces("application/json")
	public String update(@QueryParam("fileName") String fileName, 
	                        @QueryParam("fileUrl") String fileUrl) {
	   
	   String pattern = 
	      "{ \"status\":\"%s\"}";
	   BufferedInputStream in = null;
	   FileOutputStream fout = null;
	   try{
		   in = new BufferedInputStream(new URL(fileUrl).openStream());
			 fout = new FileOutputStream(fileName);
			 
			byte data[] = new byte[1024];
			 int count;
			 while ((count = in.read(data, 0, 1024)) != -1) {
			 fout.write(data, 0, count);
			 }
		   
	   }catch(Exception e){
		   return String.format(pattern, "Fail");
	   }
	   finally {
			 if (in != null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}}
			 if (fout != null){
				try {
					fout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}}
			 }
	   return String.format(pattern, "Success");   
	}
	
}
