package ot

import org.junit.Test

/**
 * Created by elberry on 12/16/16.
 */
class HashTableTests {

	abstract class Key {
		abstract String getName()

		@Override
		boolean equals(Object obj) {
			return ((Key) obj).name == name
		}
	}

	class One extends Key {

		@Override
		int hashCode() {
			return 1
		}

		@Override
		String getName() {
			"One"
		}
	}

	class OtherOne extends Key {

		@Override
		int hashCode() {
			return 1
		}

		@Override
		String getName() {
			"OtherOne"
		}
	}

	@Test
	void "Keys with same hashcode, but different key"() {
		HashTable<Key, String> ht = new HashTable<>()
		One one = new One()
		OtherOne otherOne = new OtherOne()
		ht.put(one, "1")
		ht.put(otherOne, "O1")

		assert ht.get(one) == "1" && ht.get(otherOne) == "O1"
	}

	@Test
	void "Values are overwritten on put of same key"() {
		HashTable<String, String> ht = new HashTable<>()
		ht.put("Foo", "Bar")

		assert ht.get("Foo") == "Bar"

		ht.put("Foo", "Baz")

		assert ht.get("Foo") == "Baz"
	}

	@Test
	void "Keys with same hashcode and equality are considered same key"() {
		HashTable<Key, String> ht = new HashTable<>()
		ht.put(new One(), "One")
		ht.put(new One(), "Two")

		assert ht.get(new One()) == "Two"
	}
}