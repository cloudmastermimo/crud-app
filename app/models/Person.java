package models;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Person extends Model {

	@Id
	public String id;

	public String name;

	public static List<Person> all() {
		return find.all();
	}

	public static void create(Person person) {
		person.save();
	}

	public static void delete(Long id) {
		find.ref(id).delete();
	}

	public static Finder<Long,Person> find = new Finder(
		Long.class, Person.class
	);
}