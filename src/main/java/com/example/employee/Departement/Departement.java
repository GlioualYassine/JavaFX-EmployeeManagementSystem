package com.example.employee.Departement;

import java.util.Objects;

public class Departement {

    private int idDept;
    private String NomDept;

    public Departement(int idDept, String nomDept) {
        this.idDept = idDept;
        NomDept = nomDept;
    }

    public Departement() {
    }

    public int getIdDept() {
        return idDept;
    }

    public void setIdDept(int idDept) {
        this.idDept = idDept;
    }

    public String getNomDept() {
        return NomDept;
    }

    public void setNomDept(String nomDept) {
        NomDept = nomDept;
    }

    @Override
    public String toString() {
        return "Departement{" +
                "idDept=" + idDept +
                ", NomDept='" + NomDept + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Departement that = (Departement) o;
//        return idDept == that.idDept && Objects.equals(NomDept, that.NomDept);
        Departement that = (Departement) o;
        return  Objects.equals(NomDept, that.NomDept); //usage of name to compare btw Departement objects
    }

}
