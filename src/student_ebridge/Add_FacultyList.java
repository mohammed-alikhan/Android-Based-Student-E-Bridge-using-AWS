package com.coign.student_ebridge;

import java.util.ArrayList;
import java.util.List;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpledb.AmazonSimpleDBClient;
import com.amazonaws.services.simpledb.model.Attribute;
import com.amazonaws.services.simpledb.model.CreateDomainRequest;
import com.amazonaws.services.simpledb.model.Item;
import com.amazonaws.services.simpledb.model.PutAttributesRequest;
import com.amazonaws.services.simpledb.model.ReplaceableAttribute;
import com.amazonaws.services.simpledb.model.SelectRequest;
import com.amazonaws.services.simpledb.model.SelectResult;

public class Add_FacultyList {
	AmazonSimpleDBClient sdbClient;
	String nextToken;
	protected int count;
	private static final String REG_DOMAIN = "FACULTY_REG";

	public Add_FacultyList() {
		// TODO Auto-generated constructor stub

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
	public void AddToTable(String s1,String s2, String s3, String s4, String s5, String s6) {
		// TODO Auto-generated method stub
		ReplaceableAttribute attributename = new ReplaceableAttribute("FACULTY_NAME",
				s1, Boolean.TRUE);
		ReplaceableAttribute attributeid = new ReplaceableAttribute("F_ID",
				s2, Boolean.TRUE);
		ReplaceableAttribute attributesub = new ReplaceableAttribute("SUBJECT",
				s3, Boolean.TRUE);
		ReplaceableAttribute attributeyear = new ReplaceableAttribute("YEAR",
				s4, Boolean.TRUE);
		ReplaceableAttribute attributesem = new ReplaceableAttribute("SEMISTER",
				s5, Boolean.TRUE);
		ReplaceableAttribute attributebranch = new ReplaceableAttribute("BRANCH",
				s6, Boolean.TRUE);
		ReplaceableAttribute pass = new ReplaceableAttribute("PASSWORD",
				"1234", Boolean.TRUE);
		List<ReplaceableAttribute> attrs = new ArrayList<ReplaceableAttribute>(2);
		attrs.add(attributename);
		attrs.add(attributeid);
		attrs.add(attributesub);
		attrs.add(attributeyear);
		attrs.add(attributesem);
		attrs.add(attributebranch);	
		attrs.add(pass);
		PutAttributesRequest par = new PutAttributesRequest(REG_DOMAIN,s2, attrs);
		try {
			sdbClient.putAttributes(par);
		} catch (Exception exception) {
			System.out.println("EXCEPTION = " + exception);
		}
	}
	public String loginVerify(String sname, String spass) {
		// TODO Auto-generated method stub
		String flag;
		SelectRequest selectRequest = new SelectRequest(
				"select * from FACULTY_REG where F_ID='" + sname
						+ "' and PASSWORD='" + spass + "'")
				.withConsistentRead(true);
		selectRequest.setNextToken(this.nextToken);

		SelectResult response = this.sdbClient.select(selectRequest);
		//@SuppressWarnings("unchecked")
		List<String> ls = response.getItems();
		System.out.println("@@" + ls.size());
		System.out.println("@@" + response.getItems());
		this.nextToken = response.getNextToken();
		System.out.println("@@" + this.nextToken);
		
		String[] a1 =  response.getItems().toString().split(",");

		String sub = a1[9];
		System.out.println("QQQQQQQQQ@@@@@@@@@!!!!!!!!!!!" + sub);
		String[] na = sub.split(":");
	    String fnaam = na[1];
	    
	    String s=fnaam;

		if (ls.size() > 0) {
			flag = sub;
		} else {
			sub ="Sorry";
		}
		return fnaam;
	}
	
	public List<Others> getAllValues(String s3,
			String s1, String s4) {
		// TODO Auto-generated method stub
		
		SelectRequest selectRequest = new SelectRequest(
				"select SUBJECT from SUBJECT_REG where YEAR='"
						+ s3 + "' and BRANCH='"+s1+"' and SEM='"+s4+"'").withConsistentRead(true);
		selectRequest.setNextToken(this.nextToken);

		SelectResult response = this.sdbClient.select(selectRequest);

		/* List<String> ls= response.getItems(); */

		/* return this.valuesGetting(response.getItems()); */
		// System.out.println("image names            "+valuesGetting(response.getItems()));
		System.out.println("hello          " + response.getItems().toString());
		return valuesGetting(response.getItems());
		
		
	}


	private List<Others> valuesGetting(List<Item> items) {
		// TODO Auto-generated method stub
		ArrayList<Others> alldata = new ArrayList<Others>(items.size());

		for (Item item : items) {
			alldata.add(this.individulaData(item));
		}

		System.out.println("all data size        " + alldata.size());
		for (int i = 0; i < alldata.size(); i++) {
			System.out.println(" name  " + alldata.get(i));
		}
		return alldata;
	}


	private Others individulaData(Item item) {
		// TODO Auto-generated method stub
		return new Others(this.getimagenames(item));
	}


	private String getimagenames(Item item) {
		// TODO Auto-generated method stub
		return this.getAllStringAttribute("SUBJECT", item.getAttributes());
	}


	private String getAllStringAttribute(String usernameAttribute,
			List<Attribute> list) {
		// TODO Auto-generated method stub
		for (Attribute attrib : list) {
			if (attrib.getName().equals(usernameAttribute)) {
				return attrib.getValue();
			}
		}

		return "";
	}
	public String getdataa(String s1, String s2, String syear, String ssem) {
		// TODO Auto-generated method stub
		String fnaam;
		SelectRequest selectRequest = new SelectRequest(
	"select * from FACULTY_REG where F_ID='" +s1+ "' and YEAR='"+syear+"' and SEMISTER='"+ssem+"'")
				.withConsistentRead(true);
		selectRequest.setNextToken(this.nextToken);
/*System.out.println("%^$%$%%"+"select * from FACULTY_REG where F_ID='" +s1
						+ "' and BRANCH='" +s2 + "' and YEAR='"+syear+"' and SEMISTER='"+ssem+"'");*/
		SelectResult response = this.sdbClient.select(selectRequest);
		System.out.println("GGGGGGGGGGeeeeeeeeeeetttttttingggggggggg"+response.getItems().toString());
		List<String> ls = response.getItems();
		System.out.println("@@" + ls.size());
		System.out.println("@@" + response.getItems());
		this.nextToken = response.getNextToken();
		System.out.println("@@" + this.nextToken);
		if(ls.size()==0){
			fnaam="Sorry";
		}else{
		String[] a1 =  response.getItems().toString().split(",");

		String sub = a1[19];
		System.out.println("QQQQQQQfacultyyyyyyy sbjectttt" + sub);
		String[] na = sub.split(":");
	     fnaam = na[1];
		}
		return fnaam;
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