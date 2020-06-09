package com.example.kemei.entity;

import org.springframework.stereotype.Component;

public class Baidu {
	  //用户的AK
      private String ak="d0QqTL3YQ3FTbVT1vS6UTYCY0j0hlME9";
      
      private int service_id=206370;
      
      private String entityName;
      
      private String entityDesc;
      
      private String columnKey;
      
      private String sn;

	public String getAk() {
		return ak;
	}

	public void setAk(String ak) {
		this.ak = ak;
	}

	public int getService_id() {
		return service_id;
	}

	public void setService_id(int service_id) {
		this.service_id = service_id;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getEntityDesc() {
		return entityDesc;
	}

	public void setEntityDesc(String entityDesc) {
		this.entityDesc = entityDesc;
	}

	public String getColumnKey() {
		return columnKey;
	}

	public void setColumnKey(String columnKey) {
		this.columnKey = columnKey;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}
      
      
}
