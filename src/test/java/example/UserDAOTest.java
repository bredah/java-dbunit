package example;

import java.io.FileInputStream;
import java.util.List;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

import example.core.DbUnitHelper;
import exemple.core.Utils;

public class UserDAOTest extends DbUnitHelper {

	private List<User> lista;
	private User usuario;

	public UserDAOTest(String name) {
		super(name);
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new FileInputStream(Utils.getPathResourceTest() + "user.xml"));
	}

	public void testListAllUsers() throws Exception {
		lista = UserDAO.listUsers();
		assertEquals(1, lista.size());
		assertEquals("henbreda", lista.get(0).getLogin());
		assertEquals("Henrique Breda", lista.get(0).getName());
		assertEquals("henrique.breda@email.com", lista.get(0).getEmail());
	}

	public void testAddNewUser() throws Exception {
		// Add a new user
		usuario = new User();
		usuario.setLogin("none");
		usuario.setName("None User");
		usuario.setEmail("none.user@email.com");
		UserDAO.addUser(usuario);
		// List all users from database
		lista = UserDAO.listUsers();
		assertEquals(2, lista.size());
		assertEquals(usuario.getLogin(), lista.get(1).getLogin());
		assertEquals(usuario.getName(), lista.get(1).getName());
		assertEquals(usuario.getEmail(), lista.get(1).getEmail());
	}

}
