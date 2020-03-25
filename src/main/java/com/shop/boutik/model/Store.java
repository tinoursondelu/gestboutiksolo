package com.shop.boutik.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entity class for handle Store type
 * @author Guiot Olivier
 * @version 202003
 *
 */

@Data
@EqualsAndHashCode(callSuper=false)
@Valid
@Entity
@Table(name = "stores")
public class Store extends Auditable implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotNull
	@Length(min = 3, max = 30)
	@Column(nullable = false, unique = true)
	private String designation;

	@OneToMany(mappedBy = "store")
	private Collection<Shelve> shelves;

	@ManyToMany(mappedBy = "stores")
	private Collection<ItemStore> itemsStore;

	@ManyToMany(mappedBy = "stores")
	private Collection<User> users;

}
