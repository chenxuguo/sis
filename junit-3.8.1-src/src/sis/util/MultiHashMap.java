package sis.util;

import java.util.*;
import java.util.Map.Entry;

public class MultiHashMap<K, V> {
	private Map<K, List<V>> map = new HashMap<K, List<V>>();
	
	public int size() {
		return map.size();
	}
	public void put(K key, V value) {
		List<V> values = map.get(key);
		if (values == null) {
			values = new ArrayList<V>();
		}
		values.add(value);
		map.put(key,  values);
	}
	
	public List<V> get(K key){
		return map.get(key);
	}
	
	public Set<K> keys() {
		return map.keySet();
	}
	public Set<Map.Entry<K, List<V>>> entrySet() {
		return map.entrySet();
	}
	public interface Filter<T> {
		boolean apply(T item);
	}
	public static <K, V> void filter (
			final MultiHashMap<K, ? super V> target,
			final MultiHashMap<K, V> source,
			final Filter<? super V> filter) {
		for (K key : source.keys()) {
			final List<V> values = source.get(key);
			for (V value : values)
				if (filter.apply(value))
					target.put(key, value);
		}
		
	}
}
