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

public class Questions_domain {
	AmazonSimpleDBClient sdbClient;
	String nextToken;
	protected int count;
	private static final String REG_DOMAIN = "QUESTIONS";

	public Questions_domain() {
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

	public void AddToTable(String sques, String sbranch, String sid,
			String syear, String ssem, String sname, String sub) {
		// TODO Auto-generated method stub
		ReplaceableAttribute attributeques = new ReplaceableAttribute(
				"QUERIES", sques, Boolean.TRUE);
		ReplaceableAttribute attributebranch = new ReplaceableAttribute(
				"BRANCH", sbranch, Boolean.TRUE);
		ReplaceableAttribute attributesid = new ReplaceableAttribute("S_ID",
				sid, Boolean.TRUE);
		ReplaceableAttribute attributeyear = new ReplaceableAttribute("YEAR",
				syear, Boolean.TRUE);
		ReplaceableAttribute attributesem = new ReplaceableAttribute(
				"SEMISTER", ssem, Boolean.TRUE);
		ReplaceableAttribute attributename = new ReplaceableAttribute(
				"STUDENT_NAME", sname, Boolean.TRUE);
		ReplaceableAttribute sub1 = new ReplaceableAttribute("SUBJECT", sub,
				Boolean.TRUE);
		ReplaceableAttribute stats = new ReplaceableAttribute("STATUS",
				"NotAnswered", Boolean.TRUE);

		List<ReplaceableAttribute> attrs = new ArrayList<ReplaceableAttribute>(
				2);
		attrs.add(attributeques);
		attrs.add(attributebranch);
		attrs.add(attributesid);
		attrs.add(attributeyear);
		attrs.add(attributesem);
		attrs.add(attributename);
		attrs.add(sub1);

		attrs.add(stats);

		PutAttributesRequest par = new PutAttributesRequest(REG_DOMAIN, sques,
				attrs);
		try {
			sdbClient.putAttributes(par);
		} catch (Exception exception) {
			System.out.println("EXCEPTION = " + exception);
		}

	}

	public List<Others> getsquestions(String ss1, String ss2, String ss3,
			String ss4) {
		//BRANCH='" + ss1 + "' and error
		// TODO Auto-generated method stub
		SelectRequest selectRequest = new SelectRequest(
				"select QUERIES from QUESTIONS where YEAR='" + ss2+ "' and SEMISTER='" + ss3
						+ "' and SUBJECT='" + ss4
						+ "' and STATUS='NotAnswered'")
				.withConsistentRead(true);
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
		return this.getAllStringAttribute("QUERIES", item.getAttributes());
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

	public void AddToTable1(String type, String ques, String name, String aaa) {
		// TODO Auto-generated method stub
		ReplaceableAttribute attributeques = new ReplaceableAttribute("TYPE",
				type, Boolean.TRUE);
		ReplaceableAttribute attributebranch = new ReplaceableAttribute(
				"FNAME", name, Boolean.TRUE);
		ReplaceableAttribute stats1 = new ReplaceableAttribute("Answer",
				aaa, Boolean.TRUE);

		ReplaceableAttribute stats = new ReplaceableAttribute("STATUS",
				"Answered", Boolean.TRUE);

		List<ReplaceableAttribute> attrs = new ArrayList<ReplaceableAttribute>(
				2);
		attrs.add(attributeques);
		attrs.add(attributebranch);
		attrs.add(stats1);

		attrs.add(stats);

		PutAttributesRequest par = new PutAttributesRequest(REG_DOMAIN, ques,
				attrs);
		try {
			sdbClient.putAttributes(par);
		} catch (Exception exception) {
			System.out.println("EXCEPTION = " + exception);
		}
	}
}
