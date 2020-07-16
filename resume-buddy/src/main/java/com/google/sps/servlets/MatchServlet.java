package com.google.sps.servlets;

import com.google.appengine.api.datastore.Entity;
import com.google.sps.Match;
import java.io.IOException;
import java.util.*;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that calls matching algorithm */
@WebServlet("/match")
public class MatchServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.sendRedirect("/");
  }

  /** Called every 12 hours which activates matching algorithm */
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    try {
      List<Entity> reviewees = Match.getNotMatchedUsers("Reviewee");
      List<Entity> reviewers = Match.getNotMatchedUsers("Reviewer");
      Match.match(reviewees, reviewers);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
