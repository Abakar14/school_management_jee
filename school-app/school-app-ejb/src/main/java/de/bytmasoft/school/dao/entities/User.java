package de.bytmasoft.school.dao.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import de.bytmasoft.school.dao.enums.Gender;
import de.bytmasoft.school.dao.enums.Relationship;

/**
 * 
 * 
 * @author Abakar Mahamat Mahamoud
 * 
 * Date 06.04.2017
 */

@Entity
@Table(name="byt_user")
@NamedQuery(name = User.QUERY_GETALL, query="SELECT u FROM User u")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4574836308110077029L;


	public static final String QUERY_GETALL = "User.getAll";

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int user_id;
	
	/** The first name. */
	@NotNull
	@Size(min=1,max=25)
	private String loginName;
	
	/** The first name. */
	@NotNull
	@Size(min=5)
	private String password;
	
	/** The first name. */
	@NotNull
	@Size(min=1,max=50)
	private String firstName;
	
	/** The last name. */
	@NotNull
	@Size(min=1,max=100)
	private String lastName;
	
	/** The gender. */
	private Gender gender;
	
	/** The relationship. */
	private Relationship relationship;
	
	/** The birthday. */
	private Date birthday;
	
	@Version
	private Timestamp lastChanged;
	
	
	/** The addresses. with cascade all to save the change automatic in address
	 * take care of the orphanRemoval=true that mean if we delete user that their 
	 * Communication deleted automatically */
//	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
	@OneToMany(cascade=CascadeType.ALL)
	private List<Address> addresses;
	
	
	/** The communications. save communication change automatic 
	 * take care of the orphanRemoval=true that mean if we delete user that their 
	 * Communication deleted automatically*/
	@OneToMany(cascade=CascadeType.ALL)
	private List<Communication> communications;	
	
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Gets the birthday.
	 *
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}
	
	/**
	 * Sets the birthday.
	 *
	 * @param birthday the new birthday
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	/**
	 * Gets the gender.
	 *
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}
	
	/**
	 * Sets the gender.
	 *
	 * @param gender the new gender
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	/**
	 * Gets the relationship.
	 *
	 * @return the relationship
	 */
	public Relationship getRelationship() {
		return relationship;
	}
	
	/**
	 * Sets the relationship.
	 *
	 * @param relationship the new relationship
	 */
	public void setRelationship(Relationship relationship) {
		this.relationship = relationship;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<Communication> getCommunications() {
		return communications;
	}

	public void setCommunications(List<Communication> communications) {
		this.communications = communications;
	}

	/**
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * @param loginName the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the lastChanged
	 */
	public Timestamp getLastChanged() {
		return lastChanged;
	}

	/**
	 * @param lastChanged the lastChanged to set
	 */
	public void setLastChanged(Timestamp lastChanged) {
		this.lastChanged = lastChanged;
	}
	

}
