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

import de.bytmasoft.school.dao.enums.CommunicationType;
import de.bytmasoft.school.dao.enums.Kind;

/**
 * 
 * 
 * @author Abakar Mahamat Mahamoud
 * 
 * Date 06.04.2017
 */
@Entity
@Table(name="byt_communication")
public class Communicatation {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int  communication_id;
	
	/** The name. */
	@NotNull
	@Size(max=50)
	private String name;
	
	/**value of communication email fax....*/
	@NotNull
	@Size(max=100)
	private String commValue;
	
	/** The communication type. */
	@Enumerated(EnumType.STRING)
	private CommunicationType communicationType;
	
	/** The kind. */
	@Enumerated(EnumType.STRING)
	private Kind kind;
	
	@Version
	private Timestamp upddate;

	

	public int getCommunication_id() {
		return communication_id;
	}

	public void setCommunication_id(int communication_id) {
		this.communication_id = communication_id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getCommValue() {
		return commValue;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setCommValue(String commValue) {
		this.commValue = commValue;
	}

	/**
	 * Gets the communication type.
	 *
	 * @return the communication type
	 */
	public CommunicationType getCommunicationType() {
		return communicationType;
	}

	/**
	 * Sets the communication type.
	 *
	 * @param communicationType the new communication type
	 */
	public void setCommunicationType(CommunicationType communicationType) {
		this.communicationType = communicationType;
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
