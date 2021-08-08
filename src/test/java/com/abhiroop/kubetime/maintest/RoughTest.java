package com.abhiroop.kubetime.maintest;




import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class RoughTest {

	
	public static void man(String ...a) {
		
		
		RoughTest r = new RoughTest();
		try {
			r.urltest("malik");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
String urltest(String value) throws MalformedURLException {
	String s="http://abc.com/red/${value}/new";
	
	URL u = new URL(s);
	
	System.out.println(u);
	System.out.println(u.getAuthority());
	return s;
}

	
	List<String> applyClusterDetailTest() throws ParseException{
		
		String json = "{\r\n"
				+ "  \"kind\": \"NamespaceList\",\r\n"
				+ "  \"apiVersion\": \"v1\",\r\n"
				+ "  \"metadata\": {\r\n"
				+ "    \"selfLink\": \"/api/v1/namespaces\",\r\n"
				+ "    \"resourceVersion\": \"25733207\"\r\n"
				+ "  },\r\n"
				+ "  \"items\": [\r\n"
				+ "    {\r\n"
				+ "      \"metadata\": {\r\n"
				+ "        \"name\": \"test\",\r\n"
				+ "        \"selfLink\": \"/api/v1/namespaces/test\",\r\n"
				+ "        \"uid\": \"e19c8f36-c61a-4d3d-9ea0-61f370518e68\",\r\n"
				+ "        \"resourceVersion\": \"25700595\",\r\n"
				+ "        \"creationTimestamp\": \"2021-07-08T06:55:25Z\",\r\n"
				+ "        \"labels\": {\r\n"
				+ "          \"namespace.metadata.stackrox.io/name\": \"test\",\r\n"
				+ "          \"new-label\": \"billing\"\r\n"
				+ "        },\r\n"
				+ "        \"annotations\": {\r\n"
				+ "          \"modified-by.stackrox.io/namespace-label-patcher\": \"true\",\r\n"
				+ "          \"openshift.io/description\": \"\",\r\n"
				+ "          \"openshift.io/display-name\": \"\",\r\n"
				+ "          \"openshift.io/requester\": \"admin\",\r\n"
				+ "          \"openshift.io/sa.scc.mcs\": \"s0:c16,c15\",\r\n"
				+ "          \"openshift.io/sa.scc.supplemental-groups\": \"1000270000/10000\",\r\n"
				+ "          \"openshift.io/sa.scc.uid-range\": \"1000270000/10000\"\r\n"
				+ "        },\r\n"
				+ "        \"managedFields\": [\r\n"
				+ "          {\r\n"
				+ "            \"manager\": \"manager\",\r\n"
				+ "            \"operation\": \"Update\",\r\n"
				+ "            \"apiVersion\": \"v1\",\r\n"
				+ "            \"time\": \"2021-07-08T06:55:25Z\",\r\n"
				+ "            \"fieldsType\": \"FieldsV1\",\r\n"
				+ "            \"fieldsV1\": {\"f:metadata\":{\"f:annotations\":{\".\":{},\"f:openshift.io/description\":{},\"f:openshift.io/display-name\":{},\"f:openshift.io/requester\":{},\"f:openshift.io/sa.scc.mcs\":{},\"f:openshift.io/sa.scc.supplemental-groups\":{},\"f:openshift.io/sa.scc.uid-range\":{}}},\"f:status\":{\"f:phase\":{}}}\r\n"
				+ "          },\r\n"
				+ "          {\r\n"
				+ "            \"manager\": \"kubernetes-sensor\",\r\n"
				+ "            \"operation\": \"Update\",\r\n"
				+ "            \"apiVersion\": \"v1\",\r\n"
				+ "            \"time\": \"2021-07-11T11:46:29Z\",\r\n"
				+ "            \"fieldsType\": \"FieldsV1\",\r\n"
				+ "            \"fieldsV1\": {\"f:metadata\":{\"f:annotations\":{\"f:modified-by.stackrox.io/namespace-label-patcher\":{}},\"f:labels\":{\".\":{},\"f:namespace.metadata.stackrox.io/name\":{}}}}\r\n"
				+ "          },\r\n"
				+ "          {\r\n"
				+ "            \"manager\": \"kubectl-label\",\r\n"
				+ "            \"operation\": \"Update\",\r\n"
				+ "            \"apiVersion\": \"v1\",\r\n"
				+ "            \"time\": \"2021-08-06T07:34:43Z\",\r\n"
				+ "            \"fieldsType\": \"FieldsV1\",\r\n"
				+ "            \"fieldsV1\": {\"f:metadata\":{\"f:labels\":{\"f:new-label\":{}}}}\r\n"
				+ "          }\r\n"
				+ "        ]\r\n"
				+ "      },\r\n"
				+ "      \"spec\": {\r\n"
				+ "        \"finalizers\": [\r\n"
				+ "          \"kubernetes\"\r\n"
				+ "        ]\r\n"
				+ "      },\r\n"
				+ "      \"status\": {\r\n"
				+ "        \"phase\": \"Active\"\r\n"
				+ "      }\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"metadata\": {\r\n"
				+ "        \"name\": \"userworkload\",\r\n"
				+ "        \"selfLink\": \"/api/v1/namespaces/userworkload\",\r\n"
				+ "        \"uid\": \"614787d2-58c1-4f7f-908c-600672662ccc\",\r\n"
				+ "        \"resourceVersion\": \"25701172\",\r\n"
				+ "        \"creationTimestamp\": \"2021-07-20T08:46:54Z\",\r\n"
				+ "        \"labels\": {\r\n"
				+ "          \"namespace.metadata.stackrox.io/name\": \"userworkload\",\r\n"
				+ "          \"new-label\": \"billing\",\r\n"
				+ "          \"olm.operatorgroup.uid/196d9b8f-52fd-4bbd-8d3d-c3bdb88f34ae\": \"\"\r\n"
				+ "        },\r\n"
				+ "        \"annotations\": {\r\n"
				+ "          \"modified-by.stackrox.io/namespace-label-patcher\": \"true\",\r\n"
				+ "          \"openshift.io/description\": \"\",\r\n"
				+ "          \"openshift.io/display-name\": \"\",\r\n"
				+ "          \"openshift.io/requester\": \"admin\",\r\n"
				+ "          \"openshift.io/sa.scc.mcs\": \"s0:c26,c10\",\r\n"
				+ "          \"openshift.io/sa.scc.supplemental-groups\": \"1000670000/10000\",\r\n"
				+ "          \"openshift.io/sa.scc.uid-range\": \"1000670000/10000\"\r\n"
				+ "        },\r\n"
				+ "        \"managedFields\": [\r\n"
				+ "          {\r\n"
				+ "            \"manager\": \"cluster-policy-controller\",\r\n"
				+ "            \"operation\": \"Update\",\r\n"
				+ "            \"apiVersion\": \"v1\",\r\n"
				+ "            \"time\": \"2021-07-20T08:46:54Z\",\r\n"
				+ "            \"fieldsType\": \"FieldsV1\",\r\n"
				+ "            \"fieldsV1\": {\"f:metadata\":{\"f:annotations\":{\"f:openshift.io/sa.scc.mcs\":{},\"f:openshift.io/sa.scc.supplemental-groups\":{},\"f:openshift.io/sa.scc.uid-range\":{}}}}\r\n"
				+ "          },\r\n"
				+ "          {\r\n"
				+ "            \"manager\": \"kubernetes-sensor\",\r\n"
				+ "            \"operation\": \"Update\",\r\n"
				+ "            \"apiVersion\": \"v1\",\r\n"
				+ "            \"time\": \"2021-07-20T08:46:54Z\",\r\n"
				+ "            \"fieldsType\": \"FieldsV1\",\r\n"
				+ "            \"fieldsV1\": {\"f:metadata\":{\"f:annotations\":{\"f:modified-by.stackrox.io/namespace-label-patcher\":{}},\"f:labels\":{\".\":{},\"f:namespace.metadata.stackrox.io/name\":{}}}}\r\n"
				+ "          },\r\n"
				+ "          {\r\n"
				+ "            \"manager\": \"openshift-apiserver\",\r\n"
				+ "            \"operation\": \"Update\",\r\n"
				+ "            \"apiVersion\": \"v1\",\r\n"
				+ "            \"time\": \"2021-07-20T08:46:54Z\",\r\n"
				+ "            \"fieldsType\": \"FieldsV1\",\r\n"
				+ "            \"fieldsV1\": {\"f:metadata\":{\"f:annotations\":{\".\":{},\"f:openshift.io/description\":{},\"f:openshift.io/display-name\":{},\"f:openshift.io/requester\":{}}},\"f:status\":{\"f:phase\":{}}}\r\n"
				+ "          },\r\n"
				+ "          {\r\n"
				+ "            \"manager\": \"openshift-controller-manager\",\r\n"
				+ "            \"operation\": \"Update\",\r\n"
				+ "            \"apiVersion\": \"v1\",\r\n"
				+ "            \"time\": \"2021-07-20T08:46:54Z\",\r\n"
				+ "            \"fieldsType\": \"FieldsV1\",\r\n"
				+ "            \"fieldsV1\": {\"f:spec\":{\"f:finalizers\":{}}}\r\n"
				+ "          },\r\n"
				+ "          {\r\n"
				+ "            \"manager\": \"olm\",\r\n"
				+ "            \"operation\": \"Update\",\r\n"
				+ "            \"apiVersion\": \"v1\",\r\n"
				+ "            \"time\": \"2021-08-03T06:34:34Z\",\r\n"
				+ "            \"fieldsType\": \"FieldsV1\",\r\n"
				+ "            \"fieldsV1\": {\"f:metadata\":{\"f:labels\":{\"f:olm.operatorgroup.uid/196d9b8f-52fd-4bbd-8d3d-c3bdb88f34ae\":{}}}}\r\n"
				+ "          },\r\n"
				+ "          {\r\n"
				+ "            \"manager\": \"kubectl-label\",\r\n"
				+ "            \"operation\": \"Update\",\r\n"
				+ "            \"apiVersion\": \"v1\",\r\n"
				+ "            \"time\": \"2021-08-06T07:35:36Z\",\r\n"
				+ "            \"fieldsType\": \"FieldsV1\",\r\n"
				+ "            \"fieldsV1\": {\"f:metadata\":{\"f:labels\":{\"f:new-label\":{}}}}\r\n"
				+ "          }\r\n"
				+ "        ]\r\n"
				+ "      },\r\n"
				+ "      \"spec\": {\r\n"
				+ "        \"finalizers\": [\r\n"
				+ "          \"kubernetes\"\r\n"
				+ "        ]\r\n"
				+ "      },\r\n"
				+ "      \"status\": {\r\n"
				+ "        \"phase\": \"Active\"\r\n"
				+ "      }\r\n"
				+ "    }\r\n"
				+ "  ]\r\n"
				+ "}";
		
		
		
		
		
		return getNameSpaceNames(json);
	}
	public static String getDataValue(String jsonString, String[] tagList) throws ParseException {
		String result = null;
		Object o = null;
		if (jsonString != null && tagList != null && tagList.length > 0) {
			JSONObject jo = (JSONObject) new JSONParser().parse(jsonString);
			for (int i = 0; i < tagList.length; i++) {

				o = jo.get(tagList[i]);
				if (o instanceof String) {
					result = (String) o;
					break;
				} else if (o instanceof JSONObject) {
					jo = (JSONObject) o;
				}
			}

			if (result == null) {
				result = jo.toJSONString();
			}
		}
		return result;
	}
	
	public static List<String> getNameSpaceNames(String jsonString) throws ParseException {
		String result = null;
		List<String> nameList = new ArrayList<>();
		if (jsonString != null ) {
			JSONObject jo = (JSONObject) new JSONParser().parse(jsonString);
			JSONArray ja= (JSONArray) jo.get("items");
			for (Object o : ja) {
				nameList.add(getDataValue(o.toString(),new String[] {  "metadata","name" })); 
	        }
		}
		
		
		return nameList;
		
	}
}
