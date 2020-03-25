package com.shop.boutik.helper.enumeration;

import com.shop.boutik.model.Color;

/**
 * Entity class for handle Store type
 * @author Guiot Olivier
 * @version 202003
 *
 */
public enum ColorEnum {
	
	White (1L, ""),
	Red (2L, ""),
	Blue (3L, ""),
	Green (4L, ""),
	Black (5L, "");
	
	private Long id = 0L;
	private String label;
	
//	CONSTRUCTOR
	ColorEnum(Long id, String label) {
		this.id = id;
		this.label = label;
	}
	
//	GETTERS

	public Long getId() {
		return this.id;
	}

	public String getLabel() {
		return this.label;
	}
	
	 public static ColorEnum getBrandEnum(Long id)
	 {
		 ColorEnum result = null;
		 
		 for (ColorEnum colorEnum : ColorEnum.values()) 
		 {
			 if ( id.equals(colorEnum.getId()) )
			 {
				 result = colorEnum;
				 break;
			 }
		 }
		 return result;
	 }
	 
	 public Color getColor() {
		 
		 Color color = new Color();
		 color.setId(this.id);
		 color.setLabel(this.label);
		 
		 return color;
	 }
	
	

}
