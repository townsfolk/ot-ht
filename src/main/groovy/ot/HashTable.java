package ot;

/**
 * Created by elberry on 12/16/16.
 */
public class HashTable<K, V> {

	private class LinkedEntry<EK, EV> {
		EK key;
		LinkedEntry<EK, EV> next;
		EV value;

		public LinkedEntry() {
		}

		public LinkedEntry(EK key, EV value) {
			this.key = key;
			this.value = value;
		}

		public LinkedEntry(EK key, EV value, LinkedEntry<EK, EV> next) {
			this.key = key;
			this.next = next;
			this.value = value;
		}
	}

	private static final int BUCKET_COUNT = 128;

	private final LinkedEntry<K, V>[] buckets = new LinkedEntry[BUCKET_COUNT];

	public V get(K key) {
		final int bucketIndex = key.hashCode() % BUCKET_COUNT;
		LinkedEntry<K, V> entry = buckets[bucketIndex];
		while (entry != null) {
			if (entry.key.equals(key)) {
				return entry.value;
			} else {
				entry = entry.next;
			}
		}
		return null;
	}

	public void put(K key, V val) {
		final int bucketIndex = key.hashCode() % BUCKET_COUNT;
		LinkedEntry<K, V> entry = buckets[bucketIndex];
		if (entry == null) {
			entry = new LinkedEntry<>(key, val);
			buckets[bucketIndex] = entry;
			return;
		}

		while (entry != null) {
			if (entry.key.equals(key)) {
				entry.value = val;
				return;
			} else if (entry.next == null) {
				entry.next = new LinkedEntry<>(key, val);
				return;
			} else {
				entry = entry.next;
			}
		}
	}
}