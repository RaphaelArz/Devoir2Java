package sio.devoir2graphiques.Tools;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class GraphiqueController
{
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public GraphiqueController() {
        cnx = ConnexionBDD.getCnx();
    }
    // A vous de jouer

    public HashMap<Integer, Double>getDataGraph1() throws SQLException {
        ps=cnx.prepareStatement("SELECT AVG(employe.salaireEmp), employe.ageEmp FROM employe " +
                "GROUP by (employe.ageEmp)");

        rs=ps.executeQuery();
        HashMap<Integer,Double>salaire = new HashMap<>();
        while(rs.next()){
            salaire.put(rs.getInt(2),rs.getDouble(1));

        }
        return salaire;

    }

    public HashMap<String, ArrayList<Integer>> getDataGraph2(){
        HashMap<String,ArrayList<Integer>> data2 = new HashMap<>();

        try {
            ps = cnx.prepareStatement("SELECT COUNT(*) AS nombrePersonnes, employe.sexe, employe.ageEmp\n" +
                    "FROM employe\n" +
                    "GROUP BY sexe, ageEmp\n" +
                    "ORDER BY ageEmp, sexe;\n");
            rs = ps.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return data2;
    }





    public HashMap<String,Integer>getDataGraph3() throws SQLException {
        ps= cnx.prepareStatement("SELECT count(*), employe.sexe FROM employe " +
                "GROUP by sexe");
        rs =ps.executeQuery();
        HashMap<String,Integer>dataGraph3 = new HashMap<>();
        while (rs.next()){
            dataGraph3.put(rs.getString(1),rs.getInt(2));

        }
        return dataGraph3;
    }

}
