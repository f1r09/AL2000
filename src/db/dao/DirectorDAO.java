package db.dao;

import db.pojo.DirectorPOJO;
import db.pojo.MoviePOJO;

import java.util.ArrayList;
import java.util.List;

public class DirectorDAO extends DAO<DirectorPOJO> {
    private static DirectorDAO instance;

    private DirectorDAO() {
        super(DirectorPOJO.class);
    }

    public static DirectorDAO getInstance() {
        if (instance == null) {
            instance = new DirectorDAO();
        }
        return instance;
    }

    @Override
    public void delete(DirectorPOJO directorPOJO) {
        MoviePOJO moviePOJO = DAOFactory.getMovieDAO().read(directorPOJO.getMovie().getID());
        if (!moviePOJO.getActors().isEmpty()) {
            moviePOJO.getActors().remove(directorPOJO);
            entityManager.merge(moviePOJO);
        }
        executeInsideTransaction(entityManager -> entityManager.remove(directorPOJO));
    }

    public List<DirectorPOJO> readFromMovieId(int movieId) {
        List<DirectorPOJO> directorsPOJO = new ArrayList<>();
        // TODO find all directors from a movie ID order by director_rank
        return directorsPOJO;
    }
}
