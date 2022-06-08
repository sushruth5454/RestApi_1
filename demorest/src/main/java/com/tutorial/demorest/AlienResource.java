package com.tutorial.demorest;

import java.util.List;
import java.sql.SQLException;
import java.util.Arrays;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;

import javax.ws.rs.POST;
import javax.ws.rs.PathParam;

@Path("aliens")
public class AlienResource {
	AlienRepository repo = new AlienRepository();

	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<Alien> getAliens() throws SQLException {
		System.out.println("get Alien called..");

//		List<Alien>a=Arrays.asList(a1,a2);
		return repo.getAliens();
	}

	@GET
	@Path("alien/{id}")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Alien getAlien(@PathParam("id") int id) {
		return repo.getAlien(id);
	}

	@POST
	@Path("alien") 
	public Alien createAlien(Alien a1) { System.out.println(a1);
	 repo.create(a1); return a1; 
	 }
	
	@PUT
	@Path("alien/update")
	public Alien updateAlien(Alien a1) {
		if(repo.getAlien(a1.getId()).getId()==0) {
			repo.create(a1);
		}
		else
			repo.updateAlien(a1);
		return a1;
	}
	
	@DELETE
	@Path("alien/delete/{id}")
	public void deleteAlien(@PathParam("id") int id) {
		repo.deleteAlien(id);
		
	}
	
	 
}
