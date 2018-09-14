package org.scriptonbasestar.tool.http.server;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

/**
 * ver 1.4.5 Converter is deprecated
 */
public class MapEntryConverter implements Converter {

	public boolean canConvert(Class clazz) {
		return AbstractMap.class.isAssignableFrom(clazz);
	}

	public void marshal(Object value, HierarchicalStreamWriter writer,
						MarshallingContext context) {

		AbstractMap map = (AbstractMap) value;
		for (Object obj : map.entrySet()) {
			Map.Entry entry = (Map.Entry) obj;
			writer.startNode(entry.getKey().toString());
			writer.setValue(entry.getValue().toString());
			writer.endNode();
		}
	}

	public Object unmarshal(HierarchicalStreamReader reader,
							UnmarshallingContext context) {

		Map<String, String> map = new HashMap<String, String>();

		while (reader.hasMoreChildren()) {
			reader.moveDown();

			String key = reader.getNodeName();
			String value = reader.getValue();
			map.put(key, value);

			reader.moveUp();
		}

		return map;
	}

}