package com.shop.boutik.helper.enumeration;

import com.shop.boutik.model.Size;

/**
 * Entity class for handle Store type
 * @author Guiot Olivier
 * @version 202003
 *
 */
public enum SizeEnum {
	
	XS (1L, ""),
	S (2L, ""),
	M (3L, ""),
	L (4L, ""),
	XL (5L, ""),
	XXL (6L, "");
	
	private Long id = 0L;
	private String label;
	
//	CONSTRUCTOR
	SizeEnum(Long id, String label) {
		
		this.id = id;
		this.label = label;
	}

	
//	GETTERS
	public Long getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}
	
	 public static SizeEnum getSizeEnum(Long id)
	 {
		 SizeEnum result = null;
		 
		 for (SizeEnum sizeEnum : SizeEnum.values()) 
		 {
			 if ( id.equals(sizeEnum.getId()) )
			 {
				 result = sizeEnum;
				 break;
			 }
		 }
		 return result;
	 }
	 
	 public Size getSize() {
		 
		 Size size = new Size();
		 size.setId(this.id);
		 size.setLabel(this.label);
		 
		 return size;
	 }
	

}
