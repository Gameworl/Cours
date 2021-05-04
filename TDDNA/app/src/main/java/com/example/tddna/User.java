package com.example.tddna;

import java.io.Serializable;

/**
 * Classe d'utilisateur
 */
public class User implements Serializable {

	/**
	 * L'id de l'utilisateur
	 */
	private int id;

    /**
     * Le nom d'utilisateur
     */
    private String name;

    /**
     * Son numero
     */
    private String number;

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
