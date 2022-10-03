/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.services;

import com.example.PersistenceManager;
import com.example.models.Competitor;
import com.example.models.CompetitorDTO;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.persistence.jpa.JpaHelper;
import org.json.simple.JSONObject;

/**
 *
 * @author Mauricio
 */
@Path("/competitors")
@Produces(MediaType.APPLICATION_JSON)
public class CompetitorService {

    @PersistenceContext(unitName = "competitorPU")
    private EntityManager entityManager;

    @PostConstruct
    public void init() {
        try {
            entityManager = PersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
        } catch (Exception e) {
        }
    }

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        Query selectAllCompetitors = entityManager.createQuery("SELECT u FROM Competitor u ORDER BY u.surname ASC");
        List<Competitor> competitors = selectAllCompetitors.getResultList();
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(competitors).build();
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCompetitor(CompetitorDTO competitor) {
        Competitor competitorTmp = new Competitor(competitor.getName(), competitor.getSurname(), competitor.getAge(), competitor.getTelephone(), competitor.getCellphone(), competitor.getAddress(), competitor.getCity(), competitor.getCountry(), false, competitor.getPassword());
        entityManager.getTransaction().begin();
        entityManager.persist(competitorTmp);
        entityManager.getTransaction().commit();
        entityManager.refresh(competitorTmp);
        JSONObject resp = new JSONObject();
        resp.put("ProductId", competitorTmp.getId());
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(resp).build();
    }

    @POST
    @Path("/authenticate")
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticate(String[] credentials) {   //credentials with the following array's structure: [username, password]
        try {
            Query selectCompetitor = entityManager.createQuery("SELECT u.id FROM Competitor u WHERE u.address = :login AND u.password = :password");
            selectCompetitor.setParameter("login", credentials[0]).setParameter("password", credentials[1]);
            Long id = Long.parseLong(selectCompetitor.getSingleResult().toString());
            Competitor target = entityManager.find(Competitor.class, id);
            return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(target).build();
        } catch (NoResultException e) {
            return Response.status(400).build();
        }
    }
}
