/**
 * 
 */
package de.bytmasoft.school.dao.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Abakar Mahamat Mahamoud
 * 
 * Date 06.04.2017 
 */
public class QueryParameter {


	private Map<String, Object> params = null;

	private QueryParameter(String name, Object value) {

		this.params = new HashMap<String, Object>();

		this.params.put(name, value);

	}

	public static QueryParameter with(String name, Object value) {
		return new QueryParameter(name, value);

	}

	public QueryParameter and(String name, Object value) {
		this.params.put(name, value);
		return this;

	}

	public Map<String, ? extends Object> params() {
		return this.params;
	}
}
