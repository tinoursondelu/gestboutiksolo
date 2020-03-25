package com.shop.boutik.helper.enumeration;

import com.shop.boutik.model.Brand;

/**
 * Entity class for handle Store type
 * @author Guiot Olivier
 * @version 202003
 *
 */
public enum BrandEnum {
	
	Nike (1L, "Nike"),
	Pumas (2L, "Pumas");
	
	private Long id = 0L;
	private String label;
	
//	CONSTRUCTOR
	BrandEnum(Long id, String label) {
		
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
	
	 public static BrandEnum getBrandEnum(Long id)
	 {
		 BrandEnum result = null;
		 
		 for (BrandEnum brandEnum : BrandEnum.values()) 
		 {
			 if ( id.equals(brandEnum.getId()) )
			 {
				 result = brandEnum;
				 break;
			 }
		 }
		 return result;
	 }
	 
	 public Brand getBrand() {
		 
		 Brand brand = new Brand();
		 brand.setId(this.id);
		 brand.setLabel(this.label);
		 
		 return brand;
	 }
	

}
