package com.coign.student_ebridge;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpledb.AmazonSimpleDBClient;
import com.amazonaws.services.simpledb.model.CreateDomainRequest;
import com.amazonaws.services.simpledb.model.PutAttributesRequest;
import com.amazonaws.services.simpledb.model.ReplaceableAttribute;

public class Add_Subjectlist {
	AmazonSimpleDBClient sdbClient;
	String nextToken;
	protected int count;
	private static final String REG_DOMAIN = "SUBJECT_REG";

	public Add_Subjectlist() {
		// TODO Auto-generated constructor stub

		AWSCredentials credentials = new BasicAWSCredentials(Constants.ACCESS_KEY_ID, Constants.SECRET_KEY);
		sdbClient = new AmazonSimpleDBClient(credentials);
		this.nextToken = null;
		this.count = -1;
	}
	public void createDomain() {
		// TODO Auto-generated method stub
		CreateDomainRequest cr = new CreateDomainRequest(REG_DOMAIN);
		sdbClient.createDomain(cr);

}
	public void AddToTable(String s1,String s2, String s3, String s4, String s5) {
		// TODO Auto-generated method stub
		ReplaceableAttribute attributename = new ReplaceableAttribute("YEAR",
				s1, Boolean.TRUE);
		ReplaceableAttribute attributesem = new ReplaceableAttribute("SEM",
				s2, Boolean.TRUE);
		ReplaceableAttribute attributebranch = new ReplaceableAttribute("BRANCH",
				s3, Boolean.TRUE);
		ReplaceableAttribute attributefid = new ReplaceableAttribute("F_ID",
				s4, Boolean.TRUE);
		ReplaceableAttribute attributesub = new ReplaceableAttribute("SUBJECT",
				s5, Boolean.TRUE);
		
		List<ReplaceableAttribute> attrs = new ArrayList<ReplaceableAttribute>(2);
		attrs.add(attributename);
		attrs.add(attributesem);
		attrs.add(attributebranch);
		attrs.add(attributefid);
		attrs.add(attributesub);
		
		PutAttributesRequest par = new PutAttributesRequest(REG_DOMAIN, s5, attrs);
		try {
			sdbClient.putAttributes(par);
		} catch (Exception exception) {
			System.out.println("EXCEPTION = " + exception);
		}
	}




}
