 package model;

import java.io.Serializable;

import java.util.Objects;

public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private String description;

	public Category() {
	}

	public Category(int id, String name) {
		this.id = id;
		this.name = name;
		this.description = "";
	}

	public Category(int id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		return Objects.equals(description, other.description) && id == other.id && Objects.equals(name, other.name);
	}

}


