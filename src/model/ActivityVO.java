package com.activ_manage.model;

import java.io.Reader;
import java.io.Serializable;
import java.sql.Date;

public class ActivityVO implements Serializable {
	/*SQL���O�ഫ�Ѧ�JDBC P29*/
	private String activity_Id;
	private String activity_Name;
	private String activity_Info;
	private Date activity_Start;
	private Date activity_End;
	private String activity_Code;
	private Integer token_Amount;
	/*�N�Ϥ��]��byte[]���O�A�H�Q��bDAO���ϥ�getBytes()�BsetBytes()��VO�i��ާ@*/
	private byte[] activity_Post;
	
	/*��eclisps�۰ʲ���*/
	public String getActivity_Id() {
		return activity_Id;
	}
	public void setActivity_Id(String activity_Id) {
		this.activity_Id = activity_Id;
	}
	public String getActivity_Name() {
		return activity_Name;
	}
	public void setActivity_Name(String activity_Name) {
		this.activity_Name = activity_Name;
	}
	public String getActivity_Info() {
		return activity_Info;
	}
	public void setActivity_Info(String activity_Info) {
		this.activity_Info = activity_Info;
	}
	public Date getActivity_Start() {
		return activity_Start;
	}
	public void setActivity_Start(Date activity_Start) {
		this.activity_Start = activity_Start;
	}
	public Date getActivity_End() {
		return activity_End;
	}
	public void setActivity_End(Date activity_End) {
		this.activity_End = activity_End;
	}
	public String getActivity_Code() {
		return activity_Code;
	}
	public void setActivity_Code(String activity_Code) {
		this.activity_Code = activity_Code;
	}
	public Integer getToken_Amount() {
		return token_Amount;
	}
	public void setToken_Amount(Integer token_Amount) {
		this.token_Amount = token_Amount;
	}
	public byte[] getActivity_Post() {
		return activity_Post;
	}
	public void setActivity_Post(byte[] activity_Post) {
		this.activity_Post = activity_Post;
	}

}
