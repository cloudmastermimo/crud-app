package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

import models.Person;

import play.cache.Cache;
import play.data.Form;

import java.util.List;

import play.db.ebean.Model;

import static play.libs.Json.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render());
    }

    public static Result addPerson() {
    	Person person = Form.form(Person.class).bindFromRequest().get();

    	Person.create(person);

		Cache.set(person.id, person);

		Cache.remove("users");

    	return redirect(routes.Application.index());
    }

	public static Result deletePerson(Long id) {
		Person.delete(id);

		Cache.remove(String.valueOf(id));
		Cache.remove("users");

		return redirect(routes.Application.index());
	}

    public static Result getPersons() {
		List<Person> persons = (List<Person>)(Cache.get("users"));

		if (persons == null) {
			persons = new Model.Finder(String.class, Person.class).all();

			Cache.set("users", persons);
		}

    	return ok(toJson(persons));
    }
}
