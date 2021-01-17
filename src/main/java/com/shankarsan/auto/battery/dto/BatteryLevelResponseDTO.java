package com.shankarsan.auto.battery.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class BatteryLevelResponseDTO extends BaseResponseDTO{
	
	private String id;
	private int level;
	private String level_id;
	private String passcode;

	/**
	 * @return the passcode
	 */
	public String getPasscode() {
		return passcode;
	}

	/**
	 * @param passcode the passcode to set
	 */
	public void setPasscode(String passcode) {
		this.passcode = passcode;
	}

	/**
	 * @return the level_id
	 */
	public String getLevel_id() {
		return level_id;
	}

	/**
	 * @param level_id the level_id to set
	 */
	public void setLevel_id(String level_id) {
		this.level_id = level_id;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}
}
