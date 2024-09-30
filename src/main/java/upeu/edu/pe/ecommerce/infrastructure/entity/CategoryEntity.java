/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package upeu.edu.pe.ecommerce.infrastructure.entity;
import jakarta.persistence.*;

/**
 *
 * @author Fabricio
 */
@Entity
@Table(name = "Category")
public class CategoryEntity {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer Id; 
    private String Name; 
    private String status; 

    public CategoryEntity() {
    }

    public CategoryEntity(Integer Id, String Name, String status) {
        this.Id = Id;
        this.Name = Name;
        this.status = status;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}