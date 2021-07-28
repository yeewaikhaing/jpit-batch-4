package com.mmit.shop.bean;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;

import com.mmit.shop.model.entity.Item;
import com.mmit.shop.model.service.ItemService;

@Named
@ViewScoped
public class ItemBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Item item;
	@Inject
	private ItemService service;
	
	private Part imgPart;
	
	private ServletContext s_context;
	private String imgFolder=null;
	@PostConstruct
	private void init() {
		String id=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		if(id != null)
			item=service.findById(Integer.parseInt(id));
		else
			item=new Item();
		
		s_context=(ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		imgFolder=s_context.getRealPath("/resources/uploads");
		
	}

	public String save() {
		
		try {
			System.out.println("imgPart :"+imgPart);
			if(imgPart != null && !imgPart.getSubmittedFileName().equals("")) {
				//System.out.println("Img Name: "+imgPart.getSubmittedFileName());
				String photoName=getPhotoName(imgPart.getSubmittedFileName());
				
				if(item.getId() !=0 )//edit photo
				{
					String oldPhotoName=service.findPhotoById(item.getId());
					if(oldPhotoName != null) {
						File oldPhotoFile=new File(imgFolder+File.separator+oldPhotoName);
						if(oldPhotoFile.exists())
							oldPhotoFile.delete();
					}
				}
				imgPart.write(imgFolder+File.separator+photoName);// save photo to server
				
				item.setPhoto(photoName);// set photo name to save db
				
				
			}
			service.save(item);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/admin/items?faces-redirect=true";
	}
	
	private String getPhotoName(String uploadName) {
		String tmp=uploadName.substring(0,uploadName.lastIndexOf("."));//oldname
		String newName=item.getName()+"-"+System.currentTimeMillis();
		uploadName=uploadName.replace(tmp, newName);
		return uploadName;
	}

	public String remove(int id) {
		
		String oldPhotoName=service.findPhotoById(id);
		if(oldPhotoName != null) {
			File oldPhotoFile=new File(imgFolder+File.separator+oldPhotoName);
			if(oldPhotoFile.exists())
				oldPhotoFile.delete();
		} 
		service.remove(id);
		
		return "/admin/items?faces-redirect=true";
	}
	
	public void getItemInfo(int id) {
		item=service.findById(id);
	}
	
	public List<Item> getItems(){
		return service.findAll();
	}


	public Item getItem() {
		return item;
	}


	public void setItem(Item item) {
		this.item = item;
	}

	public Part getImgPart() {
		return imgPart;
	}

	public void setImgPart(Part imgPart) {
		this.imgPart = imgPart;
	}
	
	
}
