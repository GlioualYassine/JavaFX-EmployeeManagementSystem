package com.example.employee.Employe;

import com.example.employee.Departement.Departement;

import java.util.Objects;

public class Employe {
    private int idEmp ;
    private String NomEmp ;
    private int age ;
    private double salaire ;
    private Departement RefDept ;

    public Employe(int idEmp, String nomEmp, int age,double salaire, Departement refDept) {
        this.idEmp = idEmp;
        NomEmp = nomEmp;
        this.salaire = salaire;
        RefDept = refDept;
        this.age = age ;
    }

    public Employe(String nomEmp, int age, double salaire, Departement refDept) {
        NomEmp = nomEmp;
        this.age = age;
        this.salaire = salaire;
        RefDept = refDept;
    }

    public Employe(int idEmp, String nomEmp, int age, double salaire) {
        this.idEmp = idEmp;
        NomEmp = nomEmp;
        this.salaire = salaire;
        this.age = age ;
    }

    public Employe() {
    }

    public int getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(int idEmp) {
        this.idEmp = idEmp;
    }

    public String getNomEmp() {
        return NomEmp;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setNomEmp(String nomEmp) {
        NomEmp = nomEmp;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    public Departement getRefDept() {
        return RefDept;
    }

    public void setRefDept(Departement refDept) {
        RefDept = refDept;
    }

    @Override
    public String toString() {
        return "Employe{" +
                "idEmp=" + idEmp +
                ", NomEmp='" + NomEmp + '\'' +
                ", age=" + age +
                ", salaire=" + salaire +
                ", RefDept=" + RefDept +
                '}';
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Employe employe = (Employe) o;
//        return age == employe.age && Double.compare(salaire, employe.salaire) == 0 && Objects.equals(NomEmp, employe.NomEmp) && Objects.equals(RefDept, employe.RefDept);
//    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employe employe = (Employe) o;
        return idEmp == employe.idEmp ;
    }


}
