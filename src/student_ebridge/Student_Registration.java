package com.coign.student_ebridge;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpledb.AmazonSimpleDBClient;
import com.amazonaws.services.simpledb.model.CreateDomainRequest;
import com.amazonaws.services.simpledb.model.PutAttributesRequest;
import com.amazonaws.services.simpledb.model.ReplaceableAttribute;
import com.amazonaws.services.simpledb.model.SelectRequest;
import com.amazonaws.services.simpledb.model.SelectResult;

public class Student_Registration {
	
	AmazonSimpleDBClient sdbClient;
	String nextToken;
	protected int count;
	private static final String REG_DOMAIN = "STUDENT_REG";

	public Student_Registration() {
		// TODO Auto-generated constructor stub
		// AWS credentials from Constants.java
		AWSCredentials credentials = new BasicAWSCredentials(
				Constants.ACCESS_KEY_ID, Constants.SECRET_KEY);
		sdbClient = new AmazonSimpleDBClient(credentials);
		this.nextToken = null;
		this.count = -1;
	}
	public void createDomain() {
		// TODO Auto-generated method stub
		CreateDomainRequest cr = new CreateDomainRequest(REG_DOMAIN);
		sdbClient.createDomain(cr);

}
	public void AddToTable(String syear, String ssem, String sbranch,
			String sstuname, String shall, String semail, String sphn) {
		// TODO Auto-generated method stub
		ReplaceableAttribute attributename = new ReplaceableAttribute("YEAR", 
				syear, Boolean.TRUE);
		ReplaceableAttribute attributesem = new ReplaceableAttribute("SEMISTER",
				ssem, Boolean.TRUE);
		ReplaceableAttribute attributebranch = new ReplaceableAttribute("BRANCH",
				sbranch, Boolean.TRUE);
		ReplaceableAttribute attributestuname = new ReplaceableAttribute("STUDENT_NAME",
				sstuname, Boolean.TRUE);
		ReplaceableAttribute attributehall = new ReplaceableAttribute("HALLTICKET",
				shall, Boolean.TRUE);
		ReplaceableAttribute attributeemail = new ReplaceableAttribute("EMAIL_ID",
				semail, Boolean.TRUE);
		ReplaceableAttribute attributephone = new ReplaceableAttribute("PHONE",
				sphn, Boolean.TRUE);
		ReplaceableAttribute pass = new ReplaceableAttribute("PASSWORD",
				"1234", Boolean.TRUE);

		List<ReplaceableAttribute> attrs = new ArrayList<ReplaceableAttribute>(2);
		attrs.add(attributename);
		attrs.add(attributesem);
		attrs.add(attributebranch);
		attrs.add(attributestuname);
		attrs.add(attributehall);
		attrs.add(attributeemail);
		attrs.add(attributephone);
		attrs.add(pass);

		PutAttributesRequest par = new PutAttributesRequest(REG_DOMAIN, shall, attrs);
		try {
			sdbClient.putAttributes(par);
		} catch (Exception exception) {
			System.out.println("EXCEPTION = " + exception);
		}
	}
	public String loginVerify(String sname, String spass) {
		// TODO Auto-generated method stub
		String flag ;
		SelectRequest selectRequest = new SelectRequest(
				"select * from STUDENT_REG where HALLTICKET='" + sname
						+ "' and PASSWORD='" + spass + "'").withConsistentRead(true);
		selectRequest.setNextToken(this.nextToken);
		SelectResult response = this.sdbClient.select(selectRequest);
		List<String> ls = response.getItems();
		System.out.println("@@" + ls.size());
		System.out.println("@@" + response.getItems());
		this.nextToken = response.getNextToken();
		System.out.println("@@" + this.nextToken);
		
		
		String[] a1 =  response.getItems().toString().split(",");

		String branch = a1[4];
		System.out.println("QQQQQQQQQ@@@@@@@@@!!!!!!!!!!!" + branch);
		String[] na = branch.split(":");
	    String naam = na[1];

		String hall = a1[9];
		System.out.println("QQQQQQQQQ@@@@@@@@@!!!!!!!!!!!" + hall);
		String[] desc = hall.split(":");
		String dec1 = desc[1];

		String year = a1[14];
		System.out.println("QQQQQQQQQ@@@@@@@@@#######" + year);
		String[] addr1 = year.split(":");
		final String addr2 = addr1[1];

		String sem = a1[34];
		System.out.println("QQQQQQQQQ@@@@@@@@@" + sem);
		String[] phn2 = sem.split(":");
		String co1 = phn2[1];
		String s=naam+"@"+dec1+"@"+addr2+"@"+co1;
		System.out.println("@#$$%%"+s);
		if (ls.size() > 0) {
			flag = s;
		} else {
			flag = "Sorry";
		}
		return flag;
}
	public void AddToTable2(String sname, String spass) {
		// TODO Auto-generated method stub
		ReplaceableAttribute pass = new ReplaceableAttribute("PASSWORD",
				spass, Boolean.TRUE);

		List<ReplaceableAttribute> attrs = new ArrayList<ReplaceableAttribute>(2);
		attrs.add(pass);
		PutAttributesRequest par = new PutAttributesRequest(REG_DOMAIN, sname, attrs);
		try {
			sdbClient.putAttributes(par);
		} catch (Exception exception) {
			System.out.println("EXCEPTION = " + exception);
		}
	}
}
