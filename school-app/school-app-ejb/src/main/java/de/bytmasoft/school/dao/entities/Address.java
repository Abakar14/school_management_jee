package de.bytmasoft.school.dao.entities;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import de.bytmasoft.school.dao.enums.Kind;

/**
 * 
 * @author Abakar Mahamat Mahamoud
 * 
 * Date 06.04.2017
 */
@Entity
@Table(name="byt_address")
public class Address {


	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int address_id;
	
	
	/** The street. */
	@NotNull
	@Size(max=100)
	private String street;
	
	/** The city. */
	@NotNull
	@Size(max=100)
	private String city;
	
	/** The country. */
	@NotNull
	@Size(max=100)
	private String country;
	
	/** The zip code. */
	@NotNull
	@Size(max=10)
	private String zipCode;
	
	/** save as String in DB. */
	@Enumerated(EnumType.STRING)
	private Kind kind;
	
	@Version
	private Timestamp upddate;

	/**
	 * 
	 * @return
	 */
	public int getAddress_id() {
		return address_id;
	}

	/**
	 * 
	 * @param address_id
	 */
	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}

	/**
	 * Gets the street.
	 *
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * Sets the street.
	 *
	 * @param street the new street
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Gets the zip code.
	 *
	 * @return the zip code
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * Sets the zip code.
	 *
	 * @param zipCode the new zip code
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * Gets the kind.
	 *
	 * @return the kind
	 */
	public Kind getKind() {
		return kind;
	}

	/**
	 * Sets the kind.
	 *
	 * @param kind the new kind
	 */
	public void setKind(Kind kind) {
		this.kind = kind;
	}
}
